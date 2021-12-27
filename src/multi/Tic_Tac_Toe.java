package multi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Again.*;

public class Tic_Tac_Toe implements ActionListener {

    Random random;
    JFrame frame;
    JPanel title_panel;
    JPanel board;
    JLabel text_field;
    JButton[] buttons;
    boolean first_turn;
    String first, second;

    public Tic_Tac_Toe(String f, String s){
        random = new Random();
        frame = new JFrame("Tic Tac Toe");
        title_panel = new JPanel();
        board = new JPanel();
        text_field = new JLabel();
        buttons = new JButton[9];
        first = f;
        second = s;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        text_field.setBackground(new Color(25, 25, 25));
        text_field.setForeground(new Color(255, 255, 255));
        text_field.setFont(new Font("Fedora", Font.BOLD, 75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setText("Tic-Tac-Toe");
        text_field.setOpaque(true);
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        board.setLayout(new GridLayout(3, 3));
        board.setBackground(new Color(150, 150, 150));
        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            board.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        title_panel.add(text_field);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(board);
        frame.setVisible(true);
        First_turn();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText() == "") {
                buttons[i].setForeground(new Color((first_turn ? 255 : 0), 0, (first_turn ? 0 : 255)));
                buttons[i].setText(first_turn ? "X" : "O");
                first_turn = !first_turn;
                text_field.setText((first_turn ? first : second) + " turn");
                check();
            }
        }
    }

    public int[] win_cells(String c){
        int[] cells = new int[3];
        if(buttons[0].getText() == c && buttons[1].getText() == c && buttons[2].getText() == c) {
            cells[0] = 0; cells[1] = 1; cells[2] = 2;
        }
        else if(buttons[3].getText() == c && buttons[4].getText() == c && buttons[5].getText() == c) {
            cells[0] = 3; cells[1] = 4; cells[2] = 5;
        }
        else if(buttons[6].getText() == c && buttons[7].getText() == c && buttons[8].getText() == c) {
            cells[0] = 6; cells[1] = 7; cells[2] = 8;
        }
        else if(buttons[0].getText() == c && buttons[3].getText() == c && buttons[6].getText() == c) {
            cells[0] = 0; cells[1] = 3; cells[2] = 6;
        }
        else if(buttons[1].getText() == c && buttons[4].getText() == c && buttons[7].getText() == c) {
            cells[0] = 1; cells[1] = 4; cells[2] = 7;
        }
        else if(buttons[2].getText() == c && buttons[5].getText() == c && buttons[8].getText() == c) {
            cells[0] = 2; cells[1] = 5; cells[2] = 8;
        }
        else if(buttons[0].getText() == c && buttons[4].getText() == c && buttons[8].getText() == c) {
            cells[0] = 0; cells[1] = 4; cells[2] = 8;
        }
        else if(buttons[2].getText() == c && buttons[4].getText() == c && buttons[6].getText() == c) {
            cells[0] = 2; cells[1] = 4; cells[2] = 6;
        }else {
            cells[0] = -1; cells[1] = -1; cells[2] = -1;
        }
        return cells;
    }

    public boolean is_tie(){
        for(int i = 0; i < 9; i++){
              if(buttons[i].getText() == "") return false;
        }
        return true;
    }

    public void end_with_tie(){
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        text_field.setText("Tie");
    }

    public void check(){
        if(is_win("X")){
            int[] cells = win_cells("X");
            win(cells[0], cells[1], cells[2], first);
            new again(first, second);
        }else if(is_win("O")){
            int[] cells = win_cells("O");
            win(cells[0], cells[1], cells[2], second);
            new again(first, second);
        }else if(is_tie()){
            end_with_tie();
            new again(first, second);
        }
    }

    public void win(int a, int b, int c, String w){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        text_field.setText(w + " wins");
    }

    public boolean is_win(String c){
        if(buttons[0].getText() == c &&  buttons[1].getText() == c && buttons[2].getText() == c) return true;
        if(buttons[3].getText() == c &&  buttons[4].getText() == c && buttons[5].getText() == c) return true;
        if(buttons[6].getText() == c &&  buttons[7].getText() == c && buttons[8].getText() == c) return true;
        if(buttons[0].getText() == c &&  buttons[3].getText() == c && buttons[6].getText() == c) return true;
        if(buttons[1].getText() == c &&  buttons[4].getText() == c && buttons[7].getText() == c) return true;
        if(buttons[2].getText() == c &&  buttons[5].getText() == c && buttons[8].getText() == c) return true;
        if(buttons[0].getText() == c &&  buttons[4].getText() == c && buttons[8].getText() == c) return true;
        if(buttons[2].getText() == c &&  buttons[4].getText() == c && buttons[6].getText() == c) return true;
        return false;
    }

    public void First_turn(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(random.nextInt(2) == 0 ){
            first_turn = true;
            text_field.setText(first + " turn");
        }
        else {
            first_turn = false;
            text_field.setText(second + " turn");
        }
    }

}
