package br.com.HoraDoRush.engine;

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
    private int dificuldade;
    private Carrinho carrinho;
    private ListaCompras listaCompras;
    private Prateleira prateleiraHUD;
    private ListaComprasHUD listaHUD;
    private CarrinhoHUD carrinhoHUD;
    
    // Debug / teste
    private Produtos temp_produto;
    private int itensCorretos;
    private int faltamItens;
    
    // Construtor padrão do jogo
    public HoraDoRush( int dificuldade ) {
        
        // cria a janela do jogo ou simulação
        super( 1500, 950, obterTitulo(dificuldade), 60, true );
        
        this.dificuldade = dificuldade;
        
        if( dificuldade == 1 ) {
            tempoRestante = 5*3600; // 5 minutos
            listaCompras = new ListaCompras( 5 ); // a lista tem 5 itens
            faltamItens = 5;
        } else if( dificuldade == 2 ) {
            tempoRestante = 4*3600; // 4 minutos
            listaCompras = new ListaCompras( 6 ); // a lista tem 6 itens
            faltamItens = 6;
        } else if( dificuldade == 3 ) {
            tempoRestante = 3*3600; // 3 minutos
            listaCompras = new ListaCompras( 7 ); // a lista tem 7 itens
            faltamItens = 7;
        }
        carrinho = new Carrinho();        

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
        
        // Debug / teste
        // Cria objeto "Banana" e coloca em primeiro lugar da lista de compras
        temp_produto = new Produtos( 10, 10, 100, 100 );
        System.out.println( "Produto criado: " + temp_produto.getNome() );
        
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
        
        // Debug / teste
        // Comando para debug da quantidade de itens no carrinho
//        if( isKeyPressed(KEY_H) ) {
//            carrinho.getPilha().add( temp_banana );
//            Produtos t = listaCompras.getFila().peek();
//            if( t.equals(temp_banana) ) {
//                itensCorretos++;
//                faltamItens--;
//                System.out.println( "Item retirado da lista: " + listaCompras.getFila().poll().getNome() );
//            } else {
//                System.out.println( t.getNome() + " != " + temp_banana.getNome() );
//            }
//        }
//        if( isKeyPressed(KEY_J) ) {
//            carrinho.getPilha().add( temp_morango );
//            Produtos t = listaCompras.getFila().peek();
//            if( t.equals(temp_morango) ) {
//                itensCorretos++;
//                faltamItens--;
//                System.out.println( "Item retirado da lista: " + listaCompras.getFila().poll().getNome() );
//            } else {
//                System.out.println( t.getNome() + " != " + temp_morango.getNome() );
//            }
//        }
        
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
        String faltamLabel = String.format( "Faltam: %d itens", faltamItens );
        
        // Debug / teste
        
        drawText( listaLabel, getScreenWidth()/2, getScreenHeight()/2 + 40, 20, BLACK );
        drawText( temLabel, getScreenWidth()/2, getScreenHeight()/2 + 60, 20, BLACK );
        drawText( temCorretosLabel, getScreenWidth()/2, getScreenHeight()/2 + 80, 20, BLACK );
        drawText( faltamLabel, getScreenWidth()/2, getScreenHeight()/2 + 100, 20, BLACK );

        
        temp_produto.desenhar( this );
    
    }
    
}