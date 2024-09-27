package componentes;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import Utileria.Figura;

public class JpLienzo extends JPanel implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;

    private int Fig; //aqui guardamos la figura elegida en la barra de figuras
    private int x1, x2, y2, y1, Ancho, Alto; //Variables requeridas para dibujar las figuras
    private boolean Relleno; //true para dibujar las figuras con relleno
    private Color color; //recibe el color elegido en la barra de colores
    private ArrayList<Figura> Contenido;//se guarda todo lo que dibujamos
    private boolean NoGuardado;

    public JpLienzo() {
        super();
        Contenido = new ArrayList<>();
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        Fig = 1;
        Relleno = false;
        color = Color.BLACK;
        ValoresIniciales();
        repaint();
    }

    public void ValoresIniciales() {
        NoGuardado = true;
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        Ancho = 0;
        Alto = 0;

    }

    public void PintaTodo(Graphics g) {
        Figura f;
        for (int i = 0; i < Contenido.size(); i++) {
            f = Contenido.get(i);
            g.setColor(f.getColor());
            if (f.isRelleno()) {
                //cuando la figura va rellena
                switch (f.getFig()) {
                    case 1://cuadrado
                    case 2://rectangulo
                        g.fillRect(f.getX1(), f.getY1(), f.getAncho(), f.getAlto());
                        break;
                    case 3://circulo
                    case 4: //ovalo
                        g.fillOval(f.getX1(), f.getY1(), f.getAncho(), f.getAlto());
                        break;
                    case 5://linea
                        g.drawLine(f.getX1(), f.getY1(), f.getX2(), f.getY2());
                }
            } else {
                //cuando la figura no va rellena
                switch (f.getFig()) {
                    case 1://cuadrado
                    case 2://rectangulo
                        g.drawRect(f.getX1(), f.getY1(), f.getAncho(), f.getAlto());
                        break;
                    case 3://circulo
                    case 4://ovalo
                        g.drawOval(f.getX1(), f.getY1(), f.getAncho(), f.getAlto());
                        break;
                    case 5://linea
                        g.drawLine(f.getX1(), f.getY1(), f.getX2(), f.getY2());
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == 0) {
            x2 = e.getX();
            y2 = e.getY();
            repaint();
        }
        

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {//system.out.println("Boton: " + e.getButton());
            x1 = e.getX();
            y1 = e.getY();
            

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            Contenido.add(new Figura(Fig, x1, y1, x2, y2, Ancho, Alto, Relleno, color));
            NoGuardado = true;
            
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        PintaTodo(g);
        Ancho = Math.abs(x2 - x1);
        Alto = Math.abs(y2 - y1);
        if (Fig == 1 || Fig == 3) {//Cuadrado y circulo - alto y ancho seran iguales
            Ancho = Ancho > Alto ? Ancho : Alto;
            Alto = Ancho; //Ancho > Alto ? Ancho : Alto;
        }
        g.setColor(color);
        if (Relleno) {
            switch (Fig) {
                case 1://cuadrado
                case 2://rectangulo
                    g.fillRect(x1, y1, Ancho, Alto);
                    break;
                case 3://circulo
                case 4: //ovalo
                    g.fillOval(x1, y1, Ancho, Alto);
                    break;
                case 5://linea
                    g.drawLine(x1, y1, x2, y2);
            }
        } else {
            switch (Fig) {
                case 1://cuadrado
                case 2://rectangulo
                    g.drawRect(x1, y1, Ancho, Alto);
                    break;
                case 3://circulo
                case 4: //ovalo
                    g.drawOval(x1, y1, Ancho, Alto);
                    break;
                case 5://linea
                    g.drawLine(x1, y1, x2, y2);
            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    
    
    public int getFig() {
        return Fig;
    }

    public void setFig(int fig) {
        Fig = fig;
    }
    public boolean isRelleno() {
        return Relleno;
    }

    public void setRelleno(boolean relleno) {
        Relleno = relleno;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Figura> getContenido() {
        return Contenido;
    }

    public void setContenido(ArrayList<Figura> contenido) {
        Contenido = contenido;
    }

    public boolean isNoGuardado() {
        return NoGuardado;
    }

    public void setNoGuardado(boolean noGuardado) {
        NoGuardado = noGuardado;
    }


}
