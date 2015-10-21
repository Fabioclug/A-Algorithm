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

    private int[] content;
    private static final int[] answer = {1,2,3,4,5,6,7,8,0};

    public EightPiecePuzzle(int[] content) {
        this.content = content;
    }
    
    @Override
    public void setContent(Object content) {
        this.content = (int[]) content;
    }

    @Override
    public int[] getContent() {
        return content;
    }

    @Override
    public boolean equals(StateRepresentation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int calculateHeuristic() {
        int heuristic = 0;
        for (int i = 0; i < answer.length; i++) {
            if(content[i] != answer[i])
                heuristic++;
        }
        return heuristic;
    }
    
}
