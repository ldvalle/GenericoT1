package ppal;

import servicios.Kripton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Encriptador extends  JFrame{
    private JPanel panel1;
    private JTextField txtEntrada;
    private JButton cmdDesencriptar;
    private JButton cmdEncriptar;
    private JTextField txtSalida;
    private JLabel lblEntrada;
    private JLabel lblSalida;

    private String miKey="q1w2e3r4t5y6";

    public Encriptador() {

        initComponents();
        setSize(240, 200);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cmdEncriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Encriptar();
            }
        });

        cmdDesencriptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desencriptar();
            }
        });
    }

    private void initComponents() {
        txtEntrada = new JTextField(20);
        txtSalida = new JTextField(20);

        setLayout(new FlowLayout());
        add(lblEntrada);
        add(txtEntrada);
        add(lblSalida);
        add(txtSalida);
        add(cmdEncriptar);
        add(cmdDesencriptar);
    }

    private void Encriptar(){
        String cadena =txtEntrada.getText().trim();
        String Mensaje="";

        if(!cadena.isEmpty()){
            try{
                String salida = Kripton.Encriptar2(cadena, miKey);
                txtSalida.setText(salida);
            }catch (Exception ex){
                Mensaje = "Error al encriptar \n" + ex.getMessage();
                //JOptionPane.showMessageDialog(Mensaje);

            }
        }
    }

    private void Desencriptar(){
        String cadena =txtEntrada.getText().trim();
        String Mensaje="";

        if(!cadena.isEmpty()){
            try{
                String salida = Kripton.Desencriptar2(cadena, miKey);
                txtSalida.setText(salida);
            }catch (Exception ex){
                Mensaje = "Error al encriptar \n" + ex.getMessage();
                //JOptionPane.showMessageDialog(Mensaje);

            }
        }
    }

    public static void main(String[] args){
        new Encriptador().setVisible(true);
    }
}
