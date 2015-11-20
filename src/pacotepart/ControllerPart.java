package pacotepart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author cesar
 */
public class ControllerPart {

    private final TelaPart tela;
    private DaoPart arquivo;
    private ModeloPart modelo;
    private Ils ils;
    private int vetorElementos[];
    private int solucao[];
    public int vetorAux[];
    public int tamanho;

    ControllerPart() throws FileNotFoundException {
        tela = new TelaPart();
        modelo = new ModeloPart();
        ils = new Ils();
        
    }

    public void inicializaVetor() throws IOException {
        tamanho = arquivo.leTamanhoVetor();
        this.vetorElementos = new int[tamanho];
    }

    public void start() throws FileNotFoundException {

        int op = 0;
        boolean fim = true;
        String texto = "";

        do {
            tela.exibeMenu();
            op = tela.pegaValorInt();

            switch (op) {
                case 1:
                    tela.exibeTitulo("Carregamento de arquivo texto");
                    tela.exibeMsg("Digite o nome do arquivo : ", false);
                    texto = tela.pegaValorString();
                    arquivo = new DaoPart(texto);
                    try {
                        this.inicializaVetor();
                        System.out.println("Tamanho do vetor : " + this.tamanho);
                        vetorElementos = arquivo.lerArquivo(this.tamanho);
                        tela.exibeMsg("Vetor lido .............|", true);
                        modelo.ordenaVetorDesc(this.vetorElementos);
                        tela.exibeMsg("Agora vetor ordenado ...| ", true);
                        arquivo.fechar();
                    } catch (IOException e) {
                        System.out.println("Arquivo inválido!");
                    }
                    break;
                case 2:
                    tela.exibeTitulo("Gerando solução inicial e aleatória");
                    this.vetorAux = new int[this.tamanho];
                    this.vetorAux = modelo.randomico(this.tamanho);
                    int fo_randomico;
                    fo_randomico = modelo.calculaFo(vetorElementos, vetorAux);
                    System.out.println("Fo randômico: " + fo_randomico);
                    this.solucao = this.vetorAux;
                    modelo.melhorSolAtual = this.solucao;
                    modelo.melhorFoAtual = fo_randomico;
                    tela.exibeMsg("Solução inicial armazenada", true);
                    break;
                case 3:
                    tela.exibeTitulo("Gerando solução contrutiva");
                    this.vetorAux = modelo.particionaConstrucao(vetorElementos);
                    int fo_construtivo;
                    fo_construtivo = modelo.calculaFo(vetorElementos, vetorAux);
                    System.out.println("Fo construtivo = " +fo_construtivo);
                    if(fo_construtivo < modelo.melhorFoAtual){
                        modelo.melhorFoAtual = fo_construtivo;
                        modelo.melhorSolAtual = vetorAux;
                        this.solucao = vetorAux;
                        if(fo_construtivo <=1)
                            tela.exibeMsg("Solução ótima encontrada!", true);
                        else
                            tela.exibeMsg("Houve melhora e substituições", true);
                    }
                    else 
                        tela.exibeMsg("Não houve melhora", true);
                    break;
                case 4:
                    tela.exibeTitulo("Aplicando Heurística - Busca Local");
                    this.vetorAux = modelo.melhorSolAtual;
                    int[]vetorTemp = new int[solucao.length];
                    vetorTemp = modelo.bLocal(vetorElementos, vetorAux);
                    int aux = modelo.calculaFo(vetorElementos, vetorTemp);
                    System.out.println("Fo do melhor local : "+aux);
                    modelo.passaSolucao(vetorElementos, vetorTemp, solucao);
                    this.solucao = Arrays.copyOf(modelo.melhorSolAtual, tamanho);
                    if(aux <= 1)
                            tela.exibeMsg("Solução ótima encontrada!", true);
                        else
                            tela.exibeMsg("Houve melhora e substituições", true);
                    break;
                case 5:
                    tela.exibeTitulo("Aplicando Metaeurística - ILS");
                    int iterMax = 300;
                    this.vetorAux = ils.ils_func(vetorElementos, solucao, iterMax);
                    int f_o = modelo.calculaFo(vetorElementos, vetorAux);
                    System.out.println("Máximo de Iterações .... "+iterMax);
                    System.out.println("Fo ils ................. "+f_o);
                    break;
                case 0:
                    fim = false;
                    break;
                default:
                    tela.exibeTitulo("Opção inválida!");
                    break;
            }
        } while (fim);
        this.finalizar();
    }

    public void finalizar() {
        System.out.println("\n::::: Programa Finalizado :::::\n");
    }

}
