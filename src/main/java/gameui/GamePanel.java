package gameui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GamePanel extends JFrame {
    private static final long serialVersionUID = 1L;

    public GamePanel() {
        super("360 Trivia Maze");
    }

    public void start() {

        setSize(800, 740);
        //Have basic Window, now we need a
        final JPanel panel = new JPanel(new FlowLayout());
        panel.setLocation(0,0);
        add(panel);//add panel to the frame


        InGameMenuBar myMenu = new InGameMenuBar();



        this.setJMenuBar(myMenu.getBar());
        
        setContentPane((new MainMenu().getMainMenu()));
        repaint();
        setLocationRelativeTo(null); //centers the frame to windows screen
        setLayout(null);
        setVisible(true);
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