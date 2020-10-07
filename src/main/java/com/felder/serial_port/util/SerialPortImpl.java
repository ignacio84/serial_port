package com.felder.serial_port.util;

import com.felder.serial_port.model.pojo.Configuracion;
import com.felder.serial_port.model.pojo.PesoBascula;
import java.awt.Frame;
import java.awt.TrayIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialPortImpl implements ISerialPort, SerialPortEventListener {

    private static String FILTER[] = {" ", "GROSS", "Time:", "Date:", "ID.NO.", "\\r\\n", "\\r", "\\n"};
    private static Integer BYTES_SIZE = 102;//TAMAÑO DEL BUFFER ENVIADO POR LA BASCULA
    private jssc.SerialPort port;
    private String strData;
    private Configuracion config;
    private JFrame frame;
    private TrayIcon trayIcon;
    private JTextArea txa;
    private ICallApiRest apiRest;
    private PesoBascula pesoBascula;

    public SerialPortImpl() {
        this.apiRest = new CallApiRestImpl();
    }

    @Override
    public void addEventListener() throws Exception {
        this.openSerialPort();
        if (this.port.isOpened()) {
            int mask
                    = jssc.SerialPort.MASK_RXCHAR
                    + jssc.SerialPort.MASK_CTS
                    + jssc.SerialPort.MASK_DSR
                    + jssc.SerialPort.MASK_ERR
                    + jssc.SerialPort.MASK_RING
                    + jssc.SerialPort.MASK_BREAK;
            this.port.setEventsMask(mask);
            this.port.addEventListener(this);
        }
    }

    public void openSerialPort() throws Exception {
        this.port = new jssc.SerialPort(this.config.getPort());
        this.port.openPort();
        this.port.setParams(this.config.getBaudRate(), this.config.getDataBits(), this.config.getStopBits(), this.config.getParity());
//        port.setParams( jssc.SerialPort.BAUDRATE_9600, jssc.SerialPort.DATABITS_8, jssc.SerialPort.STOPBITS_1, jssc.SerialPort.PARITY_NONE);
//        this.port.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_NONE);
    }

    @Override
    public String[] getPorts() {
        return SerialPortList.getPortNames();
    }

    @Override
    public SerialPort getPort() {
        return this.port;
    }

    @Override
    public void setObject(Object object) {
        this.trayIcon = (TrayIcon) object;
    }

    @Override
    public void writeSerialPort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readSerialPort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setObject_3(Object obj) {
        this.config = (Configuracion) obj;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() == this.BYTES_SIZE) {//bascula SWS_LP7510
            try {
                this.strData = this.port.readString(BYTES_SIZE);
                this.txa.setText(this.strData);
                this.SWS_LP7510();
                this.apiRest.setObject(this.pesoBascula);
                this.apiRest.call();
                if (this.frame.getState() == Frame.ICONIFIED) {
                    trayIcon.displayMessage("Peso recibido desde bascula", strData, TrayIcon.MessageType.NONE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
            }
        }
    }
    
   //bascula SWS_LP7510
    private void SWS_LP7510() {
        this.pesoBascula = new PesoBascula();
        this.pesoBascula.setBascula(this.config.getBascula());
        this.pesoBascula.setPeso(this.filterString(strData.split("\\n")[0]).substring(0, this.filterString(strData.split("\\n")[0]).length() - 2));
        this.pesoBascula.setHora(this.filterString(strData.split("\\n")[1]));
        this.pesoBascula.setFecha(this.filterString(strData.split("\\n")[2]));
        this.pesoBascula.setId(Integer.valueOf(this.filterString(strData.split("\\n")[3])));
        this.pesoBascula.setUm(this.filterString(strData.split("\\n")[0]).substring(this.filterString(strData.split("\\n")[0]).length() - 2).toUpperCase());
    }


    private String filterString(String str) {
        for (String string : SerialPortImpl.FILTER) {
            str = str.replaceAll(string, "");
        }
        str.trim();
        return str;
    }

    @Override
    public void setObject_2(Object obj) {
        this.frame = (JFrame) obj;
    }

    @Override
    public void closeSerialPort() throws Exception {
        if (this.port != null && this.port.isOpened()) {
            this.port.removeEventListener();
            this.port.purgePort(SerialPort.PURGE_RXABORT);
            this.port.purgePort(SerialPort.PURGE_RXCLEAR);
            this.port.purgePort(SerialPort.PURGE_TXABORT);
            this.port.purgePort(SerialPort.PURGE_TXCLEAR);
            this.port.closePort();
            this.port = null;
            System.out.println("CLOSE COM");
        }
    }

    @Override
    public void setObject_1(Object obj) {
        this.txa = (JTextArea) obj;
    }
}
