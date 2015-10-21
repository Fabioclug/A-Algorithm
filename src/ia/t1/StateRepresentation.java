package ia.t1;

/**
 *
 * @author Fabioclug
 */
public interface StateRepresentation {
    public void setContent(Object content);
    public Object getContent();
    public boolean isAnswer();
    public int calculateHeuristic();
}
