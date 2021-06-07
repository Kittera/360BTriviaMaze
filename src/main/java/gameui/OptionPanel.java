package gameui;

import model.Category;
import model.Difficulty;

import javax.swing.*;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel {

    private JPanel myPanel;
    private ButtonGroup catButtonGroup = new ButtonGroup();
    private ButtonGroup difButtonGroup = new ButtonGroup();

    public OptionPanel() {
        createPanel();
    }

    private void createPanel() {
        setLayout(null);
        setSize(1000, 740);
        createLabels();
        createButtons();
        setVisible(true);
    }

    private void createButtons() {
        difButtonGroup = new ButtonGroup();
        JCheckBox easy = new JCheckBox("Easy", true);
        easy.setActionCommand("Easy");
        easy.setBounds(100, 120, 100, 25 );
        JCheckBox medium = new JCheckBox("Medium");
        medium.setActionCommand("Medium");
        medium.setBounds(100, 140, 100, 25 );
        JCheckBox hard = new JCheckBox("Hard");
        hard.setActionCommand("Hard");
        hard.setBounds(100, 160, 100, 25 );

        difButtonGroup.add(easy);
        difButtonGroup.add(medium);
        difButtonGroup.add(hard);

        add(easy);
        add(medium);
        add(hard);

        catButtonGroup = new ButtonGroup();

        int categoryListX = 350;
        int startingCategoryListY = 120;
        int catButtonWidth = 200;
        int catButtonHeight = 22;

        int count = 0;
        for (Category c : Category.values()) {
            JCheckBox catButton = new JCheckBox(c.title);
            catButton.setActionCommand(c.title);
            catButton.setBounds(
                    categoryListX,
                    startingCategoryListY + (catButtonHeight * count++),
                    catButtonWidth,
                    catButtonHeight

            );
            catButtonGroup.add(catButton);
            this.add(catButton);
        }



        JButton startGame = new JButton("Start Game");
        startGame.setBounds(600, 620, 100, 25);
        startGame.addActionListener(StartGame);
        add(startGame);

        JButton mainMenu = new JButton("Back");
        mainMenu.setBounds(100, 620, 100, 25);
        mainMenu.addActionListener(Back);
        add(mainMenu);

    }

    private void createLabels() {
        JLabel difficulty = new JLabel("Select Difficulty");
        difficulty.setHorizontalAlignment(SwingConstants.LEFT);
        difficulty.setBounds(100,  100, 100, 20);

        JLabel category = new JLabel("Select Category");
        category.setHorizontalAlignment(SwingConstants.LEFT);
        category.setBounds(350,  100, 100, 20);

        add(category);
        add(difficulty);
    }

    ActionListener StartGame = event -> {
        String cat = catButtonGroup.getSelection().getActionCommand();
        String dif = difButtonGroup.getSelection().getActionCommand();
        Category tempcat = Category.fromName(cat);
        Difficulty tempdif = Difficulty.fromName(dif);
        InGamePanel temp = new InGamePanel(
                tempcat,
                tempdif);
        getRootPane().setContentPane(temp);
    };

    ActionListener Back = event -> {
        getRootPane().setContentPane(new MainMenu());
    };
}