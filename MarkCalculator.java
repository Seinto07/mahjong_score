/** @file MarkCaluculator.java
 * @brief 符計算に関するクラス
 * @date 2023/08/08
 * @author Seiya Takahashi
 */

import java.util.Scanner;

/** @class MarkCalculator
 * @brief 符計算に関するクラス
 */
public class MarkCalculator {

    /** @fn calMark(int howWin)
     * @brief 必要な情報を尋ねて符計算を行う
     * @param howWin (int): 上がり方 メンゼンロン...0 ツモ...1 ロン...0,1以外
     * @return int: 符
     */
    public static int calMark(int howWin) {
        Scanner scanner = new Scanner(System.in);
        int mark = 20;

        System.out.println("ピンフツモかチートイツ? ピンフツモ...0 チートイツ...1 その他...0,1以外");
        int ex = scanner.nextInt();
        if (ex == 0) {
            scanner.close();
            return 20;
        } else if (ex == 1) {
            scanner.close();
            return 25;
        } 

        if (howWin == 0) {
            mark += 10;
        } else {
            mark += 2;
        }
        
        System.out.println("待ちは? 両面,シャンポン...0 その他...0以外");
        int wait = scanner.nextInt();
        if (wait != 0) {
            mark += 2;
        }

        System.out.println("雀頭は? 役牌以外...0 役牌...0以外");
        int head = scanner.nextInt();
        if (head != 0) {
            mark += 2;
        }

        // 刻子についての処理
        System.out.println("刻子の数は?");
        int pungNum = scanner.nextInt();
        if(pungNum != 0) {
            System.out.println("明刻の数は?");
            int meldedPungNum = scanner.nextInt();
                if(meldedPungNum != 0) {
                    System.out.println("1,9,字牌の明刻の数は?");
                    int SpMelPungNum = scanner.nextInt();
                    mark += (meldedPungNum - SpMelPungNum) * 2; // その他の明刻について
                    mark += SpMelPungNum * 4;
                }
            // 暗刻についての処理
            int HiddenPungNum = (pungNum - meldedPungNum);
            if(HiddenPungNum != 0) {
                    System.out.println("1,9,字牌の暗刻の数は?");
                    int SpHidPungNum = scanner.nextInt();
                    mark += (HiddenPungNum - SpHidPungNum) * 4; // その他の暗刻について
                    mark += SpHidPungNum * 8;
            }
        }

        // 槓子についての処理
        System.out.println("槓子の数は?");
        int kongNum = scanner.nextInt();
        if(kongNum != 0) {
            System.out.println("明槓の数は?");
            int meldedKongNum = scanner.nextInt();
                if(meldedKongNum != 0) {
                    System.out.println("1,9,字牌の明槓の数は?");
                    int SpMelKongNum = scanner.nextInt();
                    mark += (meldedKongNum - SpMelKongNum) * 8; // その他の明槓について
                    mark += SpMelKongNum * 16;
                }
            // 暗刻についての処理
            int HiddenKongNum = (kongNum - meldedKongNum);
            if(HiddenKongNum != 0) {
                    System.out.println("1,9,字牌の暗槓の数は?");
                    int SpHidKongNum = scanner.nextInt();
                    mark += (HiddenKongNum - SpHidKongNum) * 16; // その他の暗槓について
                    mark += SpHidKongNum * 32;
            }
        }
        scanner.close();

        mark = roundUp(mark);

        return mark;
    }

    /** @fn roundUp(int x)
     * @brief xの位置の位を切り上げる
     * @param x (int): 整数
     * @return int
     */
    private static int roundUp(int x) {
        if (x % 10 == 0) {
            return x;
        } else {
            return ((x / 10) + 1) * 10;
        }
    }
}
