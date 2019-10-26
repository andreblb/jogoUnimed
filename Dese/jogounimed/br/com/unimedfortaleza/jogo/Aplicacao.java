// 
// Decompiled by Procyon v0.5.36
// 

package br.com.unimedfortaleza.jogo;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Component;

import br.com.unimedfortaleza.jogo.beans.Prato;
import br.com.unimedfortaleza.jogo.beans.TipoPrato;
import br.com.unimedfortaleza.jogo.ui.Tela;
import br.com.unimedfortaleza.jogo.ui.interfaces.GameStartListener;

public class Aplicacao implements GameStartListener
{
    private Tela mainFrame;
    private Prato root;
    
    public Aplicacao() {
        this.root = new Prato("Bolo de Chocolate", null);
        final Prato lasanha = new Prato("Lasanha", new TipoPrato("massa"));
        this.root.addChild(lasanha);
        this.createPane();
    }
    
    private void createPane() {
        (this.mainFrame = new Tela(this)).pack();
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Aplicacao();
            }
        });
    }
    
    @Override
    public void startGame() {
        if (!this.checkFood(this.root)) {
            this.validateFood(this.root);
        }
    }
    
    private boolean checkFood(final Prato food) {
        if (food.getFoodType() != null) {
            return this.question(food.getFoodType().getName()) && (this.iterateChilds(food) || this.validateFood(food));
        }
        return this.iterateChilds(food);
    }
    
    private boolean iterateChilds(final Prato foods) {
        return foods.getChildren().stream().filter(item -> this.checkFood(item)).findFirst().orElse(null) != null;
    }
    
    private boolean question(final String itemName) {
        return JOptionPane.showConfirmDialog(this.mainFrame.getDialog(), String.format("O prato que voc\u00ea pensou \u00e9 %s?", itemName), "Confirm", 0) == 0;
    }
    
    private boolean validateFood(final Prato food) {
        if (food == null) {
            return false;
        }
        if (this.question(food.getName())) {
            JOptionPane.showMessageDialog(this.mainFrame.getDialog(), "Acertei de novo!", "Jogo Gourmet", 1);
        }
        else {
            final String newFoodName = JOptionPane.showInputDialog(this.mainFrame.getDialog(), "Qual prato voc\u00ea pensou?", "Desisto", 3);
            final String newFoodType = JOptionPane.showInputDialog(this.mainFrame.getDialog(), String.format("%s \u00e9 _____ mas %s n\u00e3o.", newFoodName, food.getName()), "Complete", 3);
            food.addChild(new Prato(newFoodName, new TipoPrato(newFoodType)));
        }
        return true;
    }
}
