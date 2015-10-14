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
    
    private final String CAMINHO_ARQUIVO = "dados/teste.txt";
    private FileReader arq;
    private BufferedReader lerArq;
    public int tamanho = 0;
    private int vetor[];
    
    DaoPart() throws FileNotFoundException{
        arq = new FileReader(CAMINHO_ARQUIVO);
        lerArq = new BufferedReader(arq);
    }
    int leTamanhoVetor()throws IOException {
        String l = lerArq.readLine();
        tamanho = Integer.parseInt(l);
            return tamanho;
    }
    int[] lerArquivo(int t)throws IOException {
        String linha="";
        this.vetor = new int[t];
        lerArq.readLine();
        for(int i=0;i<t;i++){
            linha=lerArq.readLine();
            this.vetor[i]=Integer.parseInt(linha);
        }
        return vetor;    
    }
    public void fechar()throws IOException{
        this.lerArq.close();
    }
    
}
