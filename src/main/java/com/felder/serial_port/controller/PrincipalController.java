package com.felder.serial_port.controller;

import com.felder.serial_port.model.dao.ConfiguracionDaoImpl;
import com.felder.serial_port.model.pojo.Configuracion;
import com.felder.serial_port.model.service.ConfiguracionServiceImpl;
import com.felder.serial_port.model.service.IConfiguracionService;
import com.felder.serial_port.util.ISerialPort;
import com.felder.serial_port.util.SerialPortImpl;
import com.felder.serial_port.view.PrincipalView;
import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class PrincipalController implements ActionListener, MouseMotionListener, WindowStateListener, WindowListener {

    PrincipalView vPrincipal = new PrincipalView();
    private TrayIcon trayIcon;
    private SystemTray tray;
    private Configuracion config;
    private IConfiguracionService serviceConfig;
    private ISerialPort serialPort;

    public PrincipalController() {
        this.serviceConfig = new ConfiguracionServiceImpl(new ConfiguracionDaoImpl());
        this.serialPort = new SerialPortImpl();
        this.addListeners();
        this.propsTrayBar();
        this.loadConfiguracion();
        this.onInit();
    }

    /*AGREGA ESCUCHADORES A CONTROLES DE LA VISTA*/
    private void addListeners() {
        vPrincipal.addWindowStateListener(this);
        vPrincipal.addWindowListener(this);
        vPrincipal.getItmPortConfig().addActionListener(this);
        vPrincipal.getBtnIniciar().addActionListener(this);
        vPrincipal.getBtnDetener().addActionListener(this);
    }

    private void propsTrayBar() {
        Image img = null;
        if (SystemTray.isSupported()) {
            try {
                InputStream inAbsoluto = getClass().getResourceAsStream("/img/com_port.png");//OBTIENE IMAGEN DE ADRETO DEL JAR MEDIANTE getClass().getResourceAsStream
                img = ImageIO.read(inAbsoluto);//LEE IMAGEN OBTENIDA
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.tray = tray = SystemTray.getSystemTray();
            //Aquí se crea un popup menu
            PopupMenu popup = new PopupMenu();
            //Se agrega la opción de salir
            MenuItem salirItem = new MenuItem("Cerrar");
            MenuItem abrirItem = new MenuItem("Abrir");
            //Se le asigna al item del popup el listener para salir de la app
            salirItem.addActionListener(salirListener);
            abrirItem.addActionListener(abrirListener);
            //agrega los item al PopupMenu
            popup.add(salirItem);
            popup.add(abrirItem);
            trayIcon = new TrayIcon(img, "Bascula conectada.. ", popup);
            trayIcon.addActionListener(abrirListener);
        }
    }

    ActionListener salirListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    ActionListener abrirListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            vPrincipal.setVisible(true);
            vPrincipal.setState(0);
            tray.remove(trayIcon);
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vPrincipal.getItmPortConfig())) {
            new ConfiguracionController();
            this.loadConfiguracion();
        } else if (e.getSource().equals(vPrincipal.getBtnIniciar())) {
            this.onStart();
        } else if (e.getSource().equals(vPrincipal.getBtnDetener())) {
            try {
                this.enabledStart();
                this.serialPort.closeSerialPort();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vPrincipal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        if (e.getNewState() == Frame.ICONIFIED) {
            if (this.serialPort.getPort() != null && this.serialPort.getPort().isOpened()) {
                try {
                    tray.add(trayIcon);
                    vPrincipal.setVisible(false);
                    trayIcon.displayMessage("", "Executando en segundo plano..", TrayIcon.MessageType.NONE);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
                return;
            }
            vPrincipal.setState(0);
            JOptionPane.showMessageDialog(vPrincipal, "FAVOR DE INICIAR CONEXIÓN", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadConfiguracion() {
        try {
            this.config = (Configuracion) this.serviceConfig.get();
        } catch (Exception ex) {
        }
    }

    private void onInit() {
        this.enabledStart();
        if (this.config != null) {
            this.openPort();
            vPrincipal.setState(1);
        } else {
            JOptionPane.showMessageDialog(vPrincipal, "Error al leer JSON", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
      private void onStart() {
        this.enabledStart();
        if (this.config != null) {
            this.openPort();
        } else {
            JOptionPane.showMessageDialog(vPrincipal, "Error al leer JSON", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void disabledStart() {
        vPrincipal.getBtnIniciar().setEnabled(false);
        vPrincipal.getBtnDetener().setEnabled(true);
        vPrincipal.getItmPortConfig().setEnabled(false);
        vPrincipal.getLblEstatus().setText("CONECTADO EL PUERTO : " + this.config.getPort());
    }

    private void enabledStart() {
        vPrincipal.getBtnIniciar().setEnabled(true);
        vPrincipal.getBtnDetener().setEnabled(false);
        vPrincipal.getItmPortConfig().setEnabled(true);
        vPrincipal.getTxaBascula().setText("");
        vPrincipal.getLblEstatus().setText("SIN CONEXIÓN");
    }

    private void openPort() {
        try {
            this.serialPort.setObject(trayIcon);
            this.serialPort.setObject_1(vPrincipal.getTxaBascula());
            this.serialPort.setObject_2(vPrincipal);
            this.serialPort.setObject_3(config);
            this.serialPort.addEventListener();
            this.disabledStart();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vPrincipal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            this.serialPort.closeSerialPort();
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
