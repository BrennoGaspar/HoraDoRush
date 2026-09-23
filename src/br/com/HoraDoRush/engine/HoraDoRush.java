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
    
    // Debug / teste
    private int itensCorretos;
    private DragAndDrop arrastar;
    
    // Construtor padrão do jogo
    public HoraDoRush( int dificuldade ) {
        
        // cria a janela do jogo ou simulação
        super( 1500, 950, obterTitulo(dificuldade), 60, true );
        
        if( dificuldade == 1 ) {
            tempoRestante = 5*3600; // 5 minutos
            gerarProdutos( 3 );
            listaCompras = new ListaCompras( 1 );
            carrinho.setItensFaltando( 1 );
        } else if( dificuldade == 2 ) {
            tempoRestante = 4*3600; // 4 minutos
            gerarProdutos( 4 );
            listaCompras = new ListaCompras( 2 );
            carrinho.setItensFaltando( 2 );
        } else if( dificuldade == 3 ) {
            tempoRestante = 3*3600; // 3 minutos
            gerarProdutos( 5 );
            listaCompras = new ListaCompras( 3 );
            carrinho.setItensFaltando( 3 );
        }
        
        listaCompras.gerarLista( produtosArray );

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
        listaHUD = new ListaComprasHUD();
        carrinhoHUD = new CarrinhoHUD();
        arrastar = new DragAndDrop();
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
        
        for( Produtos p : produtosArray ) {
            int inicioX = p.getPosX();
            int inicioY = p.getPosY();
            int fimX = p.getPosX() + p.getLargura();
            int fimY = p.getPosY() + p.getAltura();

            if( getMouseX() >= inicioX && getMouseX() <= fimX && getMouseY() >= inicioY && getMouseY() <= fimY ) {
                arrastar.arrastar( p, this, carrinhoHUD, carrinho, listaCompras );
            }
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
        drawText( tempoLabel, getScreenWidth()/2, getScreenHeight()/2, 20, BLACK );
        
        // Debug / teste
        String listaLabel = String.format( "Itens na lista: %d", listaCompras.getTamanho() );;
        String temLabel = String.format( "Já tem: %d itens no carrinho", carrinho.getPilha().size() );
        String temCorretosLabel = String.format( "Já tem: %d itens corretos no carrinho", itensCorretos );
        String faltamLabel = String.format( "Faltam: %d itens", carrinho.getItensFaltando() );
        
        // Debug / teste
        drawText( listaLabel, getScreenWidth()/2, getScreenHeight()/2 + 40, 20, BLACK );
        drawText( temLabel, getScreenWidth()/2, getScreenHeight()/2 + 60, 20, BLACK );
        drawText( temCorretosLabel, getScreenWidth()/2, getScreenHeight()/2 + 80, 20, BLACK );
        drawText( faltamLabel, getScreenWidth()/2, getScreenHeight()/2 + 100, 20, BLACK );

        for( Produtos p : produtosArray ) {
            p.desenhar( this );
        }
            
    }
    
    private void gerarProdutos( int numero ) {
        produtosArray = new Produtos[numero];
        for( int i = 0; i < numero; i++ ) {
            produtosArray[i] = new Produtos( 10 + 100*i, 10 + 100*i, 100, 100 );
        }
    }

    
}