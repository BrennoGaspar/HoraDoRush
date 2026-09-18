package br.com.HoraDoRush.view;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class ListaComprasHUD {
    
    // Construtor
    public ListaComprasHUD() {}
    
    /**
     * Método para desenhar a Lista de Compras
     */
    public void desenhar( EngineFrame engine ) {
        
        engine.fillCircle( 300, 300, 100, Color.PINK );
        
    }
    
}
