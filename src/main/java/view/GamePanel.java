package view;

import model.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;

public class GamePanel extends JFrame {
    @Serial
    private static final long serialVersionUID = 1321L;
    Game currentGame;

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
    public void load(Game save){
        InGamePanel resume = new InGamePanel();
        resume.InGamePanelLoad(save);
        getRootPane().setContentPane(resume);
        pack();
        setLocation(70, 10); //centers the frame to windows screen
        setResizable(true);
        setVisible(true);
        setSize(1000, 700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
