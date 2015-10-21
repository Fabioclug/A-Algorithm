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
public interface StateRepresentation {
    public void setContent(Object content);
    public Object getContent();
    public boolean equals(StateRepresentation obj);
    public int calculateHeuristic();
}
