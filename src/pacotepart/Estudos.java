package pacotepart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Estudos {

    int[]vetor;
    int[] solucao;
    int foMelhor;
    int[] melhorSol;
    int tamanho;

    int calculaFo(int v[], int n[]) {
        int soma0 = 0, soma1 = 0, fo;
        for (int i = 0; i < v.length; i++) {
            if (v[i] == 0) {
                soma0 += n[i];
            } else {
                soma1 += n[i];
            }
        }
        return Math.abs(soma0 - soma1);

    }
    int[] solucaoIncialR(int n) {
        Random rand = new Random();
        int[] solRan = new int[n];
        //int[] solRan = {0, 0, 0, 0, 0};
       
        for (int i = 0; i < n; i++) {
            if (rand.nextBoolean() == true) {
                solRan[i] = 1;
            } else {
                solRan[i] = 0;
            }
        }
            return solRan;
    }
    boolean validaSolRand(int solRand[]){
        int somaVetor = 0;
        for(int i = 0;i<tamanho;i++){
            somaVetor += solRand[i];
        }
        if((somaVetor == 0)||(somaVetor == tamanho))
            return false;
        else
            return true;
        
    }
    int[] randomico(int n) {
        boolean teste = false;
        int si[] = new int[tamanho];
        while (!teste) {
            si = this.solucaoIncialR(n);
            teste = this.validaSolRand(si);
        }
        return si;
    }
    int[] descidaTroca(int vetorNum[], int solInicial[]) {
        melhorSol = new int[solInicial.length];
        int fo = calculaFo(solInicial, vetorNum);
        for (int i = 0; i < solInicial.length; i++) {
            if (solInicial[i] == 0) {
                solInicial[i] = 1;
            } else {
                solInicial[i] = 0;
            }
            if (fo > this.calculaFo(solInicial, vetorNum));
            melhorSol = solInicial;
        }
        return melhorSol;
    }
    void ils(int vetor[], int sInit[], int ILSmax) {
        int sAtual, aux;
        sAtual = this.calculaFo(descidaTroca(vetor, sInit), vetor);
        System.out.println("Solução inicial = " + sAtual);
        if(sAtual <= 1){
            System.out.println("Já temos uma solução ótima");
            this.melhorSol = sInit;
            this.foMelhor = sAtual;
        }
        else{
        while (ILSmax > 0) {
            for (int i = 0; i < vetor.length; i++) {//pertubação de nivel 1
                if (i == vetor.length - 1) {
                    break;
                }
                if (sInit[i] == 0) {
                    sInit[i] = 1;
                } else {
                    sInit[i] = 0;
                }
                if (sInit[i + 1] == 0) {
                    sInit[i + 1] = 1;
                } else {
                    sInit[i + 1] = 0;
                }
                aux = this.calculaFo(descidaTroca(vetor, sInit), vetor);
                if (aux < sAtual) {
                    sAtual = aux;
                    this.foMelhor = aux;
                    this.melhorSol = sInit;
                    System.out.println("ILSMax = " + ILSmax + " Melhora  >  " + foMelhor + " Vetor na posição [" + i + "]");
                    if (foMelhor <= 1) {
                        System.out.println("Solução ótima!");
                        ILSmax = 0;
                        break;
                    }
                }
                System.out.println("ILSmax = " + ILSmax);
            }
            ILSmax--;
        }
        }
    }
    int entregaTamanho(int[] vetor) {
        this.tamanho = vetor.length;
        return tamanho;
    }
    void imprimeVetor(int v[]){
    for(int t=0;t<v.length;t++){
        System.out.printf("%d ",v[t]);
    }
    System.out.println();
}
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //int[] vetor = {8, 6, 12, 17, 7, 4, 1};
        //int[] solucao;

        Estudos obj = new Estudos();
        //obj.entregaTamanho(vetor);
        
        TelaPart tela = new TelaPart();
        ControllerPart controle = new ControllerPart();
        tela.exibeTitulo("Implementação do ILS (teste)");
        tela.exibeMsg("Digite o nome do arquivo:  ",false);
        String texto = tela.pegaValorString();
        ModeloPart modelo = new ModeloPart();
        DaoPart arquivo = new DaoPart(texto);
        
                   try {
                        controle.inicializaVetor();
                        System.out.println("Tamanho do vetor : " + obj.tamanho);
                        obj.vetor = arquivo.lerArquivo(obj.tamanho);
                        tela.exibeMsg("Vetor lido .............|", true);
                        modelo.ordenaVetorDesc(obj.vetor);
                        tela.exibeMsg("Agora vetor ordenado ...| ", true);
                        arquivo.fechar();
                    } catch (IOException e) {
                        System.out.println("Arquivo inválido!");
                }
        
        
        //obj.solucao = new int[obj.entregaTamanho(vetor)];
        //solucao = obj.solucaoIncialR(obj.tamanho);
        obj.solucao = obj.randomico(obj.tamanho);
        obj.imprimeVetor(obj.solucao);
        obj.ils(obj.vetor, obj.solucao, 3);
       
    }
}
