package br.com.HoraDoRush.view;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class CarrinhoHUD {
    
    // Atributos
    private int posX = 500;
    private int posY = 500;
    private int largura = 200;
    private int altura = 200;
    
    // Construtor
    public CarrinhoHUD() {}
    
    // Getters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
    
    /**
     * Método para desenhar a prateleira
     */
    public void desenhar( EngineFrame engine ) {
        
        engine.fillRectangle( posX, posY, largura, altura, Color.RED );
        // Debug / Teste
        engine.drawText( "CARRINHO", posX + largura/3, posY + altura/2, 20, Color.BLACK );
        
    }
    
}
