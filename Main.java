import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        final int NUMBER = 4; // プレーヤーの数
        Player[] rank = new Player[NUMBER];
        String name;
        int nowScore = 0;
        int n = 0;
        char wind;
        int han = 0;
        int fu = 0;
        int score = 0;
        int winner = 0;
        int loser = 0;
        int oya = 0;
        int win = 0;
        int oyaPay = 0;
        int koPay = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("task12-1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                name = parts[0];
                nowScore = Integer.parseInt(parts[1]);
                wind = parts[2].charAt(0);
                rank[n] = new Player(name, nowScore, wind);
                n++;
            }
        } catch (IOException e) {
            System.out.println("ファイルを読み込めません。");
            return;
        }

        System.out.println("現在の点数を表示します。並びは順位です。");
        for (int i = 0; i < NUMBER; i++) {
            System.out.println(rank[i].name + " " + rank[i].haveScore + " " + rank[i].wind);
        }

        // 以下、質問のコードの続き
        // ...

        try (FileWriter writer = new FileWriter("task12-1.txt")) {
            for (int i = 0; i < NUMBER; i++) {
                writer.write(rank[i].name + " " + rank[i].haveScore + " " + rank[i].wind + "\n");
            }
        } catch (IOException e) {
            System.out.println("ファイルを書き込めません。");
        }
    }
}