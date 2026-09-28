package br.com.HoraDoRush.view;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Prateleira {
    
    // Atributos
    private Image sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/prateleira.png" );;
    
    // Construtor
    public Prateleira() {}
    
    /**
     * Método para desenhar a prateleira
     */
    public void desenhar( EngineFrame engine ) {
        
        Rectangle source = new Rectangle( 0, 0, sprite.getWidth(), sprite.getHeight() ); // qual a parte da imagem quer usar
        Rectangle dest = new Rectangle( 0, 0, 800, 800 ); // tamanho da hitbox (produto)
        engine.drawImage( sprite, source, dest );
        
    }
    
}
