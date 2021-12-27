package Choose;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import Input.input;
import multi.Tic_Tac_Toe;
import individual.Tic_Tac_Toe_Individual;

public class choose extends JFrame implements ActionListener {

    JFrame frame;
    JLabel logo;
    ImageIcon img;
    JButton individual, multi;

    public choose(){
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        individual = new JButton("1 Player");
        multi = new JButton("2 Players");
        individual.setForeground(Color.BLUE);
        multi.setForeground(Color.RED);
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("tictactoe.png")));
        logo = new JLabel(img);
        individual.addActionListener(this);
        multi.addActionListener(this);
        individual.setBounds(140, 300, 150, 30);
        multi.setBounds(310, 300, 150, 30);
        logo.setBounds(240, 75, 150, 150);
        frame.add(multi);
        frame.add(individual);
        frame.add(logo);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == multi){
            new input();
            frame.dispose();
        }else if(e.getSource() == individual){
            new Tic_Tac_Toe_Individual();
            frame.dispose();
        }
    }


}
