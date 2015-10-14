/*
 * Classe que conterá os métodos inerentes à partição de números.
 */
package pacotepart;

import java.util.List;
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
    //public List<Integer> vetorBinario;
    
void ordenaVetorDesc(int vetor[]){
    int aux=0;
    for(int i=0;i<vetor.length;i++){
        for(int j=0;j<vetor.length-1;j++){
            if(vetor[j] < vetor[j+1]){
                aux = vetor[j+1];
                vetor[j+1] = vetor[j];
                vetor[j] = aux;
            }
        }
    }
}
int []particionaConstrucao(int vetor[]){
    int tamanhoVetor = vetor.length;
    this.vetorBin = new int[tamanhoVetor];
    int soma0=0, soma1=0;
    for (int i=0;i<tamanhoVetor;i++){
        if(soma0<=soma1){
            vetorBin[i]=0;
            soma0 = soma0+vetor[i];
        }
        else {
            vetorBin[i]=1;
            soma1=soma1+vetor[i];
             }
        }
        return vetorBin;
    }

int[]geraSolAleat(int tamanho){
	Random gerador = new Random();
            int []vetorB = new int[tamanho];
            for(int i=0;i<tamanho;i++){
                    if(gerador.nextBoolean()==true){
                            vetorB[i]=1;
                    }
                    else vetorB[i]=0;
            }
            return vetorB;
    }

int calculaFo(int vetor[], int vetorBin[]){
    
    int soma0 = 0;
    int soma1 = 0;
    
    for(int i = 0;i<vetor.length;i++){
        if(vetorBin[i]==0){
            soma0 = soma0 + vetor[i];
        }
        else {
            soma1 = soma1 + vetor[i];
        }    
    }
    return Math.abs(soma0 - soma1);
    }
public boolean classificaSolucao(int solucao[], int solucaoCandidata[], int vetorElementos[]){
        return (this.calculaFo(vetorElementos, solucao)) >= (this.calculaFo(vetorElementos, solucaoCandidata));
    }
void vizinhoTroca(int vetorSolucaoIncial[], int vetorElementos[],int solucao[]){
    
    this.melhorFoAtual = this.calculaFo(vetorElementos, solucao);
    this.melhorSolAtual = new int[vetorElementos.length];
    this.melhorSolAtual = solucao;
    int []vetorMutante = new int[vetorElementos.length];
    
    for(int i = 0;i < vetorElementos.length;i ++){
        if(vetorMutante[i]==0){vetorMutante[i]=1;}
        if(vetorMutante[i]==1){vetorMutante[i]=0;}
        int foAtual = this.calculaFo(vetorElementos, vetorMutante);
        if (foAtual<this.melhorFoAtual){
            System.out.println("Melhora!-------->"+foAtual);
            this.melhorFoAtual = foAtual;
        }else {
            System.out.println("Sem melhora~~> "+foAtual);
            //System.out.println(this.calculaFo(vetorElementos, vetorMutante));
        }
        if(vetorMutante[i]==1){vetorMutante[i]=0;}
        if(vetorMutante[i]==0){vetorMutante[i]=1;}
        }
    }
}

