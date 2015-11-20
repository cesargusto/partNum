package pacotepart;

import java.util.Arrays;
import java.util.Random;

public class Ils {

    public Random rand;
    public ModeloPart modelo;
    
    Ils(){
        modelo = new ModeloPart();
        rand = new Random();
    }
    
    int[]ils_func(int[]vetor, int[]solIni, int max){
        
        int[]vetoraux = new int[solIni.length];
        int[]s1 = new int[solIni.length];
        int[]s2 = new int[solIni.length];
        int[]s3 = new int[solIni.length];
        int fo_inicial = 0;
        int fo_final = 0;
        int nivel = 1;
        int n3 = 0;
        
        vetoraux = Arrays.copyOf(solIni, solIni.length);
        s1 = modelo.bLocal(vetor, vetoraux);
        fo_inicial = modelo.calculaFo(vetor, s1);
        if(fo_inicial > 1){
        while(max > 0){
            s2 = this.pertubacao(s1, nivel);
            s3 = modelo.bLocal(vetor, s2);
            fo_final = modelo.calculaFo(vetor, s3);
                if(fo_final < fo_inicial){
                    s1 = Arrays.copyOf(s3, s3.length);
                    fo_inicial = fo_final;
                    if(fo_final <= 1)
                        max = 0;
                    nivel = 1;
                }else{
                    if (nivel == 1)
                        nivel = 2;
                    else if ((nivel == 2)&&(n3 < 3)){   
                        nivel = 3;
                        n3 ++;
                        }else 
                            nivel = 1;
                    }
                max --;
            }
        }
        else{
            s1 = Arrays.copyOf(vetoraux, vetoraux.length);
        }
            
        return s1;
    }
     
    int[]pertubacao(int[]solIni, int nivel){
        
        int[]sol = new int[solIni.length];
        sol = Arrays.copyOf(solIni, solIni.length);
        
        int p1, p2, aux = 0;
        
        p1 = rand.nextInt(sol.length);
        p2 = rand.nextInt(sol.length);
        
        switch(nivel){
            
        case 1: //INVERTE OS BITS DE P1 E P2 ... VERIFICAR DEPOIS
            if(sol[p1]== 0)
                sol[p1] = 1;
            else 
                sol[p1] = 0;

            if((p1 == sol.length - 1)&&(p2 == sol.length - 1))
                p2 = p2 - 1; //VER ISSO DEPOIS
            else 
                if(p1 == p2)
                p2 = p2 + 1;

            if(sol[p2]== 0)
                sol[p2] = 1;
            else 
                sol[p2] = 0;
        break;
        case 2: //TROCA OS BITS DE P1 E P2
            if(p1 != sol.length - 1){ // SE Ñ FOR O ULTIMO FAZ ISSO ...
                if(sol[p1] != sol[p1 + 1]){
                    aux = sol[p1];
                    sol[p1] = sol[p1 + 1];
                    sol[p1 + 1] = aux;
                }else{ //INVERTE OS DOIS BITS
                    if(sol[p1] == 0){
                        sol[p1] = 1;
                        sol[p1 + 1] = 1;}
                    else {
                        sol[p1] = 0;
                        sol[p1 + 1] = 0;
                    }                     
                }
            }
            else { //SE FOR O ÚLTIMO FAZ ISSO ...
                if(sol[p1] != sol[p1 - 1]){
                    aux = sol[p1];
                    sol[p1] = sol[p1 - 1];
                    sol[p1 - 1] = aux;
                }
                else{ //INVERTE OS DOIS BITS
                    if(sol[p1] == 0){
                        sol[p1] = 1;
                        sol[p1 - 1] = 1;
                    }
                    else {
                        sol[p1] = 0;
                        sol[p1 - 1] = 0;
                    }
                }
            }
            //AGORA COM O P2
            if(p2 != sol.length - 1){ //SE P2 Ñ FOR O ULTIMO FAZ ...
                if((p2 == p1)&&(p2 != 0))
                    p2 = p2 - 1;
                    
                if(sol[p2] != sol[p2 + 1]){ // SE FOREM DIFERENTES TROCA
                    aux = sol[p2];
                    sol[p2] = sol[p2 + 1];
                    sol[p2 + 1] = aux;                
                }else{ // SE FOREM IGUAIS INVERTE OS 2 BITS
                    if(sol[p2] == 0){
                        sol[p2] = 1;
                        sol[p2 + 1] = 1;}
                    else {
                        sol[p2] = 0;
                        sol[p2 + 1] = 0;
                    } 
                }
            }else{ //SE P2 FOR O ÚLTIMO FAZ ...
                if(sol[p2] != sol[p2 - 1]){
                    aux = sol[p2];
                    sol[p1] = sol[p2 - 1];
                    sol[p2 - 1] = aux;
                }
                else{ //INVERTE OS DOIS BITS
                    if(sol[p2] == 0){
                        sol[p2] = 1;
                        sol[p2 - 1] = 1;
                    }
                    else {
                        sol[p2] = 0;
                        sol[p2 - 1] = 0;
                    }
                }
            }
        break;
        case 3: //INVERTE P1 E TROCA P2
            //APENAS INVERTE O VALOR DO PRIMEIRO BIT SORTEADO
            if(sol[p1] == 0)
                sol[p1] = 1;
            else 
                sol[p1] = 0;
            //SE O 2º BIT SORTEADO FOR O ÚLTIMO, TROCA COM O ANTERIOR SE NÃO FOR IGUAL
            //SE FOR IGUAL INVERTE O VALOR DO BIT DAS 2 POSIÇÕES
            if(p2 == sol.length -1){
                if(sol[p2] != sol[p2 -1]){
                    aux = sol[p2];
                    sol[p2] = sol[p2 - 1];
                    sol[p2 - 1] = aux;
                }else{
                    if(sol[p1] == 0){
                        sol[p1] = 1;
                        sol[p1 - 1] = 1;}
                    else {
                        sol[p1] = 0;
                        sol[p1 - 1] = 0;
                    }                    
                }
            }
            //SE O ELEMENTO O 2º ELEMENTO SORTEADO NÃO FOR O ÚLTIMO, FAZ O MESMO COM O POSTERIOR
            //TESTE SE O PRÓXIMO NÃO É IGUAL, SE NÃO FOR TROCA, SE FOR INVERTE OS 2 VALORES DE BIT
            else 
                if(sol[p2] != sol[p2 + 1]){
                    aux = sol[p2];
                    sol[p2] = sol[p2 + 1];
                    sol[p2 + 1] = aux;
                }else{
                    if(sol[p2] == 0){
                        sol[p2] = 1;
                        sol[p2 + 1] = 1;}
                    else {
                        sol[p2] = 0;
                        sol[p2 + 1] = 0;
                    }
                }
            
        break;
        }    
        return sol;
    }
}
