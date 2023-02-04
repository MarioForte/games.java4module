

public class game {

    public int[][] mas;

    public int napr;

    private int gX, gY;

    public int kol;

    public int new_napr;

    private int dlina;

    public boolean endg;

    private void povorot(){
        if (Math.abs((new_napr-napr))!=2){
            napr=new_napr;
        }
    }
    public game()
    {
        mas = new int[30][30];
    }
    private void make_new() {

        while (true) {
            int x = (int)(Math.random()*30);
            int y = (int)(Math.random()*30);

            if (mas[y][x]==0) {
                mas[y][x] = -1;
                break;
            }
        }
    }
    public void start() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                mas[i][j] = 0;
            }
        }
        napr = 0;

        kol = 0;

        gX = gY = 14;

        mas[14][14] = 1;
        mas[14][15] = 2;
        mas[14][16] = 3;
        dlina = 3;

        endg = false;
        make_new();
    }

    public int peremGolova() {



        if(napr==0) {
            if((gX-1)>=0) {
                gX--;
            } else {
                gX = 29;
            }
        }
        if(napr==1) {
            if((gY-1)>=0) {
                gY--;
            } else {
                gY = 29;
            }
        }
        if(napr==2) {
            if((gX+1)<=29) {
                gX++;
            } else {
                gX = 0;
            }
        }
        if(napr==3) {
            if((gY+1)<=29) {
                gY++;
            } else {
                gY = 0;
            }
        }
       int rez = 0;

        if (mas[gY][gX]==-1) rez=1;
        else if (mas[gY][gX]==0) rez=2;
        else if (mas[gY][gX]>0) rez=3;

        mas[gY][gX]=-2;
        return rez;
    }

    public void perem(){
        int flag = peremGolova();
        if (flag==3) endg=true;

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (mas[i][j]>0) mas[i][j]++;
                else if (mas[i][j] == -2) mas[i][j]=1;

                if (flag!=1){
                    if (mas[i][j] == (dlina+1)) mas[i][j]=0;
                }
            }
        }
        if (flag==1){
            dlina++;
            make_new();
            kol+=10;
        }
        povorot();
    }
}
