// 
// Decompiled by Procyon v0.5.36
// 

package br.com.unimedfortaleza.jogo.beans;

import java.util.ArrayList;
import java.util.List;

public class Prato
{
    private TipoPrato foodType;
    private String name;
    private List<Prato> children;
    private Prato parent;
    
    public Prato(final String name, final TipoPrato foodType) {
        this.foodType = null;
        this.name = null;
        this.children = new ArrayList<Prato>();
        this.parent = null;
        this.name = name;
        this.foodType = foodType;
    }
    
    public Prato(final String name) {
        this.foodType = null;
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
    
    public TipoPrato getFoodType() {
        return this.foodType;
    }
    
    public void setFoodType(final TipoPrato foodType) {
        this.foodType = foodType;
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
