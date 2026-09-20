package br.com.HoraDoRush.input;

import br.com.HoraDoRush.model.Carrinho;
import br.com.HoraDoRush.model.Produtos;
import br.com.HoraDoRush.view.CarrinhoHUD;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class DragAndDrop {
    
    // Atributos
    private boolean isArrastando = false;
    private int diferencaX, diferencaY;
    private int controlador = 0;
    
    // Construtor
    public DragAndDrop() {}
    
    public void arrastar( Produtos produto, EngineFrame engine, CarrinhoHUD carrinhoHUD, Carrinho carrinho ) {
        
        int mouseX = engine.getMouseX();
        int mouseY = engine.getMouseY();
        boolean segurando = engine.isMouseButtonDown( EngineFrame.MOUSE_BUTTON_LEFT );
        
        int inicioX = produto.getPosX();
        int inicioY = produto.getPosY();
        int fimX = produto.getPosX() + produto.getLargura();
        int fimY = produto.getPosY() + produto.getAltura();
            
        if( segurando && !isArrastando ) {
            
            if( mouseX >= inicioX && mouseX <= fimX && mouseY >= inicioY && mouseY <= fimY ) {
                isArrastando = true;
                diferencaX = mouseX - inicioX;
                diferencaY = mouseY - inicioY;
            }
            
        }
        
        if( isArrastando ) {
            
            controlador = 1;
            if( segurando ) {
                int novaPosX = mouseX - diferencaX; // pega o comeco da posicao X do produto
                int novaPosY = mouseY - diferencaY; // pega o comeco da posicao Y do produto
                
                if( novaPosX < 0 ) {
                    novaPosX = 0;
                } else if( novaPosX + produto.getLargura() > engine.getScreenWidth() ) {
                    novaPosX = engine.getScreenWidth() - produto.getLargura();
                }
                
                if( novaPosY < 0 ) {
                    novaPosY = 0;
                } else if( novaPosY + produto.getAltura()> engine.getScreenHeight()) {
                    novaPosY = engine.getScreenHeight() - produto.getAltura();
                }
                
                produto.setPosX( novaPosX );
                produto.setPosY( novaPosY );
            } else {
                isArrastando = false;
            }
            
        }
        
        if( !isArrastando ) {
            if( controlador == 1 ) {
                int fimCarrinhoX = carrinhoHUD.getPosX() + carrinhoHUD.getLargura();
                int fimCarrinhoY = carrinhoHUD.getPosY() + carrinhoHUD.getAltura();

                if( fimX >= carrinhoHUD.getPosX() && fimX <= fimCarrinhoX ){
                    if( fimY >= carrinhoHUD.getPosY() && fimY <= fimCarrinhoY ) {
                        carrinho.adicionarProduto( produto );
                    }
                }
            }
            controlador = 0;
            
        }
        
    }
    
}
