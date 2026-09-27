package br.com.HoraDoRush.model;

import java.util.Stack;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Carrinho {
    
    // Atributos
    private Stack<Produtos> pilha;
    
    // Construtor
    public Carrinho() {
        pilha = new Stack<>();
    }
    
    // Getters
    public Stack<Produtos> getPilha() {
        return pilha;
    }
    
    /**
     * Método para realizar a adição do produto ao Carrinho
     */
    public void adicionarProduto( Produtos produto, ListaCompras lista ) {
        
        lista.verificarAdicao( produto );        
        pilha.add( produto );
        
    }
    
    /**
     * Método para realizar a subtração (retirada) do produto adicionado por último no Carrinho (LIFO)
     */
    public void removerProduto( ListaCompras lista, Produtos p ) {
        pilha.pop();
        lista.verificarRemocao( p, this );
    }
    
    /**
     * Método para verificar quantos itens tem no carrinho no momento
     */
    public int verificarItensNoCarrinho() {
        
        int contador = 0;
        for( Produtos p : pilha ) {
            if( p.isEstaCarrinho() ) {
                contador++;
            }
        }
        return contador;
        
    }
    
}
