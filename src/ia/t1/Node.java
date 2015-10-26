package ia.t1;

import java.util.ArrayList;
import java.util.List;


// classe que representa um nó da árvore de busca
public class Node implements Comparable<Node> {
    private StateRepresentation state; // Representação do estado
    private List<Node> children; // Lista de nós filhos
    private List<Integer> costs; // Custos relacionados aos nós filhos
    int cost; // Custo do nó
    int heuristic; // Heurística do nó
    int evaluate_function; // Função de avaliação do nó
    Node parent; // Referência para o nó pai
    
    // construtor
    Node(StateRepresentation state) {
        children = new ArrayList<Node>();
        costs = new ArrayList<Integer>();
        this.state = state;
        cost = 0;
        this.heuristic = this.state.calculateHeuristic();
        parent = null;
    }
    
    // adiciona um nó à lista de filhos
    void addChild(Node child, int cost) {
        child.setParent(this);
        child.setCost(cost);
        children.add(child);
        costs.add(cost);
    }
    
    // retorna a lista de filhos
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
    
    // retorna a lista de custos relacionados aos nós-filhos
    List<Integer> getCosts() {
        return costs;
    }
    
    StateRepresentation getContent() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    // método que compara o nó com um outro passado por parâmetro
    // usado como critério para a ordenação da lista de abertos
    @Override
    public int compareTo(Node otherNode) {
        return this.evaluate_function - otherNode.evaluate_function;
    }
}
