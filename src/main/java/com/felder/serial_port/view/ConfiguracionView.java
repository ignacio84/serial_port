package com.felder.serial_port.view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ConfiguracionView extends JDialogAbstract {

    private String strTitulo = " CONFIGURAR PUERTO COM";
    private Integer intAltura = 400;
    private Integer intAncho = 500;

    /*SE DECLARA CONTROL JComboBox QUE CONTENDRA EL LISTADO DE PEDIDOS*/
    private JComboBox cbxPuertos = new JComboBox();
    private JComboBox cbxBitsSegundo = new JComboBox();
    private JComboBox cbxBitsDatos = new JComboBox();
    private JComboBox cbxParidad = new JComboBox();
    private JComboBox cbxBitsParada = new JComboBox();
    private JComboBox cbxBascula= new JComboBox();

    /*SE DECLARAN CONTROLES JButton*/
    public JButton btnGuardar = new JButton("GUARDAR");
//    public JButton btnProbar = new JButton("PROBAR");

    public ConfiguracionView(String strTitulo, Integer intAltura, Integer intAncho) {
        super(strTitulo, intAltura, intAncho);
        this.loadConfig();
    }

    public ConfiguracionView() {
        this.loadConfig();
//        this.addControls();
    }

    private void loadConfig() {
        this.setStrTitulo(strTitulo);
        this.setIntAltura(intAltura);
        this.setIntAncho(intAncho);
        this.loadPropsWindows();
        this.addControls();
    }

    private void addControls() {
        this.createAddJLabelTitulo("CONFIGURAR PUERTO DE COMUNICACIONES", 20, Color.YELLOW, SwingConstants.CENTER, 7, 6, 485, 30);
        this.createAddJLabel("Puertos COM : ", SwingConstants.RIGHT, 30, 40, 180, 30);
        this.propsJComboBox(this.cbxPuertos, 220, 40, 180, 30);
        this.createAddJLabel("Bits por segundo : ", SwingConstants.RIGHT, 30, 90, 180, 30);
        this.propsJComboBox(this.cbxBitsSegundo, 220, 90, 180, 30);
        this.createAddJLabel("Bits de datos : ", SwingConstants.RIGHT, 30, 140, 180, 30);
        this.propsJComboBox(this.cbxBitsDatos, 220, 140, 180, 30);
        this.createAddJLabel("Paridad : ", SwingConstants.RIGHT, 30, 190, 180, 30);
        this.propsJComboBox(this.cbxParidad, 220, 190, 180, 30);
        this.createAddJLabel("Bits de parada : ", SwingConstants.RIGHT, 30, 240, 180, 30);
        this.propsJComboBox(this.cbxBitsParada, 220, 240, 180, 30);
        this.createAddJLabel("Bascula : ", SwingConstants.RIGHT, 30, 290, 180, 30);
        this.propsJComboBox(this.cbxBascula, 220, 290, 180, 30);
        this.propsJButton(this.btnGuardar, 170, 330, 180, 30);
        this.createAddJPanel(5, 5, 485, 360);
        this.createAddJPanel(5, 5, 485, 30);
        this.repaint();
    }

    public JComboBox getCbxBitsSegundo() {
        return cbxBitsSegundo;
    }

    public JComboBox getCbxBitsDatos() {
        return cbxBitsDatos;
    }

    public JComboBox getCbxParidad() {
        return cbxParidad;
    }

    public JComboBox getCbxPuertos() {
        return cbxPuertos;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JComboBox getCbxBitsParada() {
        return cbxBitsParada;
    }

    public JComboBox getCbxBascula() {
        return cbxBascula;
    }
}
