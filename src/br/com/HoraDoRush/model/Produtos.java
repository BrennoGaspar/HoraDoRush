package br.com.HoraDoRush.model;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.awt.Color;
import java.util.Random;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Produtos {
    
    // Atributos
    private String nome;
    private int posX, posY, largura, altura;
    private Image sprite;
    
    private String[] nomes = new String[]{
        "Banana",
        "Maca",
        "Pera",
    };
    
    // Construtor
    public Produtos( int posX, int posY, int largura, int altura ) {
        
        this.nome = nomes[ gerarNumero() ];
        this.posX    = posX;
        this.posY    = posY;
        this.largura = largura;
        this.altura  = altura;
        
    }
    
    /**
     * Função para escolher o nome do Produto
     */
    private static int gerarNumero() {
        Random random = new Random();
        return random.nextInt( 3 );
    }
    
    // Getter
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
    
    // Setters
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    // Verificar se dois produtos são iguais
    @Override
    public boolean equals( Object obj ) {
        Produtos t = (Produtos) obj;
        return this.nome.equals( t.nome );
    }
    
    /**
     * Método para desenhar a hitbox de cada produto
     */
    public void desenhar( EngineFrame engine ) {
        
        engine.drawImage( selecionarImage(), posX, posY );
        
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
        } else {
            return null;
        }
        
    }
    
}
