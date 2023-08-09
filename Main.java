/** @file Main.java
 * @brief 点数計算を実行する
 * @date 2023/08/08
 * @author Seiya Takahashi
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** @class Main
 * @brief 点数計算を実行するクラス
 */
public class Main {
    public static void main(String[] args) {
        final int NUMBER = 4; 
        Player[] rank = new Player[NUMBER];
        String name;
        int nowScore = 0;
        int n = 0;
        char wind;
        int han = 0;
        int mark = 0;
        int score = 0;
        int winnerRank = 0;
        int loserRank = 0;
        boolean isDealer = false;
        int howWin = 0;
        Scanner scanner = new Scanner(System.in);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/score.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                name = parts[0];
                nowScore = Integer.parseInt(parts[1]);
                wind = parts[2].charAt(0);
                rank[n] = new Player(name, nowScore, wind);
                n++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("ファイルを読み込めません。");
            scanner.close();
            return;
        }

        System.out.println("現在の点数を表示します。並びは順位です。");
        for (int i = 0; i < NUMBER; i++) {
            System.out.println(rank[i].getName() + " " + rank[i].getHaveScore() + " " + rank[i].getWind());
        }

        System.out.println("上がった人は何位？");
        System.out.print("上がった人の順位:");
        winnerRank = scanner.nextInt();

        if (rank[winnerRank - 1].getWind() == 'E') {
            isDealer = true;
        }

        System.out.println("翻数は？");
        System.out.print("翻数:");
        han = scanner.nextInt();

        if (han < 13) {
            System.out.println("上がり方は? メンゼンロン...0 ツモ...1 ロン...0,1以外");
            System.out.print("上がり方:");
            howWin = scanner.nextInt();
        }

        if (han <= 4) {
            mark = MarkCalculator.calMark(howWin);
            System.out.println(mark + "符");
        }

        if (howWin != 1) {
            System.out.println("振り込んだ人は何位？");
            System.out.print("振り込んだ人の順位:");
            loserRank = scanner.nextInt();
            score = ScoreCalculator.calRonScore(isDealer, han, mark);
            System.out.println("上がり点数は" + score);
            rank[winnerRank - 1].addScore(score);
            rank[loserRank - 1].addScore(-score);
        } else {
            // 払う点数 親:pay[0] 子:pay[1]
            int[] pay = ScoreCalculator.calTumoScore(isDealer, han, mark); 
            if (isDealer) {
                System.out.println(pay[1] + "オール");
                rank[winnerRank - 1].addScore(pay[1] * 3);
            } else {
                System.out.println(pay[1] + "・" + pay[0]);
                rank[winnerRank - 1].addScore(pay[0] + pay[1] * 2);
            }
            // 上がった人以外から点を減らす
            for (int i = 0; i < NUMBER; i++) {
                if (i != winnerRank - 1) {
                    if (rank[i].getWind() == 'E') {
                        rank[i].addScore(-pay[0]);
                    } else {
                        rank[i].addScore(-pay[1]); ;
                    }
                }
            }
        }
        scanner.close();

        if (!isDealer) {
            System.out.println("親を更新します。");
            for (int i = 0; i < NUMBER; i++) {
                rank[i].changeWind(rank[i].getWind());
            }
        }

        // 順位の更新 
        for (int i = 0; i < NUMBER - 1; i++) {
            for (int j = NUMBER - 1; j > i; j--) {
                if (rank[j - 1].getHaveScore() < rank[j].getHaveScore()) {
                    Player temp = rank[j - 1];
                    rank[j - 1] = rank[j];
                    rank[j] = temp;
                }
            }
        }

        System.out.println("更新後の点数を表示します。並びは順位です。");
        for (int i = 0; i < NUMBER; i++) {
            System.out.println(rank[i].getName() + " " + rank[i].getHaveScore() + " " + rank[i].getWind());
        }
    
        // ファイルの更新
        try (FileWriter writer = new FileWriter("src/score.txt")) {
            for (int i = 0; i < NUMBER; i++) {
                writer.write(rank[i].getName() + " " + rank[i].getHaveScore() + " " + rank[i].getWind() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ファイルを書き込めません。");
        }
    }
}