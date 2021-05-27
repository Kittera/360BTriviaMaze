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
    private Image myPic;

    public MainMenu() {
        buildMyMenu();
    }


    private void buildMyMenu() {
        setLayout(new BorderLayout(10,10));
        //this adds image but having issues with it

        myImage = new ImageIcon(this.getClass().getResource("/MyPost.png"));
        myPic = myImage.getImage();
        add(new MainButtonPanel(), BorderLayout.SOUTH);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myPic, 0, 0, null);
    }


}
