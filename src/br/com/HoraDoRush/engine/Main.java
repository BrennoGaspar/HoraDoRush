package br.com.HoraDoRush.engine;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Main extends EngineFrame {

    // declaração de variáveis
    private HoraDoRush jogo;
    
    // Construtor padrão do jogo
    public Main() {

        // cria a janela do jogo ou simulação
        super( 1500, 950, "Hora do Rush - Menu", 60, true );

    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void create() {
    }

    /**
     * Atualiza os objetos/contextos/variáveis do jogo ou simulação.
     * O parâmetro delta contém o tempo que passou entre o quadro
     * anterior e o quadro atual.
     */
    @Override
    public void update( double delta ) {
        
        if( isKeyPressed( KEY_ONE ) ) {
            jogo = new HoraDoRush( 1 );
            this.setVisible( false );
        } else if( isKeyPressed( KEY_TWO ) ) {
            jogo = new HoraDoRush( 2 );
            this.setVisible( false );
        } else if( isKeyPressed( KEY_THREE ) ) {
            jogo = new HoraDoRush( 3 );
            this.setVisible( false );
        }
        
    }

    /**
     * Desenha o estado dos objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void draw() {
        String texto = "Escolha a dificuldade do jogo!";
        drawText( texto, getScreenWidth()/2, getScreenHeight()/2, 20, BLACK);
    }

    public static void main( String[] args ) { new Main(); }
    
}