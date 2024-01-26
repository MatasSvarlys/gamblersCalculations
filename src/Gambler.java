public class Gambler {
    private int multiplier;
    private int currency;
    private int timesWon;
    private int timesLost;
    private int maxCurrency;
    private GamblingMachine gm;
    private int startBet;
    public Gambler(int startCurrency, int multiplier, int startBet){
        currency = startCurrency;
        this.multiplier = multiplier;
        gm = new GamblingMachine(multiplier);
        this.startBet = startBet;
    }

    public void gambleToOneWin(){
        int currBet = startBet;
        boolean hasWon = false;
        int winnings;
        while(currency > currBet && !hasWon){
            currency -= currBet;
            winnings = gm.roll(currBet);
            currency += winnings;
            currBet *= multiplier;
            if(winnings > 0) {
                hasWon = true;
            }
        }
        System.out.println("final bet: "+currBet/2);
    }

    public void gambleAllMultiplication(){
        maxCurrency = currency;
        int currBet = startBet;
        int winnings;
        while(currency > currBet && timesLost+timesWon < 100000){
            currency -= currBet;
            winnings = gm.roll(currBet);
            currency += winnings;

            currBet *= multiplier;

            if(winnings > 0) {
                timesWon++;
                currBet = startBet;
                if(currency > maxCurrency)
                    maxCurrency = currency;
                System.out.println("money left: "+ currency);
            } else{
                timesLost++;
                System.out.println("next bet: "+currBet);
            }
        }
        System.out.println("times won: "+timesWon+"\ntimes Lost: "+timesLost+"\nmax money had: "+maxCurrency);
    }

    public void gambleAllOscarsGrind(){
        maxCurrency = currency;
        int currBet = startBet;
        int profit = 0;
        while(currency > currBet && timesLost+timesWon < 100000){
            profit = 0;
            while (profit != startBet && currency > currBet) {
//                System.out.println(currency);
                boolean won = gm.roll(currBet) > 0;
                if (won) {
                    profit += currBet;
                    currency += currBet;

                    if (profit < startBet) {
                        if (profit + currBet + startBet > startBet) {
                            currBet = startBet - profit;
                        } else {
                            currBet += startBet;
                        }
                    }

                    timesWon++;
                } else {
                    profit -= currBet;
                    currency -= currBet;
                    timesLost++;
                }
                if (currency > maxCurrency) {
                    maxCurrency = currency;
                }
            }

        }

        System.out.println("times won: "+timesWon+"\ntimes Lost: "+timesLost+"\nmax money had: "+maxCurrency);
    }
    public int getWinCount(){
        return timesWon;
    }
    public int getLossCount(){
        return timesLost;
    }

    public int getBalance(){
        return currency;
    }
}
