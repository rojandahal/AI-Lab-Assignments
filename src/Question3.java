/**
 * Name: Rojan Dahal
 * CRN: 018-322
 * 3. WAP in C/C++/Java to calculate the heuristic value of the states for Tic-Tac-Toe
 */
public class Question3 {
    private int evalX(int total, char[][] board, int m, int n) {
        int row = 1, column = 1, winX = 0;
        int leftDiagonal = 0, rightDiagonal = 0;
        board[m][n] = 'X';
        if (m == n)
            leftDiagonal = 1;
        if (m + n == board.length - 1)
            rightDiagonal = 1;
        winX = total - row - column - leftDiagonal - rightDiagonal;
        return total - winX;
    }

    public static void main(String[] args) {
        char[][] board = new char[3][3];
        int tl = 8;
        int lX;
        int lO;
        //initially there are 8 possible winning lines for both X and O
        lX = tl;
        lO = tl;
        //initially
        int hX = tl - lX;
        int hO = 0;
        Question3 t = new Question3();
        //if player X moves in position 11
        System.out.println("After the first Move By X in position a11");
        hX = t.evalX(tl, board, 1, 1);
        System.out.println("Heuristic Value " + hX);
        //if player X moves in position 02
        System.out.println("After the first Move By X in position a02");
        hX = t.evalX(tl, board, 0, 2);
        System.out.println("Heuristic Value " + hX);
        //if player X moves in position 12
        System.out.println("After the first Move By X in position a12");
        hX = t.evalX(tl, board, 1, 2);
        System.out.println("Heuristic Value " + hX);
    }
}