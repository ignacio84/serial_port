package com.felder.serial_port.controller;

import com.felder.serial_port.model.dao.ConfiguracionDaoImpl;
import com.felder.serial_port.model.pojo.Configuracion;
import com.felder.serial_port.model.service.ConfiguracionServiceImpl;
import com.felder.serial_port.model.service.IConfiguracionService;
import com.felder.serial_port.util.ISerialPort;
import com.felder.serial_port.util.SerialPortImpl;
import com.felder.serial_port.view.ConfiguracionView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import jssc.SerialPortList;

public class ConfiguracionController implements ActionListener {

    private ConfiguracionView vConfiguracion = new ConfiguracionView();
    private IConfiguracionService serviceConfig;
    private Configuracion config;
    private ISerialPort serialPort;

    public ConfiguracionController() {
        this.serviceConfig = new ConfiguracionServiceImpl(new ConfiguracionDaoImpl());
        this.addListeners();
        this.serialPort = new SerialPortImpl();
        this.loadCombobox();
        this.loadConfiguracion();
        vConfiguracion.setVisible(true);
    }

    private void addListeners() {
        vConfiguracion.getBtnGuardar().addActionListener(this);
    }

    private void loadCombobox() {
        this.loadPuertos();
        this.loadPuertos();
        this.loadCbxBitsSegundo();
        this.loadCbxBitsDatos();
        this.loadCbxBitsParada();
        this.loadCbxParidad();
        this.loadCbxBascula();
    }

    private void loadConfiguracion() {
        try {
            this.config = (Configuracion) this.serviceConfig.get();
            if (this.config != null) {
                vConfiguracion.getCbxPuertos().setSelectedItem(config.getPort());
                vConfiguracion.getCbxBitsSegundo().setSelectedItem(this.config.getBaudRate().toString());
                vConfiguracion.getCbxBitsDatos().setSelectedItem(this.config.getDataBits().toString());
                vConfiguracion.getCbxParidad().setSelectedItem(this.parityText(this.config.getParity()));
                vConfiguracion.getCbxBitsParada().setSelectedItem(this.config.getStopBits().toString());
                vConfiguracion.getCbxBascula().setSelectedItem(this.config.getBascula());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vConfiguracion, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (this.config == null) {
            this.config = new Configuracion();
        }
    }

    private void loadPuertos() {
        for (String port : this.serialPort.getPorts()) {
            vConfiguracion.getCbxPuertos().addItem(port);
        }
    }

    private void loadCbxBitsSegundo() {
        vConfiguracion.getCbxBitsSegundo().addItem("110");
        vConfiguracion.getCbxBitsSegundo().addItem("300");
        vConfiguracion.getCbxBitsSegundo().addItem("1200");
        vConfiguracion.getCbxBitsSegundo().addItem("2400");
        vConfiguracion.getCbxBitsSegundo().addItem("4800");
        vConfiguracion.getCbxBitsSegundo().addItem("9600");
        vConfiguracion.getCbxBitsSegundo().addItem("19200");
        vConfiguracion.getCbxBitsSegundo().addItem("38400");
        vConfiguracion.getCbxBitsSegundo().addItem("57600");
        vConfiguracion.getCbxBitsSegundo().addItem("115200");
        vConfiguracion.getCbxBitsSegundo().addItem("230400");
        vConfiguracion.getCbxBitsSegundo().addItem("460800");
        vConfiguracion.getCbxBitsSegundo().addItem("921600");
    }

    private void loadCbxBitsDatos() {
        vConfiguracion.getCbxBitsDatos().addItem("5");
        vConfiguracion.getCbxBitsDatos().addItem("6");
        vConfiguracion.getCbxBitsDatos().addItem("7");
        vConfiguracion.getCbxBitsDatos().addItem("8");
    }

    private void loadCbxParidad() {
        vConfiguracion.getCbxParidad().addItem("Par");
        vConfiguracion.getCbxParidad().addItem("Inpar");
        vConfiguracion.getCbxParidad().addItem("Ninguno");
        vConfiguracion.getCbxParidad().addItem("Marca");
        vConfiguracion.getCbxParidad().addItem("Espacio");
    }

    private void loadCbxBitsParada() {
        vConfiguracion.getCbxBitsParada().addItem("1");
        vConfiguracion.getCbxBitsParada().addItem("1.5");
        vConfiguracion.getCbxBitsParada().addItem("2");
    }

    private void loadCbxBascula() {
        vConfiguracion.getCbxBascula().addItem("SWS_LP7510");
        vConfiguracion.getCbxBascula().addItem("RHINO_I-PAG");
    }

    private void save() {
        this.config.setPort(vConfiguracion.getCbxPuertos().getSelectedItem().toString());
        this.config.setBaudRate(Integer.valueOf(vConfiguracion.getCbxBitsSegundo().getSelectedItem().toString()));
        this.config.setDataBits(Integer.valueOf(vConfiguracion.getCbxBitsDatos().getSelectedItem().toString()));
        this.config.setParity(this.parity(vConfiguracion.getCbxParidad().getSelectedItem().toString()));
        this.config.setStopBits(Integer.valueOf(vConfiguracion.getCbxBitsParada().getSelectedItem().toString()));
        this.config.setBascula(vConfiguracion.getCbxBascula().getSelectedItem().toString());
        try {
            this.serviceConfig.save(this.config);
            vConfiguracion.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vConfiguracion, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vConfiguracion.getBtnGuardar()) && vConfiguracion.getCbxPuertos().getSelectedItem() != null) {
            this.save();
            return;
        }
        JOptionPane.showMessageDialog(vConfiguracion, "No se ha seleccionado puerto COM", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public Integer parity(String parity) {
        Integer parity_;
        switch (parity) {
            case "Par":
                parity_ = 2;
                break;
            case "Inpar":
                parity_ = 1;
                break;
            case "Ninguno":
                parity_ = 0;
                break;
            case "Marca":
                parity_ = 3;
                break;
            case "Espacio":
                parity_ = 4;
                break;
            default:
                parity_ = -1;
        }
        return parity_;
    }

    public String parityText(Integer parity) {
        String parity_;
        switch (parity) {
            case 2:
                parity_ = "Par";
                break;
            case 1:
                parity_ = "Inpar";
                break;
            case 0:
                parity_ = "Ninguno";
                break;
            case 3:
                parity_ = "Marca";
                break;
            case 4:
                parity_ = "Espacio";
                break;
            default:
                parity_ = "Desconocido";
        }
        return parity_;
    }
}
