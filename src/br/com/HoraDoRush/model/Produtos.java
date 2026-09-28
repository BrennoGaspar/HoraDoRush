package br.com.HoraDoRush.model;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.util.Random;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Produtos {
    
    // Atributos
    private String nome;
    private int posX, posY, largura, altura;
    private Image sprite;
    private boolean estaCarrinho;
    
    private static String[] nomes = new String[]{
        "Banana",
        "Maca",
        "Pera",
        "Morango",
        "Abacate",
    };
    
    // Construtor
    public Produtos( int posX, int posY, int largura, int altura ) {
        
        this.nome = nomes[ gerarNumero() ];
        this.posX    = posX;
        this.posY    = posY;
        this.largura = largura;
        this.altura  = altura;
        this.estaCarrinho = false;
        
    }
    
    // Getters
    public String getNome() {
        return nome;
    }

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

    public boolean isEstaCarrinho() {
        return estaCarrinho;
    }
    
    // Setters
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void alterarEstaCarrinho() {
        this.estaCarrinho = !estaCarrinho;
    }    
    
    /**
     * Função para escolher o nome do Produto
     */
    private static int gerarNumero() {
        Random random = new Random();
        return random.nextInt( nomes.length );
    }
    
    // Override para verificar se dois produtos são iguais usando o nome
    @Override
    public boolean equals( Object obj ) {
        Produtos t = (Produtos) obj;
        return this.nome.equals( t.nome );
    }
    
    /**
     * Método para desenhar a hitbox de cada produto
     */
    public void desenhar( EngineFrame engine ) {
        
        selecionarImage();
        Rectangle source = new Rectangle( 0, 0, sprite.getWidth(), sprite.getHeight() ); // qual a parte da imagem quer usar
        Rectangle dest = new Rectangle( posX, posY, largura, altura ); // tamanho da hitbox (produto)
        engine.drawImage( sprite, source, dest );
        
    }
    
    /**
     * Método para renderizar a imagem de acordo com o produto gerado
     */
    private Image selecionarImage() {
        
        if( nome.equals( "Banana" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/banana.png" );
            return sprite;
        } else if( nome.equals( "Maca" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/maca.png" );
            return sprite;
        } else if( nome.equals( "Pera" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/pera.png" );
            return sprite;
        } else if( nome.equals( "Morango" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/morango.png" );
            return sprite;
        } else if( nome.equals( "Abacate" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/abacate.png" );
            return sprite;
        } else {
            return null;
        }
        
    }
    
    /**
     * Método para trazer o produto selecionado para frente (no eixo Z)
     */
    public void trazerParaFrente( int indice, Produtos[] produtosArray ) {
        if ( indice < 0 || indice >= produtosArray.length - 1 ) {
            return; // o produto já é o primeiro
        }

        Produtos p = produtosArray[indice];
        for( int i = indice; i < produtosArray.length - 1; i++ ) {
            produtosArray[i] = produtosArray[i + 1];
        }
        produtosArray[produtosArray.length - 1] = p;
    }
    
}
