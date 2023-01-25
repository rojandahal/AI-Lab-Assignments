import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Name: Rojan Dahal
 * CRN: 018-322
 * 6. Write a program to implement steepest ascent hill climbing for 8-puzzle problem.
 * Develop appropriate heuristic function.
 */
public class Question5 {
    // This particular var can be changed to transform
    // the program from 8 puzzle(n=3) into 15
    // puzzle(n=4) and so on ...
    static int n = 3;
    // bottom, left, top, right
    static int[] rows = {1, 0, -1, 0};
    static int[] cols = {0, -1, 0, 1};

    // structure of the node
    static class Node implements Comparable<Node> {
        Node parent;
        int[][] mats;
        int[] emptyTilePosition;
        int costs;
        int levels;

        public Node(Node parent, int[][] mats, int[] emptyTilePosition, int costs, int levels) {
            this.parent = parent;
            this.mats = mats;
            this.emptyTilePosition = emptyTilePosition;
            this.costs = costs;
            this.levels = levels;
        }

        @Override
        public int compareTo(Node nxt) {
            return this.costs - nxt.costs;
        }
    }

    // method to calc. the no. of
    // misplaced tiles, that is the no. of non-blank
    // tiles not in their final position
    static int calculateCosts(int[][] mats, int[][] finals) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((mats[i][j] != 0) && (mats[i][j] != finals[i][j])) {
                    count++;
                }
            }
        }
        return count;
    }

    static Node newNodes(int[][] mats, int[] emptyTilePosition, int[] newEmptyTilePosition, int levels, Node parent, int[][] finals) {
        // Copying data from the parent matrixes to the present matrixes
        int[][] newMats = Arrays.stream(mats).map(int[]::clone).toArray(int[][]::new);

        // Moving the tile by 1 position
        int x1 = emptyTilePosition[0];
        int y1 = emptyTilePosition[1];
        int x2 = newEmptyTilePosition[0];
        int y2 = newEmptyTilePosition[1];
        newMats[x1][y1] = newMats[x2][y2];
        newMats[x2][y2] = 0;

        // Setting the no. of misplaced tiles
        int costs = calculateCosts(newMats, finals);

        Node newNode = new Node(parent, newMats, newEmptyTilePosition, costs, levels);
        return newNode;
    }

    // func to print the N by N matrix
    static void printMatrix(int[][] mats) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mats[i][j] + " ");
            }
            System.out.println();
        }
    }

    // func to know if (x, y) is a valid or invalid
    // matrix coordinates
    static boolean isSafe(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    // Printing the path from the root node to the final node
    static void printPath(Node root) {
        if (root == null) {
            return;
        }
        printPath(root.parent);
        printMatrix(root.mats);
        System.out.println();
    }

    public static void main(String[] args) {
        // creating a PriorityQueue
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Initial Configuration
        // Value 0 is used for empty space
        int[][] start = {{1, 2, 3}, {5, 0, 6}, {4, 7, 8}};

        // Solvable Final Configuration
        // Value 0 is used for empty space
        int[][] finals = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        // To store the position of blank space
        int[] emptyTilePosition = {1, 1};
        int levels = 0;

        // Creating the initial node and adding it to the queue
        Node root = new Node(null, start, emptyTilePosition, calculateCosts(start, finals), levels);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.poll();

            if (min.costs == 0) {
                printPath(min);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int row = min.emptyTilePosition[0] + rows[i];
                int col = min.emptyTilePosition[1] + cols[i];

                if (isSafe(row, col)) {
                    Node child = newNodes(min.mats, min.emptyTilePosition, new int[] {row, col}, min.levels + 1, min, finals);
                    pq.add(child);
                }
            }
        }
    }
}


