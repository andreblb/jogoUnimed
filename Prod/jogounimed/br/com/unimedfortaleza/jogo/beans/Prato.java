package br.com.unimedfortaleza.jogo.beans;

import java.util.ArrayList;
import java.util.List;

public class Prato
{
    private TipoPrato tipoPrato;
    private String nome;
    private List<Prato> children;
    private Prato parent;
    
    public Prato(final String nome, final TipoPrato tipoPrato) {
        this.tipoPrato = null;
        this.nome = null;
        this.children = new ArrayList<Prato>();
        this.parent = null;
        this.nome = nome;
        this.tipoPrato = tipoPrato;
    }
    
    public Prato(final String nome) {
        this.tipoPrato = null;
        this.nome = null;
        this.children = new ArrayList<Prato>();
        this.parent = null;
        this.nome = nome;
    }
    
    public Prato addChild(final Prato child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }
    
    public List<Prato> getChildren() {
        return this.children;
    }
    
    public TipoPrato getTipoPrato() {
        return this.tipoPrato;
    }
    
    public void setTipoPrato(final TipoPrato tipoPrato) {
        this.tipoPrato = tipoPrato;
    }
    
    private void setParent(final Prato parent) {
        this.parent = parent;
    }
    
    public Prato getParent() {
        return this.parent;
    }
    
    public String getName() {
        return this.nome;
    }
    
    public void setName(final String nome) {
        this.nome = nome;
    }
}
