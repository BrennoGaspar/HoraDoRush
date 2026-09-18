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
    
}
