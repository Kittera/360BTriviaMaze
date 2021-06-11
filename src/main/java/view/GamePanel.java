package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;


/**
 * This is the main game frame that houses all of the GUI panels for the trivia game.
 *
 */
public class GamePanel extends JFrame {
    @Serial
    private static final long serialVersionUID = 1321L;

    /**
     * Basic Constructor that starts the maze frame.
     */
    public GamePanel() {
        super("360 Trivia Maze");
        start();
    }


    private void start() {
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
            ImageIcon img = new ImageIcon("src/main/resources/PanelIcon.png");
            final GamePanel frame = new GamePanel();
            frame.setIconImage(img.getImage());
            //frame.start();
        });
    }
}
