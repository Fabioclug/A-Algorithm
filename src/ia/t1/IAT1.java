/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class IAT1 {
    
    public static List<Node> generated = new ArrayList<Node>();
    public static HashMap<String,int[]> used = new HashMap<String,int[]>();
    
    public static boolean arrayContains(int[] c) {
        if(used.containsKey(Arrays.toString(c)))
            return true;
        //System.out.println(Arrays.toString(c));
        return false;
    }
    
    public static void generate(Node tree) {
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
    
    public static boolean inversions(int[] game) {
        int n_inversions = 0;
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < i; j++) {
                if(game[i] != 0 && game[i] < game[j])
                    n_inversions++;
            }
        }
        return n_inversions % 2 == 0;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] game = {5,4,0,6,1,8,7,3,2};
        if(inversions(game)) {
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
                System.out.println("Número de jogadas: " + (path.size() - 1) + "\n");
                int[] board;
                for (Node node : path) {
                    board = (int[]) node.getContent().getContent();
                    for (int i = 0; i < 9; i++) {
                        System.out.print(board[i] + " ");
                        if((i+1) % 3 == 0)
                            System.out.println("");
                    }
                    System.out.println("");
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
