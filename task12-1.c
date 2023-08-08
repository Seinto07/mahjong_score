#include <stdio.h>
#include "task12-1.h"

//一の位を切り上げする関数
static int round_up(int x)
{
    if (x % 10 == 0) {
        return x;
    } else {
        return ((x / 10) + 1) * 10;
    }
}

//符計算を行う関数
int cal_fu(int win)
{   
    int ex = 0;             //例外　ピンフツモ...0 チートイツ...1 その他...0,1以外
    int wait = 0;           //待ちを格納    両面,シャンポン...0 単騎,カンチャン,ペンチャン...1
    int head = 0;           //雀頭を格納    役牌以外...0 役牌...1
    int minko_tan = 0;      //タンヤオ牌の明刻の数    
    int minko_19ji = 0;     //1,9,字牌の明刻の数
    int anko_tan = 0;       //タンヤオ牌の暗刻の数
    int anko_19ji = 0;      //1,9,字牌の暗刻の数
    int minkan_tan = 0;     //タンヤオ牌の明槓の数
    int minkan_19ji = 0;    //1,9,字牌の明槓の数
    int ankan_tan = 0;      //タンヤオ牌の暗槓の数
    int ankan_19ji = 0;     //1,9,字牌の暗槓の数
    int fu = 20;            //符を格納 基本符20を初期値に

    puts("ピンフツモかチートイツ? ピンフツモ...0 チートイツ...1 その他...0,1以外");
    printf("ピンフツモかチートイツ:");    scanf("%d", &ex);
    if (ex == 0) return fu; //ピンフツモなら20符
    else if (ex == 1) return fu + 5; //チートイツなら25符

    puts("待ちは? 両面,シャンポン...0 単騎,カンチャン,ペンチャン...1");
    printf("待ち:");    scanf("%d", &wait);

    puts("雀頭は? 役牌以外...0 役牌...1");
    printf("雀頭:");    scanf("%d", &head);

    puts("タンヤオ牌の明刻の数は?");
    printf("タンヤオ牌の明刻:");    scanf("%d", &minko_tan);

    puts("1,9,字牌の明刻の数は?");
    printf("1,9,字牌の明刻:");    scanf("%d", &minko_19ji);

    puts("タンヤオ牌の暗刻の数は？");
    printf("タンヤオ牌の暗刻:");    scanf("%d", &anko_tan);

    puts("1,9,字牌の暗刻の数は?");
    printf("1,9,字牌の暗刻:");    scanf("%d", &anko_19ji);

    puts("タンヤオ牌の明槓の数は?");
    printf("タンヤオ牌の明槓:");    scanf("%d", &minkan_tan);

    puts("1,9,字牌の明槓の数は?");
    printf("1,9,字牌の明槓:");    scanf("%d", &minkan_19ji);

    puts("タンヤオ牌の暗槓の数は？");
    printf("タンヤオ牌の暗槓:");    scanf("%d", &ankan_tan);

    puts("1,9,字牌の暗槓の数は?");
    printf("1,9,字牌の暗槓:");    scanf("%d", &ankan_19ji);

    // win==0  メンゼンロン     win== 1 ツモ
    if (win == 0) fu += 10;
    else if (win == 1) fu += 2;

    //wait==1  タンキ待ちまたはペンチャン待ちまたはカンチャン待ち
    if (wait == 1) fu += 2;

    //head==1  雀頭が役牌
    if (head == 1) fu += 2;

    //刻子について
    fu += minko_tan * 2 + minko_19ji * 4 + anko_tan * 4 + anko_19ji * 8;

    //槓子について
    fu += minkan_tan * 8 + minkan_19ji * 16 + ankan_tan * 16 + ankan_19ji * 32;

    fu = round_up(fu);  //一の位で切り上げ

    return fu;
}

//親が払う点数と子が払う点数を更新する (二つのポインタoya_pay,ko_payが指す値を変更する)
void cal_score_tumo(int oya, int han, int fu, int *oya_pay, int *ko_pay)
{
    //親のツモ点数表               1翻  2翻  3翻  4翻            
    int table_oya_tumo[10][4] = { {0, 700, 1300, 2600},              //20符(ピンフツモ)
                                  {500, 1000, 2000, 3900},           //30符
                                  {700, 1300, 2600, MANGAN},         //40符
                                  {800, 1600, 3200, MANGAN},         //50符
                                  {1000, 2000, 3900, MANGAN},        //60符
                                  {1200, 2300, MANGAN, MANGAN},      //70符
                                  {1300, 2600, MANGAN, MANGAN},      //80符
                                  {1500, 2900, MANGAN, MANGAN},      //90符
                                  {1600, 3200, MANGAN, MANGAN},      //100符
                                  {1800, 3600, MANGAN, MANGAN} };    //110符

    //子のツモ点数表                   1翻  2翻  3翻  4翻            
    int table_ko_tumo[10][2][4] = { { {0, 400, 700, 1300},           //20符(ピンフツモ)  子が払う点数
                                      {0, 700, 1300, 2600} },        //      親が払う点数
                                    { {300, 500, 1000, 2000},        //30符  子が払う点数
                                      {500, 1000, 2000, 3900} },     //      親が払う点数
                                    { {400, 700, 1300, 2000},        //40符  子が払う点数
                                      {700, 1300, 2600, 4000} },     //      親が払う点数
                                    { {400, 800, 1600, 2000},        //50符  子が払う点数
                                      {800, 1600, 3200, 4000} },     //      親が払う点数
                                    { {500, 1000, 2000, 2000},       //60符  子が払う点数
                                      {1000, 2000, 3900, 4000} },    //      親が払う点数
                                    { {600, 1200, 2000, 2000},       //70符  子が払う点数
                                      {1200, 2300, 4000, 4000} },    //      親が払う点数
                                    { {700, 1300, 2000, 2000},       //80符  子が払う点数
                                      {1300, 2600, 4000, 4000} },    //      親が払う点数
                                    { {800, 1500, 2000, 2000},       //90符  子が払う点数
                                      {1500, 2900, 4000, 4000} },    //      親が払う点数
                                    { {800, 1600, 2000, 2000},       //100符 子が払う点数
                                      {1600, 3200, 4000, 4000} },    //      親が払う点数
                                    { {900, 1800, 2000, 2000},       //110符 子が払う点数
                                      {1800, 3600, 4000, 4000} } };  //      親が払う点数
        
    //七対子のときの親のツモ点数表 1翻  2翻  3翻  4翻
    int table_oya_tumo_ex[4] = {0, 700, 1300, 2600};

    //七対子のときの子のツモ点数表   1翻  2翻  3翻  4翻
    int table_ko_tumo_ex[2][4] = { {0, 0, 800, 1600},       //子が払う点数
                                   {0, 0, 1600, 3200} };    //親が払う点数


    if (han <= 4) {
        if (fu == 25) { //七対子のときのみ処理を分ける
            if (oya == 1) {
                *oya_pay = 0;
                *ko_pay = table_oya_tumo_ex[han - 1];
            } else {
                *oya_pay = table_ko_tumo_ex[1][han - 1];    //親が払う点数を更新
                *ko_pay = table_ko_tumo_ex[0][han - 1];     //子が払う点数を更新
            }
        } else {
            int n = (fu - 20) / 10;     //表の何行目か
            if (oya == 1) {
                *oya_pay = 0;                               //親が払う点数を更新
                *ko_pay = table_oya_tumo[n][han - 1];       //子が払う点数を更新
            } else {
                *oya_pay = table_ko_tumo[n][1][han - 1];    //親が払う点数を更新
                *ko_pay = table_ko_tumo[n][0][han - 1];     //子が払う点数を更新
            }      
        }
    }

    //以下、満貫以上の処理
    int score = 0;        //満貫以上の点数を格納
    if (han == 5) {
        score = MANGAN;            //満貫
    } else if (6 <= han && han <= 7) {
        score = MANGAN * 1.5;      //跳満
    } else if (8 <= han && han <= 10) {
        score = MANGAN * 2;        //倍満
    } else if (11 <= han && han <= 12) {
        score = MANGAN * 3;        //三倍満
    } else if (13 <= han) {
        score = MANGAN * 4;        //数え役満
    }

    if (5 <= han) {
        if (oya == 1) {
            score = score * 1.5;    //親なら1.5倍
            *oya_pay = 0;
            *ko_pay = score / 3;
        } else {
            *oya_pay = score / 2;   //親は子の二倍払う
            *ko_pay = score / 4; 
        }
    }
}

//上がった人に加算される点数を返す
int cal_score_ron(int oya, int han, int fu)
{
    //親のロン点数表              1翻    2翻   3翻    4翻            
    int table_oya_ron[9][4] = { {1500, 2900, 5800, 11600},          //30符
                                {2000, 3900, 7700, MANGAN},         //40符
                                {2400, 4800, 9600, MANGAN},         //50符
                                {2900, 5800, 11600, MANGAN},        //60符
                                {3400, 6800, MANGAN, MANGAN},       //70符
                                {3900, 7700, MANGAN, MANGAN},       //80符
                                {4400, 8700, MANGAN, MANGAN},       //90符
                                {4800, 8700, MANGAN, MANGAN},       //100符
                                {5300, 10600, MANGAN, MANGAN} };    //110符

    //子のロン点数表              1翻    2翻   3翻    4翻            
    int table_ko_ron[9][4] = {  {1000, 2000, 3900, 7700},           //30符
                                {1300, 2600, 5200, MANGAN},         //40符
                                {1600, 3200, 6400, MANGAN},         //50符
                                {2000, 3900, 7700, MANGAN},         //60符
                                {2300, 4500, MANGAN, MANGAN},       //70符
                                {2600, 5200, MANGAN, MANGAN},       //80符
                                {2900, 5800, MANGAN, MANGAN},       //90符
                                {3200, 6400, MANGAN, MANGAN},       //100符
                                {3600, 7100, MANGAN, MANGAN} };     //110符
    
    //七対子のときの親のロン点数表 1翻  2翻  3翻  4翻
    int table_oya_ron_ex[4] = {0, 2400, 4800, 9600};

    //七対子のときの子のロン点数表 1翻  2翻  3翻  4翻
    int table_ko_ron_ex[4] = {0, 1600, 3200, 6400};
 
    if (han <= 4) { 
        if (fu == 25) {
            if (oya == 1) {
                return table_oya_ron_ex[han - 1];
            } else {
                return table_ko_ron_ex[han - 1];
            }
        } else {
            int n = (fu - 30) / 10; //表の何行目かを格納
            if (oya == 1) {
                return table_oya_ron[n][han - 1];
            } else {
                return table_ko_ron[n][han-1];
            }  
        }    
    }

    //以下、満貫以上の処理
    int score = 0;  //満貫以上の点数を格納
    if (han == 5) {
        score = MANGAN;            //満貫
    } else if (6 <= han && han <= 7) {
        score = MANGAN * 1.5;      //跳満
    } else if (8 <= han && han <= 10) {
        score = MANGAN * 2;        //倍満
    } else if (11 <= han && han <= 12) {
        score = MANGAN * 3;        //三倍満
    } else if (13 <= han) {
        score = MANGAN * 4;        //数え役満
    }
    if (oya == 1) score = score * 1.5; //親なら1.5倍
    return score;
}

//x及びyが指すプレーヤーを交換
void swap_Player(Player *x, Player *y)
{
    Player temp = *x;
    *x = *y;
    *y = temp;
}

//プレイヤーの配列aの先頭n個の要素を点数の降順にソート
void sort_by_score(Player a[], int n)
{
    for(int i = 0; i < n - 1; i++) {
        for (int j = n - 1; j > i; j--) {
            if (a[j - 1].have_score < a[j].have_score) {    //持ち点に基づいて大小関係を判定
                swap_Player(&a[j - 1], &a[j]);     //構造体ごとの交換
            }
        }
    }
}

//自風を変えるための関数
char wind_change(char wind)
{
    if (wind == 'E') return 'N';
    else if (wind == 'S') return 'E';
    else if (wind == 'W') return 'S';
    else if (wind == 'N') return 'W';
}

