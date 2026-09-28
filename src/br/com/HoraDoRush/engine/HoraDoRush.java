package br.com.HoraDoRush.engine;

import br.com.HoraDoRush.input.DragAndDrop;
import br.com.HoraDoRush.model.Carrinho;
import br.com.HoraDoRush.model.ListaCompras;
import br.com.HoraDoRush.model.Produtos;
import br.com.HoraDoRush.view.CarrinhoHUD;
import br.com.HoraDoRush.view.ListaComprasHUD;
import br.com.HoraDoRush.view.Prateleira;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class HoraDoRush extends EngineFrame {

    // declaração de variáveis
    private int tempoRestante;
    private Carrinho carrinho;
    private ListaCompras listaCompras;
    private Prateleira prateleiraHUD;
    private ListaComprasHUD listaHUD;
    private CarrinhoHUD carrinhoHUD;
    private Produtos[] produtosArray;
    private DragAndDrop dragAndDrop;
    
    // Construtor padrão do jogo
    public HoraDoRush( int dificuldade ) {
        
        // cria a janela do jogo ou simulação
        super( 1500, 950, obterTitulo(dificuldade), 180, true );
        
        if( dificuldade == 1 ) {
            tempoRestante = 5*3600; // 5 minutos
            gerarProdutos( 3 );
            listaCompras = new ListaCompras( 1 );
        } else if( dificuldade == 2 ) {
            tempoRestante = 4*3600; // 4 minutos
            gerarProdutos( 4 );
            listaCompras = new ListaCompras( 2 );
        } else if( dificuldade == 3 ) {
            tempoRestante = 3*3600; // 3 minutos
            gerarProdutos( 5 );
            listaCompras = new ListaCompras( 3 );
        }
        
        listaCompras.gerarLista( produtosArray );
        listaHUD = new ListaComprasHUD( listaCompras );

    }
    
    /**
     * Função para reconhecer o título da página do jogo, com a dificuldade.
     */
    private static String obterTitulo( int dificuldade ) {
        switch( dificuldade ){
            case 1:
                return "Hora do Rush - Fácil";
            case 2:
                return "Hora do Rush - Médio";
            case 3:
                return "Hora do Rush - Difícil";
            default:
                return "Hora do Rush";
        }
    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void create() {
        
        prateleiraHUD = new Prateleira();
        carrinhoHUD = new CarrinhoHUD();
        dragAndDrop = new DragAndDrop();
        carrinho = new Carrinho();
        produtosArray = new Produtos[0];
        
    }

    /**
     * Atualiza os objetos/contextos/variáveis do jogo ou simulação.
     * O parâmetro delta contém o tempo que passou entre o quadro
     * anterior e o quadro atual.
     */
    @Override
    public void update( double delta ) {
        
        // Diminuir o tempo restante a cada segundo
        if( tempoRestante > 0 ) {
            tempoRestante -= delta;
        }
        
        // Sistema para arrastar (mover) os produtos
        if( isMouseButtonPressed(EngineFrame.MOUSE_BUTTON_LEFT) && dragAndDrop.getArrastando() == null ) {
            for( int i = produtosArray.length - 1; i >= 0; i-- ) {
                Produtos p = produtosArray[i];
                int inicioX = p.getPosX();
                int inicioY = p.getPosY();
                int fimX = p.getPosX() + p.getLargura();
                int fimY = p.getPosY() + p.getAltura();

                if( getMouseX() >= inicioX && getMouseX() <= fimX && getMouseY() >= inicioY && getMouseY() <= fimY ) {
                    p.trazerParaFrente( i, produtosArray );
                    dragAndDrop.iniciarArrasto( p, getMouseX(), getMouseY() );
                    break; // para o for quando seleciona o produto do topo
                }
            }
        }
        dragAndDrop.arrastar( this, carrinhoHUD, carrinho, listaCompras, produtosArray );
        
        // Sistema de vitória - TODO
        if( listaCompras.getFila().isEmpty() && carrinho.getPilha().size() == listaCompras.getCopia().size() ) {
            System.out.println("vitoria");
        }
        
    }

    /**
     * Desenha o estado dos objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void draw() {
        
        // Desenhar a prateleira
        prateleiraHUD.desenhar( this );
        listaHUD.desenhar( this );
        carrinhoHUD.desenhar( this );
        
        // Desenhar os textos na tela para testes / debug
        int minutos = tempoRestante / 3600;
        int segundos = (tempoRestante % 3600) / 60;
        String tempoLabel = String.format( "Tempo restante: %02d:%02d", minutos, segundos );
        drawText( tempoLabel, getScreenWidth()/2 - 50, getScreenHeight()/2, 20, BLACK );
        
        // Debug / teste
        String listaLabel = String.format( "Itens na lista: %d", listaCompras.getTamanho() );;
        String temLabel = String.format( "Já tem: %d itens no carrinho", carrinho.verificarItensNoCarrinho() );
        
        // Debug / teste
        drawText( listaLabel, getScreenWidth()/2 - 50, getScreenHeight()/2 + 40, 20, BLACK );
        drawText( temLabel, getScreenWidth()/2 - 50, getScreenHeight()/2 + 60, 20, BLACK );
        
        for( Produtos p : produtosArray ) {
            p.desenhar( this );
        }
            
    }
    
    /**
     * Método para gerar o array com todos os produtos
     */
    private void gerarProdutos( int numero ) {
        produtosArray = new Produtos[numero];
        for( int i = 0; i < numero; i++ ) {
            produtosArray[i] = new Produtos();
        }
    }
    
}