package screens;

import components.FoodCard;
import components.FoodItem;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddFoods extends Screen {

    public static ArrayList<FoodCard> foodCards;

    public AddFoods(int width, int height) {
        super(width, height);
        this.init();
    }

    @Override
    public void init() {
        this.initFoods();
        JLabel header2 =new JLabel();
        header2.setBounds(0,0,600,100);
        header2.setBackground(Color.decode("#56ab91"));
        header2.setOpaque(true);
        this.add(header2);
        JLabel cover = new JLabel();
        cover.setBounds(225,15,180,70);
        cover.setText("ADD FOODS");
        cover.setForeground(new Color(255,255,255,255));
        cover.setFont(new Font("Arial",Font.PLAIN,21));
        header2.add(cover);
        JPanel screen1 = new JPanel(null);
        screen1.setPreferredSize(new Dimension(580,7510));
        screen1.setBackground(Color.decode("#56ab91"));
        for(FoodCard card : foodCards) {
            screen1.add(card);
        }
        JScrollPane scrollBar = new JScrollPane(screen1);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setBounds(10, 111, 580, 415);
        this.add(scrollBar);
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

    private void initFoods(){
        foodCards = new ArrayList<>();
        File file = new File("assets/Database/FoodItems/PakistaniFoods.txt");
        try {
            Scanner reader = new Scanner(file);//to read from the file
            int y = 10;
            while (reader.hasNextLine()){
                String foodItemString = reader.nextLine();
                String[] split = foodItemString.split("->");
                String name = split[0];
                String[] splitValues = split[1].split(",");
                int cals = Integer.parseInt(splitValues[0]);
                float carbs = Float.parseFloat(splitValues[1]);
                float proteins = Float.parseFloat(splitValues[2]);
                float fats = Float.parseFloat(splitValues[3]);
                String serving  = splitValues[4];
                FoodItem foodItem = new FoodItem(name, carbs, fats, proteins, cals, serving);
                foodCards.add(new FoodCard(10, y, foodItem));
                y += 150;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
