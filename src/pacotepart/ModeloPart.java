package pacotepart;

import java.util.Random;
/**
 *
 * @author cesar
 */
public class ModeloPart {
    
    public int vetorBin[];
    public int vetorMutante[];
    public int melhorFoAtual;
    public int melhorSolAtual[];
    public int tamanho;
    
    void ordenaVetorDesc(int vetor[]) {
        int aux = 0;
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length - 1; j++) {
                if (vetor[j] < vetor[j + 1]) {
                    aux = vetor[j + 1];
                    vetor[j + 1] = vetor[j];
                    vetor[j] = aux;
                }
            }
        }
    }
    int[] particionaConstrucao(int vetor[]) {
        int tamanhoVetor = vetor.length;
        this.vetorBin = new int[tamanhoVetor];
        int soma0 = 0, soma1 = 0;
        for (int i = 0; i < tamanhoVetor; i++) {
            if (soma0 <= soma1) {
                vetorBin[i] = 0;
                soma0 = soma0 + vetor[i];
            } else {
                vetorBin[i] = 1;
                soma1 = soma1 + vetor[i];
            }
        }
        return vetorBin;
    }
    int calculaFo(int vetor[], int vetorBin[]) {

        int soma0 = 0;
        int soma1 = 0;

        for (int i = 0; i < vetor.length; i++) {
            if (vetorBin[i] == 0) {
                soma0 = soma0 + vetor[i];
            } else {
                soma1 = soma1 + vetor[i];
            }
        }
        return Math.abs(soma0 - soma1);
    }
    public boolean classificaSolucao(int solucao[], int solucaoCandidata[], int vetorElementos[]) {
        return (this.calculaFo(vetorElementos, solucao)) >= (this.calculaFo(vetorElementos, solucaoCandidata));
    }
    void buscaLocal(int vetorSolucaoIncial[], int vetorElementos[], int solucao[]) {

        this.melhorFoAtual = this.calculaFo(vetorElementos, solucao);
        this.melhorSolAtual = new int[vetorElementos.length];
        this.melhorSolAtual = solucao;
        int[] vetorMutante = new int[vetorElementos.length];
        int auxT = 0;
        this.vetorMutante = solucao;
        int contaMelhoras = 0;
        for (int i = 0; vetorElementos.length >= i; i++) {
            if (this.vetorMutante[i] == 0) {
                this.vetorMutante[i] = 1;
            } else {
                this.vetorMutante[i] = 0;
            }
            int foAtual = this.calculaFo(vetorElementos, this.vetorMutante);
            if (foAtual < this.melhorFoAtual) {
                System.out.println("Melhora TROCANDO BIT   >   " + foAtual);
                this.melhorFoAtual = foAtual;
                this.melhorSolAtual = this.vetorMutante;
                contaMelhoras += 1;
            }

            if (this.vetorMutante[i] == 1) {
                this.vetorMutante[i] = 0;
            } else {
                this.vetorMutante[i] = 1;
            }
            if (i >= this.vetorMutante.length - 1) {
                break;
            }
            if ((this.vetorMutante[i + 1]) != (this.vetorMutante[i])) {
                auxT = this.vetorMutante[i];
                this.vetorMutante[i] = this.vetorMutante[i + 1];
                this.vetorMutante[i + 1] = auxT;

                foAtual = this.calculaFo(vetorElementos, this.vetorMutante);
                if (foAtual < this.melhorFoAtual) {
                    System.out.println("Melhora INVERTENDO BIT   >  " + foAtual);
                    this.melhorFoAtual = foAtual;
                    this.melhorSolAtual = this.vetorMutante;
                    contaMelhoras += 1;
                }

                auxT = this.vetorMutante[i];
                this.vetorMutante[i] = this.vetorMutante[i + 1];
                this.vetorMutante[i + 1] = auxT;
            }
        }
        System.out.println("Houve " + contaMelhoras + " melhoras com este método.");
    }
    int[] ils2(int melhorSolAtual[], int vetorElementos[], int nivel) {
        int s[] = new int[melhorSolAtual.length];
            for(int i=0;i<this.tamanho;i++){
                if(vetorElementos[i] == 0)
                    vetorElementos[i]=1;
                else vetorElementos[i] = 0;
                if(i >= this.tamanho - 1){break;}
                if(vetorElementos[i+1]==0)vetorElementos[i+1]=1;
                else vetorElementos[i+1] = 0;
                
                this.buscaLocal(vetorElementos, vetorElementos, s);
                }
        return s;
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
        for(int i = 0;i < solRand.length; i ++){
            somaVetor += solRand[i];
        }
        if((somaVetor == 0)||(somaVetor == solRand.length))
            return false;
        else
            return true;
        
    }
    int[] randomico(int n) {
        boolean teste = false;
        int si[] = new int[n];
        while (!teste) {
            si = this.solucaoIncialR(n);
            teste = this.validaSolRand(si);
        }
        return si;
    }
    
        int[] descidaTroca(int vetorNum[], int solInicial[]) {
        melhorSolAtual = new int[solInicial.length];
        int fo = calculaFo(solInicial, vetorNum);
        for (int i = 0; i < solInicial.length; i++) {
            if (solInicial[i] == 0) {
                solInicial[i] = 1;
            } else {
                solInicial[i] = 0;
            }
            if (fo > this.calculaFo(solInicial, vetorNum));
            melhorSolAtual = solInicial;
        }
        return melhorSolAtual;
    }
    void ils(int vetor[], int sInit[], int ILSmax) {
        int sAtual, aux;
        sAtual = melhorFoAtual;
        //sAtual = this.calculaFo(descidaTroca(vetor, sInit), vetor);
        System.out.println("Fo inicial = " + sAtual);
        
        if(sAtual <= 1){
            System.out.println("Já temos uma solução ótima");
            this.melhorSolAtual = sInit;
            this.melhorFoAtual = sAtual;
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
                this.buscaLocal(sInit, vetor, melhorSolAtual);
                aux = this.calculaFo(vetor, melhorSolAtual);
                if (aux <= sAtual) {
                    sAtual = aux;
                    this.melhorFoAtual = sAtual;
                    this.melhorSolAtual = sInit;
                    System.out.println("ILSMax = " + ILSmax + " Melhora  >  " + melhorFoAtual + " Vetor na posição [" + i + "]");
                    if (melhorFoAtual <= 1) {
                        System.out.println("Solução ótima!");
                        ILSmax = 0;
                        System.out.println("For interrompido pelo break no i = "+i);
                        break;
                    }
                }
                System.out.println("ILSmax = " + ILSmax);
            }
            if(melhorFoAtual <= 1){
                System.out.println("While interrompido zerando o ILSmax  com valor atual de "+ILSmax);
                ILSmax = 0;
            }
            ILSmax--;
        }
        }
    }
}
