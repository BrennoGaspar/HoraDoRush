package br.com.HoraDoRush.model;

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
        
        Random random = new Random();
        int n = random.nextInt( tamanho );
        fila.add( produtos[n] );
        
    }
    
}
