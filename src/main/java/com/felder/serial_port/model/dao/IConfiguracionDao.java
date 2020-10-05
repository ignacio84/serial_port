
package com.felder.serial_port.model.dao;


public interface IConfiguracionDao <C>{
    
    public C get() throws Exception ;
    
    public void save(C config) throws Exception ;
    
}
