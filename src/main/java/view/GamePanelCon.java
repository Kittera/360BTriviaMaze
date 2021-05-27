package view;

import javax.swing.*;

public interface GamePanelCon {
    void start();
    GamePanel getCurrent();
    void setCurrent(JFrame frame);
}
