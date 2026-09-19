package br.com.HoraDoRush.input;

import br.com.HoraDoRush.model.Produtos;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class DragAndDrop {
    
    // Atributos
    private boolean isArrastando = false;
    private int diferencaX, diferencaY;
    
    // Construtor
    public DragAndDrop() {}
    
    public void arrastar( Produtos produto, EngineFrame engine ) {
        
        int mouseX = engine.getMouseX();
        int mouseY = engine.getMouseY();
        boolean segurando = engine.isMouseButtonDown( EngineFrame.MOUSE_BUTTON_LEFT );
        
        if( segurando && !isArrastando ) {
            
            int inicioX = produto.getPosX();
            int inicioY = produto.getPosY();
            int fimX = produto.getPosX() + produto.getLargura();
            int fimY = produto.getPosY() + produto.getAltura();
            
            if( mouseX >= inicioX && mouseX <= fimX && mouseY >= inicioY && mouseY <= fimY ) {
                isArrastando = true;
                diferencaX = mouseX - inicioX;
                diferencaY = mouseY - inicioY;
            }
            
        }
        
        if( isArrastando ) {
            
            if( segurando ) {
                produto.setPosX( mouseX - diferencaX );
                produto.setPosY( mouseY - diferencaY );
            } else {
                isArrastando = false;
            }
            
        }
        
    }
    
}
