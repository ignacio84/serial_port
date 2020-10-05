
package com.felder.serial_port.model.service;

import com.felder.serial_port.model.dao.ConfiguracionDaoImpl;
import com.felder.serial_port.model.dao.IConfiguracionDao;
import com.felder.serial_port.model.pojo.Configuracion;


public class ConfiguracionServiceImpl implements IConfiguracionService<Configuracion>{
    
    private IConfiguracionDao configDao;

    public ConfiguracionServiceImpl(IConfiguracionDao configDao) {
        this.configDao = configDao;
    }

    @Override
    public Configuracion get() throws Exception  {
        return (Configuracion) this.configDao.get();
    }

    @Override
    public void save(Configuracion config) throws Exception {
        this.configDao.save(config);
    }
}
