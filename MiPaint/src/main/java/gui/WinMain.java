package gui;

import Utileria.Fecha;
import Utileria.Figura;
import componentes.JpLienzo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.Timer;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WinMain extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;

    private JMenuBar jmbMenu;
    private JMenu jmArchivo, jmEdicion, jmColores, jmFormas, jmAcercaDe;
    //Menu Archivo
    private JMenuItem jmiNuevo, jmiAbrir, jmiGuardar, jmiGuardarComo, jmiSalir;
    //Menu Edicion 
    private JMenuItem jmiCopiar, jmiCortar, jmiPegar;
    //Menu Colores
    private JMenuItem jmiPersonalizado;
    //Menu Formas
    private JMenuItem jmiCuadrado, jmiRectangulo, jmiCirculo, jmiOvalo, jmiLinea;
    //Menu AcercaDe
    private JMenuItem jmiAyuda, jmiAcercaDe;
    //barra de herramientas
    private JToolBar tbHerramientas;
    private JButton btnNuevo, btnAbrir, btnGuardar, btnCopiar, btnCortar, btnPegar, btnSalir;
    //barra de status
    private JToolBar jtbStatus;
    private JLabel lblInfo1, lblInfo2, lblHora;
    private Timer tiempo, timer;
    private JpLienzo Lienzo;
    //private JpLienzo Lienzo;
    //barra de colores
    private JToolBar tbColores;
    private JButton btnAzul, btnNegro, btnRojo, btnPersonalizado;
    //barra de formas
    private JToolBar tbFormas;
    private JButton btnCuadrado, btnRectangulo, btnCirculo, btnOvalo, btnLinea;
    private JCheckBox cbRelleno;

    private File Archivo;

    public WinMain() {
        setTitle("Mi paint");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon("src/imagenes/mipaint.png").getImage());
        addWindowListener(this);
        InitComponents();
        tiempo = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                RestauraStatus();

            }
        });
    }

    private void InitComponents() {
        Archivo = null;
        //menu
        Menu();
        //Norte
        BarraHerramientas();
        //centro
        Lienzo = new JpLienzo();
        add(Lienzo, BorderLayout.CENTER);
        //Sur
        BarradeStatus();
        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ActualizarFecha();
            }
        });
        tiempo.start();
        //Este
        BarraDeColores();
        //Oeste
        BarraDeFormas();
    }

    private void ActualizarFecha() {
        lblHora.setText(Fecha.now());
    }

    private void ActualizaStatus1(String texto) {
        lblInfo1.setText(texto);
        timer.start();
    }

    private void RestauraStatus() {
        lblInfo1.setText("Listo");
        timer.stop();
    }

    private void ActualizaStatus2(String texto) {
        lblInfo2.setText(texto);
    }

    private void BarradeStatus() {

        jtbStatus = new JToolBar();
        jtbStatus.setFloatable(false);
        lblInfo1 = new JLabel("Listo");
        lblInfo1.setToolTipText("Informacion de uso");
        jtbStatus.add(lblInfo1);

        jtbStatus.add(new JSeparator(JSeparator.VERTICAL));

        lblInfo2 = new JLabel("Listo");
        lblInfo2.setToolTipText("Informacion secundaria");
        jtbStatus.add(lblInfo2);

        jtbStatus.add(new JSeparator(JSeparator.VERTICAL));

        lblHora = new JLabel(Fecha.now());
        lblHora.setToolTipText("Hora actual");
        jtbStatus.add(lblHora);
        add(jtbStatus, BorderLayout.SOUTH);

    }

    private void Menu() {
        //menu bar
        jmbMenu = new JMenuBar();
        jmbMenu.setAlignmentX(LEFT_ALIGNMENT);

        //Archivo
        jmArchivo = new JMenu("Archivo");
        jmArchivo.setMnemonic('A');

        jmiNuevo = new JMenuItem("Nuevo");
        jmiNuevo.addActionListener(this);
        jmiNuevo.setMnemonic('N');
        jmArchivo.add(jmiNuevo);

        jmiAbrir = new JMenuItem("Abrir...");
        jmiAbrir.addActionListener(this);
        jmiAbrir.setMnemonic('A');
        jmArchivo.add(jmiAbrir);

        jmiGuardar = new JMenuItem("Guardar");
        jmiGuardar.addActionListener(this);
        jmiGuardar.setMnemonic('G');
        jmArchivo.add(jmiGuardar);

        jmiGuardarComo = new JMenuItem("GuardarComo...");
        jmiGuardarComo.addActionListener(this);
        jmiGuardarComo.setMnemonic('C');
        jmArchivo.add(jmiGuardarComo);

        jmArchivo.addSeparator();

        jmiSalir = new JMenuItem("Salir");
        jmiSalir.addActionListener(this);
        jmiSalir.setMnemonic('S');
        jmArchivo.add(jmiSalir);

        jmbMenu.add(jmArchivo);

        //Edicion
        jmEdicion = new JMenu("Edicion");
        jmEdicion.setMnemonic('E');

        jmiCopiar = new JMenuItem("Copiar");
        jmiCopiar.addActionListener(this);
        jmiCopiar.setMnemonic('C');
        jmEdicion.add(jmiCopiar);

        jmiCortar = new JMenuItem("Cortar");
        jmiCortar.addActionListener(this);
        jmiCortar.setMnemonic('O');
        jmEdicion.add(jmiCortar);

        jmiPegar = new JMenuItem("Pegar");
        jmiPegar.addActionListener(this);
        jmiPegar.setMnemonic('P');
        jmEdicion.add(jmiPegar);

        jmbMenu.add(jmEdicion);

        //Colores
        jmColores = new JMenu("Colores");
        jmColores.setMnemonic('C');

        jmiPersonalizado = new JMenuItem("Personalizado");
        jmiPersonalizado.addActionListener(this);
        jmiPersonalizado.setMnemonic('P');
        jmColores.add(jmiPersonalizado);

        jmbMenu.add(jmColores);

        //Formas
        jmFormas = new JMenu("Formas");
        jmFormas.setMnemonic('F');

        jmiCuadrado = new JMenuItem("Cuadrado");
        jmiCuadrado.addActionListener(this);
        jmiCuadrado.setMnemonic('C');
        jmFormas.add(jmiCuadrado);

        jmiRectangulo = new JMenuItem("Rectangulo");
        jmiRectangulo.addActionListener(this);
        jmiRectangulo.setMnemonic('R');
        jmFormas.add(jmiRectangulo);

        jmiCirculo = new JMenuItem("Circulo");
        jmiCirculo.addActionListener(this);
        jmiCirculo.setMnemonic('I');
        jmFormas.add(jmiCirculo);

        jmiOvalo = new JMenuItem("Ovalo");
        jmiOvalo.addActionListener(this);
        jmiOvalo.setMnemonic('O');
        jmFormas.add(jmiOvalo);

        jmiLinea = new JMenuItem("Linea");
        jmiLinea.addActionListener(this);
        jmiLinea.setMnemonic('L');
        jmFormas.add(jmiLinea);

        jmbMenu.add(jmFormas);

        //Acerca de...
        jmAcercaDe = new JMenu("Acerca de...");
        jmAcercaDe.setMnemonic('D');

        jmiAyuda = new JMenuItem("Ayuda");
        jmiAyuda.addActionListener(this);
        jmiAyuda.setMnemonic('A');
        jmAcercaDe.add(jmiAyuda);
        
        jmAcercaDe.addSeparator();

        jmiAcercaDe = new JMenuItem("Acerca de...");
        jmiAcercaDe.addActionListener(this);
        jmiAcercaDe.setMnemonic('D');
        jmAcercaDe.add(jmiAcercaDe);

        jmbMenu.add(jmAcercaDe);

        setJMenuBar(jmbMenu);
    }

    private void BarraHerramientas() {
        //Barra de herramientas
        tbHerramientas = new JToolBar();
        tbHerramientas.setFloatable(true);
        //Nuevo
        btnNuevo = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnNuevo.setToolTipText("Nueva imagen"); // texto cuando dejas el cursor
        btnNuevo.addActionListener(this);
        tbHerramientas.add(btnNuevo);
        //Abrir
        btnAbrir = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnAbrir.setToolTipText("Abrir imagen existente");
        btnAbrir.addActionListener(this);
        tbHerramientas.add(btnAbrir);
        //Guardar
        btnGuardar = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnGuardar.setToolTipText("Guardar la imagen");
        btnGuardar.addActionListener(this);
        tbHerramientas.add(btnGuardar);
        
        //Realizaremos un separador pero limitandolo para que las imagenes no se expandan en todo el toolbar asi como
        //cuando justificas texto en word
        tbHerramientas.add(Box.createRigidArea(new Dimension(20, 0)));
        JSeparator separator1 = new JSeparator(JSeparator.VERTICAL);
        separator1.setMaximumSize(new Dimension(20, 55));
        tbHerramientas.add(separator1);

        //Copiar
        btnCopiar = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnCopiar.setToolTipText("Copiar");
        btnCopiar.addActionListener(this);
        tbHerramientas.add(btnCopiar);
        //Cortar
        btnCortar = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnCortar.setToolTipText("Cortar");
        btnCortar.addActionListener(this);
        tbHerramientas.add(btnCortar);
        //Pegar
        btnPegar = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnPegar.setToolTipText("Pegar");
        btnPegar.addActionListener(this);
        tbHerramientas.add(btnPegar);

        tbHerramientas.add(Box.createRigidArea(new Dimension(20, 0)));
        JSeparator separator2 = new JSeparator(JSeparator.VERTICAL);
        separator2.setMaximumSize(new Dimension(20, 55)); 
        tbHerramientas.add(separator2);
        
        btnSalir = new JButton(new ImageIcon(("src/imagenes/Paleta1.jpg")));
        btnSalir.setToolTipText("Salir de Mi Paint");
        btnSalir.addActionListener(this);
        tbHerramientas.add(btnSalir);

        add(tbHerramientas, BorderLayout.NORTH);

    }

    //Barra de colores
    private void BarraDeColores() {
        tbColores = new JToolBar(JToolBar.VERTICAL);
        //Azul
        btnAzul = new JButton(new ImageIcon(("src/imagenes/azul.png")));
        btnAzul.setToolTipText("Azul"); // texto cuando dejas el cursor
        btnAzul.addActionListener(this);
        tbColores.add(btnAzul);
        //Negro
        btnNegro = new JButton(new ImageIcon(("src/imagenes/negro.png")));
        btnNegro.setToolTipText("Negro");
        btnNegro.addActionListener(this);
        tbColores.add(btnNegro);
        //Rojo
        btnRojo = new JButton(new ImageIcon(("src/imagenes/rojo.png")));
        btnRojo.setToolTipText("Rojo");
        btnRojo.addActionListener(this);
        tbColores.add(btnRojo);
        //Personalizado
        btnPersonalizado = new JButton(new ImageIcon(("src/imagenes/cafe.png")));
        btnPersonalizado.setToolTipText("Personalizado");
        btnPersonalizado.addActionListener(this);
        tbColores.add(btnPersonalizado);

        add(tbColores, BorderLayout.EAST);

    }

    //Barra de figuras
    private void BarraDeFormas() {
        tbFormas = new JToolBar(JToolBar.VERTICAL);
        //Cuadrado
        btnCuadrado = new JButton(new ImageIcon(("src/imagenes/cuadrado.png")));
        btnCuadrado.setSize(new Dimension(10, 10));
        btnCuadrado.setToolTipText("Cuadrado"); // texto cuando dejas el cursor
        btnCuadrado.addActionListener(this);
        tbFormas.add(btnCuadrado);
        //Rectangulo
        btnRectangulo = new JButton(new ImageIcon(("src/imagenes/rectangulo.png")));
        btnRectangulo.setToolTipText("Rectangulo");
        btnRectangulo.addActionListener(this);
        tbFormas.add(btnRectangulo);
        //Circulo
        btnCirculo = new JButton(new ImageIcon(("src/imagenes/circulo.png")));
        btnCirculo.setToolTipText("Circulo");
        btnCirculo.addActionListener(this);
        tbFormas.add(btnCirculo);
        //Ovalo
        btnOvalo = new JButton(new ImageIcon(("src/imagenes/ovalo.png")));
        btnOvalo.setToolTipText("Ovalo");
        btnOvalo.addActionListener(this);
        tbFormas.add(btnOvalo);
        //Linea
        btnLinea = new JButton(new ImageIcon(("src/imagenes/linea.png")));
        btnLinea.setToolTipText("Linea");
        btnLinea.addActionListener(this);
        tbFormas.add(btnLinea);

        cbRelleno = new JCheckBox("Relleno");
        cbRelleno.setSelected(false);
        cbRelleno.addActionListener(this);
        tbFormas.add(cbRelleno);

        add(tbFormas, BorderLayout.WEST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmiSalir || e.getSource() == btnSalir) {
            Cerrar();
        }

        if (e.getSource() == jmiNuevo || e.getSource() == btnNuevo) {
             if (Lienzo.isNoGuardado() && Lienzo.getContenido().size() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "Imagen no guardada, ¿Desea guardarla ahora?",
                    "Salir de Mi Paint", JOptionPane.YES_NO_CANCEL_OPTION);
            if (resp == JOptionPane.CANCEL_OPTION) {
                return;
             }
            else if (resp == JOptionPane.YES_OPTION) {
                Guardar();
            }
        }         
            ActualizaStatus1("Nuevo Lienzo Preparado");
            Lienzo.getContenido().clear();
            Lienzo.ValoresIniciales();
            Archivo = null;
            Lienzo.repaint();
        }

        if (e.getSource() == jmiAbrir || e.getSource() == btnAbrir) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setDialogTitle("Abrir imagen existente");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Imagen de Mi Paint", "imp");
            jfc.setFileFilter(fnef);
            if (jfc.showOpenDialog(this) != JFileChooser.CANCEL_OPTION) {
                Archivo = jfc.getSelectedFile();
                ActualizaStatus1("Abriendo imagen");
                LeerArchivo();
            }
        }

        if (e.getSource() == jmiGuardar || e.getSource() == btnGuardar) {
            Guardar();
        }
        if (e.getSource() == jmiGuardarComo) {
            GuardarComo();
        }
        //Colores
        if (e.getSource() == btnAzul) {
            Lienzo.setColor(Color.BLUE);
        }
        if (e.getSource() == btnNegro) {
            Lienzo.setColor(Color.BLACK);
        }
        if (e.getSource() == btnRojo) {
            Lienzo.setColor(Color.RED);
        }
        if (e.getSource() == btnPersonalizado) {
            Lienzo.setColor(JColorChooser.showDialog(this, "Seleccion de color personalizado",
                    btnPersonalizado.getBackground()));
            btnPersonalizado.setBackground(Lienzo.getColor());
        }

        //Figuras
        if (e.getSource() == btnCuadrado || e.getSource() == jmiCuadrado) {
            Lienzo.setFig(1);
        }
        if (e.getSource() == btnRectangulo || e.getSource() == jmiRectangulo) {
            Lienzo.setFig(2);
        }
        if (e.getSource() == btnCirculo || e.getSource() == jmiCirculo) {
            Lienzo.setFig(3);
        }
        if (e.getSource() == btnOvalo || e.getSource() == jmiOvalo) {
            Lienzo.setFig(4);
        }
        if (e.getSource() == btnLinea || e.getSource() == jmiLinea) {
            Lienzo.setFig(5);
        }
        if (e.getSource() == cbRelleno) {
            Lienzo.setRelleno(cbRelleno.isSelected());
        }

    }

    private void GuardarComo() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle("Guardar imagen actual");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Imagen de Mi Paint", "imp");
        jfc.setFileFilter(fnef);
        if (jfc.showOpenDialog(this) != JFileChooser.CANCEL_OPTION) {
            if (jfc.getSelectedFile().getName().indexOf('.') == -1) {
                Archivo = new File(jfc.getSelectedFile().getPath() + ".imp");
            } else {
                Archivo = jfc.getSelectedFile();
            
            ActualizaStatus1("Guardando imagen");
            GuardarArchivo();
            ActualizaStatus1("Imagen guardada");
                    }
            }
    }

    private void Guardar() {
        if (Archivo == null) {
            GuardarComo();
        } else {
            ActualizaStatus1("Guardando imagen");
            GuardarArchivo();
            ActualizaStatus1("Imagen guardada");
        }
    }

    private void GuardarArchivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Archivo.getPath()));
            oos.writeObject(Lienzo.getContenido());
            oos.close();
            Lienzo.setNoGuardado(false);
        } catch (IOException e) {
            ActualizaStatus1("Error: " + e.getMessage());
        }
    }

    private void LeerArchivo(){
        try{
            ObjectInputStream ios = new ObjectInputStream(new FileInputStream(Archivo.getPath()));
            Lienzo.setContenido((ArrayList<Figura>) ios.readObject());
            Lienzo.setNoGuardado(false);
            ios.close();
            Lienzo.repaint();
        }catch(IOException e){
            ActualizaStatus1("Error: " + e.getMessage());
        }catch(ClassNotFoundException e2){
            ActualizaStatus1("Error: " + e2.getMessage());
    }
        }

    private void Salir() {
        tiempo.stop();
        System.exit(0);
    }

    private void Cerrar() {
        if (Lienzo.isNoGuardado() && Lienzo.getContenido().size() > 0) {
            int resp = JOptionPane.showConfirmDialog(null, "Imagen no guardada, ¿Desea guardarla ahora?",
                    "Salir de Mi Paint", JOptionPane.YES_NO_CANCEL_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                Guardar();
                Salir();
            } else if (resp == JOptionPane.NO_OPTION) {
                Salir();
            }
            return;
        }
        Salir();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Cerrar();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
