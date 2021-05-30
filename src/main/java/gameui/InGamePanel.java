package gameui;

import javax.swing.*;
import java.awt.*;

public class InGamePanel extends JPanel {

    private JPanel myPanel;
    private JPanel myQuestionPanel;
    private JPanel MyRoomPanel;
    private MenuBar myMenuBar;


    public InGamePanel(ButtonGroup catButtonGroup) {
        createPanel();
    }

    public InGamePanel(ButtonModel selection, ButtonModel selection1) {
    }

    private void createPanel() {

        setSize(800, 740);
    }
    //todo this will be the panel that houses the door panel/info(stat panel)/question panel

}
