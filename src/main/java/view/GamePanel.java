package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;

public class GamePanel extends JFrame {
    @Serial
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
        setResizable(true);
        setLocationRelativeTo(null); //centers the frame to windows screen
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);

        Component mainFrame = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String closeMessage = "Warning: Any game data not saved will be lost on exit.";
                int reply = JOptionPane.showConfirmDialog(
                        mainFrame,
                        closeMessage,
                        "Warning",
                        JOptionPane.YES_NO_OPTION );
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
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
        EventQueue.invokeLater(() -> {
            final GamePanel frame = new GamePanel();
            frame.start();
        });
    }
}
