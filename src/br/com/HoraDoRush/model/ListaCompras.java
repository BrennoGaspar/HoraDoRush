package br.com.HoraDoRush.model;

import java.util.ArrayList;
import java.util.List;
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
    private Queue<Produtos> copia;
    
    // Construtor
    public ListaCompras( int tamanho ) {
        this.tamanho = tamanho;
        fila = new ArrayBlockingQueue<>( tamanho );
        copia = new ArrayBlockingQueue<>( tamanho );
    }
    
    // Getters
    public int getTamanho() {
        return tamanho;
    }

    public Queue<Produtos> getFila() {
        return fila;
    }
    
    // Setters
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
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
        
        for( Produtos p : fila ) {
            copia.add( p );
        }
        
    }
    
    /**
     * Método para verificar se o item adicionado no carrinho está na lista de compras
     */
    public void verificarAdicao ( Produtos p ) {
        
        Produtos primeiroLista = fila.peek();
        if( p.equals(primeiroLista) ){
            fila.poll();
        }
        
    }
    
    /**
     * Método para verificar se o item retirado do carrinho está na lista de compras original
     */
    public void verificarRemocao( Produtos p, Carrinho carrinho ) {
        
        if( copia.contains(p) ) {
            fila.clear();
            fila.addAll( copia );
            fila.removeAll( carrinho.getPilha() );
        }
        
    }
    
}
