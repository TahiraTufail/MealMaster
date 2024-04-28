package screens;

import utils.MessageBox;
import utils.UserManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends Screen {

    public LoginScreen(int width, int height) {
        super(width, height);
        this.init();
    }

    @Override
    public void init() {
        ImageIcon image = new ImageIcon("assets/Images/Logo.png");
        Image img = image.getImage();
        img = img.getScaledInstance(200,94, Image.SCALE_SMOOTH);
        image = new ImageIcon(img);
        JLabel logo = new JLabel();
        logo.setIcon(image);
        logo.setBounds(200,70,200,94);
        this.add(logo);
        JLabel header = new JLabel();
        header.setText("Login");
        header.setBounds(70,200,150,50);
        header.setForeground(Color.decode("#237a5f"));
        header.setFont(new Font("Arial",Font.BOLD,25));
        this.add(header);
        JLabel inputLabel = new JLabel();
        inputLabel.setText("Email Address");
        inputLabel.setBounds(70,260,150,20);
        inputLabel.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel);
        JTextField inputEmail = new JTextField();
        inputEmail.setBounds(70,280,460,50);
        inputEmail.setBackground(Color.decode("#aef5df"));
        inputEmail.setFont(new Font("Arial",Font.PLAIN,18));
        inputEmail.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(inputEmail);
        JLabel inputLabel2 = new JLabel();
        inputLabel2.setText("Password");
        inputLabel2.setBounds(70,355,150,20);
        inputLabel2.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel2);
        JTextField inputPassword= new JTextField();
        inputPassword.setBounds(70,375,460,50);
        inputPassword.setBackground(Color.decode("#aef5df"));
        inputPassword.setFont(new Font("Arial",Font.PLAIN,18));
        inputPassword.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(inputPassword);
        JButton signIn = new JButton("SignIn");
        signIn.setBounds(225, 470, 150, 40);
        signIn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        signIn.setBackground(Color.decode("#56ab91"));
        signIn.setFont(new Font("Arial", Font.BOLD, 20));
        signIn.setForeground(Color.white);
        signIn.setFocusable(false);
//        signIn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        signIn.addActionListener(e -> {
            if(!inputEmail.getText().isBlank() && !inputPassword.getText().isBlank()) {
                if (UserManager.readUser(inputEmail.getText())) {
                    if (UserManager.currentUser.getPassword().equals(inputPassword.getText())) {
                        MainWindow.screenManager.changeScreen("homescreen");
                    } else {
                        new MessageBox("Error", "Invalid Password, Please re-enter...");
                    }
                } else {
                    new MessageBox("Error", "Invalid Email, Please re-enter...");
                }
            } else {
                new MessageBox("Error", "All Fields are required...");
            }
        });
        this.add(signIn);
        JLabel registerText = new JLabel();
        registerText.setText("Don't have an account?");
        registerText.setBounds(220,515,150,10);
        registerText.setFont(new Font("Arial",Font.PLAIN,10));
        this.add(registerText);
        JLabel registerText2 = new JLabel();
        registerText2.setText("REGISTER");
        registerText2.setBounds(325,515,150,10);
        registerText2.setFont(new Font("Arial",Font.BOLD,10));
        registerText2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                registerText2.setForeground(new Color(200, 200, 200));
                MainWindow.screenManager.changeScreen("signup");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                registerText2.setForeground(new Color(0, 0, 0));
            }
        });
        this.add(registerText2);
    }
}