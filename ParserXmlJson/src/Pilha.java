import java.util.*;


public class Pilha {
	private List <Character>dados;
    public Pilha (){

        dados = new ArrayList<Character>();

    }
    public void empilhar(Character empilha){

        dados.add(empilha);

    }
    public Character desempilhar( ) throws Exception {

        if(dados.isEmpty() || dados == null) {

            throw new Exception("A pilha está vazia");
        }
       return dados.remove(dados.size() -1);
    }
}
