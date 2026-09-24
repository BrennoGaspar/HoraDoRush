package br.com.HoraDoRush.view;

import br.com.HoraDoRush.model.ListaCompras;
import br.com.HoraDoRush.model.Produtos;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class ListaComprasHUD {
    
    // Atributos
    private ListaCompras listaCompras;
    
    // Construtor
    public ListaComprasHUD( ListaCompras listaCompras ) {
        this.listaCompras = listaCompras;
    }
    
    /**
     * Método para desenhar a Lista de Compras
     */
    public void desenhar( EngineFrame engine ) {
        
        int larguraTela = engine.getScreenWidth();
        int alturaTela = engine.getScreenHeight();
        
        engine.fillRectangle( larguraTela - 350, alturaTela / 2 - 250, 300, 500, Color.GRAY );
        engine.drawText( "Lista de compras", larguraTela - 300, alturaTela / 2 - 200, 20, Color.BLACK );
        
        int espacamento = 0;
        for( Produtos p : listaCompras.getFila() ) {
            String nomeProduto = p.getNome();
            engine.drawText( nomeProduto, larguraTela - 300, alturaTela / 2 - 150 + (30*espacamento++), 20, Color.BLACK );
        }
        
    }
    
}
