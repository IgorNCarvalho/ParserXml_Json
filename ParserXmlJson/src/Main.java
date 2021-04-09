import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		String path = "xml.txt";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(path));
			PilhaDinamica pilha = new PilhaDinamica();
			ArrayList<String> parser = new ArrayList<String>();
			String st, st1;
			int contador = 0;
			parser.add("{");
			String[] vetor;
			String col = "";
			Estado estAnterior = new Estado();
			while ((st = br.readLine()) != null) {
				vetor = st.split("<");
				if(vetor.length == 2) {										
					if(pilha.retornaTopo() != null && ("/" + pilha.retornaTopo().getValor()).equals(vetor[1].substring(0, vetor[1].length() - 1))) {						
						if(contador != 0 && !estAnterior.getValor().equals(pilha.retornaTopo().getValor()))
							col = "]";
						estAnterior = pilha.retornaTopo();
						pilha.desempilha();
						if(parser.get(parser.size() - 1).contains(",")) {
							st1 = parser.get(parser.size() - 1).replace(",", "");
							parser.set(parser.size() - 1, st1);
						}
						if(col.equals("]")) {
							parser.add(col);
							col = "";
							contador = 0;
						}							
						parser.add("}");
					}													
					else {
						if(vetor[1].substring(0, vetor[1].length() - 1).equals(estAnterior.getValor())) {
							int ind = parser.indexOf("\"" + vetor[1].substring(0, vetor[1].length() - 1) + "\": ");
							if(ind != -1)
								parser.set(ind, "\"" + vetor[1].substring(0, vetor[1].length() - 1) + "\": [");
							contador++;
							st1 = parser.get(parser.size() - 1);
							parser.set(parser.size() - 1, st1.concat(","));
						}
						else {
							parser.add("\"" + vetor[1].substring(0, vetor[1].length() - 1) + "\": ");
							contador = 0;
						}							
						pilha.empilha(vetor[1].substring(0, vetor[1].length() - 1));						
						parser.add("{");
					}
				}
				else {
					parser.add("\"" + vetor[1].split(">")[0] + "\": " + "\"" + vetor[1].split(">")[1] + "\",");
				}								
			}
			parser.add("}");
			if(pilha.Tamanho() != 0)
				System.out.println("Arquivo com erro!");
			else
			{
				String pathh = "json.txt";
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathh))){
					
					for(String caracter : parser)
					{
						if(caracter== null )
						{
							break;
						}
						else {
							System.out.println(caracter);
							bw.write(caracter);
							bw.newLine();
						}			
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException erro) {
			erro.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}


