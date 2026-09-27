package br.com.HoraDoRush.input;

import br.com.HoraDoRush.model.Carrinho;
import br.com.HoraDoRush.model.ListaCompras;
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
    private Produtos arrastando = null;
    
    // Construtor
    public DragAndDrop() {}
    
    public void iniciarArrasto( Produtos produto, int mouseX, int mouseY ) {
        this.arrastando = produto;
        this.isArrastando = true;
        this.diferencaX = mouseX - produto.getPosX();
        this.diferencaY = mouseY - produto.getPosY();
    }
    
    public void arrastar( EngineFrame engine, CarrinhoHUD carrinhoHUD, Carrinho carrinho, ListaCompras lista, Produtos[] produtosArray ) {
        
        if ( !isArrastando || arrastando == null ) {
            return;
        }
        
        int mouseX = engine.getMouseX();
        int mouseY = engine.getMouseY();
        boolean segurando = engine.isMouseButtonDown( EngineFrame.MOUSE_BUTTON_LEFT );
    
        if ( segurando ) {
            controlador = 1;
            int novaPosX = mouseX - diferencaX;
            int novaPosY = mouseY - diferencaY;
            
            // Colisão com o limite da tela
            if ( novaPosX < 0 ) {
                novaPosX = 0;
            } else if ( novaPosX + arrastando.getLargura() > engine.getScreenWidth() ) {
                novaPosX = engine.getScreenWidth() - arrastando.getLargura();
            }
            
            if ( novaPosY < 0 ) {
                novaPosY = 0;
            } else if ( novaPosY + arrastando.getAltura() > engine.getScreenHeight() ) {
                novaPosY = engine.getScreenHeight() - arrastando.getAltura();
            }
            
            arrastando.setPosX( novaPosX );
            arrastando.setPosY( novaPosY );

        } else { // Quando solta o mouse ( !segurando )

            int inicioX = arrastando.getPosX();
            int inicioY = arrastando.getPosY();
            int fimX = inicioX + arrastando.getLargura();
            int fimY = inicioY + arrastando.getAltura();

            // Verificar colisões entre produtos
            for ( Produtos p : produtosArray ) {
                
                if ( !p.equals( arrastando ) ) {
                    int inicioProdutoX = p.getPosX();
                    int inicioProdutoY = p.getPosY();
                    int fimProdutoX = p.getPosX() + p.getLargura();
                    int fimProdutoY = p.getPosY() + p.getAltura();
                    
                    boolean colideX = fimX > inicioProdutoX && inicioX < fimProdutoX;
                    boolean colideY = fimY > inicioProdutoY && inicioY < fimProdutoY;
                    
                    if ( colideX && colideY ) {
                        int saidaEsquerda = fimX - inicioProdutoX;
                        int saidaDireita = fimProdutoX - inicioX;
                        if ( saidaEsquerda < saidaDireita ) {
                            arrastando.setPosX( inicioProdutoX - arrastando.getLargura() );
                        } else {
                            arrastando.setPosX( fimProdutoX );
                        }
                    }
                }
                
            }

            fimX = arrastando.getPosX() + arrastando.getLargura();
            fimY = arrastando.getPosY() + arrastando.getAltura();

            // Verificar se está no carrinho
            int fimCarrinhoX = carrinhoHUD.getPosX() + carrinhoHUD.getLargura();
            int fimCarrinhoY = carrinhoHUD.getPosY() + carrinhoHUD.getAltura();

            if ( controlador == 1 ) {
                boolean colideX = (fimX >= carrinhoHUD.getPosX() && fimX <= fimCarrinhoX);
                boolean colideY = (fimY >= carrinhoHUD.getPosY() && fimY <= fimCarrinhoY);

                if ( colideX && colideY && !arrastando.isEstaCarrinho() ) {
                    carrinho.adicionarProduto( arrastando, lista );
                    arrastando.alterarEstaCarrinho();
                } else if ( arrastando.isEstaCarrinho() ) {
                    carrinho.removerProduto();
                    arrastando.alterarEstaCarrinho();
                }
                controlador = 0;
            }
            
            isArrastando = false;
            arrastando = null;
            
        }
    }

    public Produtos getArrastando() {
        return arrastando;
    }
    
}