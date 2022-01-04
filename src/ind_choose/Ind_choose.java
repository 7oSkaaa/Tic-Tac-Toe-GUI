package ind_choose;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import individual.*;

public class Ind_choose extends JFrame implements ActionListener {

    JFrame frame;
    JLabel logo;
    ImageIcon img;
    JButton easy, hard, quit;

    public Ind_choose(){
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        easy = new JButton("Easy");
        hard = new JButton("Hard");
        easy.setForeground(Color.BLUE);
        hard.setForeground(Color.RED);
        quit = new JButton("Quit");
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("ind.png")));
        logo = new JLabel(img);
        quit.addActionListener(this);
        easy.addActionListener(this);
        hard.addActionListener(this);
        easy.setBounds(140, 300, 150, 30);
        hard.setBounds(310, 300, 150, 30);
        quit.setBounds(225, 350, 150, 30);
        logo.setBounds(240, 75, 150, 150);
        frame.add(hard);
        frame.add(easy);
        frame.add(logo);
        frame.add(quit);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hard){
            new Tic_Tac_Toe_Individual(true);
            frame.dispose();
        }else if(e.getSource() == easy){
            new Tic_Tac_Toe_Individual(false);
            frame.dispose();
        }else if(e.getSource() == quit){
            System.exit(0);
        }
    }


}
