
package com.felder.serial_port.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

abstract class JDialogAbstract extends JDialog {

    /*VARIABLES PARA EL TAMAÑO Y TITULO DE LA VENTANA*/
    private String strTitulo;
    private Integer intAltura;
    private Integer intAncho;


    /*CONSTANTE CON EL DIRECTORIO DEL ICONO*/
    private static final String DIR_LOGO = "/img/logoMini.png";

    public JDialogAbstract(String strTitulo, Integer intAltura, Integer intAncho) {
        this.strTitulo = strTitulo;
        this.intAltura = intAltura;
        this.intAncho = intAncho;
        this.logoAplicacion();
        this.loadPropsWindows();
    }

    public JDialogAbstract() {
    }
    
    

    /*APLICA PROPIEDADES AL LA VENTANA*/
    protected void loadPropsWindows() {
        this.setLayout(null);
        this.setSize(intAncho, intAltura);
        this.setTitle(strTitulo);
        this.setType(Window.Type.NORMAL);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//PROPIEDAD PARA QUE CUENDO CIERREN LA VENTANA SE TEMINE LA APLICACION DE EJECUTAR
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);
        this.setModal(true);
        this.setAlwaysOnTop(true);
        this.logoAplicacion();
    }

    /*APLICA LOGO A LA VENTANA*/
    protected void logoAplicacion() {
        try {
            this.setIconImage(new ImageIcon(DIR_LOGO).getImage());//DIRECCION DE LA IMAGEN QUE MOSTRARA COMO ICONO
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*APLICA FORMATO A CONTROLES JLABEL*/
    protected void propsJLabel(JLabel lbl, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 22));
        lbl.setForeground(Color.BLACK);
    }

    /*METODO APLICA COLOR ROJO A JLabel RECIBIDO*/
    public void setErrorColorToLbl(JLabel lbl) {
        lbl.setForeground(Color.red);
    }

    /*APLICA FORMATO A CONTROLES JLABEL TITULO*/
    protected void propsJLabelTitulo(JLabel lbl, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 28));
        lbl.setForeground(Color.YELLOW);
    }

    /*METODO APLICA COLOR VERDE A JLabel RECIBIDO*/
    public void setOkColorToLbl(JLabel lbl) {
        lbl.setForeground(new Color(12, 159, 10));
    }

    /*CREA Y APLICA FORMATO A CONTROLES JPANEL*/
    protected void createAddJPanel(Integer x, Integer y, Integer w, Integer h) {
        JPanel pnl = new JPanel();
        this.add(pnl);
        pnl.setBounds(x, y, w, h);
        pnl.setBackground(new Color(10, 10, 10, 34));
        pnl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
    }

    /* PROPIEDADES DEL JFileChooser*/
    protected String createJFileChooser(String[] strArrFiltro) {
        String strDir = "";
        JFileChooser jfc = new JFileChooser("C:");
        //Indicamos que podemos seleccionar varios ficheros
        jfc.setMultiSelectionEnabled(false);
        //Indicamos lo que podemos seleccionar
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //deshabilita opcion todos los archivos
        jfc.setAcceptAllFileFilterUsed(false);
        disableNewFolderButton(jfc);//METODO DESHABILITA BOTON DE CREAR CARPETA NUEVA;
//        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        for (String str : strArrFiltro) {//AGREGA FILTROS
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(str, str);
            jfc.setFileFilter(filtro);
        }
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            File file;
            file = jfc.getSelectedFile();
            strDir = file.toString();
        }
        return strDir;
    }

    /*METODO DESHABILITA BOTON DE CREAR CARPETA Y EL JTEXTFIELD DONDE SE ESCRIBE EL NUMBRE DEL ARCHIVO EN EL JFileChooser jfcOpen */
    private void disableNewFolderButton(Container c) {
        int len = c.getComponentCount();
        for (int i = 0; i < len; i++) {
            Component comp = c.getComponent(i);
            /*DESHABILITA TEXTFIELD DONDE SE ESCRIBE EL DIRECTORIO*/
            if (comp instanceof JTextField) {
                JTextField t = (JTextField) comp;
                t.setEditable(false);
            }
            /*DESHABILITA BOTON DE CREAR CARPETA NUEVA*/
            if (comp instanceof JButton) {
                JButton b = (JButton) comp;

                Icon icon = b.getIcon();
                if (icon != null
                        && icon == UIManager.getIcon("FileChooser.newFolderIcon")) {
                    b.setEnabled(false);
                }
            } else if (comp instanceof Container) {
                disableNewFolderButton((Container) comp);
            }
        }
    }

    /*CREA Y APLICA FORMATO A CONTROLES JLABEL TITULO*/
    protected void createAddJLabelTitulo(String strText, Integer intSizeFont, Color clrFont, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        JLabel lbl = new JLabel(strText);
        lbl.setName("lbl");
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(aling);
        lbl.setFont(new Font("Agency FB", Font.BOLD, intSizeFont));
        lbl.setForeground(clrFont);
    }

    /*APLICA FORMATO A CONTROLES JLABEL DE VALORES VARIABLES*/
    protected void propsJLabelValues(JLabel lbl, Integer size, Color c, Integer align, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);

//        lbl.setOpaque(true);
//        lbl.setBackground(Color.red);
        lbl.setVerticalAlignment(SwingConstants.CENTER);

        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(align);
//        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setFont(new Font("Agency FB", Font.BOLD, size));
        lbl.setForeground(c);
    }

    /*CREA Y APLICA FORMATO A CONTROLES JLABEL TITULO*/
    protected void createAddJLabelTituloTbPanel(JPanel pnl, String strText, Integer intSizeFont, Color clrFont, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        JLabel lbl = new JLabel(strText);
        lbl.setName("lbl");
        pnl.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(aling);
        lbl.setFont(new Font("Agency FB", Font.BOLD, intSizeFont));
        lbl.setForeground(clrFont);

    }

    /*METODO APLICA PROPIEDADES AL CHECKBOX*/
    protected void propsJCheckBox(JCheckBox chk, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        this.add(chk);
        chk.setBounds(x, y, w, h);
        chk.setHorizontalTextPosition(aling);
        chk.setOpaque(false);
        chk.setFont(new Font("Agency FB", Font.BOLD, 20));
        chk.setForeground(Color.BLACK);
        chk.setFocusable(false);
        chk.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JCheckBox) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO APLICA PROPIEDADES AL JSpinner*/
    protected void propsJSpinner(JSpinner spn, Integer x, Integer y, Integer w, Integer h) {
        this.add(spn);
        spn.setBounds(x, y, w, h);
        spn.setFont(new Font("Agency FB", Font.BOLD, 20));
        spn.setForeground(Color.BLACK);
        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        spn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JSpinner) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO APLICA PROPIEDADES AL JSpinner*/
    protected void propsJSpinnerTbPanel(JPanel pnl, JSpinner spn, Integer x, Integer y, Integer w, Integer h) {
        pnl.add(spn);
        spn.setBounds(x, y, w, h);
        spn.setFont(new Font("Agency FB", Font.BOLD, 20));
        spn.setForeground(Color.BLACK);
        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        spn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JSpinner) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO APLICA PROPIEDADES AL JSpinner*/
    protected void propsJSpinnerEditable(JSpinner spn, Integer x, Integer y, Integer w, Integer h) {
        this.add(spn);
        spn.setBounds(x, y, w, h);
        spn.setFont(new Font("Agency FB", Font.BOLD, 20));
        spn.setForeground(Color.BLACK);
//        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        spn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JSpinner) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO APLICA PROPIEDADES AL JComboBox*/
    protected void propsJComboBox(JComboBox cbx, Integer x, Integer y, Integer w, Integer h) {
        this.add(cbx);
        cbx.setBounds(x, y, w, h);
        ((JLabel)cbx.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        cbx.setFont(new Font("Agency FB", Font.BOLD, 20));
        cbx.setForeground(Color.BLACK);
        cbx.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);//PROPIEDAD PERMITE BUSCAR CON LA TECLAS Y HASTA QUE PRESIONA ENTER LO MARCA COMO ITEM SELECT
        cbx.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JComboBox) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO APLICA PROPIEDADES AL JComboBox*/
    protected void propsJComboBoxTbPanel(JPanel pnl, JComboBox cbx, Integer x, Integer y, Integer w, Integer h) {
        pnl.add(cbx);
        cbx.setBounds(x, y, w, h);
//        lbl.setHorizontalAlignment(intAlingCbx);
        cbx.setFont(new Font("Agency FB", Font.BOLD, 20));
        cbx.setForeground(Color.BLACK);
        ((JLabel) cbx.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);//CONTENIDO CENTRADO
        cbx.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);//PROPIEDAD PERMITE BUSCAR CON LA TECLAS Y HASTA QUE PRESIONA ENTER LO MARCA COMO ITEM SELECT
        cbx.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JComboBox) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO APLICA PROPIEDADES AL JProgressBar*/
    protected void propsJProgressBar(JProgressBar pbr, Integer x, Integer y, Integer w, Integer h) {
        this.add(pbr);
        pbr.setBounds(x, y, w, h);
//        lbl.setHorizontalAlignment(intAlingCbx);
        pbr.setFont(new Font("Agency FB", Font.BOLD, 20));
        pbr.setForeground(new Color(139, 7, 19));
        pbr.setEnabled(false);
        pbr.setStringPainted(true);
    }

    /*METODO APLICA PROPIEDADES AL JComboBox*/
    protected void propsJComboBoxAutocomplete(JComboBox cbx, Integer x, Integer y, Integer w, Integer h) {
        this.add(cbx);
        cbx.setBounds(x, y, w, h);
//        AutoCompleteDecorator.decorate(cbx);
//        AutoCompletion.enable(cbx);
//        lbl.setHorizontalAlignment(intAlingCbx);
        cbx.setFont(new Font("Agency FB", Font.BOLD, 20));
        cbx.setForeground(Color.BLACK);
        cbx.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);//PROPIEDAD PERMITE BUSCAR CON LA TECLAS Y HASTA QUE PRESIONA ENTER LO MARCA COMO ITEM SELECT
        cbx.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JComboBox) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*CREA Y APLICA FORMATO A CONTROLES JLABEL*/
    protected void createAddJLabel(String strText, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        JLabel lbl = new JLabel(strText);
        lbl.setName("lbl");
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(aling);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 20));
        lbl.setForeground(Color.BLACK);
    }

    /*APLICA FORMATO A CONTROLES JTEXTFIELD */
    protected void propsJTextField(JTextField txt, Integer x, Integer y, Integer w, Integer h) {
        this.add(txt);
        txt.setBounds(x, y, w, h);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setFont(new Font("Arial", Font.BOLD, 15));
        txt.setForeground(Color.BLACK);
        txt.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JTextField) e.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }
        });
    }

    /*APLICA FORMATO A CONTROLES JTEXTFIELD */
    protected void propsJTextFieldDecimal(JTextField txt, Integer x, Integer y, Integer w, Integer h) {
        this.add(txt);
        txt.setBounds(x, y, w, h);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setFont(new Font("Arial", Font.BOLD, 15));
        txt.setForeground(Color.BLACK);
        txt.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JTextField) e.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }
        });
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_PERIOD || (c == KeyEvent.VK_PERIOD && txt.getText().indexOf(".") >= 0)) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /*APLICA FORMATO A CONTROL JTEXTFIELD EDITABLE SOLO NUMERICO */
    protected void propsJTextFieldNumeric(JTextField txt, Boolean bolEditable, Integer x, Integer y, Integer w, Integer h) {
        this.add(txt);
        txt.setBounds(x, y, w, h);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setFont(new Font("Arial", Font.BOLD, 15));
        txt.setForeground(Color.BLACK);
        txt.setEditable(bolEditable);
        txt.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JTextField) e.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }
        });
        txt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /*APLICA FORMATO A CONTROLES JTEXTFIELD */
    protected void propsJTextFieldEditable(JTextField txt, Boolean bolEditable, Integer x, Integer y, Integer w, Integer h) {
        this.add(txt);
        txt.setBounds(x, y, w, h);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setFont(new Font("Arial", Font.BOLD, 15));
        txt.setForeground(Color.BLACK);
        txt.setEditable(bolEditable);
        txt.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JTextField) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*APLICA FORMATO A CONTROLES PASSWORDFIELD*/
    protected void propsJPasswordField(JPasswordField pxt, Integer x, Integer y, Integer w, Integer h) {
        this.add(pxt);
        pxt.setBounds(x, y, w, h);
        pxt.setHorizontalAlignment(SwingConstants.CENTER);
        pxt.setFont(new Font("Arial", Font.BOLD, 15));
        pxt.setForeground(Color.BLACK);
        pxt.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JPasswordField) e.getSource()).setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }
        });
    }

    /*CREA Y APLICA FORMATO A CONTROLES JBUTTON*/
    protected void propsJButton(JButton btn, Integer x, Integer y, Integer w, Integer h) {
        this.add(btn);
        btn.setBounds(x, y, w, h);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFont(new Font("Agency FB", Font.BOLD, 20));
        btn.setForeground(Color.BLACK);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        btn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*CREA Y APLICA FORMATO A CONTROLES JBUTTON*/
    protected void propsJButtonTbPanel(JPanel pnl, JButton btn, Integer x, Integer y, Integer w, Integer h) {
        pnl.add(btn);
        btn.setBounds(x, y, w, h);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFont(new Font("Agency FB", Font.BOLD, 20));
        btn.setForeground(Color.BLACK);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        btn.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    //AGREGA MENUS A LA BARRA
    protected void addMenuBar(JMenuBar mnuBar, JMenu menMen, int evtTcl) {
        if (mnuBar != null) {
            mnuBar.add(menMen);
            menMen.setMnemonic(evtTcl);//ACCESO DIRECTO CONBINACION DE TECLAS ALT+TECLA        
        } else {
            System.err.println("Error no se ha inicializado el JMenuBar ");
        }
    }

    //AGREGA LOS ITEM A LOS JMenu Y AGREGA ACCESOS DIRECTOS ALT+LETRA O CTRL+LETRA
    protected void addItemMenu(JMenu mnuMenu, JMenuItem iteItemMenu, String strIcon, int intAcce) {
        iteItemMenu.setMnemonic(intAcce);
        iteItemMenu.setAccelerator(KeyStroke.getKeyStroke(intAcce, java.awt.Event.CTRL_MASK));
        try {
            iteItemMenu.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator + strIcon));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        mnuMenu.add(iteItemMenu);
    }

    /*METODO AGREGA JLABELS AL JMenu*/
    protected void addLblToMenuBar(JMenuBar menuBar, JLabel lbl, Color clr) {
        try {
            menuBar.add(Box.createHorizontalGlue());//PROPIEDA PARA PODER ALINEAR EL JLABEL QUE SE AGREGARA HACIA LA DERECHA DENTRO DEL MENU 
            lbl.setForeground(clr);
            menuBar.add(lbl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

 
    /*APLICA FORMATO AL JScrollPane*/
    protected void propsJScrollPane(JScrollPane scrl, Integer x, Integer y, Integer w, Integer h) {
        this.add(scrl);
        scrl.setBounds(x, y, w, h);
//        scrl.setOpaque(false);
//        scrl.getViewport().setOpaque(false);
//        scrl.setBorder(null);   
    }

    /*APLICA FORMATO A CONTROLES JLABEL DE VALORES VARIABLES*/
    protected void propsJLabelValues(JLabel lbl, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 30));
        lbl.setForeground(new Color(12, 159, 10));
    }

    /*APLICA FORMATO AL JTextArea */
    public void propsJTextArea(JTextArea txa) {
        txa.setLineWrap(true);
        txa.setEditable(false);
        txa.enable(false);
    }

    /*OBTIENE LISTADO DE TODOS LOS CONTROLES EN PANTALLA LIMPIA INFORMACION DE CICHOS CONTROLES*/
    public void clearForm() {
        Component[] componentes = this.getContentPane().getComponents();
        for (Component com : componentes) {
            this.clearControl(com);
        }
    }

    /*LIMPIA LIMPIA INFORMACION DEL CONTROL QUE SE RECIBE*/
    private void clearControl(Component component) {
        switch (component.getClass().getName()) {
            case "javax.swing.JLabel":
                ((JLabel) component).setText((component.getName() == null) ? "" : ((JLabel) component).getText());
                break;
            case "javax.swing.JScrollPane":
                for (Component comp : ((JScrollPane) component).getViewport().getComponents()) {//OBTIENE CONTROLES AGREGADOS AL SCROLLPANEL
                    if (comp.getClass().getName().equals("javax.swing.JTextArea")) {//SI EL CONTROL AGREGADO AL SCROLLPANEL ES UN JTextArea
                        this.clearControl(comp);
                    } else if (comp instanceof JTable) {//SI EL CONTROL AGREGADO AL SCOLLPANEL ES UN JTable
                        this.clearTable((DefaultTableModel) ((JTable) comp).getModel());//LLAMA METODO QUE LIMPIA 
                    }
                }
                break;
            case "javax.swing.JTextArea":
                ((JTextArea) component).setText((component.getName() == null) ? "" : ((JTextArea) component).getText());
                break;
            case "javax.swing.JTextField":
                ((JTextField) component).setText((component.getName() == null) ? "" : ((JTextField) component).getText());
                break;
//            case "com.toedter.calendar.JDateChooser":
//                ((JDateChooser) component).setDate(((JDateChooser) component).getName() == null ? null : ((JDateChooser) component).getDate());
//                break;

            case "javax.swing.JSpinner":
                ((JSpinner) component).setValue(((JSpinner) component).getName() == null ? 1 : ((JSpinner) component).getValue());
                break;
//           case "javax.swing.JComboBox":
//                ((JComboBox) component).setSelectedIndex(0);
//                break;
//                default:
//                    this.controlCodes = new ControlBarCodes(this.strReadTemp);
//                    if (this.controlCodes.getEtq() != null) {
//                        this.barCode(this.controlCodes.getEtq());
//                    } else {
//                        this.setMsjErrorToView("Codigo No Valido!!");
//                    }
//                    break;
            }
    }

  

    /*METODO RECIBE MODELO DE TABLA PARA LIMPIARLO*/
    public void clearTable(DefaultTableModel model) {
        int a = model.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            model.removeRow(model.getRowCount() - 1);
        }
    }

    /*DESACTIVA COMPONENTES DEL FORMULARIO*/
    public void disabledForm() {
        Component[] componentes = this.getContentPane().getComponents();
        for (Component com : componentes) {
            this.disableControl(com);
        }
    }

    /*DESACTIVA COMPONENTE*/
    private void disableControl(Component component) {
        switch (component.getClass().getName()) {
            case "javax.swing.JScrollPane":
                for (Component comp : ((JScrollPane) component).getViewport().getComponents()) {//OBTIENE CONTROLES AGREGADOS AL SCROLLPANEL
                    if (comp.getClass().getName().equals("javax.swing.JTextArea")) {//SI EL CONTROL AGREGADO AL SCROLLPANEL ES UN JTextArea
                        this.clearControl(comp);
                    } else if (comp instanceof JTable) {//SI EL CONTROL AGREGADO AL SCOLLPANEL ES UN JTable
                        this.clearTable((DefaultTableModel) ((JTable) comp).getModel());//LLAMA METODO QUE LIMPIA 
                    }
                }
                break;
            case "javax.swing.JTextArea":
                ((JTextArea) component).setEnabled(false);
                break;
            case "javax.swing.JTextField":
                ((JTextField) component).setEnabled(false);
                break;
//            case "com.toedter.calendar.JDateChooser":
//                ((JDateChooser) component).setEnabled(false);
//                break;
            case "javax.swing.JSpinner":
                ((JSpinner) component).setEnabled(false);
                break;
            case "javax.swing.JComboBox":
                ((JComboBox) component).setEnabled(false);
                break;
        }
    }

    /*ACTIVA COMPONENTES DEL FORMULARIO*/
    public void enabledForm() {
        Component[] componentes = this.getContentPane().getComponents();
        for (Component com : componentes) {
            this.enabledControl(com);
        }
    }

    /*ACTIVA COMPONENTE*/
    private void enabledControl(Component component) {
        switch (component.getClass().getName()) {
            case "javax.swing.JScrollPane":
                for (Component comp : ((JScrollPane) component).getViewport().getComponents()) {//OBTIENE CONTROLES AGREGADOS AL SCROLLPANEL
                    if (comp.getClass().getName().equals("javax.swing.JTextArea")) {//SI EL CONTROL AGREGADO AL SCROLLPANEL ES UN JTextArea
                        this.clearControl(comp);
                    } else if (comp instanceof JTable) {//SI EL CONTROL AGREGADO AL SCOLLPANEL ES UN JTable
                        this.clearTable((DefaultTableModel) ((JTable) comp).getModel());//LLAMA METODO QUE LIMPIA 
                    }
                }
                break;
            case "javax.swing.JTextArea":
                ((JTextArea) component).setEnabled(true);
                break;
            case "javax.swing.JTextField":
                ((JTextField) component).setEnabled(true);
                break;
//            case "com.toedter.calendar.JDateChooser":
//                ((JDateChooser) component).setEnabled(true);
//                break;
            case "javax.swing.JSpinner":
                ((JSpinner) component).setEnabled(true);
                break;
            case "javax.swing.JComboBox":
                ((JComboBox) component).setEnabled(true);
                break;
        }
    }

    /*MENSAJE OK*/
    public void JOptionPaneOk(String strMsj) {
        JOptionPane.showMessageDialog(this, strMsj, "Mensaje", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getResourceAsStreamImg("/com/felder/img/Check_48x48.png")));
    }

    /*METODO CARGA IMAGEN MEDIANTE getResourceAsStream PARA PODER UTILIZAR IMAGENENES DENTRO EL ARCHIVO JAR FINAL */
    protected Image getResourceAsStreamImg(String strDirImg) {
        Image img = null;//LEE IMAGEN OBTENIDA
        try {
            InputStream inAbsoluto = getClass().getResourceAsStream(strDirImg);//OBTIENE IMAGEN DE ADRETO DEL JAR MEDIANTE getClass().getResourceAsStream
            img = ImageIO.read(inAbsoluto);//LEE IMAGEN OBTENIDA
            return img;
        } catch (IOException ex) {
        }
        return img;
    }

    public void setStrTitulo(String strTitulo) {
        this.strTitulo = strTitulo;
    }

    public void setIntAltura(Integer intAltura) {
        this.intAltura = intAltura;
    }

    public void setIntAncho(Integer intAncho) {
        this.intAncho = intAncho;
    }
    
    
    
}
