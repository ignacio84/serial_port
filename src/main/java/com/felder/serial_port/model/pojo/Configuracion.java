
package com.felder.serial_port.model.pojo;


public class Configuracion {
    private String port;
    private Integer baudRate;
    private Integer dataBits;
    private Integer stopBits;
    private Integer parity;
 
    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public Integer getDataBits() {
        return dataBits;
    }

    public void setDataBits(Integer dataBits) {
        this.dataBits = dataBits;
    }

    public Integer getStopBits() {
        return stopBits;
    }

    public void setStopBits(Integer stopBits) {
        this.stopBits = stopBits;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Configuracion{" + "port=" + port + ", baudRate=" + baudRate + ", dataBits=" + dataBits + ", stopBits=" + stopBits + ", parity=" + parity + '}';
    }
}
