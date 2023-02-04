

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;


public class zmeika {

    public static void main(String[] args) {
        myFrame okno = new myFrame();
    }

}

class myFrame extends JFrame{
    public myFrame() {
        myPanel pan = new myPanel();
        Container cont = getContentPane();
        cont.add(pan);

        setTitle("Игра \"Змейка\"");

        setBounds(0, 0, 800, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}

class myPanel extends JPanel{
    private game myGame;

    private Timer tmDraw, tmUpdate;

    private Image fon,telo,golova,ob,endg;

    private myPanel pan;

    private JLabel lb;

    private JButton btn1,btn2;

    private class myKey implements KeyListener{
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) myGame.new_napr = 0;
            if (key == KeyEvent.VK_UP) myGame.new_napr = 1;
            if (key == KeyEvent.VK_RIGHT) myGame.new_napr = 2;
            if (key == KeyEvent.VK_DOWN) myGame.new_napr = 3;
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

    public myPanel() {
        this.addKeyListener(new myKey());
        this.setFocusable(true);
        try {
            fon = ImageIO.read(new File("zmeika/fon.png"));
            telo = ImageIO.read(new File("zmeika/telo.png"));
            golova = ImageIO.read(new File("zmeika/golova.png"));
            ob = ImageIO.read(new File("zmeika/ob.png"));
            endg = ImageIO.read(new File("zmeika/endg.png"));

        } catch (Exception ex) {}

        myGame = new game();

        myGame.start();

        pan = this;

        tmDraw = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();

            }
        });
        tmDraw.start();

        tmUpdate = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (myGame.endg == false){
                    myGame.perem();
                }

                lb.setText("Счет: "+myGame.kol);
            }
        });
        tmUpdate.start();

        setLayout(null);

        lb = new JLabel("Счет: 0");
        lb.setForeground(Color.WHITE);
        lb.setFont(new Font("serif",0,20));
        lb.setBounds(630,200,150,50);
        add(lb);

        btn1 = new JButton();
        btn1.setText("Новая игра");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif",0,20));
        btn1.setBounds(630,30,150,50);
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                myGame.start();
                btn1.setFocusable(false);
                btn2.setFocusable(false);
                pan.setFocusable(true);

            }
        });
        add(btn1);

        btn2 = new JButton();
        btn2.setText("Выход");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif",0,20));
        btn2.setBounds(630,100,150,50);
        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        add(btn2);

    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        gr.drawImage(fon, 0, 0, 800, 650, null);

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (myGame.mas[i][j]!=0) {
                    if (myGame.mas[i][j]==1) {
                        gr.drawImage(golova, 10+j*20, 10+i*20, 20,20,null);
                    } else if (myGame.mas[i][j]==-1) {
                        gr.drawImage(ob, 10+j*20, 10+i*20, 20,20,null);
                    } else if (myGame.mas[i][j]>=2) {
                        gr.drawImage(telo, 10+j*20,10+i*20,20,20,null);
                    }
                }
            }

        }
        gr.setColor(Color.BLUE);
        for (int i = 0; i <= 30; i++) {
            gr.drawLine(10+i*20, 10, 10+i*20, 610);
            gr.drawLine(10, 10+i*20, 610, 10+i*20);
        }
        if (myGame.endg==true){
            gr.drawImage(endg,250,200,200,100,null);
        }
    }
}