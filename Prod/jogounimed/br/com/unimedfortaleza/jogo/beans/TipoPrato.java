package br.com.unimedfortaleza.jogo.beans;

public class TipoPrato
{
    private String nome;
    
    public TipoPrato(final String nome) {
        this.nome = nome;
    }
    
    public String getName() {
        return this.nome;
    }
    
    public void setName(final String nome) {
        this.nome = nome;
    }
}
