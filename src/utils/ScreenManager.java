package utils;
import screens.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

//JPanel
public class ScreenManager extends JPanel {
    //final binds at run time const binds at compile time. const declare+initialize at a same time.
    private final HashMap<String,Screen> screens;

    public ScreenManager(int width, int height) {
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(width, height));
        this.screens = new HashMap<>();
        //Initializing keys of all the screens. But the screens are null as initially there is no screen
        //HomeScreen and AddFoods screens require a logged-in user, thus we cannot create their objects until we have a user that is logged-in
        this.screens.put("login",null);
        this.screens.put("signup",null);
        this.screens.put("homescreen",null);
        this.screens.put("addfoods",null);
        this.changeScreen("login");
    }

    public void changeScreen(String key) {

        if(!this.screens.containsKey(key)) return;

        if(this.screens.get(key)==null){
            if(key=="login"){
                this.screens.put(key,new LoginScreen(600,625));
            }
            else if (key=="signup") {
                this.screens.put(key,new SignupScreen(600,625));
            } else if (key=="homescreen") {
                this.screens.put(key,new HomeScreen(600,625));
            } else if (key=="addfoods") {
                this.screens.put(key,new AddFoods(600,625));
            }
        }
        this.removeAll(); // if the JPanel has anything inside, it will remove it else do nothing
        screens.get(key).refresh(); // to refresh the screen before adding it
        this.add(screens.get(key));
        this.revalidate(); //Used to invalidate and revalidate a screen (Refreshes the ScreenManager)
        this.repaint(); // It repaints the components after refresh
    }
}

