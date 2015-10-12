/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ia.t1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabioclug
 */
public class Node implements Comparable<Node> {
    private StateRepresentation state;
    private List<Node> children;
    private List<Integer> costs;
    int cost;
    int heuristic;
    int evaluate_function;
    
    Node(StateRepresentation state, int heuristic) {
        children = new ArrayList<Node>();
        costs = new ArrayList<Integer>();
        this.state = state;
        cost = 0;
        this.heuristic = heuristic;
    }
    
    void addChild(Node child, int cost) {
        children.add(child);
        costs.add(cost);
    }
    
    List<Node> getChildren(){
        return children;
    }
    
    int getCost() {
        return cost;
    }
    
    int getHeuristic() {
        return heuristic;
    }
    
    int getEvaluateFunction() {
        return evaluate_function;
    }
    
    void setCost(int cost) {
        this.cost = cost;
    }
    
    void setEvaluateFunction(int efunction) {
        this.evaluate_function = efunction;
    }
    
    List<Integer> getCosts() {
        return costs;
    }
    
    StateRepresentation getContent() {
        return state;
    }
    
    @Override
    public int compareTo(Node otherNode) {
        return this.cost - otherNode.cost;
    }
}
