package gameui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GamePanel extends JFrame {
    private static final long serialVersionUID = 1321L;


    public GamePanel() {
        super("360 Trivia Maze");
        start();
    }

    public void start() {
        setContentPane(new MainMenu());

        InGameMenuBar myMenu = new InGameMenuBar();
        setJMenuBar(myMenu.getBar());

        pack();
        setLocationRelativeTo(null); //centers the frame to windows screen
        setVisible(true);
        setSize(800, 740);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setContentPane(tempMenu.getMainMenu());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String closeMessage = "Warning: Any game data not saved will be lost on exit.";
                JOptionPane warningPane = null;
                int reply = warningPane.showConfirmDialog(null, closeMessage,
                        "warning", JOptionPane.YES_NO_OPTION );
                if(reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {

                }
            }
        });
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