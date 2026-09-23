package br.com.HoraDoRush.model;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class ListaCompras {
    
    // Atributos
    private int tamanho;
    private Queue<Produtos> fila;
    
    // Construtor
    public ListaCompras( int tamanho ) {
        this.tamanho = tamanho;
        fila = new ArrayBlockingQueue<>( tamanho );
    }
    
    // Getters
    public int getTamanho() {
        return tamanho;
    }

    public Queue<Produtos> getFila() {
        return fila;
    }
    
    /**
     * Método para criar a lista de itens a partir dos produtos
     */
    public void gerarLista( Produtos[] produtos ){
        
        ArrayList<Integer> numerosEscolhidos = new ArrayList<>();
                
        for( int i = 0; i < tamanho; i++ ) {
            Random random = new Random();
            int n = random.nextInt( produtos.length );
            
            while( numerosEscolhidos.contains(n) ) {
                n = random.nextInt( produtos.length );
            }
            
            fila.add( produtos[n] );
            numerosEscolhidos.add( n );
        }
        
    }
    
}
