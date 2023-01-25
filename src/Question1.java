import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Question1 {

    public static void water_jug_bfs(int ja, int jb, int target) {
        HashMap<String, Integer> m = new HashMap<>();
        boolean isSolvable = false;
        int[][] path = new int[ja + jb][2];
        Deque<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0}); // initial state: deque([(0, 0)])
        while (q.size() > 0) {
            int[] u = q.pop(); // pop leftmost(first-element) element of queue and return it

            // check if this state is visited
            if (m.containsKey(u[0] + "," + u[1])) {
                continue;
            }

            // check if this state is valid => deque([(jugA, jugB)])
            if (u[0] > ja || u[1] > jb || u[0] < 0 || u[1] < 0) {
                continue;
            }

            // add current state to path: eg -> path = [ [9, 5], [4, 5] ]
            path[path.length - 1] = new int[]{u[0], u[1]};

            // mark current state as visited: eg -> m = {(0, 5): 1, (1, 5): 1}
            m.put(u[0] + "," + u[1], 1);

            if (u[0] == target || u[1] == target) {
                isSolvable = true;
                if (u[0] == target) { // if jarA contains target liters of water
                    if (u[1] != 0) { // if jarB doesn't contain 0 liters of water
                        path[path.length - 1] = new int[]{u[0], 0};
                    }
                } else {
                    if (u[0] != 0) {
                        path[path.length - 1] = new int[]{0, u[1]};
                    }
                }

                // printing result:
                for (int[] i : path) {
                    if (i[0] == 0 && i[1] == 0) continue;
                    System.out.println("(" + i[0] + "," + i[1] + ")");
                }

                break;
            }

            // if we are not getting solution yet, we need to expand to the next child
            q.add(new int[]{u[0], jb}); // fill jug 1
            q.add(new int[]{ja, u[1]});
            for (int ap = 1; ap <= Math.max(ja, jb); ap++) {
                // pour ap amount from jug 2 to 1
                int c = u[0] + ap;
                int d = u[1] - ap;

                // check its validity as new state
                if (c == ja || (d == 0)) {
                    q.add(new int[]{c, d});
                }

                // pour ap amount from jug1 to 2
                c = u[0] - ap;
                d = u[1] + ap;
                if ((c == 0) || d == jb) {
                    q.add(new int[]{c, d});
                }
            }
            // empty jug 1 and 2
            q.add(new int[]{ja, 0});
            q.add(new int[]{0, jb});
        }

        if (!isSolvable) {
            System.out.println("No solution possible.");
        }
    }

    public static void main(String[] args) {
        int jug1 = 4, jug2 = 3, target = 2;

        System.out.println("Solution of water jug problem is:");
        water_jug_bfs(jug1, jug2, target);
    }
}

