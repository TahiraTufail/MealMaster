package components;

import screens.HomeScreen;
import utils.MessageBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FoodCard extends JPanel implements MouseListener {

    private FoodItem foodItem;

    public FoodCard(int x, int y, FoodItem foodItem){
        this.setLayout(null);
        this.setBounds(x,y,535,130);
        this.setBackground(Color.white);
        this.foodItem = foodItem;
        this.addMouseListener(this);
        this.init();

    }
    private void init(){
        JLabel foodName = new JLabel(foodItem.getName());
        foodName.setBounds(10,25,250,40);
        foodName.setFont(new Font("Arial",Font.BOLD,20));
        this.add(foodName);
        JLabel serving = new JLabel("Serving/Person: "+foodItem.getServing());
        serving.setBounds(10,60,250,40);
        serving.setFont(new Font("Arial",Font.BOLD,16));
        this.add(serving);
        JLabel calories = new JLabel("Calories: "+foodItem.getCalories());
        calories.setBounds(230,35,250,20);
        calories.setFont(new Font("Arial",Font.PLAIN,16));
        this.add(calories);
        JLabel carbs = new JLabel("Carbohydrates: "+foodItem.getCarbs()+"g");
        carbs.setBounds(360,70,250,20);
        carbs.setFont(new Font("Arial",Font.PLAIN,16));
        this.add(carbs);
        JLabel protein = new JLabel("Proteins: "+foodItem.getProtein()+"g");
        protein.setBounds(360,35,250,20);
        protein.setFont(new Font("Arial",Font.PLAIN,16));
        this.add(protein);
        JLabel fats = new JLabel("Fats: "+foodItem.getFats()+"g");
        fats.setBounds(230,70,250,20);
        fats.setFont(new Font("Arial",Font.PLAIN,16));
        this.add(fats);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // e.getSource() returns the object that is clicked
        // so we typecast it into FoodCard since it is the only clickable object is the food card
        FoodCard card = (FoodCard) e.getSource();
        HomeScreen.selectedFoods.add(card.foodItem);
        new MessageBox("Confirm Adding Food", "Sucessfully added " + card.foodItem.getName() + "!");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
