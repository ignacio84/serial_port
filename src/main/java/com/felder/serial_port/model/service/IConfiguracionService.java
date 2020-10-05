package com.felder.serial_port.model.service;

public interface IConfiguracionService<C> {

    public C get() throws Exception;

    public void save(C config) throws Exception;

}
