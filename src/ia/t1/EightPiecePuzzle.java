package ia.t1;

import java.util.Arrays;

// classe que implementa a interface StateRepresentation
// contém a representação específica do jogo
public class EightPiecePuzzle implements StateRepresentation {

    private int[] content; // Conteúdo do estado, nese caso um vetor de inteiros que representa o tabuleiro do jogo
    private static final int[] answer = {1,2,3,4,5,6,7,8,0}; // Resultado Final

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
    public boolean isAnswer() {
        if(Arrays.equals(content, answer))
            return true;
        return false;
    }

    // a heurística é calculada de acordo com o número de peças fora do lugar
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
