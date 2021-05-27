package view;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();

        System.out.println("hello");
        JFrame frame = menu.Menu();
        frame.setSize(450,600);
        frame.setVisible( true );
        frame.setDefaultCloseOperation
                ( JFrame.EXIT_ON_CLOSE );
    }
}
