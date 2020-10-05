package com.felder.serial_port.util;

import com.felder.serial_port.model.pojo.Configuracion;
import java.awt.Frame;
import java.awt.TrayIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialPortImpl implements ISerialPort, SerialPortEventListener {

    private static String FILTER[] = {" ", "GROSS", "kg", "Time:", "Date:", "ID.NO."};
    private static Integer BYTES_SIZE = 102;//TAMAÑO DEL BUFFER ENVIADO POR LA BASCULA
    private jssc.SerialPort port;
    private String strData;
    private Configuracion config;
    private JFrame frame;
    private TrayIcon trayIcon;
    private JTextArea txa;

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
    public void setTrayIcon(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
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
    public void setConfiguration(Configuracion config) {
        this.config = config;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        System.out.println(event.getEventValue());
        //CONFIGURACION PARA LA BASCULA
        if (event.isRXCHAR() && event.getEventValue() == 95) {
            try {
                this.strData = this.port.readString(95);
                this.txa.setText(this.strData);
                System.out.println(this.strData);
                System.out.println(this.filterString(strData));
                //System.out.println("numero de saltos :"+strData.split("\\n").length);// 7 saltos de linea lectura bascula
                System.out.println("numero de saltos :" + strData.split("\\n").length);
                if (this.frame.getState() == Frame.ICONIFIED) {
                    trayIcon.displayMessage("Peso recibido desde bascula", this.filterString(strData), TrayIcon.MessageType.NONE);
                }
            } catch (SerialPortException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private String filterString(String str) {
        for (String string : SerialPortImpl.FILTER) {
            str = str.replaceAll(string, "");
        }
        return str;
    }

    @Override
    public void setFrame(JFrame frame) {
        this.frame = frame;
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
    public void setTextArea(JTextArea txa) {
        this.txa = txa;
    }
}
