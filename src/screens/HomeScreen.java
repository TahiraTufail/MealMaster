package screens;

import components.FoodItem;
import components.User;
import utils.UserManager;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeScreen extends  Screen{

    //used to store the selected foods of the user .
    public static ArrayList<FoodItem> selectedFoods = new ArrayList<>();
    //used to store the progress of the currently selected date.
    private static User.Progress currentProgress;
    //used to store currently selected date.
    private static Date selectedDate;
    //used to store the index of currently selected date.
    private static int selectedIndex;

    public HomeScreen(int width, int height) {
        super(width, height);
        Date loginDate = new Date();
        //getting the progress of the login Date.
        currentProgress = UserManager.currentUser.getProgress(loginDate);
        //checking if there is any progress or not,Progress  will be null if it's a new day.
        if(currentProgress==null){
            UserManager.currentUser.setProgress(loginDate,new double[]{0,0,0,0});
            currentProgress = UserManager.currentUser.getProgress(loginDate);
        }
        selectedDate = loginDate;
        selectedIndex = UserManager.currentUser.getProgress().size()-1;
        this.init();
    }

    @Override
    public void init() {

        int percentCal = (int) ((currentProgress.nutritions[0] / UserManager.currentUser.getRecommendedDietPlan()[0]) * 100);
        int percentCarb = (int) ((currentProgress.nutritions[1] / UserManager.currentUser.getRecommendedDietPlan()[1]) * 100);
        int percentProtein = (int) ((currentProgress.nutritions[2] / UserManager.currentUser.getRecommendedDietPlan()[2]) * 100);
        int percentFats = (int) ((currentProgress.nutritions[3] / UserManager.currentUser.getRecommendedDietPlan()[3]) * 100);
        ImageIcon image = new ImageIcon("assets/Images/Logo-White.png");
        Image img = image.getImage();
        img=img.getScaledInstance(150,70,Image.SCALE_SMOOTH);
        image=new ImageIcon(img);
        JLabel cover =new JLabel();
        cover.setBounds(225,15,150,70);
        cover.setIcon(image);
        //Main header
        JLabel header2 =new JLabel();
        header2.setBounds(0,0,600,100);
        header2.setBackground(Color.decode("#56ab91"));
        header2.setOpaque(true);
        this.add(header2);
        header2.add(cover);
        JLabel userNameTag=new JLabel();
        userNameTag.setBounds(10,30,150,40);
        userNameTag.setText("Hello " + UserManager.currentUser.getUserName() + "!");
        userNameTag.setFont(new Font("Arial",Font.BOLD,16));
        userNameTag.setForeground(new Color(255,255,255));
        header2.add(userNameTag);
        JLabel logout= new JLabel();
        logout.setText("Log Out");
        logout.setFont(new Font("Arial",Font.BOLD,16));
        logout.setForeground(new Color(255,255,255));
        logout.setBounds(520,30,150,40);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserManager.logOut();
                MainWindow.screenManager.changeScreen("login");
            }
        });
        header2.add(logout);
        //Label number 1
        JLabel userInfo = new JLabel();
        userInfo.setBounds(0,100,600,100);
        userInfo.setBackground(new Color(255, 255, 255));
        userInfo.setOpaque(true);
        this.add(userInfo);
        JLabel labelNum1= new JLabel();
        labelNum1.setText("UserName: " + UserManager.currentUser.getUserName());
        labelNum1.setBounds(70,10,170,20);
        labelNum1.setFont(new Font("Arial",Font.BOLD,13));
        userInfo.add(labelNum1);
        JLabel labelNum2 = new JLabel();
        labelNum2.setText("Height: " + UserManager.currentUser.getHeight() + " ft");
        labelNum2.setBounds(360,10,150,20);
        labelNum2.setFont(new Font("Arial",Font.BOLD,13));
        userInfo.add(labelNum2);
        JLabel labelNum3= new JLabel();
        labelNum3.setText("Age: " + UserManager.currentUser.getAge());
        labelNum3.setBounds(70,40,150,20);
        labelNum3.setFont(new Font("Arial",Font.BOLD,13));
        userInfo.add(labelNum3);
        JLabel labelNum4 = new JLabel();
        labelNum4.setText("Weight: " + UserManager.currentUser.getWeight() + " kgs");
        labelNum4.setBounds(360,40,150,20);
        labelNum4.setFont(new Font("Arial",Font.BOLD,13));
        userInfo.add(labelNum4);
        JLabel labelNum5= new JLabel();
        labelNum5.setText("Gender: " + UserManager.currentUser.getGender());
        labelNum5.setBounds(70,70,150,20);
        labelNum5.setFont(new Font("Arial",Font.BOLD,13));
        userInfo.add(labelNum5);
        JLabel labelNum6= new JLabel();
        labelNum6.setText("BMI: " + UserManager.currentUser.getBmi() + " (Normal Range)");
        labelNum6.setBounds(360,70,200,20);
        labelNum6.setFont(new Font("Arial",Font.BOLD,13));
        userInfo.add(labelNum6);
        //label number2
        JLabel nutritionalInfoLabel = new JLabel();
        nutritionalInfoLabel.setText("    Nutritional Information");
        nutritionalInfoLabel.setBounds(0,200,600,50);
        nutritionalInfoLabel.setBackground(Color.decode("#56ab91"));
        nutritionalInfoLabel.setFont(new Font("Arial",Font.BOLD,15));
        nutritionalInfoLabel.setForeground(new Color(255,255,255));
        nutritionalInfoLabel.setOpaque(true);
        this.add(nutritionalInfoLabel);
        JLabel nutritionalInfo = new JLabel();
        nutritionalInfo.setBounds(0,240,600,310);
        nutritionalInfo.setBackground(new Color(255, 255, 255));
        nutritionalInfo.setOpaque(true);
        this.add(nutritionalInfo);
        ArrayList<User.Progress> progress = UserManager.currentUser.getProgress();
        String[] daysList = new String[progress.size()];
        for(int i=0; i< progress.size(); i++){
            Date d = progress.get(i).date;//getting the date of the ith progress.
            String date = new SimpleDateFormat( "dd/MM/yyyy").format(d);//Coverts dates into String in the given format
            daysList[i]=date;
        }
        JComboBox<String> days = new JComboBox<>(daysList);
        days.setBounds(370,35,173,40);
        days.setBackground(Color.decode("#aef5df"));
        days.setFont(new Font("Arial",Font.PLAIN,15));
        days.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        days.setFocusable(false);
        days.setSelectedIndex(selectedIndex);
        days.setUI(new BasicComboBoxUI());
        //getting selected date
        days.addActionListener(e -> {
            try {
                selectedDate = new SimpleDateFormat("dd/MM/yyyy").parse(days.getSelectedItem().toString());
                //getting the progress if the selected date
                currentProgress = UserManager.currentUser.getProgress(selectedDate);
                selectedIndex = days.getSelectedIndex();
                this.refresh();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        nutritionalInfo.add(days);
        for(FoodItem food: selectedFoods) {
                currentProgress.nutritions[0] += food.getCalories();
                currentProgress.nutritions[1] += food.getCarbs();
                currentProgress.nutritions[2] += food.getProtein();
                currentProgress.nutritions[3] += food.getFats();
                 percentCal = (int) ((currentProgress.nutritions[0] / UserManager.currentUser.getRecommendedDietPlan()[0]) * 100);
                 percentCarb = (int) ((currentProgress.nutritions[1] / UserManager.currentUser.getRecommendedDietPlan()[1]) * 100);
                 percentProtein = (int) ((currentProgress.nutritions[2] / UserManager.currentUser.getRecommendedDietPlan()[2]) * 100);
                 percentFats = (int) ((currentProgress.nutritions[3] / UserManager.currentUser.getRecommendedDietPlan()[3]) * 100);
        }
        selectedFoods.clear();
        UserManager.currentUser.setProgress(selectedDate, currentProgress.nutritions);
        UserManager.updateUser();
        JLabel calories = new JLabel();
        calories.setText("Calories: "+ currentProgress.nutritions[0] +" / "+ UserManager.currentUser.getRecommendedDietPlan()[0] );
        calories.setFont(new Font("Arial", Font.BOLD, 16));
        calories.setBounds(50,20,250,30);
        calories.setForeground(new Color(0, 0, 0));
        nutritionalInfo.add(calories);
        JProgressBar progressBar=new JProgressBar();
        progressBar.setValue(percentCal);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.decode("#56ab91"));
        progressBar.setForeground(Color.decode("#7efcd6"));
        progressBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.white; }
            protected Color getSelectionForeground() { return Color.black; }
        });
        progressBar.setBounds(50,50,290,30);
        progressBar.setOpaque(true);
        nutritionalInfo.add(progressBar);
        JLabel carbs = new JLabel();
        carbs.setText("Carbohydrates: "+ currentProgress.nutritions[1] +" / "+ UserManager.currentUser.getRecommendedDietPlan()[1] );
        carbs.setFont(new Font("Arial", Font.BOLD, 16));
        carbs.setBounds(50,90,200,30);
        carbs.setForeground(new Color(0, 0, 0));
        nutritionalInfo.add(carbs);
        JProgressBar progressBar2 = new JProgressBar();
        progressBar2.setValue(percentCarb);
        progressBar2.setStringPainted(true);
        progressBar2.setBackground(Color.decode("#56ab91"));
        progressBar2.setForeground(Color.decode("#7efcd6"));
        progressBar2.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.white; }
            protected Color getSelectionForeground() { return Color.black; }
        });
        progressBar2.setBounds(50,120,290,30);
        progressBar2.setOpaque(true);
        nutritionalInfo.add(progressBar2);
        JLabel fats = new JLabel();
        fats.setText("Fats: "+ currentProgress.nutritions[2] +" / "+ UserManager.currentUser.getRecommendedDietPlan()[2] );
        fats.setFont(new Font("Arial", Font.BOLD, 16));
        fats.setBounds(50,160,200,30);
        fats.setForeground(new Color(0, 0, 0));
        nutritionalInfo.add(fats);
        JProgressBar progressBar3 = new JProgressBar();
        progressBar3.setValue(percentFats);
        progressBar3.setStringPainted(true);
        progressBar3.setBackground(Color.decode("#56ab91"));
        progressBar3.setForeground(Color.decode("#7efcd6"));
        progressBar3.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.white; }
            protected Color getSelectionForeground() { return Color.black; }
        });
        progressBar3.setBounds(50,190,290,30);
        progressBar3.setOpaque(true);
        nutritionalInfo.add(progressBar3);
        JLabel proteins = new JLabel();
        proteins.setText("Proteins: "+ currentProgress.nutritions[3] +" / "+ UserManager.currentUser.getRecommendedDietPlan()[3] );
        proteins.setFont(new Font("Arial", Font.BOLD, 16));
        proteins.setBounds(50,230,200,30);
        proteins.setForeground(new Color(0, 0, 0));
        nutritionalInfo.add(proteins);
        JProgressBar progressBar4 = new JProgressBar();
        progressBar4.setValue(percentProtein);
        progressBar4.setStringPainted(true);
        progressBar4.setBackground(Color.decode("#56ab91"));
        progressBar4.setForeground(Color.decode("#7efcd6"));
        progressBar4.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.white; }
            protected Color getSelectionForeground() { return Color.black; }
        });
        progressBar4.setBounds(50,260,290,30);
        progressBar4.setOpaque(true);
        nutritionalInfo.add(progressBar4);
        JPanel navBar = new JPanel(null);
        navBar.setBounds(0, 550, 600, 75);
        navBar.setBackground(Color.decode("#56ab91"));
        JButton home=new JButton();
        home.setText("HOME");
        home.setBounds(70,17,170,40);
        home.setFont(new Font("Arial",Font.BOLD,17));
        home.setBackground(Color.decode("#7efcd6"));
        home.setFocusable(false);
        home.addActionListener(e -> {
            MainWindow.screenManager.changeScreen("homescreen");
        });
        navBar.add(home);
        JButton addFoods=new JButton();
        addFoods.setText("ADD FOODS");
        addFoods.setBounds(360,17,170,40);
        addFoods.setFont(new Font("Arial",Font.BOLD,17));
        addFoods.setBackground(Color.decode("#7efcd6"));
        addFoods.setFocusable(false);
        addFoods.addActionListener(e -> {
            MainWindow.screenManager.changeScreen("addfoods");
        });
        navBar.add(addFoods);
        this.add(navBar);
    }
}