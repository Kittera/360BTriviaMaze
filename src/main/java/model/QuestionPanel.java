package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextPane;


public class QuestionPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public MainPanel() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JButton playGame = new JButton("New Game");
        playGame.setBounds(10, 50, 120, 150);
        contentPane.add(playGame);

        JButton loadGame = new JButton("Load Game");
        loadGame.setBounds(10, 200, 120, 150);
        contentPane.add(loadGame);



        JLabel gameTitle = new JLabel("Trivia Maze");
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        gameTitle.setBounds(187, 10, 225, 40);
        contentPane.add(gameTitle);

        btnPlayGame.addActionListener(playGameButton);
        btnLoadGame.addActionListener(loadGameButton);
    }

    ActionListener playGameButton = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            setContentPane(new GamePanel());
        }
    };

    ActionListener loadGameButton = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            setContentPane(new GamePanel());
        }
    };

}
