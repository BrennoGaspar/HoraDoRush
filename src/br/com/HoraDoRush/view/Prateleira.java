package br.com.HoraDoRush.view;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Prateleira {
    
    // Construtor
    public Prateleira() {}
    
    /**
     * Método para desenhar a prateleira
     */
    public void desenhar( EngineFrame engine ) {
        
        engine.fillCircle( 100, 100, 100, Color.BLACK );
        
    }
    
}
