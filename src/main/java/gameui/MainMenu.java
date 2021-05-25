package gameui;

import javax.swing.*;
import java.awt.*;


public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel myBackground;
    private JPanel myMainMenu;
   // private ImageIcon myImage;

    public MainMenu() {
        buildMyMenu();
    }


    private void buildMyMenu() {
        myMainMenu = new JPanel(new FlowLayout());

        //this adds image but having issues with it

        //myImage = new ImageIcon(this.getClass().getResource("/MyPost.png"));
        //myBackground = new JLabel(myImage);
        //myBackground.setBounds(0,0,800,740);
        //myMainMenu.add(myBackground);

        MainButtonPanel tempGroup = new MainButtonPanel();
        myMainMenu.add(tempGroup.getButtonGroup());
        myMainMenu.setVisible(true);


    }

    public JPanel getMainMenu() {
        return myMainMenu;
    }


}
