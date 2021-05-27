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

// Main Menu JFrame
public class MainMenu extends JFrame implements ActionListener {

    private BufferedImage image;
    private JButton start;
    private JFrame frame;

    public JFrame Menu(){
        this.frame = new JFrame("Title Menu");

        frame.setLayout(new BorderLayout());
        try{
            image = ImageIO.read(new File("Images/post.png"));
        } catch (IOException e) {
            e.printStackTrace();
       }
        JLabel pic = new JLabel(new ImageIcon(image));


        start = new JButton("New Game");
        start.addActionListener(this);
        JButton load = new JButton("Load Game");
        JButton setting = new JButton("Settings");

        JPanel imagePanel = new JPanel(new GridLayout(0,1));
        imagePanel.add(pic);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(start);
        buttons.add(load);
        buttons.add(setting);

        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.PAGE_END);

        return frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    QuestionRoom room = new QuestionRoom();
    GamePanel panel = new GamePanel();
    if (e.getSource() == this.start){
        frame.dispose();
        panel.setCurrent(room.room());
        JFrame frame = panel.getCurrent();
        frame.setSize(820,820);
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    }
    }
}
