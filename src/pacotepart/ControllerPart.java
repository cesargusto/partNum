package pacotepart;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author cesar
 */
public class ControllerPart {
    
    private final TelaPart tela;
    private DaoPart arquivo;
    private ModeloPart modelo;
    private int vetorElementos[];
    private int solucao[];
    private int vetorAux[];
    
    ControllerPart()throws FileNotFoundException{
        tela = new TelaPart();
        arquivo = new DaoPart();
        modelo = new ModeloPart();
    }
    public void inicializaVetor() throws IOException{
        int tamanho=arquivo.leTamanhoVetor();
        this.vetorElementos = new int[tamanho];
    }
    public void start(){
        
        int op = 0;
        boolean fim = true;
    
        do {
        tela.exibeMenu();
        op = tela.pegaValor();
        
        switch(op){
            case 1:
                tela.exibeTitulo("Carregamento de arquivo texto");
                try{
                    this.inicializaVetor();
                    System.out.println("Tamanho do vetor : "+vetorElementos.length);
                    vetorElementos = arquivo.lerArquivo(vetorElementos.length);
                    tela.exibeMsg("Vetor lido .............|", true);
                    //tela.exibeVetor(vetorElementos);
                    modelo.ordenaVetorDesc(this.vetorElementos);
                    tela.exibeMsg("Agora vetor ordenado ...| ", true);
                    //tela.exibeVetor(vetorElementos);
                    arquivo.fechar();
                }catch(IOException e){}
                break;
            case 2:
                tela.exibeTitulo("Gerando solução inicial e aleatória");
                this.vetorAux = new int[vetorElementos.length];
                this.vetorAux = modelo.geraSolAleat(vetorElementos.length);
                //tela.exibeVetor(this.vetorAux);
                System.out.println("Fo : "+modelo.calculaFo(vetorElementos, vetorAux));
                this.solucao = this.vetorAux;
                tela.exibeMsg("Solução inicial armazenada", true);
                
            break;
            case 3:
                   tela.exibeTitulo("Gerando solução contrutiva");
                   this.vetorAux = modelo.particionaConstrucao(vetorElementos);
                   //tela.exibeVetor(this.vetorAux);
                   System.out.println("Fo = "+modelo.calculaFo(vetorElementos, vetorAux));
                   if(modelo.classificaSolucao(solucao, vetorAux, vetorElementos)){
                       this.solucao = this.vetorAux;
                       tela.exibeMsg("Houve melhora e troca", true);
                   }else 
                       tela.exibeMsg("Não houve melhora", true);
                   break;
            case 4:
                tela.exibeTitulo("Aplicando Heurística - Busca Local");
                this.vetorAux = this.solucao;
                modelo.vizinhoTroca(vetorAux, vetorElementos, solucao);
                //tela.exibeVetor(this.solucao);
                //System.out.println("Fo : "+modelo.calculaFo(vetorElementos, solucao));
            
            break;
            case 5:tela.exibeTitulo("Aplicando Metaeurística - ILS");break;
            case 0:fim=false;break;       
            default: tela.exibeTitulo("Opção inválida!");break;
        }
        }while(fim);
        this.finalizar();
    }
    
    public void finalizar(){
        System.out.println("\n::::: Programa Finalizado :::::\n");
    }
    
}
