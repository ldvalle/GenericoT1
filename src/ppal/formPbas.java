package ppal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class formPbas extends JFrame implements ActionListener {
    private JLabel lblEntrada;
    private JLabel labelWeight;
    private JTextField fieldWeight;
    private JButton buttonTellMe;


    public  formPbas() {
        super("Calculador de Hidrataci\u00f3n.");

        initComponents();

        setSize(240, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        lblEntrada = new JLabel("Cu\u00e1nta agua debo tomar ?");
        labelWeight = new JLabel("Mi peso (kg):");
        fieldWeight = new JTextField(5);
        buttonTellMe = new JButton("Decime");

        setLayout(new FlowLayout());

        add(lblEntrada);
        add(labelWeight);
        add(fieldWeight);
        add(buttonTellMe);

        buttonTellMe.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        String message = "Amigo, ten\u00e9s que tomar %.1f Lts. de agua por d\u00eda!";

        if(fieldWeight.getText().isEmpty())
            return;

        float weight = Float.parseFloat(fieldWeight.getText());
        float waterAmount = calculateWaterAmount(weight);

        message = String.format(message, waterAmount);

        JOptionPane.showMessageDialog(this, message);
    }

    private float calculateWaterAmount(float weight) {
        return (weight / 10f) * 0.4f;
    }


    public static void main(String[] args){
        new formPbas().setVisible(true);
    }
}
