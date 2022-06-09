import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    private final Game game;

    public GameFrame(Game game){
        this.game = game;
        this.game.ready();

        init();
        new PlayDialog();

        this.game.play();
    }

    private void init(){
//        drawMap();
        setLayout(new GridLayout());

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        setSize(new Dimension(gamePanel.getWidth(), gamePanel.getHeight()));
        setUndecorated(true); //장식줄 없애기
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void drawMap(){
        for(int i = 0; i< game.getGameInfo().getMap().getCells().length; i++){ //draw row
            for(int j=0; j<game.getGameInfo().getMap().getCells()[0].length; j++){
                if(game.getGameInfo().getMap().getCells()[i][j] != null){
                    if(game.getGameInfo().getMap().getCells()[i][j].getCard() != null){System.out.print(game.getGameInfo().getMap().getCells()[i][j].getCard().getCardInfo().getCardType());}
                    else{System.out.print("■");}
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    class GamePanel extends JPanel{
        private Image noTile;
        private Image start;
        private Image end;
        private Image cell;
        private Image philipsDriver;
        private Image hammer;
        private Image saw;
        private Image bridge;

        private ArrayList<Image> playerInGameImages;

        private int len;

        public GamePanel(){
            noTile = new ImageIcon("resource/image/noTile.png").getImage();
            start = new ImageIcon("resource/image/start.png").getImage();
            end = new ImageIcon("resource/image/end.png").getImage();
            cell = new ImageIcon("resource/image/cell.png").getImage();
            philipsDriver = new ImageIcon("resource/image/philipsDriver.png").getImage();
            hammer = new ImageIcon("resource/image/hammer.png").getImage();
            saw = new ImageIcon("resource/image/saw.png").getImage();
            bridge = new ImageIcon("resource/image/bridge.png").getImage();

            playerInGameImages = new ArrayList<>();
            for(int i = 0; i<game.getGameInfo().getNumPlayer();i++){playerInGameImages.add(new ImageIcon("resource/player/player"+i+".png").getImage());}

//            len = noTile.getWidth(null);
            len = 50;

            setSize(game.getGameInfo().getMap().getCells()[0].length*50, game.getGameInfo().getMap().getCells().length*50);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Cell[][] tempCells = game.getGameInfo().getMap().getCells();
            System.out.println("height: "+tempCells.length+" width: "+ tempCells[0].length);

            //map그리기
            for(int i = 0; i< tempCells.length; i++){ //draw row
                for(int j=0; j<tempCells[0].length; j++){
                    int x = j*len;
                    int y = i*len;
                    if(tempCells[i][j] != null){
                            if(tempCells[i][j].getCard() != null){
                                String tempCardType = tempCells[i][j].getCard().getCardInfo().getCardType();
                                if(tempCardType.equals("S")) {g.drawImage(saw,x,y,this);}
                                else if(tempCardType.equals("P")){g.drawImage(philipsDriver,x,y,this);}
                                else if(tempCardType.equals("H")){g.drawImage(hammer,x,y,this);}
                                else if(tempCardType.equals("B")){g.drawImage(cell,x,y,this);}
                                else if(tempCardType.equals("b")){g.drawImage(bridge,x-50, y, this); g.drawImage(cell,x,y,this);}

//                                System.out.print(tempCardType);
                            }
                        else{
                            if(i == game.getGameInfo().getMap().getStartCoordinate().getY() && j == game.getGameInfo().getMap().getStartCoordinate().getX()){//start
                                g.drawImage(start,x,y,this);
                            }else if(i == game.getGameInfo().getMap().getEndCoordinate().getY() && j == game.getGameInfo().getMap().getEndCoordinate().getX()){
                                g.drawImage(end,x,y,this);
                            }else{
                                g.drawImage(cell,x,y,this);
                            }
//                            System.out.print("■");
                        }
                    }else{
                        g.drawImage(noTile,x,y,this);
//                        System.out.print(" ");
                    }
                }
//                System.out.println();
            }

            //player그리기
            for(int i = 0; i<game.getGameInfo().getNumPlayer(); i++){
                Coordinate tempCoord = game.getGameInfo().getPlayers().get(i).getLocation();
                int x = tempCoord.getX()*len;
                int y = tempCoord.getY()*len;
                if(i == 0) {g.drawImage(playerInGameImages.get(i),x, y,this);}
                if(i == 1) {g.drawImage(playerInGameImages.get(i),x+(int)len/2, y,this);}
                if(i == 2) {g.drawImage(playerInGameImages.get(i),x, (int)y+len/2,this);}
                if(i == 3) {g.drawImage(playerInGameImages.get(i),x+len/2, y+len/2,this);}
            }

        }

        @Override
        public void revalidate() {
            super.revalidate();
            System.out.println("revalidate");
        }
    }

    class PlayDialog extends JDialog{
        private Font mainFont;

        public PlayDialog(){
            super(new JFrame(),"Player 정보",false);

            mainFont = MakeGame.getFont();


            if(game.getGameInfo().getPlayers().size()==2){
                setLayout(new GridLayout(1,2));
                setSize(500,400);
            }else{
                setLayout(new GridLayout(2,2));
                setSize(500,800);
            }
            try {
                for (int i = 0; i < game.getGameInfo().getPlayers().size(); i++) {
                    add(new PlayerInfoPanel(game.getGameInfo().getPlayers().get(i), i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            setUndecorated(true); //장식줄 없애기

            Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(10, dimen.height/2-this.getHeight()/2);
            setVisible(true);
        }

        class PlayerInfoPanel extends JPanel{
            private final int BORDER_THICKNESS = 5;
            public PlayerInfoPanel(Player player, int i) throws IOException, FontFormatException {
                setPreferredSize(new Dimension(250, 400));

                setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
                add(new TurnLabel(player));
                add(new PlayerImageLabel(i));
                add(new PlayerNameLabel(player.getName()));
//                add(new CardPanel(player.getCard));
                setBorder(new LineBorder(Color.BLACK, BORDER_THICKNESS));
            }

            class TurnLabel extends JLabel{
                public TurnLabel(Player player) throws IOException, FontFormatException {
                    setPreferredSize(new Dimension(250-BORDER_THICKNESS*2,20));
                    setOpaque(true);
                    setHorizontalAlignment(JLabel.CENTER);
                    setForeground(Color.WHITE);
                    setFont(mainFont.deriveFont(20F));

                    if(player == game.currentPlayer()){  //해당 유저 차례
                        setText("MY TURN");
                        setBackground(new Color(255,0,0));
                    }else{
                        setText("WAIT");
                        setBackground(new Color(0,0,0));
                    }
                }
            }


            class PlayerImageLabel extends JLabel{
                public PlayerImageLabel(int i){
                    setIcon(new ImageIcon("resource/image/player" + i + ".png"));
                    setOpaque(true);
                    setBackground(new Color(255,255,255));
                    setPreferredSize(new Dimension(250-BORDER_THICKNESS*2,130));
                    setHorizontalAlignment(JLabel.CENTER);
                }
            }

            class PlayerNameLabel extends JLabel{
                public PlayerNameLabel(String name) throws IOException, FontFormatException {
                    setPreferredSize(new Dimension(250-BORDER_THICKNESS*2,50));
                    setOpaque(true);
                    setBackground(new Color(0,0,0));
                    setHorizontalAlignment(JLabel.CENTER);
                    setText(name);
                    setFont(mainFont.deriveFont(30F));
                    setForeground(Color.WHITE);
                }
            }

            class CardPanel extends JPanel{
                public CardPanel(){

                }
            }
        }
    }
}
