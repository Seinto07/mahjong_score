#define MANGAN 8000      //満貫の点数
#define NAME_LEN 64      //名前の文字
#define NUMBER 4         //プレイヤーの人数

typedef struct {
    char name[NAME_LEN];    //プレイヤー名
    int have_score;         //持ち点
    char wind;              //自風
} Player;

static int round_up(int x);
int cal_fu(int win);
void cal_score_tumo(int oya, int han, int fu, int *oya_pay, int *ko_pay);
int cal_score_ron(int oya, int han, int fu);
void swap_Player(Player *x, Player *y);
void sort_by_score(Player a[], int n);
char wind_change(char wind);