package controller;
import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class saveGame {
    /* save class that will include load in progress

     */
    public void save(String name)throws IOException{
        String filename= name.toLowerCase()+".txt";
        BufferedWriter bFile = new BufferedWriter(new FileWriter(new File(filename)));

    }
}
