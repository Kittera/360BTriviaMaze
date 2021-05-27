package view;


import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainMenu extends JFrame implements ActionListener {
   
    private BufferedImage image;

    public JFrame Menu(){
        JFrame frame = new JFrame("Title Menu");
        frame.setLayout(new GridLayout(2, 0));
        try{
            image = ImageIO.read(new File("Images/post.png"));
        } catch (IOException e) {
            e.printStackTrace();
       }
        JLabel pic = new JLabel(new ImageIcon(image));
        JButton start = new JButton("New Game");
        JButton load = new JButton("Load Game");
        JButton setting = new JButton("Settings");

        JPanel imagePanel = new JPanel(new GridLayout(0,1));
        imagePanel.add(pic);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(start);
        buttons.add(load);
        buttons.add(setting);
        frame.add(imagePanel);
        frame.add(buttons);



        return frame;
    }



    public void paintComponent(Graphics g){
       super.paintComponents(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
