/** @file ScoreCalculator.java
 * @brief 点数計算に関するクラス
 * @date 2023/08/08
 * @author Seiya Takahashi
 */

 /** @class ScoreCalculator
 * @brief 点数計算に関するクラス
 */
public class ScoreCalculator {
    private static final int MANGAN = 8000;

    /** @fn calTumoScore(boolean isDealer, int han, int mark)
     * @brief ツモで上がったときに親と子が払う点数を計算する
     * @param isDealer (boolean): 上がった人が親かどうか
     * @param han (int): 翻数
     * @param mark (int): 符
     * @return int[]: int[0]...親が払う点数 int[1]...子が払う点数
     */
    static int[] calTumoScore(boolean isDealer, int han, int mark) {
        // 親のツモ点数表（払う点数） 1翻 2翻 3翻 4翻
        final int[][] TABLE_DEALER = {
                { 0, 700, 1300, 2600 }, // 20符
                { 500, 1000, 2000, 3900 }, // 30符
                { 700, 1300, 2600, 4000 }, // 40符
                { 800, 1600, 3200, 4000 }, // 50符
                { 1000, 2000, 3900, 4000 }, // 60符
                { 1200, 2300, 4000, 4000 }, // 70符
                { 1300, 2600, 4000, 4000 }, // 80符
                { 1500, 2900, 4000, 4000 }, // 90符
                { 1600, 3200, 4000, 4000 }, // 100符
                { 1800, 3600, 4000, 4000 } // 110符
        };

        // 子のツモ点数表（払う点数） 1翻 2翻 3翻 4翻
        final int[][][] TABLE_CHILd = {
                { { 0, 400, 700, 1300 },    // 20符(ピンフツモ) 子が払う点数
                        { 0, 700, 1300, 2600 } }, // 親が払う点数
                { { 300, 500, 1000, 2000 }, // 30符 子が払う点数
                        { 500, 1000, 2000, 3900 } }, // 親が払う点数
                { { 400, 700, 1300, 2000 }, // 40符 子が払う点数
                        { 700, 1300, 2600, 4000 } }, // 親が払う点数
                { { 400, 800, 1600, 2000 }, // 50符 子が払う点数
                        { 800, 1600, 3200, 4000 } }, // 親が払う点数
                { { 500, 1000, 2000, 2000 }, // 60符 子が払う点数
                        { 1000, 2000, 3900, 4000 } }, // 親が払う点数
                { { 600, 1200, 2000, 2000 }, // 70符 子が払う点数
                        { 1200, 2300, 4000, 4000 } }, // 親が払う点数
                { { 700, 1300, 2000, 2000 }, // 80符 子が払う点数
                        { 1300, 2600, 4000, 4000 } }, // 親が払う点数
                { { 800, 1500, 2000, 2000 }, // 90符 子が払う点数
                        { 1500, 2900, 4000, 4000 } }, // 親が払う点数
                { { 800, 1600, 2000, 2000 }, // 100符 子が払う点数
                        { 1600, 3200, 4000, 4000 } }, // 親が払う点数
                { { 900, 1800, 2000, 2000 }, // 110符 子が払う点数
                        { 1800, 3600, 4000, 4000 } } // 親が払う点数
        }; 

        // 七対子のときの親のツモ点数表 1翻 2翻 3翻 4翻
        final int[] TABLE_DEALER_PAIRS = { 0, 700, 1300, 2600 };

        // 七対子のときの子のツモ点数表 1翻 2翻 3翻 4翻
        final int[][] TABLE_CHILD_PAIRS = {
                { 0, 0, 800, 1600 }, // 子が払う点数
                { 0, 0, 1600, 3200 } // 親が払う点数
        }; 

        int score = 0;
        int[] pay = {0, 0};

        if (han <= 4) {
            if (mark == 25) { //七対子のときのみ処理を分ける
                if (isDealer) {
                    pay[0] = 0;
                    pay[1] = TABLE_DEALER_PAIRS[han - 1];
                } else {
                    pay[0] = TABLE_CHILD_PAIRS[1][han - 1];    //親が払う点数を更新
                    pay[1] = TABLE_CHILD_PAIRS[0][han - 1];     //子が払う点数を更新
                }
            } else {
                int n = (mark - 20) / 10;     //表の何行目か
                if (isDealer) {
                    pay[0] = 0;                               //親が払う点数を更新
                    pay[1] = TABLE_DEALER[n][han - 1];       //子が払う点数を更新
                } else {
                    pay[0] = TABLE_CHILd[n][1][han - 1];    //親が払う点数を更新
                    pay[1] = TABLE_CHILd[n][0][han - 1];     //子が払う点数を更新
                }      
            }
        }
    
        //以下、満貫以上の処理
        if (han == 5) {
            score = MANGAN;            //満貫
        } else if (6 <= han && han <= 7) {
            score = (int)(MANGAN * 1.5);      //跳満
        } else if (8 <= han && han <= 10) {
            score = MANGAN * 2;        //倍満
        } else if (11 <= han && han <= 12) {
            score = MANGAN * 3;        //三倍満
        } else if (13 <= han) {
            score = MANGAN * 4;        //数え役満
        }
    
        if (5 <= han) {
            if (isDealer) {
                score = (int)(score * 1.5);    
                pay[0] = 0;
                pay[1] = score / 3;
            } else {
                pay[0] = score / 2;   
                pay[1] = score / 4; 
            }
        }

        return pay;
    }

    /** @fn calRonScore(boolean isDealer, int han, int mark)
     * @brief ロンで上がったときの点数を計算する
     * @param isDealer (boolean): 上がった人が親かどうか
     * @param han (int): 翻数
     * @param mark (int): 符
     * @return int
     */
    static int calRonScore(boolean isDealer, int han, int mark) {
        // 親のロン点数表 1翻 2翻 3翻 4翻
        final int[][] TABLE_DEALER = {
                { 1500, 2900, 5800, 11600 }, // 30符
                { 2000, 3900, 7700, MANGAN }, // 40符
                { 2400, 4800, 9600, MANGAN }, // 50符
                { 2900, 5800, 11600, MANGAN }, // 60符
                { 3400, 6800, MANGAN, MANGAN }, // 70符
                { 3900, 7700, MANGAN, MANGAN }, // 80符
                { 4400, 8700, MANGAN, MANGAN }, // 90符
                { 4800, 8700, MANGAN, MANGAN }, // 100符
                { 5300, 10600, MANGAN, MANGAN } // 110符
        };

        // 子のロン点数表 1翻 2翻 3翻 4翻
        final int[][] TABLE_CHILd = {
                { 1000, 2000, 3900, 7700 }, // 30符
                { 1300, 2600, 5200, MANGAN }, // 40符
                { 1600, 3200, 6400, MANGAN }, // 50符
                { 2000, 3900, 7700, MANGAN }, // 60符
                { 2300, 4500, MANGAN, MANGAN }, // 70符
                { 2600, 5200, MANGAN, MANGAN }, // 80符
                { 2900, 5800, MANGAN, MANGAN }, // 90符
                { 3200, 6400, MANGAN, MANGAN }, // 100符
                { 3600, 7100, MANGAN, MANGAN } // 110符
        };

        // 七対子のときの親のロン点数表 1翻  2翻  3翻  4翻
        int[] TABLE_DEALER_PAIRS = {0, 2400, 4800, 9600};

        // 七対子のときの子のロン点数表 1翻  2翻  3翻  4翻
        int[] TABLE_CHILD_PAIRS = {0, 1600, 3200, 6400};

        int score = 0;
        if (han <= 4) { 
            if (mark == 25) {
                if (isDealer) {
                    return TABLE_DEALER_PAIRS[han - 1];
                } else {
                    return TABLE_CHILD_PAIRS[han - 1];
                }
            } else {
                int n = (mark - 30) / 10; //表の何行目かを格納
                if (isDealer) {
                    return TABLE_DEALER[n][han - 1];
                } else {
                    return TABLE_CHILd[n][han-1];
                }  
            }    
        }
    
        //以下、満貫以上の処理
        if (han == 5) {
            score = MANGAN;            //満貫
        } else if (6 <= han && han <= 7) {
            score = (int)(MANGAN * 1.5);      //跳満
        } else if (8 <= han && han <= 10) {
            score = MANGAN * 2;        //倍満
        } else if (11 <= han && han <= 12) {
            score = MANGAN * 3;        //三倍満
        } else if (13 <= han) {
            score = MANGAN * 4;        //数え役満
        }

        if (isDealer) score = (int)(score * 1.5); 

        return score;
    }

}
