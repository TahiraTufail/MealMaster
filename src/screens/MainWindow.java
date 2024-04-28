package screens;

import utils.ScreenManager;
import javax.swing.*;

//hold all screens
public class MainWindow extends JFrame {
    //a static constant reference to screen manager. Access through class name.
    public static final ScreenManager screenManager = new ScreenManager(600,625);

    private MainWindow() {
        this.setIconImage(new ImageIcon("assets/Images/Icon.png").getImage());
        this.setTitle("MasterMeal");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(screenManager);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}