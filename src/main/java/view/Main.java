package view;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GamePanel start =  new GamePanel();
        start.start();
        System.out.println("hello");
        JFrame frame = start.getCurrent();
        frame.setSize(820,820);
        frame.setVisible( true );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}
