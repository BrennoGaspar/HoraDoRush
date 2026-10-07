package br.com.HoraDoRush.model;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Produtos {
    
    // Atributos
    private String nome;
    private int posX, posY, largura = 100, altura = 100;
    private Image sprite;
    private boolean estaCarrinho;
    private static ArrayList<String> posicoesOcupadas = new ArrayList<>();
    private static ArrayList<String> produtosGerados = new ArrayList<>();
    
    private static String[] nomes = new String[]{
        "Banana",
        "Maca",
        "Pera",
        "Morango",
        "Abacate",
        "Carne",
        "Oleo",
        "Sal",
        "Cafe",
        "Leite",
        "Manteiga",
        "Queijo",
        "Pao",
        "Ovo",
        "Frango",
        "Cebola",
        "Alho",
        "Tomate",
        "Batata",
        "Cenoura"
    };
    
    // Construtor
    public Produtos() {
        
        this.nome = gerarNome();
        gerarPosicao();
        
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
    
    // resetar os dados pra proxima partida 
    public static void resetStaticData() {
        posicoesOcupadas.clear();
        produtosGerados.clear();
    }
    
    // Override para verificar se dois produtos são iguais usando o nome
    @Override
    public boolean equals( Object obj ) {
        Produtos t = (Produtos) obj;
        return this.nome.equals( t.nome );
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
    
    /**
     * Método para gerar o nome do produto criado (evitar duplicidade)
     */
    private String gerarNome() {
        
        String nomeGerado;
        while( true ){
            nomeGerado = nomes[ gerarNumero() ];
            if( !produtosGerados.contains( nomeGerado ) ) {
                produtosGerados.add( nomeGerado );
                break;
            }
        }
        return nomeGerado;
        
    }
    
    /**
     * Método para randomizar a posição do produto
     */
    private void gerarPosicao() {
        
        Random random = new Random();
        boolean valido = false;
    
        // Posições calculadas usando debug por desenho
        int[] posicoesXPrateleira = new int[]{ 0, 150, 300, 450, 600 };
        int[] posicoesYPrateleira = new int[]{ 190, 400, 580, 780 };
        
        while( !valido ) {
            int sorteioX = posicoesXPrateleira[ random.nextInt(5) ];
            int sorteioY = posicoesYPrateleira[ random.nextInt(4) ] - altura;
            String posicaoChave = sorteioX + ", " + sorteioY; // X, Y

            if( !posicoesOcupadas.contains(posicaoChave) ) {
                posX = sorteioX;
                posY = sorteioY;
                posicoesOcupadas.add( posicaoChave );
                valido = true;
            }
        }
        
    }
    
    /**
     * Método para escolher o nome do Produto
     */
    private static int gerarNumero() {
        Random random = new Random();
        return random.nextInt( nomes.length );
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
        } else if( nome.equals( "Carne" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/carne.png" );
            return sprite;
        } else if( nome.equals( "Oleo" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/oleo.png" );
            return sprite;
        } else if( nome.equals( "Sal" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/sal.png" );
            return sprite;
        } else if( nome.equals( "Cafe" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/cafe.png" );
            return sprite;
        } else if( nome.equals( "Leite" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/leite.png" );
            return sprite;
        } else if( nome.equals( "Manteiga" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/manteiga.png" );
            return sprite;
        } else if( nome.equals( "Queijo" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/queijo.png" );
            return sprite;
        } else if( nome.equals( "Pao" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/pao.png" );
            return sprite;
        } else if( nome.equals( "Ovo" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/ovo.png" );
            return sprite;
        } else if( nome.equals( "Frango" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/frango.png" );
            return sprite;
        } else if( nome.equals( "Cebola" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/cebola.png" );
            return sprite;
        } else if( nome.equals( "Alho" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/alho.png" );
            return sprite;
        } else if( nome.equals( "Tomate" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/tomate.png" );
            return sprite;
        } else if( nome.equals( "Batata" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/batata.png" );
            return sprite;
        } else if( nome.equals( "Cenoura" ) ) {
            sprite = ImageUtils.loadImage( "src/br/com/HoraDoRush/model/assets/cenoura.png" );
            return sprite;
        } else {
            return null;
        }
        
    }
    
}
