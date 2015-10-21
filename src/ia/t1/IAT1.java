/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ia.t1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Fabioclug
 */
public class IAT1 {
    
    public static List<Node> generated = new ArrayList<Node>();
    public static List<int[]> used = new ArrayList<int[]>();
    
    public static boolean arrayContains(int[] c) {
        for (int[] is : used) {
            if(Arrays.equals(is,c))
                return true;
        }
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
                used.add(b.clone());
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
                used.add(b.clone());
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
                used.add(b.clone());
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
                used.add(b.clone());
                Node child = new Node(new EightPiecePuzzle(b.clone()));
                tree.addChild(child, 1);
                generated.add(child);
            }
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] game = {5,4,0,6,1,8,7,3,2};
        int count = 0;
        EightPiecePuzzle initial = new EightPiecePuzzle(game);
        Node tree = new Node(initial);
        used.add(game);
        generated.add(tree);
        while(!generated.isEmpty()) {
            generate(generated.get(0));
            generated.remove(0);
            count++;
            if((count % 100000) == 0)
                System.out.println("olar");
        }
        System.out.println("fim");
        System.out.println("asadfnvmfwd");
    }
    
}
