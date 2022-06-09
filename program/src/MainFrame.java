import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Handler;

public class MainFrame extends JFrame {
    public final static String TITLE = "BRIDGE GAME";
    private static JPanel mainPanel;
    private Font mainFont;

    public MainFrame(){
        mainFont = MakeGame.getFont();

        mainPanel = new MainPanel();
        try {
            this.setSize(500,300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    class MainPanel extends JPanel{
        private JLabel titleLabel;
        private JButton gameStartButton;
        private JButton gameResultButton;

        public MainPanel(){
            setLayout(new GridLayout(3,1));
            setOpaque(true);
            setBackground(Color.BLACK);

            titleLabel = new JLabel(MainFrame.TITLE);
            titleLabel.setFont(mainFont.deriveFont(40F));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            add(titleLabel);

            ButtonInit();

            add(gameStartButton);
            add(gameResultButton);
        }

        private void ButtonInit(){
            gameStartButton = new JButton("GAME START");
            gameStartButton.setFont(mainFont.deriveFont(30F));

            gameResultButton = new JButton("GAME RESULTS");
            gameResultButton.setFont(mainFont.deriveFont(30F));

            gameStartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Map map = MakeGame.enterMapInfo(); //map 선택

                    int numPlayer = MakeGame.enterPlayerInfo(); //Player 수 선택

                    Game game = new Game(map, numPlayer);
                    GameFrame gameFrame = new GameFrame(game); //게임프레임객체,  game 객체를 인자로 넘기며 생성

                    dispose(); //mainFrame 닫기기
                }
            });
        }

    }
}



