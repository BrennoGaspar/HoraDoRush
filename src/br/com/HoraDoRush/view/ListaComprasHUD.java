package br.com.HoraDoRush.view;

import br.com.HoraDoRush.model.ListaCompras;
import br.com.HoraDoRush.model.Produtos;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class ListaComprasHUD {
    
    // Atributos
    private ListaCompras listaCompras;
    private Image sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/notes.png" );;
    
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
       
        // Rascunho de Implementação de sprite
        /*
        Rectangle source = new Rectangle( 0, 0, sprite.getWidth(), sprite.getHeight() );
        Rectangle dest = new Rectangle( larguraTela - 350,alturaTela / 2 - 400, 300, 500 );
        engine.drawImage(sprite, source, dest);
        */

        engine.fillRectangle( larguraTela - 350, alturaTela / 2 - 400, 300, 500, Color.YELLOW );
        engine.drawText( "Lista de compras", larguraTela - 300, alturaTela / 2 - 380, 20, Color.BLACK );
        
        int espacamento = 0;
        for( Produtos p : listaCompras.getFila() ) {
            String nomeProduto = p.getNome();
            engine.drawText( nomeProduto, larguraTela - 300, alturaTela / 2 - 330 + (30*espacamento++), 20, Color.BLACK );
        }
        
    }
    
}
