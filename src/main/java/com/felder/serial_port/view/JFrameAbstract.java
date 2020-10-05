
package com.felder.serial_port.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


abstract class JFrameAbstract extends JFrame {

    /*VARIABLES PARA EL TAMAÑO Y TITULO DE LA VENTANA*/
    private String strTitulo;
    private Integer intAltura;
    private Integer intAncho;

    /*CONSTANTE CON EL DIRECTORIO DEL ICONO*/
    private static final String DIR_LOGO = "/img/com_port.png";

    public JFrameAbstract(String strTitulo, Integer intAltura, Integer intAncho) {
        this.strTitulo = strTitulo;
        this.intAltura = intAltura;
        this.intAncho = intAncho;
        this.logoAplicacion();
        this.propiedadesVentana();
    }

    public JFrameAbstract() {

    }

    /*APLICA PROPIEDADES AL LA VENTANA*/
    protected void propiedadesVentana() {
        this.setLayout(null);
        this.setSize(intAncho, intAltura);
        this.setTitle(strTitulo);
        this.setType(Window.Type.NORMAL);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.logoAplicacion();
    }

    /*APLICA LOGO A LA VENTANA*/
    protected void logoAplicacion() {
        try {
            InputStream inAbsoluto = getClass().getResourceAsStream(DIR_LOGO);//OBTIENE IMAGEN DE ADRETO DEL JAR MEDIANTE getClass().getResourceAsStream
            
            Image img = ImageIO.read(inAbsoluto);//LEE IMAGEN OBTENIDA
            this.setIconImage(img);//DIRECCION DE LA IMAGEN QUE MOSTRARA COMO ICONO
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*APLICA FORMATO A CONTROLES JLABEL*/
    protected void propsJLabel(JLabel lbl, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(aling);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 22));
        lbl.setForeground(Color.BLUE);
    }

    /*APLICA FORMATO A CONTROLES JLABEL TITULO*/
    protected void propsJLabelTitulo(JLabel lbl, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);

        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(SwingConstants.RIGHT);

        lbl.setFont(new Font("Agency FB", Font.BOLD, 28));
        lbl.setForeground(Color.YELLOW);
    }

    /*APLICA FORMATO A CONTROLES JLABEL DE VALORES VARIABLES*/
    protected void propsJLabelValues(JLabel lbl, Integer size, Color c, Integer align, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);
        lbl.setName("lbl");
//        lbl.setOpaque(true);
//        lbl.setBackground(Color.red);
        lbl.setVerticalAlignment(SwingConstants.CENTER);

        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(align);
//        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setFont(new Font("Agency FB", Font.BOLD, size));
        lbl.setForeground(c);
    }

    /*APLICA FORMATO A CONTROLES JLABEL DE VALORES VARIABLES*/
    protected void propsJLabelValueMsj(JLabel lbl, Integer x, Integer y, Integer w, Integer h) {
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 60));
        lbl.setForeground(new Color(12, 159, 10));
    }

    /*METODO APLICA COLOR ROJO A JLabel RECIBIDO*/
    public void setErrorColorToLbl(JLabel lbl) {
        lbl.setForeground(Color.red);
    }

    /*METODO APLICA COLOR VERDE A JLabel RECIBIDO*/
    public void setOkColorToLbl(JLabel lbl) {
        lbl.setForeground(new Color(12, 159, 10));
    }

    /*APLICA FORMATO AL JScrollPane*/
    public void propsJScrollPane(JScrollPane scrl, Integer x, Integer y, Integer w, Integer h) {
        this.add(scrl);
        scrl.setBounds(x, y, w, h);
        scrl.setOpaque(false);
        scrl.getViewport().setOpaque(false);
//        scrl.setBorder(null);   
    }

    /*APLICA FORMATO AL JTextArea */
    public void propsJTextArea(JTextArea txa) {
        txa.setLineWrap(true);
        txa.setOpaque(false);
        txa.setBackground(new Color(0,0,0,0));
//        txa.setEditable(false);
//        txa.enable(false);
    }
    
    

    /*CREA Y APLICA FORMATO A CONTROLES JPANEL*/
    protected void createAddJPanel(Integer x, Integer y, Integer w, Integer h) {
        JPanel pnl = new JPanel();
        this.add(pnl);
//        this.add(pnl);
        pnl.setBounds(x, y, w, h);
        pnl.setBackground(new Color(10, 10, 10, 34));
        pnl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
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

    /*CREA Y APLICA FORMATO A CONTROLES JLABEL*/
    protected void createAddJLabel_1(String strText, Integer aling, Integer x, Integer y, Integer w, Integer h) {
        JLabel lbl = new JLabel(strText);
        lbl.setName("lbl");
        this.add(lbl);
        lbl.setBounds(x, y, w, h);
        lbl.setHorizontalAlignment(aling);
        lbl.setFont(new Font("Agency FB", Font.BOLD, 23));
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

    protected void propsJProgressBar(JProgressBar bar, Integer intSizeFont, Color colorBar, Integer x, Integer y, Integer w, Integer h) {
        this.add(bar);
        bar.setBounds(x, y, w, h);
        bar.setFont(new Font("Arial", Font.BOLD, intSizeFont));
        bar.setForeground(colorBar);
        bar.setStringPainted(true);
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
                if (!(c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9) && c != KeyEvent.VK_ENTER && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_PERIOD || (c == KeyEvent.VK_PERIOD && txt.getText().indexOf(".") >= 0)) {
                    getToolkit().beep();
                    e.consume();
                }
                if (txt.getText().matches("^\\d{3}$") || txt.getText().matches("^[1-9]\\d*(\\.\\d{2})$") || txt.getText().matches("^\\.\\d{2}$")) {
                    e.consume();
                }
                if (txt.getText().length() == 4 && !txt.getText().contains(".")) {
                    e.consume();
                }
            }

            @Override

            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        }
        );
    }

    /*METODO APLICA PROPIEDADES AL JComboBox*/
    protected void propsJComboBox(JComboBox cbx, Integer x, Integer y, Integer w, Integer h) {
        this.add(cbx);
        cbx.setBounds(x, y, w, h);
//        cbx.setName("cbx");
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

    /*METODO APLICA PROPIEDADES AL JSpinner*/
    protected void propsJSpinner(JSpinner spn, Integer x, Integer y, Integer w, Integer h) {
        this.add(spn);
        spn.setBounds(x, y, w, h);
        spn.setFont(new Font("Agency FB", Font.BOLD, 20));
        spn.setForeground(Color.BLACK);
        ((JSpinner.DefaultEditor) spn.getEditor()).getTextField().setEditable(true);
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
//        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spn.getEditor();
//        JTextField textField = editor.getTextField();
//        textField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                System.out.println(e);
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                System.out.println(e);
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                System.out.println(e);
//            }
//        });

//        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spn.getEditor();
//        editor.getTextField().addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                editor.getTextField().selectAll();
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//            }
//        });
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
    protected void propsJCheckBox(JCheckBox chk, Integer txtPostition, Integer x, Integer y, Integer w, Integer h) {
        this.add(chk);
        chk.setBounds(x, y, w, h);
        chk.setOpaque(false);
        chk.setFocusable(false);
        chk.setHorizontalTextPosition(txtPostition);
        chk.setHorizontalAlignment(SwingConstants.RIGHT);
        chk.setFont(new Font("Agency FB", Font.BOLD, 20));
        chk.setForeground(Color.BLACK);
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
    }

    /*AGREGA MENUS A LA BARRA*/
    protected void addMenuBar(JMenuBar mnuBar, JMenu menMen, int evtTcl) {
        if (mnuBar != null) {
            mnuBar.add(menMen);
            menMen.setMnemonic(evtTcl);//ACCESO DIRECTO CONBINACION DE TECLAS ALT+TECLA        
        } else {
            System.err.println("Error no se ha inicializado el JMenuBar ");
        }
        menMen.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JMenu) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*AGREGA LOS ITEM A LOS JMenu Y AGREGA ACCESOS DIRECTOS ALT+LETRA O CTRL+LETRA*/
    protected void addItemMenu(JMenu mnuMenu, JMenuItem iteItemMenu, Image img, int intAcce) {
        iteItemMenu.setMnemonic(intAcce);
        iteItemMenu.setAccelerator(KeyStroke.getKeyStroke(intAcce, java.awt.Event.CTRL_MASK));
        try {
            iteItemMenu.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        mnuMenu.add(iteItemMenu);
        iteItemMenu.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((JMenuItem) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    /*METODO AGREGA JLABELS AL JMenu*/
    protected void addLblToMenuBar(JMenuBar menuBar, JLabel lbl, JButton btn, Color clr) {

        try {
            menuBar.add(Box.createHorizontalGlue());//PROPIEDA PARA PODER ALINEAR EL JLABEL QUE SE AGREGARA HACIA LA DERECHA DENTRO DEL MENU 
            lbl.setForeground(clr);
            menuBar.add(lbl);
            menuBar.add(btn);
            btn.setHorizontalAlignment(SwingConstants.CENTER);
            btn.setFocusable(false);
            btn.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    ((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    /*APLICA PROPIEDADES AL JTable Y LO RETORNA*/
//    public JTable propsJTable(String[] strNomColum, Integer[] arrTamCol) {
//        /*SE CREA DefaultTableModel CONTENDRA TODOS LOS DATOS DE LA TABLA */
//        DefaultTableModel dtm = new DefaultTableModel(strNomColum, 0) {/*HACE QUE LAS CELDAS SEAN NO EDITABLES*/
//            public boolean isCellEditable(int row, int column) {
//                if (column == 2) {
//                    return false;
//                } else {
//                    return false;
//                }
//            }
//        };
//        /*SE CREA TABLA*/
//        JTable tbl = new TableCrossOver(dtm, new Font("Agency FB", Font.BOLD, 21));
//
//        /*ELIMINA LINEAS INTERNAS DE LA TABLA*/
////        tbl.setShowGrid(false);
//
//        /* ALTURA DEL RENGLON */
//        tbl.setRowHeight(25);
//
//        /*DESHABILITA AJUSTE AUTOMATICO DE LAS COLUMNAS*/
//        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        //DESHABILITA SELECCION MULTIPLE
//        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        /*NO ARRASTRAR COLUMNA*/
//        tbl.getTableHeader().setReorderingAllowed(false);
////        System.out.println("hola:" + tblSup.getModel().getRowCount());
//
//        //CAMBIA EL CURSOR A MANITA CUANDO PASA SOBRE LA TABLA
//        tbl.setCursor(new Cursor(HAND_CURSOR));
//
//        //CAMBIA EL CURSOR A MANITA CUANDO PASA SOBRE LA CABECERA DE LA TABLA
//        tbl.getTableHeader().setCursor(new Cursor(HAND_CURSOR));
//
//        //PROPIEDAD PARA ORDENAR AL HACER CLICK EN EL ENCAVEZADO
//        tbl.setAutoCreateRowSorter(true);
//
//        //PROPIEDAD LE DA ESTILO AL ENCABEZADO DE LA TABLA
//        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//        tcr.setHorizontalAlignment(SwingConstants.CENTER);
//
//        for (int i = 0; i < tbl.getColumnModel().getColumnCount(); i++) {
//            tbl.getColumnModel().getColumn(i).setCellRenderer(tcr);
//            tbl.getColumnModel().getColumn(i).setPreferredWidth(arrTamCol[i]);
//        }
//        return tbl;
//    }
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
            Logger.getLogger(JFrameAbstract.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return img;
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
            case "javax.swing.JSpinner":
                ((JSpinner) component).setValue((component.getName() == null) ? 1 : ((JSpinner) component).getValue());
                break;
//            case "com.toedter.calendar.JDateChooser":
//                ((JDateChooser) component).setDate((component.getName() == null) ? null : ((JDateChooser) component).getDate());
//                break;
            case "javax.swing.JComboBox":
                if (((JComboBox) component).getItemCount() > 0) {
                    ((JComboBox) component).setSelectedIndex((component.getName() == null) ? 0 : ((JComboBox) component).getSelectedIndex());

                }
                break;
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
