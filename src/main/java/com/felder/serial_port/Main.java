package com.felder.serial_port;

import com.felder.serial_port.controller.PrincipalController;
import java.util.Properties;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
         Properties props = new Properties();
        try {
            props.put("logoString", "");
            props.put("textShadow", "on");
            props.put("windowDecoration", "on");
            props.put("systemTextFont", "Agency FB BOLD 17");
            props.put("controlTextFont", "Agency FB BOLD 17");
            props.put("menuTextFont", "Agency FB BOLD 17");
            props.put("userTextFont", "Agency FB BOLD 17");
            props.put("subTextFont", "Agency FB BOLD 17");
            props.put("windowTitleFont", "Agency FB BOLD 17");
            com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new PrincipalController();
    }
}
