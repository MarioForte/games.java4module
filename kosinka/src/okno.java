import javax.swing.*;
import java.awt.*;

public class okno extends JFrame {
    public okno(){
        
        pole panel = new pole();
        Container cont = getContentPane();
        cont.add(panel);
        
        setTitle("Игра \"Пасьянс-Косинка\"");
        setBounds(0, 0, 1000, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
