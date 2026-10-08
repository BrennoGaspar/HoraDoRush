package br.com.HoraDoRush.engine;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class HowToPlay extends EngineFrame {

    private Image menuBackground = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/mainMenuBackgorund.png" );
    
    // Configurações do botão voltar
    private int btnLargura = 250;
    private int btnAltura = 60;
    private int btnX;
    private int btnY;

    public HowToPlay() {
        
        super( 1500, 950, "Hora do Rush - Como Jogar", 60, true );
        
        // centralizar o botão
        this.btnX = getScreenWidth() / 2 - (btnLargura / 2);
        this.btnY = getScreenHeight() - 150;
        
    }

    @Override
    public void create() {}

    @Override
    public void update( double delta ) {
        
        // Posição do mouse
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        boolean clicou = isMouseButtonPressed( MOUSE_BUTTON_LEFT );

        // Verifica clique no botão
        if ( isMouseOver(mouseX, mouseY, btnX, btnY, btnLargura, btnAltura) && clicou ) {
            new Main();
            this.setVisible( false );
        }
        
    }

    @Override
    public void draw() {
        
        // Desenha o fundo
        if( menuBackground != null ) {
            Rectangle sourceI = new Rectangle( 0, 0, menuBackground.getWidth(), menuBackground.getHeight() );
            Rectangle destI = new Rectangle( 0, 0, getScreenWidth(), getScreenHeight() );
            drawImage(menuBackground, sourceI, destI);
        }

        int larguraPainel = 1080;
        int alturaPainel = 550;
        int painelX = getScreenWidth() / 2 - (larguraPainel / 2);
        int painelY = getScreenHeight() / 2 - (alturaPainel / 2) - 40;

        // Desenha um fundo opaco (facilitar leitura)
        Color painelFundo = new Color( 0, 0, 0, 180 );
        fillRectangle( painelX, painelY, larguraPainel, alturaPainel, painelFundo );
        drawRectangle( painelX, painelY, larguraPainel, alturaPainel, WHITE );

        // Posições base para as instruções
        int textoX = painelX + 50;
        int textoY = painelY + 140;
        int espacamento = 50;
        
        // Título
        drawText( "COMO JOGAR", getScreenWidth() / 2 - 120, painelY + 50, 40, WHITE );

        // Linhas de instrução baseadas nas mecânicas de Drag and Drop e Fila de Compras
        drawText( "1. O seu objetivo é colocar os produtos no carrinho o mais rápido possível.", textoX, textoY, 22, WHITE );
        drawText( "2. Utilize o MOUSE para CLICAR e ARRASTAR os produtos.", textoX, textoY += espacamento, 22, WHITE );
        drawText( "3. Solte o produto no carrinho para confirmá-lo.", textoX, textoY += espacamento, 22, WHITE );
        drawText( "4. Os produtos devem ser colocados na ordem correta da \"fila de compras\".", textoX, textoY += espacamento, 22, WHITE );
        drawText( "5. Ao acertar, o produto será riscado automaticamente da sua lista.", textoX, textoY += espacamento, 22, WHITE );
        drawText( "6. A dificuldade escolhida afeta a velocidade e a quantidade de itens.", textoX, textoY += espacamento, 22, WHITE );
        
        drawText( "DICA: O tempo é o seu maior inimigo na Hora do Rush. Seja ágil!", textoX, textoY += (espacamento + 30), 22, new Color(241, 196, 15) );

        // Posição atual do mouse
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        Color corBotao = new Color(231, 76, 60);
        
        desenharBotao( "Voltar", btnX, btnY, btnLargura, btnAltura, mouseX, mouseY, corBotao );
        
    }

    /**
     * Método auxiliar para desenhar o efeito hover do botão
     */
    private void desenharBotao( String texto, int x, int y, int larg, int alt, int mx, int my, Color corBase ) {
        
        boolean hover = isMouseOver( mx, my, x, y, larg, alt );
        Color corAtual = hover ? corBase.darker() : corBase;
        int paddingX = ( larg - (texto.length() * 12) ) / 2; 
        int paddingY = ( alt - 20 ) / 2;
        
        fillRectangle( x, y, larg, alt, corAtual );
        drawRectangle( x, y, larg, alt, BLACK );
        
        drawText( texto, x + paddingX, y + paddingY, 20, WHITE );
        
    }

    /**
     * Método auxiliar para verificar colisão do mouse
     */
    private boolean isMouseOver( int mouseX, int mouseY, int x, int y, int largura, int altura ) {
        return mouseX >= x && mouseX <= (x + largura) && mouseY >= y && mouseY <= (y + altura);
    }
    
}