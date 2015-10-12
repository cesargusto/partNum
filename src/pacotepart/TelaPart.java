package pacotepart;

import java.util.Scanner;
/**
 *
 * @author cesar
 */
public class TelaPart {
    
public void exibeMenu(){
    String textoMenu = "*** Menu ***\n\n";
			
        textoMenu += "1 - Carregar arquivo.\n";
        textoMenu += "2 - Gerar solução inicial [Aleatória].\n";
        textoMenu += "3 - Gerar solução inicial [Construtiva].\n";
        textoMenu += "4 - Gerar solução inicial [Busca Local].\n";
        textoMenu += "5 - Aplicar MetaHeuristica [ILS].\n";
        textoMenu += "0 - Encerrar Aplicação.\n";

        System.out.println(textoMenu);
        System.out.print("Opção: ");
}    
public void exibeMsg(String msg, boolean quebra){
    if(quebra)
        System.out.println(msg);
    else
        System.out.print(msg);
}
public void exibeTitulo(String titulo){
    System.out.println("\n\n***** "+titulo+" ***** \n\n");
}
public int pegaValor(){
   Scanner entrada = new Scanner(System.in);
   int valor = entrada.nextInt();
   return valor;
}
    
}