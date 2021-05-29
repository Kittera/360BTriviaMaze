package gameui;

import javax.swing.*;

public class OptionPanel extends JPanel {

    private JPanel myPanel;

    public OptionPanel() {
        createPanel();
    }

    private void createPanel() {
        setLayout(null);
        setSize(800, 740);
        createLabels();
        createButtons();
    }

    private void createButtons() {
        ButtonGroup difButtonGroup = new ButtonGroup();
        JCheckBox easy = new JCheckBox("Easy", true);
        easy.setBounds(100, 120, 100, 25 );
        JCheckBox medium = new JCheckBox("Medium");
        medium.setBounds(100, 140, 100, 25 );
        JCheckBox hard = new JCheckBox("Hard");
        hard.setBounds(100, 160, 100, 25 );

        difButtonGroup.add(easy);
        difButtonGroup.add(medium);
        difButtonGroup.add(hard);

        add(easy);
        add(medium);
        add(hard);

        ButtonGroup catButtonGroup = new ButtonGroup();

        JCheckBox anyCat = new JCheckBox("All", true);
        anyCat.setBounds(350, 120, 100, 20 );

        JCheckBox genCat = new JCheckBox("General");
        genCat.setBounds(350, 140, 100, 20 );

        JCheckBox filmCat = new JCheckBox("Film");
        filmCat.setBounds(350, 160, 100, 20 );

        JCheckBox musicCat = new JCheckBox("Music");
        musicCat.setBounds(350, 180, 100, 20 );

        JCheckBox vidGameCat = new JCheckBox("Video Games");
        vidGameCat.setBounds(350, 200, 100, 20 );

        JCheckBox compSciCat = new JCheckBox("Computer Science");
        compSciCat.setBounds(350, 220, 200, 20 );

        JCheckBox mathCat = new JCheckBox("Math");
        mathCat.setBounds(350, 240, 100, 20 );

        JCheckBox mythologyCat = new JCheckBox("Mythology");
        mythologyCat.setBounds(350, 260, 100, 20 );

        JCheckBox historyCat = new JCheckBox("Animals");
        historyCat.setBounds(350, 280, 100, 20 );

        JCheckBox animalsCat = new JCheckBox("History");
        animalsCat.setBounds(350, 300, 100, 20 );

        JCheckBox vehiclesCat = new JCheckBox("Vehicles");
        vehiclesCat.setBounds(350, 320, 100, 20 );

        JCheckBox randomCat = new JCheckBox("Random");
        randomCat.setBounds(350, 340, 100, 20 );


        catButtonGroup.add(anyCat);
        catButtonGroup.add(genCat);
        catButtonGroup.add(filmCat);
        catButtonGroup.add(musicCat);
        catButtonGroup.add(vidGameCat);
        catButtonGroup.add(compSciCat);
        catButtonGroup.add(mathCat);
        catButtonGroup.add(mythologyCat);
        catButtonGroup.add(historyCat);
        catButtonGroup.add(animalsCat);
        catButtonGroup.add(vehiclesCat);
        catButtonGroup.add(randomCat);

        add(anyCat);
        add(genCat);
        add(filmCat);
        add(musicCat);
        add(vidGameCat);
        add(compSciCat);
        add(mathCat);
        add(mythologyCat);
        add(historyCat);
        add(animalsCat);
        add(vehiclesCat);
        add(randomCat);


        JButton startGame = new JButton("Start Game");
        startGame.setBounds(600, 620, 100, 25);
        add(startGame);

        JButton mainMenu = new JButton("Back");
        mainMenu.setBounds(100, 620, 100, 25);
        add(mainMenu);

    }

    private void createLabels() {
        JLabel difficulty = new JLabel("Select Difficulty");
        difficulty.setHorizontalAlignment(SwingConstants.LEFT);
        difficulty.setBounds(100,  100, 100, 20);

        JLabel catagory = new JLabel("Select Catagory");
        catagory.setHorizontalAlignment(SwingConstants.LEFT);
        catagory.setBounds(350,  100, 100, 20);

        add(catagory);
        add(difficulty);
    }

}
