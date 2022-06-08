import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final Game game;
    public GameFrame(Game game){
        this.game = game;
        drawMap();
        setLayout(new BorderLayout());
        add(new GamePanel(), BorderLayout.CENTER);
        setSize(new Dimension(1920, 1080));
        setPreferredSize(new Dimension(1920, 1080));

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

//            len = noTile.getWidth(null);
            len = 50;

            setSize(game.getGameInfo().getMap().getCells()[0].length*50, game.getGameInfo().getMap().getCells().length*50);
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Cell[][] tempCells = game.getGameInfo().getMap().getCells();
            System.out.println("height: "+tempCells.length+" width: "+ tempCells[0].length);
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
        }
    }
}
