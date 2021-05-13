package gameui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JFrame {
    private static final long serialVersionUID = 1L;

    public GamePanel() {
        super("360 Trivia Maze");
    }

    public void start() {

        setSize(800, 740);

        final JPanel panel = new JPanel();
        panel.setBounds(40, 80, 200, 200);
        panel.setBackground(Color.gray);
        panel.setLayout(null);
        add(panel);//add panel to the frame

        // Button1
        final JButton button1 = new JButton("Click Here");
        button1.setBounds(50, 100, 95, 30);
        button1.setBackground(Color.yellow);
        InGameMenuBar myMenu = new InGameMenuBar();
        // add(button1); //adds button1 to the frame directly
        this.setJMenuBar(myMenu.getBar());
        panel.add(button1); //add button to the panel

        setLocationRelativeTo(null); //centers the frame to windows screen
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Main method.
     *
     * @param theArgs arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                final GamePanel frame = new GamePanel();
                frame.start();

            }
        });
    }


    //Build Maze panel here
}