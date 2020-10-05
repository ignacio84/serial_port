package com.felder.serial_port.util;

import com.felder.serial_port.model.pojo.Configuracion;
import java.awt.TrayIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public interface ISerialPort {

    public void addEventListener() throws Exception;

    public String[] getPorts();

    public jssc.SerialPort getPort();

    public void setTrayIcon(TrayIcon trayIcon);
    
    public void setTextArea(JTextArea txa);

    public void writeSerialPort();

    public void readSerialPort();

    public void setConfiguration(Configuracion config);

    public void setFrame(JFrame frame);

    public void closeSerialPort() throws Exception;

    public void openSerialPort() throws Exception;

}
