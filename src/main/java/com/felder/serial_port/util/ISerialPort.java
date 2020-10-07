package com.felder.serial_port.util;

import com.felder.serial_port.model.pojo.Configuracion;
import java.awt.TrayIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public interface ISerialPort {

    public void addEventListener() throws Exception;

    public String[] getPorts();

    public jssc.SerialPort getPort();

    public void setObject(Object obj);
    
    public void setObject_1(Object obj);
    
    public void setObject_2(Object obj);
    
    public void setObject_3(Object obj);

    public void writeSerialPort();

    public void readSerialPort();

    public void closeSerialPort() throws Exception;

    public void openSerialPort() throws Exception;

}
