import java.util.Stack;

/**
 * Name: Rojan Dahal
 * CRN: 018-322
 * 2. WAP in C/C++/Java to calculate the heuristic value of the states for Blocks World Problem
 */
public class Question2 {
    public static void main(String[] args) {
        char initial_state[] = {'B', 'C', 'D', 'A'};
        char goal_state[] = {'A', 'B', 'C', 'D'};
        char popped_item;
        int h = 0, h1 = 0, h2 = 0;
        int hi = 0, hg = 0;
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        Stack<Character> s3 = new Stack<>();
        Stack<Character> s4 = new Stack<>();
        // List<Stack<Character>> lstStack = new ArrayList<Stack<Character>>(400);
        //calculate heuristic value for initial state
        for (int j = 0; j < initial_state.length; j++)
            hi -= j;
        //calculate heurisitic value for goal state
        for (int k = 0; k < goal_state.length; k++)
            hg += k;
        //pushing the character in the stack
        for (int i = 0; i < initial_state.length; i++) {
            s1.push(initial_state[i]);
        }
        System.out.println("The heuristic value of initial state is: " + hi);
        System.out.println("The heuristic value of goal state is: " + hg);
        //initial state has only one choice i.e push the block from top of the stack and  store it in any one stack(i.e on table)
        s2.push(s1.pop());
        //calculate heuristic value now of new state
        if (s2.size() == 1) {
            for (int j = 0; j < s1.size(); j++)
                h -= j;
        }
        System.out.println("The heuristic value is: " + h);
        System.out.println("s1 is" + s1);
        System.out.println("s2 is" + s2);
        //we got two state i.e s1 and s2. so what are the other possibility?
        //1.create a new stack(State A)
        System.out.println("One possibility");
        popped_item = s1.pop();
        s3.push(popped_item);
        //let's calculate heuristic value for this state also
        if (s1.size() == 1 && s2.size() == 1 && s3.size() == 1)
            h1 = 0;
        else if (s2.size() == 1 && s3.size() == 1) {
            for (int j = 0; j < s1.size(); j++)
                h1 -= j;
        } else if (s1.size() == 1 && s3.size() == 1) {
            for (int j = 0; j < s2.size(); j++)
                h1 -= j;
        } else {
            for (int j = 0; j < s1.size(); j++)
                h1 -= j;
            for (int j = 0; j < s2.size(); j++)
                h1 -= j;
            for (int j = 0; j < s3.size(); j++)
                h1 -= j;
        }
        System.out.println("s1 is" + s1);
        System.out.println("s2 is" + s2);
        System.out.println("s3 is" + s3);
        System.out.println("heuristic value h1 is: " + h1);
        // 2.accumulated to tos(State B)
        System.out.println("Another possibility");
        s2.push(popped_item);
        System.out.println("s1 is" + s1);
        System.out.println("s2 is" + s2);
        //let's calculate heuristic value for this state also
        for (int j = 0; j < s1.size(); j++)
            h2 -= j;
        for (int j = 0; j < s2.size(); j++)
            h2 -= j;
        System.out.println("heuristic value h2 is: " + h2);
        //Compairing the heuristic value
        //heuristic value h=-3,h1=-1,h2=-2
        //which is greater compare with h??
        if (h1 > h) {
        //choose the state A
            s2.pop();
            System.out.println("The value with highest heuristic value is " + h1);
            System.out.println("So We Choose State A");
            System.out.println("s1 is" + s1);
            System.out.println("s2 is " + s2);
            System.out.println("s3 is" + s3);
            System.out.println("heuristic value h1 is: " + h1);
        //Do Some Operation
            if (s1.size() != 1) {
                s4.push(s1.pop());
            }
        } else {
        //choose the state B
            System.out.println("The value with highest heuristic value is " + h2);
            System.out.println("So We Choose State A");
            s2.push(popped_item);
            System.out.println("s1 is" + s1);
            System.out.println("s2 is" + s2);
        }
    }
}