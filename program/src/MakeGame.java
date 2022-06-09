import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MakeGame {
    public static Map enterMapInfo(){
        Map map = null;
        JFileChooser jFileChooser = new JFileChooser();

        String fileExtention = "";
        File mapDataFile = null;

        while(!fileExtention.equals("map")){
            if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                mapDataFile = jFileChooser.getSelectedFile();
                fileExtention = mapDataFile.getName().substring(mapDataFile.getName().lastIndexOf(".")+1);
            }else{
                mapDataFile = new File("resource/map/default.map");
                fileExtention = mapDataFile.getName().substring(mapDataFile.getName().lastIndexOf(".")+1);
            }
        }

        try {
            map = new Map(mapDataFile);
        }
        catch (NullPointerException | FileNotFoundException error){
            error.printStackTrace();
        }

        System.out.println(mapDataFile.getName());
        return map;
    }

    public static int enterPlayerInfo() {

        class InputNumPlayer extends JDialog {
            int numPlayer = 0;


            private JFormattedTextField numPlayerTextField;
            private JButton okButton;
            private JLabel numPlayerInfo;

            public InputNumPlayer() {
                super(new JFrame(),"Player 수 정하기",true);
                setLayout(new GridLayout(3, 1));
                setSize(300, 100);
                setLocationRelativeTo(null);

                buttonInit();
                add(numPlayerTextField);
                add(numPlayerInfo);
                add(okButton);
            }

            private void buttonInit() {
                NumberFormat numberFormat = NumberFormat.getNumberInstance();

                numPlayerTextField = new JFormattedTextField(numberFormat);
                numPlayerInfo = new JLabel("2~4 사이의 수만 입력해주세요");
                okButton = new JButton("확인");

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            setNumPlayer();
                            dispose();
                        } catch (NumberFormatException error) {
                            System.out.println("숫자오류");
                        }
                    }
                });
            }
            private void setNumPlayer(){
                numPlayer = Integer.parseInt(numPlayerTextField.getText());
            }

            public int getNumPlayer(){
                return numPlayer;
            }
        }

        InputNumPlayer inputNumPlayer = new InputNumPlayer();
        while(inputNumPlayer.getNumPlayer()<2 || inputNumPlayer.getNumPlayer()>4){
            inputNumPlayer.setVisible(true);
        }

        System.out.println(inputNumPlayer.getNumPlayer());
        return inputNumPlayer.getNumPlayer();
    }

    public static ArrayList<Player> makePlayers(int numPlayer){
        ArrayList<Player> players = new ArrayList<>();

        for(int i= 0; i<numPlayer; i++){
            players.add(new Player());
        }

        return players;
    }

    public static Card findCardType(String cardType){

        if(cardType.equals("B")||cardType.equals("b")){return new BridgeCard(cardType);}
        else if(cardType.equals("H")){return new HammerCard(cardType);}
        else if(cardType.equals("P")){return new PhilipsDriverCard(cardType);}
        else if(cardType.equals("S")){return new SawCard(cardType);}
        else if(cardType.equals("C") || cardType.equals("E")){return null;}
        else {
            System.out.println("해당 타입의 카드가 존재하지않습니다");
            return null;
        }
    }

    public static void setName(ArrayList<Player> players){

        class SetNameDialog extends JDialog {

            private JTextField playerNameTextField;
            private JButton okButton;
            private int playerId;

            public SetNameDialog(String title, int i) {
                super(new JFrame(),title,true);

                this.playerId = i;

                setLayout(new GridLayout(2, 1));
                setSize(300, 100);
                setLocationRelativeTo(null);

                buttonInit();

                add(playerNameTextField);
                add(okButton);

            }

            private void buttonInit() {
                playerNameTextField = new JTextField();
                okButton = new JButton("확인");

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(playerNameTextField.getText().isEmpty()){players.get(playerId).setName("Player #"+(playerId+1));}
                        else{players.get(playerId).setName(playerNameTextField.getText());}

                        System.out.println(players.get(playerId).getName());
                        dispose();
                    }
                });
            }
        }

        for(int i = 0; i<players.size(); i++){
            String title = ""+(i+1)+" Player 이름 설정하기";
            SetNameDialog setNameDialog = new SetNameDialog(title, i);
            setNameDialog.setVisible(true);
        }
    }

    public static Font getFont(){
        Font tempFont = null;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resource/font/ChangwonDangamAsac-Bold_0712.ttf")));
            Font[] fonts = ge.getAllFonts();

            for(int i = 0; i< fonts.length; i++){
                if(fonts[i].getFontName().equals("창원단감아삭 Bold")){
                    tempFont = fonts[i].deriveFont(30F);
//                    tempFont = new Font(fonts[i].getFontName(),Font.PLAIN,10);
                    System.out.println(tempFont.getSize());
                    break;}

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        return tempFont;
    }


}
