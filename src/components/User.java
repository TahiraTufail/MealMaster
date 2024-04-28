package components;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//store user info..
public class User implements Serializable {
//whenever a object need to written inside a file we implement serializable in it.
    private static final long serialVersionUID = 1L; //use to identify classes when reading from file
    private String userName, email, password, gender;
    private int age;
    private double bmi, weight, height;
    private ArrayList<Progress> dailyProgress;//progress of multiple days

    public User(String userName, String email, String password, String gender, int age, double weight, double height, Date joiningDate) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.bmi = weight / Math.pow(height, 2);
        this.weight = weight;
        this.height = height;
        this.dailyProgress = new ArrayList<>();
        this.setProgress(joiningDate,new double[]{0,0,0,0});
    }

    public int[] getRecommendedDietPlan (){
        if(this.bmi<18.5){
            return DietPlan.getWeightGain(); 
        } else if (this.bmi<=24.9) {
            return DietPlan.getMaintainWeight();
        }else {
            return DietPlan.getWeightLoss();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getBmi() {
        return bmi;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setProgress(Date date, double[] nutritions){//set user progress + add progress if not present.
        for(int i=0; i< dailyProgress.size(); i++){
            String d1 = new SimpleDateFormat("dd/MM/yyyy").format(date);//current date
            String d2 = new SimpleDateFormat("dd/MM/yyyy").format(dailyProgress.get(i).date);//All dates in List
            if(d1.equals(d2)){
                dailyProgress.set(i,new Progress(date,nutritions));
                return;
            }
        }
        this.dailyProgress.add(new Progress(date,nutritions));
    }

    public ArrayList<Progress>  getProgress(){//get user progress
        return dailyProgress;
    }

    //return progress of the given date
    public Progress getProgress(Date d){
        for(Progress p: dailyProgress){
            String d1 = new SimpleDateFormat("dd/MM/yyyy").format(d);
            String d2 = new SimpleDateFormat("dd/MM/yyyy").format(p.date);
            if(d1.equals(d2)){
                return p;
            }
        }
        return null;
    }

    //store the progress of the specific date.
    public class Progress implements Serializable{
        private static final long serialVersionUID = 1L;
        public Date date;//jis din ki nutrition os din ki date...
        public double[] nutritions;
        Progress(Date date, double[] nutritions){
          this.date = date;
          this.nutritions = nutritions;
        }
    }
}