package br.com.unimedfortaleza.jogo.beans;

public class TipoPrato
{
    private String name;
    
    public TipoPrato(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
