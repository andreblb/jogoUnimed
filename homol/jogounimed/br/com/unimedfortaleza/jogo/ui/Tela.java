package br.com.unimedfortaleza.jogo.ui;

import java.beans.PropertyChangeEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import br.com.unimedfortaleza.jogo.ui.interfaces.JogoListener;

import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

public class Tela extends JFrame implements PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private JOptionPane optionPane;
    private JDialog dialog;
    private JogoListener jogoListener;
    
    public Tela() {
        this.optionPane = new JOptionPane(new JLabel("Pense num prato que gosta", 0), -1, -1);
        (this.dialog = new JDialog(this, "Jogo Unimed", true)).setContentPane(this.optionPane);
        this.dialog.setModal(true);
        this.dialog.pack();
        this.dialog.setDefaultCloseOperation(2);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        this.optionPane.addPropertyChangeListener(this);
        this.setDefaultCloseOperation(3);
    }
    
    public JDialog getDialog() {
        return this.dialog;
    }
    
    @Override
    public void setVisible(final boolean b) {
        this.dialog.setVisible(b);
    }
    
    public Tela(final JogoListener jogoListener) {
        this();
        this.jogoListener = jogoListener;
    }
    
    public void setJogoListener(final JogoListener jogoListener) {
        this.jogoListener = jogoListener;
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent e) {
        final String prop = e.getPropertyName();
        if (this.dialog.isVisible() && e.getSource() == this.optionPane && ("value".equals(prop) || "inputValue".equals(prop))) {
            final Object value = this.optionPane.getValue();
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            this.optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            Integer intValue = null;
            try {
                intValue = (int)value;
            }
            catch (Exception ex) {}
            if (this.jogoListener != null && intValue != null && intValue == 0) {
                this.jogoListener.startGame();
            }
        }
    }
}
