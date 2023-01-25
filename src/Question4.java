import java.util.*;

/**
 * Name: Rojan Dahal
 * CRN: 018-322
 * 4. Solve the 8 puzzle Problem-using A* algorithm in Java.
 */

public class Question4 {

    public static int[] findZeroPos(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static ArrayList<int[]> possibleMoves(int[][] state) {
        int[] zeroPos = findZeroPos(state);
        int x = zeroPos[0];
        int y = zeroPos[1];
        ArrayList<int[]> moves = new ArrayList<>();
        if (x > 0) {
            moves.add(new int[]{x - 1, y});
        }
        if (x < 2) {
            moves.add(new int[]{x + 1, y});
        }
        if (y > 0) {
            moves.add(new int[]{x, y - 1});
        }
        if (y < 2) {
            moves.add(new int[]{x, y + 1});
        }
        return moves;
    }

    public static int[][] swapPositions(int[][] state, int[] p1, int[] p2) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            newState[i] = Arrays.copyOf(state[i], 3);
        }
        int x1 = p1[0];
        int y1 = p1[1];
        int x2 = p2[0];
        int y2 = p2[1];
        newState[x1][y1] = state[x2][y2];
        newState[x2][y2] = state[x1][y1];
        return newState;
    }

    public static int manhattanDistance(int[][] state) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = state[i][j];
                if (val != 0) {
                    int targetX = (val - 1) / 3;
                    int targetY = (val - 1) % 3;
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return distance;
    }

    public static int[][] aStar(int[][] startState, int[][] goalState) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<int[][]> heap = new PriorityQueue<>((a, b) -> manhattanDistance(a) - manhattanDistance(b));
        heap.offer(startState);

        while (!heap.isEmpty()) {
            int[][] currentState = heap.poll();
            for (int[] c : currentState) {
                System.out.println(Arrays.toString(c));
            }
            System.out.println("");

            if (Arrays.deepEquals(currentState, goalState)) {
                return currentState;
            }
            visited.add(Arrays.deepToString(currentState));
            for (int[] move : possibleMoves(currentState)) {
                int[][] newState = swapPositions(currentState, findZeroPos(currentState), move);
                if (!visited.contains(Arrays.deepToString(newState))) {
                    heap.offer(newState);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] startState = new int[][] {{1, 2, 3}, {5, 0, 6}, {4, 7, 8}};
        int[][] goalState = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] solution = aStar(startState, goalState);

        System.out.println("");

        if (solution == null) {
            System.out.println("No solution found");
        } else {
            System.out.println("Solution found:");
            for (int[] row : solution) {
                System.out.println(Arrays.toString(row));
            }
        }
    }
}

