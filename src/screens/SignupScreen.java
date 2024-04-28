package screens;

import components.User;
import utils.MessageBox;
import utils.UserManager;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class SignupScreen extends Screen {

    public SignupScreen(int width, int height) {
        super(width, height);
        this.init();
    }

    @Override
    public void init() {
        ImageIcon image = new ImageIcon("assets/Images/Logo.png");
        Image img = image.getImage();
        img=img.getScaledInstance(100,55,Image.SCALE_SMOOTH);
        image=new ImageIcon(img);
        JLabel logo = new JLabel();
        logo.setIcon(image);
        logo.setBounds(20,20,100,55);
        this.add(logo);
        JLabel header = new JLabel();
        header.setText("Registration");
        header.setBounds(195,25,300,50);
        header.setForeground(Color.decode("#237a5f"));
        header.setFont(new Font("Arial",Font.BOLD,40));
        this.add(header);
        JLabel inputLabel = new JLabel();
        inputLabel.setText("Username");
        inputLabel.setBounds(20,120,75,20);
        inputLabel.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel);
        JTextField inputName= new JTextField();
        inputName.setBounds(20,142,270,40);
        inputName.setBackground(Color.decode("#aef5df"));
        inputName.setFont(new Font("Arial",Font.PLAIN,18));
        inputName.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(inputName);
        JLabel inputLabel1 = new JLabel();
        inputLabel1.setText("Email Address");
        inputLabel1.setBounds(310,120,100,20);
        inputLabel1.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel1);
        JTextField inputEmail= new JTextField();
        inputEmail.setBounds(310,142,270,40);
        inputEmail.setBackground(Color.decode("#aef5df"));
        inputEmail.setFont(new Font("Arial",Font.PLAIN,18));
        inputEmail.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(inputEmail);
        JLabel inputLabel2 = new JLabel();
        inputLabel2.setText("Password");
        inputLabel2.setBounds(20,200,150,20);
        inputLabel2.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel2);
        JTextField inputPassword= new JTextField();
        inputPassword.setBounds(20,222,270,40);
        inputPassword.setBackground(Color.decode("#aef5df"));
        inputPassword.setFont(new Font("Arial",Font.PLAIN,18));
        inputPassword.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(inputPassword);
        JLabel inputLabel3 = new JLabel();
        inputLabel3.setText("Confirm Password");
        inputLabel3.setBounds(310,200,150,20);
        inputLabel3.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel3);
        JTextField inputPassword2= new JTextField();
        inputPassword2.setBounds(310,222,270,40);
        inputPassword2.setBackground(Color.decode("#aef5df"));
        inputPassword2.setFont(new Font("Arial",Font.PLAIN,18));
        inputPassword2.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(inputPassword2);
        JLabel inputLabel4 = new JLabel();
        inputLabel4.setText("Age");
        inputLabel4.setBounds(20,280,150,20);
        inputLabel4.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel4);
        JTextField age= new JTextField();
        age.setBounds(20,302,173,40);
        age.setBackground(Color.decode("#aef5df"));
        age.setFont(new Font("Arial",Font.PLAIN,18));
        age.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(age);
        JLabel inputLabel6 = new JLabel();
        inputLabel6.setText("Height");
        inputLabel6.setBounds(213,280,150,20);
        inputLabel6.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel6);
        JTextField height= new JTextField();
        height.setBounds(213,302,174,40);
        height.setBackground(Color.decode("#aef5df"));
        height.setFont(new Font("Arial",Font.PLAIN,18));
        height.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(height);
        JLabel inputLabel7 = new JLabel();
        inputLabel7.setText("Weight");
        inputLabel7.setBounds(407,280,150,20);
        inputLabel7.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel7);
        JTextField weight= new JTextField();
        weight.setBounds(407,302,173,40);
        weight.setBackground(Color.decode("#aef5df"));
        weight.setFont(new Font("Arial",Font.PLAIN,18));
        weight.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        this.add(weight);
        JLabel inputLabel5 = new JLabel();
        inputLabel5.setText("Gender");
        inputLabel5.setBounds(20,360,150,20);
        inputLabel5.setFont(new Font("Arial",Font.PLAIN,15));
        this.add(inputLabel5);
        String genders[]={"Male","Female"};
        JComboBox<String> gender = new JComboBox<>(genders);
        gender.setBounds(20,382,173,40);
        gender.setBackground(Color.decode("#aef5df"));
        gender.setFont(new Font("Arial",Font.PLAIN,15));
        gender.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gender.setFocusable(false);
        gender.setUI(new BasicComboBoxUI());
        this.add(gender);
        JButton signUp = new JButton("SignUp");
        signUp.setBounds(225, 470, 150, 40);
        signUp.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        signUp.setBackground(Color.decode("#56ab91"));
        signUp.setFont(new Font("Arial", Font.BOLD, 20));
        signUp.setForeground(Color.white);
        signUp.setFocusable(false);
        signUp.addActionListener(e -> {
            if(!inputName.getText().isBlank() && !inputEmail.getText().isBlank() && !inputPassword.getText().isBlank()
                    && !inputPassword2.getText().isBlank() && !age.getText().isBlank() && !weight.getText().isBlank()
                    && !height.getText().isBlank()) {
                if(inputEmail.getText().contains("@gmail.com")) {
                    if(inputPassword.getText().equals(inputPassword2.getText())) {
                        //creating a user with all values and saving it in current user
                        UserManager.currentUser = new User(inputName.getText(), inputEmail.getText(),
                                inputPassword.getText(), gender.getSelectedItem().toString(),
                                Integer.parseInt(age.getText()), Double.parseDouble(weight.getText()),
                                Double.parseDouble(height.getText()), new Date());
                        //saving the user in database
                        UserManager.saveUser();
                        MainWindow.screenManager.changeScreen("homescreen");
                    } else {
                        new MessageBox("Error", "Passwords do not match");
                    }
                } else {
                    new MessageBox("Error", "Invalid Email, Please re-enter...");
                }
            } else {
                new MessageBox("Error", "All Fields are required...");
            }
        });
        this.add(signUp);
        JLabel registerText = new JLabel();
        registerText.setText("Already have an account?");
        registerText.setBounds(220,515,150,10);
        registerText.setFont(new Font("Arial",Font.PLAIN,10));
        this.add(registerText);
        JLabel signInText2 = new JLabel();
        signInText2.setText("SIGN IN");
        signInText2.setBounds(335,515,150,10);
        signInText2.setFont(new Font("Arial",Font.BOLD,10));
        signInText2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                signInText2.setForeground(new Color(200, 200, 200));
                MainWindow.screenManager.changeScreen("login");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                signInText2.setForeground(new Color(0, 0, 0));
            }
        });
        this.add(signInText2);
    }
}
