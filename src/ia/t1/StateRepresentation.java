package ia.t1;

// interface genérica que representa um estado do problema
// para criar a representação de um problema, basta implementar essa interface
public interface StateRepresentation {
    public void setContent(Object content); // atribuição do estado
    public Object getContent(); // retorna o conteúdo do estado
    public boolean isAnswer(); // verifica se é um estado final
    public int calculateHeuristic(); // calcula a heurística do estado
}
