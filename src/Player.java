package src;
public class Player {
    private String name;
    private int haveScore;
    private char wind;

    public Player(String name, int haveScore, char wind) {
        this.name = name;
        this.haveScore = haveScore;
        this.wind = wind;
    }

    public String getName() {
        return this.name;
    }

    public int getHaveScore() {
        return this.haveScore;
    }

    public char getWind() {
        return this.wind;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHaveScore(int score) {
        this.haveScore = score;
    }

    public void setWind(char wind) {
        this.wind = wind;
    }

    public void addScore(int score) {
        this.haveScore += score;
    }

    public void changeWind(char wind) {
        if (wind == 'E') this.wind = 'N';
        else if (wind == 'S') this.wind = 'E';
        else if (wind == 'W') this.wind = 'S';
        else if (wind == 'N') this.wind = 'W';
    }
}
