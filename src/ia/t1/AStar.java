/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ia.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Fabioclug
 */
public class AStar {
    private List<Node> open;
    private List<Node> closed;
    private List<Node> children;
    
    AStar() {
        open = new ArrayList<Node>();
        closed = new ArrayList<Node>();
        children = new ArrayList<Node>();
    }
    
    public Node execute(Node root, StateRepresentation target) throws Exception {
       open.add(root);
       Node current;
       List<Integer> costs = new ArrayList<Integer>();
       int cost, ev_function;
       while(!open.isEmpty()) {
           current = open.remove(0);
           if(current.getContent().isAnswer())
               return current;
           children = current.getChildren();
           costs = current.getCosts();
           for(int i=0; i < children.size(); i++) {
               Node child = children.get(i);
               cost = current.getCost() + costs.get(i);
               ev_function = cost + child.getHeuristic();
               if(!open.contains(child) && !closed.contains(child)) {
                   child.setCost(cost);
                   child.setEvaluateFunction(ev_function);
                   open.add(child);
               }
               else if(open.contains(child)) {
                   if(ev_function < child.getEvaluateFunction()) {
                       child.setCost(cost);
                       child.setEvaluateFunction(ev_function);
                   }
               }
               else {
                   if(ev_function < child.getEvaluateFunction()) {
                       child.setCost(cost);
                       child.setEvaluateFunction(ev_function);
                       closed.remove(child);
                       open.add(child);
                   }
               }  
           }
           closed.add(current);
           Collections.sort(open);
       }
       throw new Exception("Search failed!");
    }
    
}
