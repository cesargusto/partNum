/*
 * Conterá os métodos de manipulação de arquivos - base de dados
 */
package pacotepart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;

/**
 *
 * @author cesar
 */
public class DaoPart {
    
    private final String CAMINHO_ARQUIVO = "dados/teste1.txt";
    private FileReader arq;
    private BufferedReader lerArq;
    public int tamanho = 0;
    private int vetor[];
    
    DaoPart() throws FileNotFoundException{
        arq = new FileReader(CAMINHO_ARQUIVO);
        lerArq = new BufferedReader(arq);
    }
    
    int[] lerArquivo()throws IOException {

        String linha = lerArq.readLine();
        tamanho = Integer.parseInt(linha);
        linha = lerArq.readLine();
        //linha = lerArq.readLine();
        this.vetor = new int[tamanho];
        for(int i=0;i<tamanho;i++){
            linha=lerArq.readLine();
            vetor[i]=Integer.parseInt(linha);   
        }
        return vetor;    
    }
}
