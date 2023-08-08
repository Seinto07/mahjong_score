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

}
