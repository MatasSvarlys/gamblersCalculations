import java.util.Random;

public class GamblingMachine {
    private int chooseFrom;
    Random rand = new Random();
    public GamblingMachine(int num){
        chooseFrom = num;
    }

    public int roll(int bet){
        int pickedNum = rand.nextInt(chooseFrom);
        if(pickedNum == 1){
            return bet*chooseFrom;
        } else {
            return 0;
        }
    }
}
