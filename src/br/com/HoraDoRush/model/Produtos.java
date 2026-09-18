package br.com.HoraDoRush.model;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;
import java.util.Random;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Produtos {
    
    // Atributos
    private String nome;
    private int posX, posY, largura, altura;
    private Color cor;
    
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
        int numeroGerado = random.nextInt() % 3;
        if( numeroGerado < 0 ) numeroGerado *= -1;
        return numeroGerado;
    }
    
    // Getter
    public String getNome() {
        return nome;
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
        
        cor = selecionarCor();
        engine.fillRectangle( posX, posY, altura, largura, cor );
        
    }
    
    private Color selecionarCor() {
        
        if( nome == "Banana" ) {
            return Color.YELLOW;
        } else if( nome == "Maca" ) {
            return Color.RED;
        } else if( nome == "Pera" ) {
            return Color.PINK;
        } else {
            return Color.BLACK;
        }
        
    }
    
}
