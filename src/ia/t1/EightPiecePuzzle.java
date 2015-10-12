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
public class EightPiecePuzzle implements StateRepresentation {

    private int[][] content;

    public EightPiecePuzzle() {
        //content = new int[3][3];
    }
    
    @Override
    public void setContent(Object content) {
        this.content = (int[][]) content;
    }

    @Override
    public StateRepresentation getContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(StateRepresentation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
