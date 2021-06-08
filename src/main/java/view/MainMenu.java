package view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


public class MainMenu extends JPanel {

    @Serial
    private static final long serialVersionUID = 123215456L;


    private Image myPic;

    public MainMenu() {
        buildMyMenu();
    }


    private void buildMyMenu() {
        setLayout(new BorderLayout(10,10));

        myPic = new ImageIcon("src/main/resources/MyPost.png").getImage();

        setPreferredSize(
                new Dimension(
                        myPic.getWidth(null),
                        myPic.getHeight(null)
                )
        );
        add(new MainButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myPic, 0, 0, null);
    }
}
