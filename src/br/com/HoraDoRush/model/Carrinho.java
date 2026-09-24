package br.com.HoraDoRush.model;

import java.util.Stack;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Carrinho {
    
    // Atributos
    private Stack<Produtos> pilha;
    private int itensFaltando;
    private int itensNoCarrinho;
    
    // Construtor
    public Carrinho() {
        pilha = new Stack<>();
    }
    
    // Getters
    public Stack<Produtos> getPilha() {
        return pilha;
    }

    public int getItensFaltando() {
        return itensFaltando;
    }
    
    // Setters
    public void setItensFaltando(int itensFaltando) {
        this.itensFaltando = itensFaltando;
    }
    
    /**
     * Método para realizar a adição do produto ao Carrinho
     */
    public void adicionarProduto( Produtos produto, ListaCompras lista ) {
        
        Produtos primeiroLista = lista.getFila().peek();
        
        if( produto.equals(primeiroLista) ){
            lista.getFila().poll();
            lista.setTamanho( --itensFaltando );
        } else {
            System.out.printf( "\nDEBUG - Produto incorreto, voce adicionou %s - o correto seria %s\n", produto.getNome(), lista.getFila().peek().getNome() );
        }
        
        pilha.add( produto );
        
    }
    
    /**
     * Método para realizar a subtração (retirada) do produto adicionado por último no Carrinho (LIFO)
     */
    public void removerProduto() {
        pilha.pop();
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
