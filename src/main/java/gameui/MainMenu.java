package gameui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JPanel{

    private static final long serialVersionUID = 1L;

    private JLabel myBackground;
    private JPanel myMainMenu;
    private ImageIcon myImage;

    public MainMenu() {
        buildMyMenu();
    }


    private void buildMyMenu() {
        myMainMenu = new JPanel(new BorderLayout(10,10));
        //this adds image but having issues with it

        myImage = new ImageIcon(this.getClass().getResource("/MyPost.png"));

        add(new MainButtonPanel());
        //buildButtons();
        myMainMenu.setVisible(true);
    }

    private void buildButtons() {

        JButton loadGame = new JButton("Load Game");
        JButton newGame = new JButton("New Game");
        JButton exitGame = new JButton("Exit Game");

        JLabel label = new JLabel("help");

        exitGame.addActionListener(ExitGame);
        loadGame.addActionListener(LoadGame);
        newGame.addActionListener(NewGame);



        add(loadGame, BorderLayout.NORTH);
        add(newGame, BorderLayout.CENTER);
        add(exitGame, BorderLayout.SOUTH);
    }


    ActionListener ExitGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    };
    ActionListener NewGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {

            getRootPane().setContentPane(new InGamePanel());
            //todo brings up new game trivia options.
        }
    };
    ActionListener LoadGame = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {
            //todo load function for serialization
        }
    };

}
