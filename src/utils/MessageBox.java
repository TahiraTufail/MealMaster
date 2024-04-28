package utils;
import javax.swing.*;
import java.awt.*;

public class MessageBox extends JFrame {

    public MessageBox(String title, String message){
        this.setTitle(title);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("assets/Images/Icon.png").getImage());
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(400, 100));
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(30, 35, 340, 30);
        panel.setBackground(Color.decode("#e1fcf4"));
        panel.add(label);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
