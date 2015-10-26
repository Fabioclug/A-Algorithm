package ia.t1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
    private List<Node> open; // Lista de nós abertos 
    private List<Node> closed; // Lista de nós fechados
    private List<Node> children; // Lista dos nós filhos
    
    AStar() {
        open = new ArrayList<Node>(); 
        closed = new ArrayList<Node>(); 
        children = new ArrayList<Node>(); 
    }
    
    public Node execute(Node root) throws Exception { // Execução do algortimo A*
       open.add(root);
       Node current;
       List<Integer> costs = new ArrayList<Integer>();
       int cost, ev_function;
       while(!open.isEmpty()) { // Enquanto a lista de abertos for diferente de zero
           current = open.remove(0);
           if(current.getContent().isAnswer()) // Verifica se é a resposta
               return current;
           children = current.getChildren();
           costs = current.getCosts();
           for(int i=0; i < children.size(); i++) { 
               Node child = children.get(i);
               cost = current.getCost() + costs.get(i);
               ev_function = cost + child.getHeuristic();
               if(!open.contains(child) && !closed.contains(child)) { // Caso o nó filho não esteja na lista de abertos ou fechados, ele é adicionado a lista de fechados
                   child.setCost(cost);
                   child.setEvaluateFunction(ev_function);
                   open.add(child);
                   child.setParent(current);
               }
               else if(open.contains(child)) { // Caso esteja na lista de abertos
                   if(ev_function < child.getEvaluateFunction()) {
                       child.setCost(cost);
                       child.setEvaluateFunction(ev_function);
                       child.setParent(current);
                   }
               }
               else {
                   if(ev_function < child.getEvaluateFunction()) { // Caso esteja na lista de fechados
                       child.setCost(cost);
                       child.setEvaluateFunction(ev_function);
                       closed.remove(child);
                       open.add(child);
                       child.setParent(current);
                   }
               }  
           }
           closed.add(current);
           Collections.sort(open); // Ordena a lista de nós abertos
       }
       throw new Exception("Search failed!");
    }
    
}
