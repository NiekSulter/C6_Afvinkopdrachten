import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

public class gui extends JFrame implements ActionListener {

    JPanel panel1, panel2;
    JTextArea ta1, ta2, ta3;
    JButton btn1;
    JTextField tf1;
    Box b1;
    JComboBox jcb;

    public static void main(String[] args) {
        gui frame = new gui();
        frame.setSize(1000,520);
        frame.setTitle("gui");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.mkGui();
        frame.setVisible(true);

        JScrollPane sp = new JScrollPane();
        frame.getContentPane().add(sp);
    }

    public void mkGui() {
        Container window = getContentPane();
        FlowLayout fl = new FlowLayout();
        window.setLayout(fl);

        //panels maken
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(900, 360));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0.4;
        gbc.weighty = 0.1;
        panel1.setLayout(new GridBagLayout());

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(900, 160));
        panel2.setLayout(new FlowLayout());

        //elementen panel1
        ta1 = new JTextArea();
        ta1.setEditable(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        ta1.setPreferredSize(new Dimension(200, 300));
        panel1.add(ta1, gbc);

        ta2 = new JTextArea();
        ta2.setEditable(true);
        gbc.gridx = 1;
        gbc.gridy = 0;
        ta2.setPreferredSize(new Dimension(200, 300));
        panel1.add(ta2, gbc);

        ta3 = new JTextArea();
        ta3.setEditable(true);
        gbc.gridx = 2;
        gbc.gridy = 0;
        ta3.setPreferredSize(new Dimension(200, 300));
        panel1.add(ta3, gbc);

        btn1 = new JButton("Click");
        btn1.addActionListener(this);
        btn1.setSize(100, 50);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel1.add(btn1, gbc);

        String[] options = {"Overeenkomst 1 & 2", "Overeenkomst 1 & 3", "Overeenkomst 2 & 3"};
        jcb = new JComboBox<>(options);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(jcb, gbc);

        //panel 2
        tf1 = new JTextField();
        tf1.setPreferredSize(new Dimension(700, 100));
        b1 = new Box(1);
        panel2.add(tf1);
        panel2.add(Box.createHorizontalStrut(125));

        //container
        window.add(panel1);
        window.add(panel2);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            String input = ta1.getText();
            String input2 = ta2.getText();
            String input3 = ta3.getText();
            int index = jcb.getSelectedIndex();

            compareLists cl = new compareLists(input, input2, input3, index);
            tf1.setText(String.join("\n", cl.alRet));

        }
    }
}
