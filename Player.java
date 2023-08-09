/** @file Player.java
 * @brief プレイヤに関するファイル
 * @date 2023/08/08
 * @author Seiya Takahashi
 */

/** @class Player
 * @brief プレイヤに関するクラス
 */
public class Player {
    private String name;
    private int haveScore;
    private char wind;

    /** @fn Player(String name, int haveScore, char wind)
     * @brief コンストラクタ
     * @param name (String): プレイヤ名
     * @param haveScore (int): 持ち点数
     * @param wind (char): 自風
     */
    public Player(String name, int haveScore, char wind) {
        this.name = name;
        this.haveScore = haveScore;
        this.wind = wind;
    }

    /** @fn getName()
     * @brief プレイヤ名を取得
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /** @fn getHaveScore()
     * @brief 持ち点数を取得
     * @return int
     */
    public int getHaveScore() {
        return this.haveScore;
    }

    /** @fn getWind()
     * @brief 自風を取得
     * @return char
     */
    public char getWind() {
        return this.wind;
    }

    /** @fn setName()
     * @brief プレイヤ名を設定
     */
    public void setName(String name) {
        this.name = name;
    }

    /** @fn setHaveScore()
     * @brief 持ち点数を設定
     */
    public void setHaveScore(int score) {
        this.haveScore = score;
    }

    /** @fn setWind()
     * @brief 自風を設定
     */
    public void setWind(char wind) {
        this.wind = wind;
    }

    /** @fn addScore()
     * @brief 点数を持ち点に加算
     * @param score (int): 点数
     */
    public void addScore(int score) {
        this.haveScore += score;
    }

    /** @fn changeWind(char wind)
     * @brief 自風を次の風に変える
     * @param wind (char): 自風
     */
    public void changeWind() {
        if (this.wind == 'E') this.wind = 'N';
        else if (this.wind == 'S') this.wind = 'E';
        else if (this.wind == 'W') this.wind = 'S';
        else if (this.wind == 'N') this.wind = 'W';
    }
}
