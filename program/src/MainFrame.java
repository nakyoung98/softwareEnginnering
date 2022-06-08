import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
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
                    Map map = MakeGame.enterMapInfo(); //map 선택

                    int numPlayer = MakeGame.enterPlayerInfo(); //Player 수 선택
                    ArrayList<Player> players = MakeGame.makePlayers(numPlayer);

                    Game game = new Game(map, numPlayer);
                    GameFrame gameFrame = new GameFrame(game); //게임프레임객체,  game 객체를 인자로 넘기며 생성

                    dispose(); //mainFrame 닫기기
                }
            });
        }

    }
}



