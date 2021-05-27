package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//used to travel between different JFrame

public class GamePanel extends JFrame implements ActionListener {
    private JFrame current;

    public void setCurrent(JFrame current) {
        this.current = current;
    }

    public JFrame getCurrent(){
        return current;
    }

    public void start(){
        MainMenu menuFrame =  new MainMenu();
        current = menuFrame.Menu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
