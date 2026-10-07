package br.com.HoraDoRush.engine;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Main extends EngineFrame {

    // declaração de variáveis
    private HoraDoRush jogo;
    
    // Configurações de layout dos botões
    private int btnLargura = 250;
    private int btnAltura = 60;
    private int espacamento = 30;
    
    // Calcula a posição dos botões
    private int btnX = getScreenWidth() / 2 - (btnLargura / 2);
    private int btnYFacil = getScreenHeight() / 2 - 20;
    private int btnYMedio = btnYFacil + btnAltura + espacamento;
    private int btnYDificil = btnYMedio + btnAltura + espacamento;

    // Construtor padrão do jogo
    public Main() {
        super( 1500, 950, "Hora do Rush - Menu", 60, true );
    }

    @Override
    public void create() {}

    @Override
    public void update( double delta ) {

        // Posição do mouse
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        boolean clicou = isMouseButtonPressed( MOUSE_BUTTON_LEFT );

        // Seleção via botões
        if ( isMouseOver(mouseX, mouseY, btnX, btnYFacil, btnLargura, btnAltura) && clicou ) {
            iniciarJogo(1);
        } else if ( isMouseOver(mouseX, mouseY, btnX, btnYMedio, btnLargura, btnAltura) && clicou ) {
            iniciarJogo(2);
        } else if ( isMouseOver(mouseX, mouseY, btnX, btnYDificil, btnLargura, btnAltura) && clicou ) {
            iniciarJogo(3);
        }
        
        // Seleção via teclado
        if( isKeyPressed( KEY_ONE ) || isKeyPressed( KEY_KP_1 ) ) {
            iniciarJogo(1);
        } else if( isKeyPressed( KEY_TWO ) || isKeyPressed( KEY_KP_2 ) ) {
            iniciarJogo(2);
        } else if( isKeyPressed( KEY_THREE ) || isKeyPressed( KEY_KP_3 ) ) {
            iniciarJogo(3);
        }
        
    }

    @Override
    public void draw() {
        
        // Limpa o fundo
        clearBackground( Color.WHITE );

        // Títulos
        String titulo = "HORA DO RUSH";
        drawText( titulo, getScreenWidth() / 2 - 190, getScreenHeight() / 2 - 200, 60, BLACK );
        
        String subtitulo = "Selecione a Dificuldade";
        drawText( subtitulo, getScreenWidth() / 2 - 110, getScreenHeight() / 2 - 100, 20, DARKGRAY );

        // Posição atual do mouse para o efeito Hover
        int mx = getMouseX();
        int my = getMouseY();

        // Desenha os três botões baseados nas posições dinâmicas
        desenharBotao( "1. Fácil", btnX, btnYFacil, btnLargura, btnAltura, mx, my, new Color(46, 204, 113) );
        desenharBotao( "2. Médio", btnX, btnYMedio, btnLargura, btnAltura, mx, my, new Color(241, 196, 15) );
        desenharBotao( "3. Difícil", btnX, btnYDificil, btnLargura, btnAltura, mx, my, new Color(231, 76, 60) );
        
        // Nome dos autores do projeto
        drawText( "Desenvolvido por Brenno Gaspar & Victor Altran", 20, getScreenHeight() - 40, 16, GRAY );
        
    }

    /**
     * Método auxiliar para desenhar um botão com efeito de Hover (passar o mouse).
     */
    private void desenharBotao( String texto, int x, int y, int larg, int alt, int mx, int my, Color corBase ) {
        boolean hover = isMouseOver( mx, my, x, y, larg, alt );
        
        Color corAtual = hover ? corBase.darker() : corBase;
        
        fillRectangle( x, y, larg, alt, corAtual );
        drawRectangle( x, y, larg, alt, BLACK );
        
        int paddingX = ( larg - (texto.length() * 12) ) / 2; 
        int paddingY = ( alt - 20 ) / 2;
        
        drawText( texto, x + paddingX, y + paddingY, 20, WHITE );
    }

    /**
     * Método auxiliar para verificar colisão do mouse.
     */
    private boolean isMouseOver( int mouseX, int mouseY, int x, int y, int largura, int altura ) {
        return mouseX >= x && mouseX <= (x + largura) && mouseY >= y && mouseY <= (y + altura);
    }
    
    /**
     * Centraliza a lógica de iniciar o jogo.
     */
    private void iniciarJogo( int dificuldade ) {
        jogo = new HoraDoRush( dificuldade );
        this.setVisible( false );
    }

    public static void main( String[] args ) { new Main(); }
    
}