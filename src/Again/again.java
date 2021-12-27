package Again;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import multi.Tic_Tac_Toe;
import Choose.*;

public class again extends JFrame implements ActionListener {

    JFrame frame;
    JButton yes, no;
    JLabel text_field;
    String first, second;

    public again(String first, String second){
        this.first = first;
        this.second = second;
        yes = new JButton("Yes");
        no = new JButton("No");
        text_field = new JLabel("Do you want to play again ?");
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 180);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        yes.setBounds(140, 100, 100, 30);
        no.setBounds(260, 100, 100, 30);
        text_field.setBounds(115, 20, 300, 50);
        yes.addActionListener(this);
        no.addActionListener(this);
        yes.setForeground(new Color(46, 162, 46));
        no.setForeground(Color.RED);
        text_field.setForeground(Color.BLUE);
        text_field.setFont(new Font("MV Boli", Font.BOLD, 17));
        yes.setFont(new Font("MV Boli", Font.BOLD, 13));
        no.setFont(new Font("MV Boli", Font.BOLD, 13));
        frame.add(yes);
        frame.add(no);
        frame.add(text_field);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window[] win = Window.getWindows();
        for (Window window : win) {
            window.dispose();
        }
        if(e.getSource() == yes){
           new Tic_Tac_Toe(first, second);
       }else {
           new choose();
       }
    }
}
