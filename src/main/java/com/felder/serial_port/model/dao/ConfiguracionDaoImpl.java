package com.felder.serial_port.model.dao;

import com.felder.serial_port.model.pojo.Configuracion;
import com.felder.serial_port.util.Json;

public class ConfiguracionDaoImpl implements IConfiguracionDao<Configuracion> {

    private Json json = new Json();

    @Override
    public Configuracion get() throws Exception {
        return (Configuracion) json.readJson("config.json", "com.felder.serial_port.model.pojo.Configuracion");
    }

    @Override
    public void save(Configuracion config) throws Exception {
        json.createJson(config, "config.json");
    }

}
