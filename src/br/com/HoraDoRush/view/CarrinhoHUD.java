package br.com.HoraDoRush.view;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class CarrinhoHUD {
    
    // Atributos
    private int posX = 1175;
    private int posY = 650;
    private int largura = 250;
    private int altura = 250;
    private Image sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/basket2.png" );;
    
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
     * Método para desenhar a prateleira (nao eh o carrinho?)
     */
    public void desenhar( EngineFrame engine ) {
        
        Rectangle source = new Rectangle( 0, 0, sprite.getWidth(), sprite.getHeight() );
        Rectangle dest = new Rectangle( posX, posY, largura, altura );
        engine.drawImage(sprite, source, dest);
        
        /*
        engine.fillRectangle( posX, posY, largura, altura, Color.RED );
        // Debug / Teste
        engine.drawText( "CARRINHO", posX + largura/3, posY + altura/2, 20, Color.BLACK );
        */
    }
    
}
