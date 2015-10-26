package ia.t1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PuzzleSolver {
    public List<Node> generated = new ArrayList<Node>();
    public HashMap<String,Node> used = new HashMap<String,Node>();
    int[] game;
    
    public PuzzleSolver(int[] game) {
        this.game = game;
    }
    
    public Node arrayContains(int[] c) {
        if(used.containsKey(Arrays.toString(c)))
            return used.get(Arrays.toString(c));
        return null;
    }
    
    public boolean branchContains(Node tree, Node existing) {
        Node a = tree;
        while(a != null) {
            if(a == existing)
                return true;
            a = a.getParent();
        }
        return false;
    }
    
    public void addNode(Node tree, int[] matrix) {
        Node existing = arrayContains(matrix);
            if(existing == null) { // Verifica se o array já está na lista de criados
                Node child = new Node(new EightPiecePuzzle(matrix.clone()));
                tree.addChild(child, 1);
                used.put(Arrays.toString(matrix), child); // Utiliza uma hash para guardar os arrays já gerados
                generated.add(child);
            }
            else
                if(!branchContains(tree, existing))
                    tree.addChild(existing, 1);
    }
    
    public void generate(Node tree) { // Gera a árvore
        int i, aux;
        int[] a = (int[]) tree.getContent().getContent();
        int[] b;
        for (i = 0; i < a.length; i++) {
            if(a[i] == 0)
                break;
        }
        if((i % 3) != 0) { // Caso que o espaço vazio vai para a esquerda
            b = a.clone();
            aux = b[i-1];
            b[i-1] = b[i];
            b[i] = aux;
            addNode(tree, b);
        }
        if(i >= 3) { // Caso que o espaço vazio vai para cima
            b = a.clone();
            aux = b[i-3];
            b[i-3] = b[i];
            b[i] = aux;
            addNode(tree, b);
        }
        if(((i-2) % 3) != 0) { // Caso em que o espaço vazio vai para a direita
            b = a.clone();
            aux = b[i+1];
            b[i+1] = b[i];
            b[i] = aux;
            addNode(tree, b);
        }
        if(i < 6) { // Caso em que o espaço vazio vai para baixo
            b = a.clone();
            aux = b[i+3];
            b[i+3] = b[i];
            b[i] = aux;
            addNode(tree, b);
        }
        
    }
    
    public static boolean isSolvable(int[] game) { //Verifica se o número de inversões é ímpar ou par
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
        if(isSolvable(game)) { // Caso o jogo tenha solução
            EightPiecePuzzle initial = new EightPiecePuzzle(game);
            Node tree = new Node(initial);
            used.put(Arrays.toString(game), tree);
            generated.add(tree);
            while(!generated.isEmpty()) { 
                generate(generated.get(0));
                generated.remove(0);   
            }
            AStar astar = new AStar();
            try {
                Node answer = astar.execute(tree);
                LinkedList<Node> path = new LinkedList<Node>(); // Caminho para o reultado
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
