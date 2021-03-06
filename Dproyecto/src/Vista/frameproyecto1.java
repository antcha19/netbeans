/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controller.ControllerPelicula;
import Modelo.Pelicula;
import com.connection.Conexion;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author antcha
 */
class frameproyecto1 extends JFrame {

    private DefaultTableModel model;
    private JTable jTable1;
    private JScrollPane jscrollPanel1;
    //algunos paneles necesarios
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JButton bupdate = new JButton("UPDATE");
    JButton bborrar = new JButton("DELETE");
    JButton binsert = new JButton("INSERTAR");
    JButton bselect = new JButton("select all list");

    JButton bupdate2 = new JButton("Update a movie");
    JButton bborrar2 = new JButton("Delete a movie");
    JButton binsert2 = new JButton("Insert a new movie");
    JButton bselect2 = new JButton("select all list ");

    JLabel Id = new JLabel("ID-int");
    JLabel Name = new JLabel("NAME-vachar");
    JLabel Year = new JLabel("YEAR-int");
    JLabel Tipe = new JLabel("TYPE-vachar");
    JLabel opciones = new JLabel("Que quieres hacer?");

    JTextField idtext = new JTextField();
    JTextField nametext = new JTextField();
    JTextField yeartext = new JTextField();
    JTextField tipotext = new JTextField();

    public frameproyecto1() {

        setTitle(" proyecto1");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel.setLayout(new GridLayout(2, 2));

        //panel uno de la lista
        TitledBorder title;
        title = BorderFactory.createTitledBorder("tabla");
        panel1.setBorder(title);

        //tabla
        jTable1 = new JTable();

        jscrollPanel1 = new JScrollPane(jTable1);
        panel1.add(jscrollPanel1, BorderLayout.CENTER);

        TitledBorder title1;
        title1 = BorderFactory.createTitledBorder("Opciones");
        panel4.setBorder(title1);
        panel4.setLayout(new GridLayout(5, 1));
        panel4.add(opciones);
        panel4.add(binsert2);
        binsert2.setEnabled(false);
        binsert2.addActionListener(new botonopciones());
        panel4.add(bupdate2);
        bupdate2.setEnabled(false);
        bupdate2.addActionListener(new botonopciones());
        panel4.add(bborrar2);
        bborrar2.setEnabled(false);
        bborrar2.addActionListener(new botonopciones());
        /// panel4.add(bselect2);
        //bselect2.addActionListener(new botonopciones());
        panel4.add(bselect);
        // bselect.setEnabled(true);
        bselect.addActionListener(new botonselect());

        TitledBorder title2;
        title2 = BorderFactory.createTitledBorder("Datos");
        panel2.setBorder(title2);
        panel2.setLayout(new GridLayout(4, 2));
        panel2.add(Id);
        panel2.add(idtext);
        idtext.setEditable(false);
        panel2.add(Name);
        panel2.add(nametext);
        nametext.setEditable(false);
        panel2.add(Year);
        panel2.add(yeartext);
        yeartext.setEditable(false);
        panel2.add(Tipe);
        panel2.add(tipotext);
        tipotext.setEditable(false);

        TitledBorder title3;
        title3 = BorderFactory.createTitledBorder("Botones");
        panel3.setBorder(title3);
        panel3.setLayout(new GridLayout(3, 1));
        panel3.add(binsert);
        binsert.setEnabled(false);
        binsert.addActionListener(new botoninsertar());
        panel3.add(bupdate);
        bupdate.setEnabled(false);
        bupdate.addActionListener(new botonupdate());
        panel3.add(bborrar);
        bborrar.setEnabled(false);
        bborrar.addActionListener(new Butonborrar());

        panel.add(panel1);
        panel.add(panel4);
        panel.add(panel2);
        panel.add(panel3);

        add(panel);

    }

    //activar y desabilitar opciones y botones
    class botonopciones implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == binsert2) {
                JOptionPane.showMessageDialog(null, "solo necesitas el name,year,type for insert a movie");
                binsert.setEnabled(true);
                nametext.setEditable(true);
                yeartext.setEditable(true);
                tipotext.setEditable(true);
                bupdate2.setEnabled(false);
                binsert2.setEnabled(false);
                bborrar2.setEnabled(false);
            }
            if (e.getSource() == bupdate2) {
                JOptionPane.showMessageDialog(null, "introduce la id a actualizar y todos los campos ");
                actulizarlista();
                bupdate.setEnabled(true);
                idtext.setEditable(true);
                nametext.setEditable(true);
                yeartext.setEditable(true);
                tipotext.setEditable(true);
                bupdate2.setEnabled(false);
                binsert2.setEnabled(false);
                bborrar2.setEnabled(false);

            }
            if (e.getSource() == bborrar2) {
                JOptionPane.showMessageDialog(null, "introduce solo la id para elimiar");
                actulizarlista();
                bborrar.setEnabled(true);
                idtext.setEditable(true);
                bupdate2.setEnabled(false);
                binsert2.setEnabled(false);
                bborrar2.setEnabled(false);

            }
        }
    }

    //selecciona todos los campos de la tabla y los insertar en un jTabla
    class botonselect implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "A partir de ahora la lista se actuliza automaticamente");
            binsert2.setEnabled(true);
            bupdate2.setEnabled(true);
            bborrar2.setEnabled(true);
            bselect.setEnabled(false);
            ControllerPelicula controller = new ControllerPelicula();
            try {
                jTable1.setModel(controller.selectcontroller());
            } catch (SQLException ex) {
                Logger.getLogger(frameproyecto1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    //se tiene  que insertar un campo ID existente
    class botonupdate implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bupdate) {

                int confirmar = JOptionPane.showConfirmDialog(null, "Do you confirm this?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    ControllerPelicula controller = new ControllerPelicula();
                    String id = idtext.getText();
                    int id2 = Integer.parseInt(id);
                    String nombre = nametext.getText();
                    String anyo = yeartext.getText();
                    int anyo2 = Integer.parseInt(anyo);
                    String tipo = tipotext.getText();
                    Pelicula insertarpeli = new Pelicula(id2, nombre, anyo2, tipo);
                    controller.actualizarcontroller(insertarpeli);
                    idtext.setText("");
                    nametext.setText("");
                    yeartext.setText("");
                    tipotext.setText("");
                    actulizarlista();
                    //activo botones y textfield correspondientes
                    bupdate.setEnabled(false);
                    idtext.setEditable(false);
                    nametext.setEditable(false);
                    yeartext.setEditable(false);
                    tipotext.setEditable(false);
                    bupdate2.setEnabled(true);
                    binsert2.setEnabled(true);
                    bborrar2.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "you have selected no");
                }
            }

        }
    }

    //se tiene  que insertar un campo ID existente
    class Butonborrar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bborrar) {

                int confirmar = JOptionPane.showConfirmDialog(null, "Do you confirm this?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    ControllerPelicula controller = new ControllerPelicula();
                    String id = idtext.getText();
                    int id2 = Integer.parseInt(id);
                    Pelicula borrar = new Pelicula(id2);
                    if (borrar != null) {
                        controller.eliminarcontroller(borrar);
                    }
                    idtext.setText("");
                    actulizarlista();
                    bborrar.setEnabled(false);
                    idtext.setEditable(false);
                    bupdate2.setEnabled(true);
                    binsert2.setEnabled(true);
                    bborrar2.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "you have selected no");
                }
            }

        }
    }

    //para insertar se tiene que insertar todos los cambios menos la ID
    class botoninsertar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //confirmacion
            if (e.getSource() == binsert) {

                int confirmar = JOptionPane.showConfirmDialog(null, "Do you confirm this?");
                if (JOptionPane.OK_OPTION == confirmar) {
                    ControllerPelicula controller = new ControllerPelicula();
                    String nombre = nametext.getText();
                    String anyo = yeartext.getText();
                    int anyo2 = Integer.parseInt(anyo);
                    String tipo = tipotext.getText();

                    Pelicula insertarpeli = new Pelicula(nombre, anyo2, tipo);
                    controller.registrarcontroller(insertarpeli);
                    idtext.setText("");
                    nametext.setText("");
                    yeartext.setText("");
                    tipotext.setText("");
                    actulizarlista();
                    nametext.setEditable(false);
                    yeartext.setEditable(false);
                    tipotext.setEditable(false);
                    binsert.setEnabled(false);
                    bupdate2.setEnabled(true);
                    binsert2.setEnabled(true);
                    bborrar2.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "you have selected no");
                }
            }

        }
    }

    //actualizara la Tabla
    public void actulizarlista() {

        ControllerPelicula controller = new ControllerPelicula();
        try {
            jTable1.setModel(controller.selectcontroller());
        } catch (SQLException ex) {
            Logger.getLogger(frameproyecto1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
