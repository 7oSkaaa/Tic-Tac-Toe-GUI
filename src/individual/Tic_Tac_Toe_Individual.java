package individual;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Again.*;

public class Tic_Tac_Toe_Individual implements ActionListener {
    static char player = 'O', opponent = 'X';
    Random random;
    JFrame frame;
    JPanel title_panel;
    JPanel board;
    JLabel text_field;
    JButton[] buttons;
    boolean first_turn;
    boolean is_end, is_Hard;
    char[][] grid;

    public Tic_Tac_Toe_Individual(boolean is_Hard) {
        this.is_Hard = is_Hard;
        grid = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = '-';
        random = new Random();
        frame = new JFrame("Tic Tac Toe");
        title_panel = new JPanel();
        board = new JPanel();
        text_field = new JLabel();
        buttons = new JButton[9];
        frame.setSize(800, 800);
        frame.setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        title_panel.setLayout(new BorderLayout());
        text_field.setBackground(Color.BLACK);
        text_field.setForeground(new Color(255, 255, 255));
        text_field.setFont(new Font("MV Boli", Font.BOLD, 75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setText("Tic-Tac-Toe");
        text_field.setOpaque(true);
        title_panel.setBackground(Color.black);
        title_panel.setBounds(0, 0, 700, 100);
        board.setLayout(new GridLayout(3, 3));
        board.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
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
        is_end = false;
        First_turn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText() == "") {
                buttons[i].setForeground(new Color((first_turn ? 255 : 0), 0, (first_turn ? 0 : 255)));
                buttons[i].setText(first_turn ? "X" : "O");
                grid[i / 3][i % 3] = (first_turn ? 'X' : 'O');
                first_turn = !first_turn;
                text_field.setText("Your turn");
                check();
            }
        }
        if (!first_turn)
            computer_play();
    }

    void computer_play() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        if (!first_turn && !is_end) {
            int idx;
            if(is_Hard) {
                for (int i = 0; i < 9; i++) {
                    if (buttons[i].getText() == "") grid[i / 3][i % 3] = '-';
                    if (buttons[i].getText() == "X") grid[i / 3][i % 3] = 'X';
                    if (buttons[i].getText() == "O") grid[i / 3][i % 3] = 'O';
                }
                Move mv = findBestMove(grid);
                grid[mv.row][mv.col] = player;
                idx = mv.row * 3 + mv.col;
            }else {
                idx = random.nextInt(8);
                while(buttons[idx].getText() != ""){
                    idx = random.nextInt(8);
                }
            }
            if (buttons[idx].getText() == "") {
                buttons[idx].setForeground(new Color((first_turn ? 255 : 0), 0, (first_turn ? 0 : 255)));
                buttons[idx].setText(first_turn ? "X" : "O");
                grid[idx / 3][idx % 3] = (first_turn ? 'X' : 'O');
                first_turn = !first_turn;
                text_field.setText("Your turn");
                check();
            }
        }
    }

    public int[] win_cells(String c) {
        int[] cells = new int[3];
        if (buttons[0].getText() == c && buttons[1].getText() == c && buttons[2].getText() == c) {
            cells[0] = 0;
            cells[1] = 1;
            cells[2] = 2;
        } else if (buttons[3].getText() == c && buttons[4].getText() == c && buttons[5].getText() == c) {
            cells[0] = 3;
            cells[1] = 4;
            cells[2] = 5;
        } else if (buttons[6].getText() == c && buttons[7].getText() == c && buttons[8].getText() == c) {
            cells[0] = 6;
            cells[1] = 7;
            cells[2] = 8;
        } else if (buttons[0].getText() == c && buttons[3].getText() == c && buttons[6].getText() == c) {
            cells[0] = 0;
            cells[1] = 3;
            cells[2] = 6;
        } else if (buttons[1].getText() == c && buttons[4].getText() == c && buttons[7].getText() == c) {
            cells[0] = 1;
            cells[1] = 4;
            cells[2] = 7;
        } else if (buttons[2].getText() == c && buttons[5].getText() == c && buttons[8].getText() == c) {
            cells[0] = 2;
            cells[1] = 5;
            cells[2] = 8;
        } else if (buttons[0].getText() == c && buttons[4].getText() == c && buttons[8].getText() == c) {
            cells[0] = 0;
            cells[1] = 4;
            cells[2] = 8;
        } else if (buttons[2].getText() == c && buttons[4].getText() == c && buttons[6].getText() == c) {
            cells[0] = 2;
            cells[1] = 4;
            cells[2] = 6;
        } else {
            cells[0] = -1;
            cells[1] = -1;
            cells[2] = -1;
        }
        return cells;
    }

    public boolean is_tie() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText() == "") return false;
        }
        return true;
    }

    public void end_with_tie() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        text_field.setText("Tie");
    }

    public void check() {
        if (is_win("X")) {
            int[] cells = win_cells("X");
            win(cells[0], cells[1], cells[2], "You");
            is_end = true;
            new again_ind(is_Hard);
        } else if (is_win("O")) {
            int[] cells = win_cells("O");
            win(cells[0], cells[1], cells[2], "Dev");
            is_end = true;
            new again_ind(is_Hard);
        } else if (is_tie()) {
            end_with_tie();
            is_end = true;
            new again_ind(is_Hard);
        }
    }

    public void win(int a, int b, int c, String w) {
        buttons[a].setBackground((w == "Dev" ? Color.RED : Color.GREEN));
        buttons[b].setBackground((w == "Dev" ? Color.RED : Color.GREEN));
        buttons[c].setBackground((w == "Dev" ? Color.RED : Color.GREEN));
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        if (w == "Dev") {
            text_field.setForeground(Color.RED);
            text_field.setText("You Lose");
        } else {
            text_field.setForeground(Color.GREEN);
            text_field.setText("You Won");
        }
    }

    public boolean is_win(String c) {
        if (buttons[0].getText() == c && buttons[1].getText() == c && buttons[2].getText() == c) return true;
        if (buttons[3].getText() == c && buttons[4].getText() == c && buttons[5].getText() == c) return true;
        if (buttons[6].getText() == c && buttons[7].getText() == c && buttons[8].getText() == c) return true;
        if (buttons[0].getText() == c && buttons[3].getText() == c && buttons[6].getText() == c) return true;
        if (buttons[1].getText() == c && buttons[4].getText() == c && buttons[7].getText() == c) return true;
        if (buttons[2].getText() == c && buttons[5].getText() == c && buttons[8].getText() == c) return true;
        if (buttons[0].getText() == c && buttons[4].getText() == c && buttons[8].getText() == c) return true;
        if (buttons[2].getText() == c && buttons[4].getText() == c && buttons[6].getText() == c) return true;
        return false;
    }

    public void First_turn() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        text_field.setText("Your Turn");
        if (random.nextInt(2) == 0) {
            first_turn = true;
        } else {
            first_turn = false;
            computer_play();
        }
    }

    static class Move {
        int row, col;
    }

    static Boolean isMovesLeft(char[][] b){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (b[i][j] == '-') return true;
        return false;
    }

    static int evaluate(char[][] b){
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == player)  return +10;
                else if (b[row][0] == opponent)  return -10;
            }
        }
        for (int col = 0; col < 3; col++){
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]){
                if (b[0][col] == player) return +10;
                else if (b[0][col] == opponent) return -10;
            }
        }
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]){
            if (b[0][0] == player) return +10;
            else if (b[0][0] == opponent) return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]){
            if (b[0][2] == player) return +10;
            else if (b[0][2] == opponent) return -10;
        }
        return 0;
    }

    static int minimax(char[][] b, int depth, Boolean isMax){
        int score = evaluate(b);
        if (score == 10)  return score;
        if (score == -10) return score;
        if (!isMovesLeft(b)) return 0;
        if (isMax){
            int best = -1000;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (b[i][j] == '-'){
                        b[i][j] = player;
                        best = Math.max(best, minimax(b, depth + 1, !isMax));
                        b[i][j] = '-';
                    }
                }
            }
            return best;
        }
        else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (b[i][j] == '-'){
                        b[i][j] = opponent;
                        best = Math.min(best, minimax(b, depth + 1, !isMax));
                        b[i][j] = '-';
                    }
                }
            }
            return best;
        }
    }

    static Move findBestMove(char[][] b) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (b[i][j] == '-'){
                    b[i][j] = player;
                    int moveVal = minimax(b, 0, false);
                    b[i][j] = '-';
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}