package br.com.HoraDoRush.model;

import java.util.Stack;

/**
 * @author Brenno Gaspar Pinto & Victor Altran Soares
 */
public class Carrinho {
    
    // Atributos
    private Stack<Produtos> pilha;
    private int itensFaltando;
    
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
    public void adicionarProduto( Produtos produto ) {
        itensFaltando--;
    }
    
}
