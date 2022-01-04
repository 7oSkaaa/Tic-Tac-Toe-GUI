package Input;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import multi.Tic_Tac_Toe;

public class input extends JFrame implements ActionListener {
    JFrame frame;
    JTextField name1, name2;
    JLabel user1, user2, logo;
    JButton play;
    ImageIcon img;
    String First, Second;

    public input(){
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("img.png")));
        play = new JButton("Let's Play");
        name1 = new JTextField("", 20);
        name2 = new JTextField("", 20);
        user1 = new JLabel("Name of the first player");
        user2 = new JLabel("Name of the second player");
        logo = new JLabel(img);
        play.addActionListener(this);
        user1.setBounds(100, 350, 300, 40);
        name1.setBounds(400,350,300, 40);
        user2.setBounds(100, 400, 300, 40);
        name2.setBounds(400,400,300, 40);
        play.setBounds(320, 600, 200, 30);
        logo.setBounds(320, 75, 150, 150);
        user1.setFont(new Font("MV Boli", Font.PLAIN, 20));
        user2.setFont(new Font("MV Boli", Font.PLAIN, 20));
        name1.setFont(new Font("MV Boli", Font.PLAIN, 20));
        name2.setFont(new Font("MV Boli", Font.PLAIN, 20));
        name1.setMargin(new Insets(2, 4, 2, 2));
        name2.setMargin(new Insets(2, 4, 2, 2));
        user1.setForeground(Color.RED);
        user2.setForeground(Color.BLUE);
        frame.add(logo);
        frame.add(user1);
        frame.add(name1);
        frame.add(user2);
        frame.add(name2);
        frame.add(play);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        First = name1.getText();
        Second = name2.getText();
        new Tic_Tac_Toe(First, Second);
        frame.dispose();
    }
}
