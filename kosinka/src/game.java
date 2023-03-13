import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class game {
    public Image rubashka;
    private stopka[] stopki;
    private boolean pervVidacha;
    public boolean endGame;

    private int nomStopki;
    private int nomKarti;
    private int dx, dy;
    private int oldX, oldY;
    private Timer tmEndGame;

    public game() {
        try{
            rubashka = ImageIO.read(new File("cards/k0.png"));
        } catch (Exception e) {}

        stopki = new stopka[13];

        for (int i = 0; i < 13; i++) {
            stopki[i] = new stopka();
        }
        tmEndGame = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 2; i <= 5; i++) {
                    karta getKarta = stopki[i].get(0);
                    stopki[i].add(getKarta);
                    stopki[i].remove(0);
                }
            }
        });
        start();
    }
    private void testEndGame(){
        if ((stopki[2].size()==13) &&
            (stopki[3].size()==13) &&
            (stopki[4].size()==13) &&
            (stopki[5].size()==13)){
            endGame = true;
            tmEndGame.start();
        }
    }
    private void openKarta(){
        for (int i = 6; i <= 12; i++) {
            if (stopki[i].size()>0){
                int nomPosled = stopki[i].size()-1;
                karta getKarta = stopki[i].get(nomPosled);
                if (getKarta.tipRubashka) getKarta.tipRubashka = false;
            }
        }
    }

    public void mouseDragged(int mX, int mY) {
        if (nomStopki>=0){
            karta getKarta = stopki[nomStopki].get(nomKarti);
            getKarta.x = mX - dx;
            getKarta.y = mY - dy;

            if (getKarta.x<0) getKarta.x = 0;
            if (getKarta.x>720) getKarta.x = 720;
            if (getKarta.y<0) getKarta.y = 0;
            if (getKarta.y>650) getKarta.y = 650;

            int y = 20;
            for (int i = nomKarti+1; i < stopki[nomStopki].size(); i++) {
                stopki[nomStopki].get(i).x = getKarta.x;
                stopki[nomStopki].get(i).y = getKarta.y + y;
                y += 20;
            }
        }
    }

    public void mousePressed(int mX, int mY) {

    }

    public void mouseDoublePressed(int mX, int mY) {

    }
    public  void mouseReleased(int mX, int mY) {
        int nom = getNomKolodaPress(mX, mY);

        if (nom==0){
            vidacha();
        }
    }
    private int getNomKolodaPress(int mX, int mY){
        int nom = -1;
        if ((mY>=15) && (mY<=(30+72))){
            if ((mX>=30) && (mX<=(30+72))) nom = 0;
            if ((mX>=140) && (mX<=(140+72))) nom = 1;
            if ((mX>=360) && (mX<=(360+72))) nom = 2;
            if ((mX>=470) && (mX<=(470+72))) nom = 3;
            if ((mX>=580) && (mX<=(580+72))) nom = 4;
            if ((mX>=690) && (mX<=(690+72))) nom = 5;
        } else if ((mY >= 130) && (mY <= (700))) {
            if ((mX >= 30) && (mX<=110*7)){
                if (((mX-30)%110)<=72){
                    nom = (mX-30)/110;
                    nom += 6;
                }
            }
        }
        return nom;
    }
    private void vidacha(){
        if (stopki[0].size()>0){
            int nom;
            if (pervVidacha){
                nom = (int)(Math.random()*stopki[0].size());
            }
            else {
                nom = stopki[0].size()-1;
            }
            karta getKarta = stopki[0].get(nom);
            getKarta.tipRubashka = false;
            getKarta.x += 110;
            stopki[1].add(getKarta);
            stopki[0].remove(nom);
        }
        else{
            int nomPosled = stopki[1].size()-1;
            for (int i = nomPosled; i >=0 ; i--) {
                karta getKarta = stopki[1].get(i);
                getKarta.tipRubashka = true;
                getKarta.x -= 110;
                stopki[0].add(getKarta);
            }
            stopki[1].clear();
            pervVidacha=false;
        }
    }
    public void start(){
        for (int i = 0; i < 13; i++) {
            stopki[i].clear();
        }
        load();
        razdacha();
        endGame = false;
        pervVidacha = true;
        nomKarti = -1;
        nomStopki = -1;
    }
    private void load(){
        for (int i = 1; i <= 52; i++) {
            stopki[0].add(new karta("cards/k"+(i)+".png", rubashka, i));
        }
    }
    public void drawKoloda(Graphics gr){
        if (stopki[0].size()>0){
            stopki[0].get(stopki[0].size()-1).draw(gr);
        }
        if (stopki[1].size()>1){
            stopki[1].get(stopki[1].size()-2).draw(gr);
            stopki[1].get(stopki[1].size()-1).draw(gr);
        } else if (stopki[1].size() == 1) {
            stopki[1].get(stopki[1].size()-1).draw(gr);
        }
        for (int i = 2; i <= 5; i++) {
            if (stopki[i].size()>1){
                stopki[i].get(stopki[i].size()-2).draw(gr);
                stopki[i].get(stopki[i].size()-1).draw(gr);
            } else if (stopki[i].size()==1) {
                stopki[i].get(stopki[i].size()-1).draw(gr);
            }
        }
        for (int i = 6; i < 13; i++) {
            if (stopki[i].size()>0){
                for (int j = 0; j < stopki[i].size(); j++) {
                    if (stopki[i].get(j).vibrana) break;
                    stopki[i].get(j).draw(gr);
                }
            }
        }
        if (nomStopki!=-1){
            for (int i = nomKarti; i < stopki[nomStopki].size(); i++) {
                stopki[nomStopki].get(i).draw(gr);
            }
        }
    }

    private void razdacha(){
        int x = 30;
        for (int i = 6; i < 13; i++) {
            for (int j = 6; j <= i; j++) {
                int rnd = (int)(Math.random()*stopki[0].size());
                karta getKarta = stopki[0].get(rnd);
                if (j<i) getKarta.tipRubashka = true;
                else getKarta.tipRubashka = false;
                getKarta.x = x;
                getKarta.y = 130+stopki[i].size()*20;
                stopki[i].add(getKarta);
                stopki[0].remove(rnd);
            }
            x+=110;
        }
    }
}