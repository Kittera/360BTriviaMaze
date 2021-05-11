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

    public MainMenuPanel() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JButton btnPlayGame = new JButton("New Game");
        btnPlayGame.setBounds(10, 50, 120, 150);
        contentPane.add(btnPlayGame);

        JButton btnLoadGame = new JButton("Load Game");
        btnLoadGame.setBounds(10, 200, 120, 150);
        contentPane.add(btnLoadGame);



        JLabel lblGameTitle = new JLabel("Trivia Maze");
        lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        lblGameTitle.setBounds(187, 10, 225, 40);
        contentPane.add(lblGameTitle);

        btnPlayGame.addActionListener(PlayGameButton);
        btnLoadGame.addActionListener(LoadGameButton);
    }

    ActionListener PlayGameButton = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            setContentPane(new GamePanel());
        }
    };

    ActionListener LoadGameButton = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            setContentPane(new GamePanel());
        }
    };

}
