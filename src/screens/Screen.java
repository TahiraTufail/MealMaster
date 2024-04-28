package screens;
import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {

    public Screen(int width, int height) {
        this.setLayout(null);// pixel by pixel work, X and Y location.
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#e1fcf4"));
    }

    public abstract void init(); //Every screen will implement its own init to initialize its UI

    public void refresh() { //This function will be used to refresh the screens
        this.removeAll();
        this.init();
        this.revalidate();
        this.repaint();
    }
}