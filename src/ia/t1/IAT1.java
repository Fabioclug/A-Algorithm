/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ia.t1;

/**
 *
 * @author Fabioclug
 */
public class IAT1 {
    static Node tree;

    public void generate() {
        int [] start = {5,4,0,6,1,8,7,3,2};
        int[][] matrix = new int[3][3];
        int i = 0, index = 0;
        while(i < 36) {
            for(int j=0; j < 3; j ++) {
                for(int k=0; k < 3; k++) {
                    matrix[j][k] = start[index];
                    index++;
                }
            }
            index = 0;
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] game = {{5,4,0}, {6,1,8}, {7,3,2}};
        EightPiecePuzzle initial = new EightPiecePuzzle();
        initial.setContent(game);
        tree = new Node(initial, 0);
    }
    
}
