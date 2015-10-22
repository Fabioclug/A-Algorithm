package ia.t1;

public class IAT1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] game = {5,4,0,6,1,8,7,3,2};
        PuzzleSolver puzzleSolver = new PuzzleSolver(game);
        puzzleSolver.solve();
    }
    
}
