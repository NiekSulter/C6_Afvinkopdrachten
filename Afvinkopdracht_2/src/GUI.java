import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class GUI extends JFrame  {

    private JPanel p1, p2;
    private JButton btnStart, btnStop, buttons[][];

    private int width = 32;
    private int length = 22;

    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setSize(800,620);
        frame.setTitle("Game of Life");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.GUI();
        frame.setVisible(true);
    }

    private void GUI() {
        Container window = getContentPane();
        FlowLayout fl = new FlowLayout();
        window.setLayout(fl);
        fl.setVgap(0);


        //Panels maken
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(800, 40));
        p1.setBackground(Color.BLUE);

        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(800, 580));
        p2.setBackground(Color.RED);

        //Controle buttons
        btnStart = new JButton("Start");
        p1.add(btnStart);
        btnStop = new JButton("Stop");
        p1.add(btnStop);


        p2.setLayout(new GridLayout(width, length));
        //GoL buttons
        for (int x=0; x<width; x++) {
            for (int y=0; y<length; y++) {
                System.out.println(width);
                buttons[x][y] = new JButton();
                //buttons[x][y].setPreferredSize(new Dimension(20, 20));
                //System.out.println(buttons[x][y]);
                //p2.add(buttons[x][y]);
            }

        }




        window.add(p1);
        window.add(p2);

    }
}
