import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.logging.Handler;

public class MainFrame extends JFrame {
    public final static String TITLE = "BRIDGE GAME";
    private static JPanel mainPanel;

    public MainFrame(){
        mainPanel = new MainPanel();
        try {
            this.setSize(1000,500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.add(mainPanel);
    }


}

class MainPanel extends JPanel{
    private JButton gameStartButton;
    private JButton gameResultButton;

    public MainPanel(){
        this.add(new JLabel(MainFrame.TITLE));

        ButtonInit();

        this.add(gameStartButton);
        this.add(gameResultButton);
    }

    private void ButtonInit(){
        gameStartButton = new JButton("GAME START");
        gameResultButton = new JButton("GAME RESULTS");

        gameStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map map = enterMapInfo(); //map 선택
                int numPlayer = enterPlayerInfo(); //Player 수 선택

                Game game = new Game(map, numPlayer);
                GameFrame gameFrame = new GameFrame();
                gameFrame.setGame(game);
            }
        });
    }

    private Map enterMapInfo(){
        Map map = null;
        JFileChooser jFileChooser = new JFileChooser();

        String fileExtention = "";
        File mapDataFile = null;

        while(!fileExtention.equals("map")){
            if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                mapDataFile = jFileChooser.getSelectedFile();
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

    private int enterPlayerInfo() {

        class InputNumPlayer extends JDialog {
            int numPlayer = 0;


            private JFormattedTextField numPlayerTextField;
            private JButton okButton;
            private JLabel numPlayerInfo;

            public InputNumPlayer() {
                super(new JFrame(),"Player 수 정하기",true);
                setLayout(new GridLayout(2, 1));
                setSize(300, 200);
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
}

