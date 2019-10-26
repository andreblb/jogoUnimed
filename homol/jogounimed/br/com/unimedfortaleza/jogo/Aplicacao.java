package br.com.unimedfortaleza.jogo;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Component;

import br.com.unimedfortaleza.jogo.beans.Prato;
import br.com.unimedfortaleza.jogo.beans.TipoPrato;
import br.com.unimedfortaleza.jogo.ui.Tela;
import br.com.unimedfortaleza.jogo.ui.interfaces.JogoListener;

public class Aplicacao implements JogoListener
{
    private Tela tela;
    private Prato prato;
    
    public Aplicacao() {
        this.prato = new Prato("Bolo de Chocolate", null);
        final Prato pizza = new Prato("Pizza", new TipoPrato("massa"));
        this.prato.addChild(pizza);
        this.createPane();
    }
    
    private void createPane() {
        (this.tela = new Tela(this)).pack();
        this.tela.setLocationRelativeTo(null);
        this.tela.setVisible(true);
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
        if (!this.checkFood(this.prato)) {
            this.validateFood(this.prato);
        }
    }
    
    private boolean checkFood(final Prato prato) {
        if (prato.getTipoPrato() != null) {
            return this.question(prato.getTipoPrato().getName()) && (this.iterateChilds(prato) || this.validateFood(prato));
        }
        return this.iterateChilds(prato);
    }
    
    private boolean iterateChilds(final Prato pratos) {
        return pratos.getChildren().stream().filter(item -> this.checkFood(item)).findFirst().orElse(null) != null;
    }
    
    private boolean question(final String itemName) {
        return JOptionPane.showConfirmDialog(this.tela.getDialog(), String.format("O prato que voc\u00ea pensou \u00e9 %s?", itemName), "Confirm", 0) == 0;
    }
    
    private boolean validateFood(final Prato prato) {
        if (prato == null) {
            return false;
        }
        if (this.question(prato.getName())) {
            JOptionPane.showMessageDialog(this.tela.getDialog(), "Acertei de novo!", "Jogo Gourmet", 1);
        }
        else {
            final String newFoodName = JOptionPane.showInputDialog(this.tela.getDialog(), "Qual prato voc\u00ea pensou?", "Desisto", 3);
            final String newFoodType = JOptionPane.showInputDialog(this.tela.getDialog(), String.format("%s \u00e9 _____ mas %s n\u00e3o.", newFoodName, prato.getName()), "Complete", 3);
            prato.addChild(new Prato(newFoodName, new TipoPrato(newFoodType)));
        }
        return true;
    }
}
