/*
 * Conterá os métodos de manipulação de arquivos - base de dados
 */
package pacotepart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author cesar
 */

public class DaoPart {

    private String caminho;
    private FileReader arq;
    private BufferedReader lerArq;
    public int tamanho = 0;
    private int vetor[];

    DaoPart(String nome) throws FileNotFoundException {
//	this.caminho = "/home/cesar/NetBeansProjects/ParticaoNumeros/dados/" + nome;
        this.caminho = "pacotepart/dados/" + nome;
        arq = new FileReader(this.caminho);
        lerArq = new BufferedReader(arq);
    }

    int leTamanhoVetor() throws IOException {
        String l = lerArq.readLine();
        tamanho = Integer.parseInt(l);
        return tamanho;
    }

    int[] lerArquivo(int t) throws IOException {
        String linha = "";
        this.vetor = new int[t];
        lerArq.readLine();
        for (int i = 0; i < t; i++) {
            linha = lerArq.readLine();
            this.vetor[i] = Integer.parseInt(linha);
        }
        return vetor;
    }

    public void fechar() throws IOException {
        this.lerArq.close();
    }

}
