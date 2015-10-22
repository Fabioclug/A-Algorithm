package ia.t1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Fabioclug
 */
public class PuzzleSolver {
    public List<Node> generated = new ArrayList<Node>();
    public HashMap<String,int[]> used = new HashMap<String,int[]>();
    int[] game;
    
    public PuzzleSolver(int[] game) {
        this.game = game;
    }
    
    public boolean arrayContains(int[] c) {
        return used.containsKey(Arrays.toString(c));
    }
    
    public void generate(Node tree) {
        int i, aux;
        int[] a = (int[]) tree.getContent().getContent();
        int[] b;
        for (i = 0; i < a.length; i++) {
            if(a[i] == 0)
                break;
        }
        if((i % 3) != 0) {
            b = a.clone();
            aux = b[i-1];
            b[i-1] = b[i];
            b[i] = aux;
            if(!arrayContains(b)) {
                used.put(Arrays.toString(b), b.clone());
                Node child = new Node(new EightPiecePuzzle(b.clone()));
                tree.addChild(child, 1);
                generated.add(child);
            }
        }
        if(i >= 3) {
            b = a.clone();
            aux = b[i-3];
            b[i-3] = b[i];
            b[i] = aux;
            if(!arrayContains(b)) {
                used.put(Arrays.toString(b), b.clone());
                Node child = new Node(new EightPiecePuzzle(b.clone()));
                tree.addChild(child, 1);
                generated.add(child);
            }
        }
        if(((i-2) % 3) != 0) {
            b = a.clone();
            aux = b[i+1];
            b[i+1] = b[i];
            b[i] = aux;
            if(!arrayContains(b)) {
                used.put(Arrays.toString(b), b.clone());
                Node child = new Node(new EightPiecePuzzle(b.clone()));
                tree.addChild(child, 1);
                generated.add(child);
            }
        }
        if(i < 6) {
            b = a.clone();
            aux = b[i+3];
            b[i+3] = b[i];
            b[i] = aux;
            if(!arrayContains(b)) {
                used.put(Arrays.toString(b), b.clone());
                Node child = new Node(new EightPiecePuzzle(b.clone()));
                tree.addChild(child, 1);
                generated.add(child);
            }
        }
        
    }
    
    public static boolean isSolvable(int[] game) {
        int n_inversions = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < i; j++) {
                if(game[i] != 0 && game[i] < game[j])
                    n_inversions++;
            }
        }
        return n_inversions % 2 == 0;
    }
    
    public void solve() {
        if(isSolvable(game)) {
            EightPiecePuzzle initial = new EightPiecePuzzle(game);
            Node tree = new Node(initial);
            used.put(Arrays.toString(game), game);
            generated.add(tree);
            while(!generated.isEmpty()) {
                generate(generated.get(0));
                generated.remove(0);   
            }
            AStar astar = new AStar();
            try {
                Node answer = astar.execute(tree, initial);
                LinkedList<Node> path = new LinkedList<Node>();
                Node ref = answer;
                while(ref != null) {
                    path.push(ref);
                    ref = ref.getParent();
                }
                System.out.println("Total number of moves: " + (path.size() - 1) + "\n");
                int[] board;
                for (Node node : path) {
                    System.out.println("Cost: " + node.getCost());
                    System.out.println("Heuristic: " + node.getHeuristic());
                    System.out.println("Evaluation Function: " + node.getEvaluateFunction() + "\n");
                    board = (int[]) node.getContent().getContent();
                    for (int i = 0; i < 9; i++) {
                        System.out.print(board[i] + " ");
                        if((i+1) % 3 == 0)
                            System.out.println("");
                    }
                    System.out.println("\n");
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else {
            System.out.println("Odd number of inversions! Can't solve...");
        }
    }
    
}
