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
    public int tamanho;

    ControllerPart() throws FileNotFoundException {
        tela = new TelaPart();
        modelo = new ModeloPart();
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
                    System.out.println("Fo : " + modelo.calculaFo(vetorElementos, vetorAux));
                    this.solucao = this.vetorAux;
                    tela.exibeMsg("Solução inicial armazenada", true);

                    break;
                case 3:
                    tela.exibeTitulo("Gerando solução contrutiva");
                    this.vetorAux = modelo.particionaConstrucao(vetorElementos);
                    System.out.println("Fo = " + modelo.calculaFo(vetorElementos, vetorAux));
                    if (modelo.classificaSolucao(solucao, vetorAux, vetorElementos)) {
                        this.solucao = this.vetorAux;
                        tela.exibeMsg("Houve melhora e troca", true);
                    } else {
                        tela.exibeMsg("Não houve melhora", true);
                    }
                    break;
                case 4:
                    tela.exibeTitulo("Aplicando Heurística - Busca Local");
                    this.vetorAux = this.solucao;
                    modelo.buscaLocal(vetorAux, vetorElementos, solucao);
                    break;
                case 5:
                    tela.exibeTitulo("Aplicando Metaeurística - ILS");
                    this.vetorAux = this.solucao;
                    modelo.ils(vetorElementos, solucao, 5);
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
