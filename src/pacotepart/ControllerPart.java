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
    
    ControllerPart()throws FileNotFoundException{
        tela = new TelaPart();
        arquivo = new DaoPart();
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
                   
                    tela.exibeVetor(arquivo.lerArquivo());
                    System.out.println("Numero de elementos : "+arquivo.tamanho);
                    
                }catch(IOException e){}
                break;
            case 2:tela.exibeTitulo("Gerando solução aleatória");break;
            case 3:tela.exibeTitulo("Gerando solução contrutiva");break;
            case 4:tela.exibeTitulo("Aplicando Heurística - Busca Local");break;
            case 5:tela.exibeTitulo("Aplicando Metaeurística - ILS");break;
            case 0:fim=false;break;       
            default: tela.exibeTitulo("Opção inválida!");break;
        }
        }while(fim);
        this.finalizar();
    }
    
    public void finalizar(){
        System.out.println("\n\n::::: Programa Finalizado :::::\n\n\n");
    }
    
}
