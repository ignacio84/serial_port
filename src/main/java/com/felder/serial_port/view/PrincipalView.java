
package com.felder.serial_port.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class PrincipalView extends JFrameAbstract  {

    private String strTitulo = " LECTURA DE PESO BASCULA";
    private Integer intAltura = 400;
    private Integer intAncho = 700;

    //BARRA DE MENU
    private JMenuBar mbrBarMenu;
    private JMenu menConfig;
    private JMenuItem itmPortConfig;

    //CONTROLES JLABELS
    private JLabel lblEstatus = new JLabel("SIN CONEXIÓN");

    
    /*SE DECLARAN CONTROLES JButton*/
    private JButton btnIniciar = new JButton("INICIAR");
    private JButton btnDetener = new JButton("DETENER");
    
    //DECLARA  JTextArea QUE CONTENDRA LOS COMENTARIOS Y UN JScrollPane QUE MANEJARA LA BARRA DE DESPLAZAMIENTO
    public JTextArea txaBascula = new JTextArea();
    private final JScrollPane scrTxaComent = new JScrollPane(txaBascula);

    public PrincipalView(String strTitulo, Integer intAltura, Integer intAncho) {
        super(strTitulo, intAltura, intAncho);
        this.loadConfig();
    }

    public PrincipalView() {
        this.loadConfig();
    }

    private void loadConfig() {
        this.setStrTitulo(strTitulo);
        this.setIntAltura(intAltura);
        this.setIntAncho(intAncho);
        this.propiedadesVentana();
        this.addControls();
        this.addBarMenu();
    }

    private void addControls() {
        this.createAddJLabelTitulo("COMUNICACIÓN CON BASCULA [ PUERTO RS232 ] ", 20, Color.YELLOW, SwingConstants.CENTER, 7, 6, 685, 30);
        this.createAddJLabel("COMUNICACIÓN : ", SwingConstants.LEFT, 30, 40, 120, 30);
        this.propsJLabel(lblEstatus, SwingConstants.LEFT, 160, 40, 400, 30);
        this.propsJTextArea(txaBascula);
        this.propsJScrollPane(scrTxaComent, 30, 70, 640, 200);
        this.propsJButton(this.btnIniciar, 150, 310, 180, 30);
        this.propsJButton(this.btnDetener, 335, 310, 180, 30);
        this.createAddJPanel(5, 5, 685, 300);
        this.createAddJPanel(5, 5, 685, 30);
        this.repaint();
    }

    /*METODO CON LA CONFIGURACION DE LA BARRA DE MENU*/
    private void addBarMenu() {
        //SE CREA BARRA DE MENUS
        this.mbrBarMenu = new JMenuBar();
        this.setJMenuBar(mbrBarMenu);
        //SE AGREGAN OPCIONES A BARRA DE MENU
        this.menConfig = new JMenu("Configuración");
//        this.menFolioNuevo = new JMenu("Folios Nuevos");
        this.addMenuBar(mbrBarMenu, menConfig, KeyEvent.VK_O);
//        this.addMenuBar(mbrBarMenu, menFolioNuevo, KeyEvent.VK_F);
//        this.addLblToMenuBar(mbrBarMenu,new JLabel("Ignacio"), Color.red);
//        this.addLblToMenuBar(mbrBarMenu, new JLabel((usr == null || usr.getPropietario() == null) ? " " : usr.getPropietario() + "   "), Color.red);
        //SE AGREGAN ITEMS A LAS OPCIONES DE LA BARRA DE MENU
        this.itmPortConfig = new JMenuItem("Configurar Puerto COM");
        this.addItemMenu(menConfig, itmPortConfig, this.getResourceAsStreamImg("/img/settings.png"), KeyEvent.VK_C);
//        this.itmRestConfig = new JMenuItem("Regresar A Menú");
//        this.addItemMenu(menConfig, itmRestConfig, this.getResourceAsStreamImg("/img/settings.png"), KeyEvent.VK_M);
//        this.itmBloq = new JMenuItem("Bloquear");
//        this.addItemMenu(menConfig, itmBloq, this.getResourceAsStreamImg("/com/felder/img/lock.png"), KeyEvent.VK_B);
//        this.itmAddFolio = new JMenuItem("Generar Nuevo Folio");
//        this.addItemMenu(menFolioNuevo, itmAddFolio, this.getResourceAsStreamImg("/com/felder/img/add_doc16x16.png"), KeyEvent.VK_N);
    }

    public JMenuItem getItmPortConfig() {
        return itmPortConfig;
    }

    public JLabel getLblEstatus() {
        return lblEstatus;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnDetener() {
        return btnDetener;
    }

    public JTextArea getTxaBascula() {
        return txaBascula;
    }
}