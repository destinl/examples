package Hello;

/**
 * @Description: 回溯算法示例（八皇后问题）
 * @Author: ls
 * @Date: 2024/8/19 21:41
 */
public class EightQueens {
    private static final int BOARD_SIZE = 8;
    private int[] queens;

    public EightQueens() {
        queens = new int[BOARD_SIZE];
    }

    public void solve() {
        plaveQueen(0);
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            int otherRow = i;
            int otherCol = queens[i];
            if (otherCol == col || Math.abs(otherRow - row) == Math.abs(otherCol - col)) {
                return false;
            }
        }
        return true;
    }

    private void plaveQueen(int row) {
        if (row == BOARD_SIZE) {
            printBoard();
            return;
        }
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (isSafe(row, col)) {
                queens[row] = col;
                plaveQueen(row + 1);
            }
        }
    }


    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (queens[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.solve();
    }
}
