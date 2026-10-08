package br.com.HoraDoRush.engine;

import br.com.HoraDoRush.input.DragAndDrop;
import br.com.HoraDoRush.model.Carrinho;
import br.com.HoraDoRush.model.ListaCompras;
import br.com.HoraDoRush.model.Produtos;
import br.com.HoraDoRush.view.CarrinhoHUD;
import br.com.HoraDoRush.view.ListaComprasHUD;
import br.com.HoraDoRush.view.Prateleira;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.awt.Color;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class HoraDoRush extends EngineFrame {

    // definição do estado do jogo para telas de win e loss
    public enum EstadoJogo{
        PLAYING,
        TELA_WIN,
        TELA_LOSS
    }
    private EstadoJogo estadoAtual = EstadoJogo.PLAYING;

    // declaração de variáveis
    private double tempoRestante;
    private Carrinho carrinho;
    private ListaCompras listaCompras;
    private Prateleira prateleiraHUD;
    private ListaComprasHUD listaHUD;
    private CarrinhoHUD carrinhoHUD;
    private Produtos[] produtosArray;
    private DragAndDrop dragAndDrop;
    private Image gameBackground;
    private Image logo;
    
    // Dimensões Botões
    private int btnLargura = 250;
    private int btnAltura = 60;
    
    // Dimensões da Logo
    private int logoLargura = 612 ;
    private int logoAltura = 408 ; 
    private int posX = getScreenWidth() / 2 - 315 ;
    private int posY = getScreenHeight() / 2 - 450 ;
    
    // Construtor padrão do jogo
    public HoraDoRush( int dificuldade ) {
        
        // Cria a janela do jogo ou simulação
        super( 1500, 950, obterTitulo(dificuldade), 180, true );
        
        // Limpa a memória vinculada à partida anterior (static att)
        Produtos.resetStaticData();
        
        if( dificuldade == 1 ) {
            tempoRestante = 1*60; // 1 minuto
            gerarProdutos( 10 );
            listaCompras = new ListaCompras( 5 );
        } else if( dificuldade == 2 ) {
            tempoRestante = 1.5*60; // 1.5 minutos
            gerarProdutos( 15 );
            listaCompras = new ListaCompras( 7 );
        } else if( dificuldade == 3 ) {
            tempoRestante = 0.75*60; // 0.45 minutos
            gerarProdutos( 20 );
            listaCompras = new ListaCompras( 9 );
        }
        
        listaCompras.gerarLista( produtosArray );
        listaHUD = new ListaComprasHUD( listaCompras );

    }
    
    /**
     * Função para reconhecer o título da página do jogo, com a dificuldade.
     */
    private static String obterTitulo( int dificuldade ) {
        switch( dificuldade ){
            case 1:
                return "Hora do Rush - Fácil";
            case 2:
                return "Hora do Rush - Médio";
            case 3:
                return "Hora do Rush - Difícil";
            default:
                return "Hora do Rush";
        }
    }

    /**
     * Processa a entrada inicial fornecida pelo usuário e cria
     * e/ou inicializa os objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void create() {
        
        prateleiraHUD = new Prateleira();
        carrinhoHUD = new CarrinhoHUD();
        dragAndDrop = new DragAndDrop();
        carrinho = new Carrinho();
        produtosArray = new Produtos[0];
        // Import da imagem do fundo
        gameBackground = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/backgroundPastelv2.png" );
        // Import da Logo
        logo = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/logo.png" );
           
    }

    /**
     * Atualiza os objetos/contextos/variáveis do jogo ou simulação.
     * O parâmetro delta contém o tempo que passou entre o quadro
     * anterior e o quadro atual.
     */
    @Override
    public void update( double delta ) {
        
        // Lógica pra derrota
        if(estadoAtual == EstadoJogo.PLAYING) {
            // Diminuir o tempo restante a cada segundo
            if( tempoRestante > 0 ) {
                tempoRestante -= delta;
            } else{
                estadoAtual = EstadoJogo.TELA_LOSS; // Condição de derrota
            }

        }

        // Sistema para arrastar (mover) os produtos
        if( isMouseButtonPressed(EngineFrame.MOUSE_BUTTON_LEFT) && dragAndDrop.getArrastando() == null ) {
            for( int i = produtosArray.length - 1; i >= 0; i-- ) {
                Produtos p = produtosArray[i];
                int inicioX = p.getPosX();
                int inicioY = p.getPosY();
                int fimX = p.getPosX() + p.getLargura();
                int fimY = p.getPosY() + p.getAltura();

                if( getMouseX() >= inicioX && getMouseX() <= fimX && getMouseY() >= inicioY && getMouseY() <= fimY ) {
                    p.trazerParaFrente( i, produtosArray );
                    dragAndDrop.iniciarArrasto( p, getMouseX(), getMouseY() );
                    break; // para o for quando seleciona o produto do topo
                }
            }
        }
        dragAndDrop.arrastar( this, carrinhoHUD, carrinho, listaCompras, produtosArray );
        
        // Sistema de vitória
        try{
            if( listaCompras.getFila().isEmpty() && carrinho.getPilha().size() == listaCompras.getCopia().size() ) {
                estadoAtual = EstadoJogo.TELA_WIN;
            }
        } catch ( NullPointerException exc ) {
            // apenas para não printar nada no terminal
        }
        
        // Lógica de retorno para o menu com o teclado (ENTER)
        if( estadoAtual == EstadoJogo.TELA_LOSS || estadoAtual == EstadoJogo.TELA_WIN ){
            if( isKeyPressed(KEY_ENTER) ){
                new Main();
                this.setVisible( false ); 
            }
        }
        
       // Lógica de retorno para o menu com o botão 
        if( estadoAtual == EstadoJogo.TELA_LOSS || estadoAtual == EstadoJogo.TELA_WIN ){
            
            // Calcula a posição dinamicamente
            int atualX = getScreenWidth() / 2 - (btnLargura / 2);
            int atualY = getScreenHeight() / 2 + 150;
            
            // Verifica o mouse e o clique em tempo real
            if ( isMouseOver(getMouseX(), getMouseY(), atualX, atualY, btnLargura, btnAltura) && isMouseButtonPressed(MOUSE_BUTTON_LEFT) ) {
                new Main();
                this.dispose(); // NOTA: use dispose() no lugar de setVisible(false) para liberar a memória RAM
            }
        }
    }

    /**
     * Desenha o estado dos objetos/contextos/variáveis do jogo ou simulação.
     */
    @Override
    public void draw() {
        
        // Desenhar o fundo primeiro 
        Rectangle source = new Rectangle( 0, 0, gameBackground.getWidth(), gameBackground.getHeight() );
        Rectangle dest = new Rectangle( 0, 0, getScreenWidth(), getScreenHeight() );
        drawImage( gameBackground, source, dest );
        
        // Desenhar a prateleira
        try{
            prateleiraHUD.desenhar( this );
            listaHUD.desenhar( this );
            carrinhoHUD.desenhar( this );
        } catch ( NullPointerException exc ) {
            // apenas para retirar erro no console
        }
        
        // Desenhar o tempo na tela
        int tempoTotalSegundos = (int) tempoRestante;
        int minutos = tempoTotalSegundos / 60;
        int segundos = tempoTotalSegundos % 60;
        String tempoLabel = String.format( "Tempo restante: %02d:%02d", minutos, segundos );
        
        fillRectangle( getScreenWidth()/2 - 205 ,  getScreenHeight() - 75 , 390, 50, new java.awt.Color(0, 0, 0, 100) );  
        drawText( tempoLabel, getScreenWidth()/2 - 200, getScreenHeight() - 60 , 30, WHITE );
       
        for( Produtos p : produtosArray ) {
            p.desenhar( this );
        }
        
        // Texto de Vitória
        String textoWin = "Compra Concluida com Sucesso!"; 
        
        // Texto de Derrota
        String textoLossTime = "O tempo acabou, voce perdeu!"; 
        
        // Texto do Menu
        String textoMenu = "Pressione ENTER para voltar ao menu";
        
        // Desenha as telas de win e loss
        if(estadoAtual == EstadoJogo.TELA_WIN) {
            
            fillRectangle( 0, 0, getScreenWidth(), getScreenHeight(), new java.awt.Color(0, 0, 0, 240) );
            drawText( textoWin, getScreenWidth() - 1000, getScreenHeight() - 450, 30, WHITE );
            drawText( textoMenu, getScreenWidth() - 1000, getScreenHeight() - 400, 25, WHITE );
            
            // Logo
            Rectangle sourceI = new Rectangle( 0, 0, logo.getWidth(), logo.getHeight() );
            Rectangle destI = new Rectangle( posX, posY, logoLargura, logoAltura );
            drawImage(logo, sourceI, destI);
           
            int atualPosX = getScreenWidth() / 2 - (btnLargura / 2);
            int atualPosY = getScreenHeight() / 2 + 150;
            desenharBotao( "Voltar ao Menu", atualPosX, atualPosY, btnLargura, btnAltura, getMouseX(), getMouseY(), new Color(46, 204, 113) );
            
        } else if (estadoAtual == EstadoJogo.TELA_LOSS) {
            
            fillRectangle( 0, 0, getScreenWidth(), getScreenHeight(), new java.awt.Color(0, 0, 0, 240) );
            drawText( textoLossTime, getScreenWidth() - 1000, getScreenHeight() - 500, 30, WHITE );
            drawText( textoMenu, getScreenWidth() - 1000, getScreenHeight() - 450, 25, WHITE );
            
            // Logo
            Rectangle sourceI = new Rectangle( 0, 0, logo.getWidth(), logo.getHeight() );
            Rectangle destI = new Rectangle( posX, posY, logoLargura, logoAltura );
            drawImage(logo, sourceI, destI);
            
            int atualPosX = getScreenWidth() / 2 - (btnLargura / 2);
            int atualPosY = getScreenHeight() / 2 + 150;
            desenharBotao( "Voltar ao Menu", atualPosX, atualPosY, btnLargura, btnAltura, getMouseX(), getMouseY(), new Color(46, 204, 113) );
        }
    }
    
    private void desenharBotao( String texto, int x, int y, int larg, int alt, int mx, int my, Color corBase ) {
        boolean hover = isMouseOver( mx, my, x, y, larg, alt );
        
        Color corAtual = hover ? corBase.darker() : corBase;
        
        fillRectangle( x, y, larg, alt, corAtual );
        drawRectangle( x, y, larg, alt, BLACK );
        
        int paddingX = ( larg - (texto.length() * 12) ) / 2; 
        int paddingY = ( alt - 20 ) / 2;
        
        drawText( texto, x + paddingX, y + paddingY, 20, WHITE );
    }
       
    private boolean isMouseOver( int mouseX, int mouseY, int x, int y, int largura, int altura ) {
        return mouseX >= x && mouseX <= (x + largura) && mouseY >= y && mouseY <= (y + altura);
    }
    
    
    /**
     * Método para gerar o array com todos os produtos
     */
    private void gerarProdutos( int numero ) {
        produtosArray = new Produtos[numero];
        for( int i = 0; i < numero; i++ ) {
            produtosArray[i] = new Produtos();
        }
    }
    
}