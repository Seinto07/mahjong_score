#include <stdio.h>
#include <string.h>
#include "task12-1.h"

int main(void)
{
    FILE *fp;               //ファイル用のポインタ
    Player rank[NUMBER];    //プレーヤーの配列　持ち点の高い順にプレーヤーを格納
    char name[NAME_LEN];    //ファイルから読み込んだプレーヤー名を格納
    int now_score = 0;      //ファイルから読み込んだスコアを格納
    int n = 0;              //ファイルの何行目を読み込んだか
    char wind;              //ファイルから読み込んだ自風を格納
    int han = 0;            //翻数を格納
    int fu = 0;             //符を格納
    int score = 0;          //点数を格納
    int winner = 0;         //上がった人の順位を格納
    int loser = 0;          //振り込んだ人の順位を格納
    int oya = 0;            //上がった人が親か子かを格納 0...子 1...親
    int win = 0;            //上がり方を格納
    int oya_pay = 0;        //親が払う点数
    int ko_pay = 0;         //子が払う点数

    if((fp = fopen("task12-1.txt", "r")) == NULL) {
        printf("ファイルを開けません。\n");
    } else {
        //ファイルを読み込んで名前と点数を格納
        while (fscanf(fp, "%s %d %c", name, &now_score, &wind) == 3) {
            strcpy(rank[n].name, name);
            rank[n].have_score = now_score;
            rank[n].wind = wind;
            n++;
        }
        fclose(fp);
    }

    puts("現在の点数を表示します。並びは順位です。");
    for (int i = 0; i < NUMBER; i++) {
        printf("%s %d %c\n", rank[i].name, rank[i].have_score, rank[i].wind);
    }

    puts("上がった人は何位？");
    printf("上がった人の順位:");    scanf("%d", &winner);

    if (rank[winner - 1].wind == 'E') oya = 1;  //上がった人の自風が東ならoyaを1に

    puts("翻数は？");
    printf("翻数:");    scanf("%d", &han);
    
    puts("上がり方は? メンゼンロン...0 ツモ...1 ロン...0,1以外");
    printf("上がり方:");    scanf("%d", &win);

    //4翻以下は符計算をする
    if (han <= 4) {
        fu = cal_fu(win);
        printf("%d符\n", fu);
    }

    if (win != 1) { //ロン
        puts("振り込んだ人は何位？");             //ロンなら振り込んだ人を聞く
        printf("振り込んだ人の順位:");    scanf("%d", &loser);
        score = cal_score_ron(oya, han, fu);
        printf("上がり点数は%d\n", score);        
        rank[winner - 1].have_score += score;   //上がった人に点数を加える
        rank[loser - 1].have_score -= score;    //振り込んだ人から点数を引く
    } else {    //ツモ
        cal_score_tumo(oya, han, fu, &oya_pay, &ko_pay);
        if (oya == 1) {
            printf("%dオール\n", ko_pay);
            rank[winner - 1].have_score += (ko_pay * 3);  //上がった人に点数を加える
        } else {
            printf("%d・%d\n", ko_pay, oya_pay);
            rank[winner - 1].have_score += (oya_pay + ko_pay * 2);  //上がった人に点数を加える
        }
        
        for (int i = 0; i < NUMBER; i++) {
            if (i != winner -1) {   //上がった人以外について減点
                if (rank[i].wind == 'E') {
                    rank[i].have_score -= oya_pay;  //親の点数を引く
                } else {
                    rank[i].have_score -= ko_pay;   //子の点数を引く
                }
            }
        }
    }

    if (oya != 1){//親以外が上がったときは各プレイヤーの自風を変える
        puts("親を更新します。");
        for (int i = 0; i < NUMBER; i++) {
                rank[i].wind = wind_change(rank[i].wind);   
        }
    }
    
    sort_by_score(rank, NUMBER);            //持ち点順に降順ソート


    puts("更新後の点数を表示します。並びは順位です。");
    for (int i = 0; i < NUMBER; i++) {
        printf("%s %d %c\n", rank[i].name, rank[i].have_score, rank[i].wind);
    }

    if((fp = fopen("task12-1.txt", "w")) == NULL) {
        printf("ファイルを開けません。\n");
    } else {
        //ファイルにプレーヤー情報を書き込む　並び順は順位
        for (int i = 0; i < NUMBER; i++) {
            fprintf(fp, "%s %d %c\n", rank[i].name, rank[i].have_score, rank[i].wind);        
        }
        fclose(fp);
    }

    return 0;
}