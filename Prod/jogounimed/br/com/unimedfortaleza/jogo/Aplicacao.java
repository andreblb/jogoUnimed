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
    public void inicioJogo() {
        if (!this.checkPrato(this.prato)) {
            this.validatePrato(this.prato);
        }
    }
    
    private boolean checkPrato(final Prato prato) {
        if (prato.getTipoPrato() != null) {
            return this.question(prato.getTipoPrato().getName()) && (this.iterateChilds(prato) || this.validatePrato(prato));
        }
        return this.iterateChilds(prato);
    }
    
    private boolean iterateChilds(final Prato pratos) {
        return pratos.getChildren().stream().filter(item -> this.checkPrato(item)).findFirst().orElse(null) != null;
    }
    
    private boolean question(final String itemNome) {
        return JOptionPane.showConfirmDialog(this.tela.getDialog(), String.format("O prato que voc\u00ea pensou \u00e9 %s?", itemNome), "Confirm", 0) == 0;
    }
    
    private boolean validatePrato(final Prato prato) {
        if (prato == null) {
            return false;
        }
        if (this.question(prato.getName())) {
            JOptionPane.showMessageDialog(this.tela.getDialog(), "Acertei de novo!", "Jogo Gourmet", 1);
        }
        else {
            final String novoPrato = JOptionPane.showInputDialog(this.tela.getDialog(), "Qual prato voc\u00ea pensou?", "Desisto", 3);
            final String novoTipoPrato = JOptionPane.showInputDialog(this.tela.getDialog(), String.format("%s \u00e9 _____ mas %s n\u00e3o.", novoPrato, prato.getName()), "Complete", 3);
            prato.addChild(new Prato(novoPrato, new TipoPrato(novoTipoPrato)));
        }
        return true;
    }
}
