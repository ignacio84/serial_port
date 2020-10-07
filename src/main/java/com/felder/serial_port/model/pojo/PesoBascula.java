
package com.felder.serial_port.model.pojo;
public class PesoBascula {
    
    
    private Integer id;
    
    private String peso;
    
    private String hora;
    
    private String fecha;
    
    private String um;
    
    private String bascula;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getBascula() {
        return bascula;
    }

    public void setBascula(String bascula) {
        this.bascula = bascula;
    }

    @Override
    public String toString() {
        return "PesoBascula{" + "id=" + id + ", peso=" + peso + ", hora=" + hora + ", fecha=" + fecha + ", um=" + um + ", bascula=" + bascula + '}';
    }
    
}