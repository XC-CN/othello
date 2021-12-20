package rankinglist;

public class Player {
    public String playerName;
    public int winGames;
    public int loseGames;
    public double winRates;

    //构造器
    public Player(String playerName, int winGames, int loseGames, double winRates) {
        this.playerName = playerName;
        this.winGames = winGames;
        this.loseGames = loseGames;
        this.winRates = winRates;
    }


}
