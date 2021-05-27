package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class QuestionRoom extends JFrame implements ActionListener {
    private JFrame frame;
    private JButton menu;
    public JFrame room(){
        this.frame = new JFrame("360 Trivia Maze");

        frame.setLayout(new BorderLayout());


        this.menu = new JButton("Menu");
        menu.addActionListener(this);
        JButton one = new JButton("Option 1");
        JButton two = new JButton("Option 2");
        JButton three = new JButton("Option 3");
        JButton four = new JButton("Option 4");


        JPanel menuButton = new JPanel(new FlowLayout());
        menuButton.add(menu);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(four);

        frame.add(menuButton, BorderLayout.PAGE_START);
        frame.add(buttons, BorderLayout.PAGE_END);

        return frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenu menuFrame = new MainMenu();
        GamePanel panel = new GamePanel();
        if(e.getSource() == this.menu){
            frame.dispose();
            panel.setCurrent(menuFrame.Menu());
            JFrame frame = panel.getCurrent();
            frame.setSize(820,820);
            frame.setVisible( true );
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        }

    }
}
