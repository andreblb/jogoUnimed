package br.com.unimedfortaleza.jogo.beans;

import java.util.ArrayList;
import java.util.List;

public class Prato
{
    private TipoPrato tipoPrato;
    private String name;
    private List<Prato> children;
    private Prato parent;
    
    public Prato(final String name, final TipoPrato tipoPrato) {
        this.tipoPrato = null;
        this.name = null;
        this.children = new ArrayList<Prato>();
        this.parent = null;
        this.name = name;
        this.tipoPrato = tipoPrato;
    }
    
    public Prato(final String name) {
        this.tipoPrato = null;
        this.name = null;
        this.children = new ArrayList<Prato>();
        this.parent = null;
        this.name = name;
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
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
