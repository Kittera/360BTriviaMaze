package gameui;

import model.Category;
import model.Difficulty;
import model.Game;

import javax.swing.*;
import java.io.*;

public class test {
    public static void main(String[] args) {
        InGamePanel game = new InGamePanel(Category.MYTHOLOGY, Difficulty.EASY);

        String filename = "file.ser";
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(game.InGamePanelSave());
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            Game state = (Game) in.readObject();
            game.InGamePanelLoad(state);
            GamePanel frame = new GamePanel();
            frame.load(state);
            in.close();
            file.close();

        }
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                    " is caught");
        }
    }
}
