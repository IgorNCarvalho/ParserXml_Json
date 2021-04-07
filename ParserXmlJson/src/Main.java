import java.io.*;
import java.util.*;


public class Main {

	public static ArrayList<String> word = new ArrayList<>();
	
	
	
	public static void main(String[] args) {
		//Leitura do arquivo texto 
		
				String path = "xml.txt";
				FileReader fr = null;
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(path));
					
					String line = br.readLine();
					System.out.println("Leitura do arquivo texto: ");
					word.add(line);
					
					while(line != null) {
						System.out.println(line);
						line = br.readLine();
						word.add(line);
					}
				}
				catch(IOException e) {
					System.out.println("Erro: " + e.getMessage());	
				}
				finally {
					System.out.println();
					try {
						if(br != null)
						{
							br.close();
						}
						if(fr != null)
						{
							fr.close();
						}
					}catch (IOException e) {
						e.printStackTrace();
					}
				}				
	}

}
