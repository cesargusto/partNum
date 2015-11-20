package pacotepart;

import java.util.Arrays;
import java.util.Random;

/**
 * @author cesar
 */

public class ModeloPart {

    public int vetorBin[];
    public int vetorMutante[];
    public int melhorFoAtual;
    public int melhorSolAtual[];    
    public int tamanho;
    public int contatdor;
    public int melhorasBuscaLocal;

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
            if (vetorBin[i] == 0){
                soma0 = soma0 + vetor[i];
            } else {
                soma1 = soma1 + vetor[i];
            }
        }
        return Math.abs(soma0 - soma1);
    }
    boolean classificaSolucao(int solucao[], int solucaoCandidata[], int vetorElementos[]) {
        return (this.calculaFo(vetorElementos, solucao)) >= (this.calculaFo(vetorElementos, solucaoCandidata));
    }
    int[] buscaLocal(int vetorSolucaoIncial[], int vetorElementos[], int solucao[]) {
        this.melhorasBuscaLocal = 0;
        int auxT = 0;
        auxT = this.calculaFo(vetorElementos, solucao);
        if (auxT < this.melhorFoAtual) {
            this.melhorFoAtual = auxT;
            this.melhorSolAtual = solucao;
            auxT = 0;
        }
        int[] vetorMutante = new int[vetorElementos.length];
        this.vetorMutante = this.melhorSolAtual;

        for (int i = 0; vetorElementos.length >= i; i++) {
            if (this.vetorMutante[i] == 0) {
                this.vetorMutante[i] = 1;
            } else {
                this.vetorMutante[i] = 0;
            }
            int foAtual = this.calculaFo(vetorElementos, this.vetorMutante);
            if (foAtual < this.melhorFoAtual) {
                //System.out.println("Melhora TROCANDO BIT   >   " + foAtual);
                this.melhorFoAtual = foAtual;
                this.melhorSolAtual = this.vetorMutante;
                this.melhorasBuscaLocal += 1;
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
                    //System.out.println("Melhora INVERTENDO BIT   >  " + foAtual);
                    this.melhorFoAtual = foAtual;
                    this.melhorSolAtual = this.vetorMutante;
                    this.melhorasBuscaLocal += 1;
                }

                auxT = this.vetorMutante[i];
                this.vetorMutante[i] = this.vetorMutante[i + 1];
                this.vetorMutante[i + 1] = auxT;
            }
        }
        return melhorSolAtual;
    }
    
    int[] solucaoIncialR(int n) {
        Random rand = new Random();
        int[] solRan = new int[n];

        for (int i = 0; i < n; i++) {
            if (rand.nextBoolean() == true) {
                solRan[i] = 1;
            } else {
                solRan[i] = 0;
            }
        }
        return solRan;
    }
    boolean validaSolRand(int solRand[]) {
        int somaVetor = 0;
        for (int i = 0; i < solRand.length; i++) {
            somaVetor += solRand[i];
        }
        if ((somaVetor == 0) || (somaVetor == solRand.length)) {
            return false;
        } else {
            return true;
        }

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
        int t = vetorNum.length;
        int vetor_melhora_auxT[];
        vetor_melhora_auxT = new int[t];
        int vetor_auxiliar[];
        vetor_auxiliar = new int[t];
        
        vetor_auxiliar = Arrays.copyOf(solInicial, t);
        
        int fo_inicial_troca = 0;
        int fo_descida_troca = 0;
        //CRIEI 2 VARIAVEIS INTEIRAS PRA ARMAZENAR FO INICIAL E FO ATUAL
        fo_inicial_troca = calculaFo(vetorNum, vetor_auxiliar);
        //FO INICIAL É O MESMO DO PROCESSOR ANTERIOR DE SOL. CONSTRUTIVA -- DESNECESSARIO
        
        if(fo_inicial_troca <= this.melhorFoAtual){
            melhorFoAtual = fo_inicial_troca;
            vetor_melhora_auxT = Arrays.copyOf(vetor_auxiliar, t);
        }else{
            vetor_melhora_auxT = Arrays.copyOf(vetor_auxiliar, t);
        }
        for (int i = 0; i < t; i++) {
            if (vetor_auxiliar[i] == 0) {
                vetor_auxiliar[i] = 1;
            } else {
                vetor_auxiliar[i] = 0;
            }
            fo_descida_troca = this.calculaFo(vetorNum, vetor_auxiliar);
            if(fo_descida_troca < fo_inicial_troca){
                vetor_melhora_auxT = Arrays.copyOf(vetor_auxiliar, t);
                fo_inicial_troca = fo_descida_troca;
                if(fo_descida_troca < this.melhorFoAtual){
                    this.melhorFoAtual = fo_descida_troca;
                    this.melhorSolAtual = Arrays.copyOf(vetor_auxiliar, t);
                }
            }
            
            if(vetor_auxiliar[i] == 0)
                vetor_auxiliar[i] = 1;
            else
                vetor_auxiliar[i] = 0;
        }
        return vetor_melhora_auxT;
    }

    int[] descidaInverte(int vetorNum[], int solInicial[]){
        int t = vetorNum.length;
        //VETOR PARA GUARDAR O MELHOR RESULTADO DA BUSCA
        int vetor_melhora_auxI[];
        vetor_melhora_auxI = new int[t];
        //VETOR DE TRABALHO ONDE OCORRERÁ ALTERAÇÕES
        int vetor_auxiliar[];
        vetor_auxiliar = new int[t];
        //CRIA E ALOCA VARIAVEIS INTEIRAS PARA OS TESTES
        int auxI = 0;
        int fo_descida_inverte = 0;
        int fo_inicial_inverte = 0; 
       
        vetor_auxiliar = Arrays.copyOf(solInicial, t);
        
        fo_inicial_inverte = this.calculaFo(vetorNum, vetor_auxiliar);
        //CALCULA O VALOR DO FO ATUAL DAS INSTANCIAS PASSADAS
        //INICIA A CRIAÇÃO E VERIFICAÇÃO DOS VIZINHOS
        for(int j = 0; j < t; j ++){
            if (j >= t - 1) {
                break;
            }
            if ((vetor_auxiliar[j + 1]) != (vetor_auxiliar[j])) {
                
                auxI = vetor_auxiliar[j];
                vetor_auxiliar[j] = vetor_auxiliar[j + 1];
                vetor_auxiliar[j + 1] = auxI;

                fo_descida_inverte = this.calculaFo(vetorNum, vetor_auxiliar);
                
                if(fo_descida_inverte < fo_inicial_inverte){
                    vetor_melhora_auxI = Arrays.copyOf(vetor_auxiliar, t);
                    fo_inicial_inverte = fo_descida_inverte;
                    
                    if(fo_descida_inverte < this.melhorFoAtual){
                        this.melhorFoAtual = fo_descida_inverte;
                        this.melhorSolAtual = Arrays.copyOf(vetor_auxiliar, t);
                    }
                }
                auxI = vetor_auxiliar[j];
                vetor_auxiliar[j] = vetor_auxiliar[j + 1];
                vetor_auxiliar[j + 1] = auxI;
            
            }
        }
            
        return vetor_melhora_auxI;
        
    }    
    int[] bLocal(int numeros[], int solInicial[]){
        
        int[] melhorLocal = new int[numeros.length];
        int fo_troca = 0, fo_inverte = 0;
        int[]melhor_troca = new int[numeros.length];
        int[]melhor_inverte = new int[numeros.length];
        
        melhor_troca = this.descidaTroca(numeros, solInicial);
        fo_troca = this.calculaFo(numeros, melhor_troca);        
        melhor_inverte = this.descidaInverte(numeros, solInicial);
        fo_inverte = this.calculaFo(numeros, melhor_inverte);
        
        if (fo_troca <= fo_inverte) {
            melhorLocal = melhor_troca;
        } else {
            melhorLocal = melhor_inverte;
        }
        return melhorLocal;
    }
    public void passaSolucao(int[]vetor, int[]solucaoInicial, int[]solucaoFinal){
        int i = this.calculaFo(vetor, solucaoInicial);
        int f = this.calculaFo(vetor, solucaoFinal);
            if(i < f){
                this.melhorSolAtual = Arrays.copyOf(solucaoInicial, solucaoInicial.length);
            }
        System.out.println("Houve atualização da solução");
    }
}
