package ahorcadomain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AhorcadoGUI extends BaseFrame {

    public AhorcadoGUI() {
        super("Ahorcado", 940, 760);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = crearLabel("Ahorcado", 0, 0, 0, 0, Font.BOLD, 32f);
        panelNorte.add(lblTitulo);

        JLabel lblIntentos = crearLabel("Intentos restantes: ", 50, 20, 230, 25, Font.BOLD, 22f);
        panelCentro.add(lblIntentos);

        JLabel lblIntentos2 = crearLabel("-", 280, 20, 230, 25, Font.BOLD, 22f);
        panelCentro.add(lblIntentos2);

        JLabel lblPalabra = crearLabel("Prueba", 580, 220, 250, 85, Font.BOLD, 70f);
        panelCentro.add(lblPalabra);

        JLabel lblTipo = crearLabel("Modo de juego: ", 540, 20, 230, 25, Font.BOLD, 18f);
        panelCentro.add(lblTipo);

        JTextField txtCaracter = crearTextField(660, 350, 80, 45);
        txtCaracter.setHorizontalAlignment(JTextField.CENTER);
        panelCentro.add(txtCaracter);

        JButton btnAdivinar = crearBoton("Adivinar", 650, 410, 100, 40);
        panelCentro.add(btnAdivinar);

        String tipo[] = {"Base", "Azar"};
        JComboBox<String> cboTipo = new JComboBox(tipo);
        cboTipo.setBounds(700, 20, 180, 25);
        panelCentro.add(cboTipo);

        JButton btnSalir = crearBoton("Salir", 740, 580, 150, 45);
        panelCentro.add(btnSalir);

        JLabel lblBase = new JLabel();
        lblBase.setBounds(0, 500, 500, 45);
        lblBase.setIcon(new ImageIcon(getClass().getResource("/Imagenes/base.png")));
        panelCentro.add(lblBase);

        JLabel lblPalo = new JLabel();
        lblPalo.setBounds(0, 500, 450, 45);
        lblPalo.setIcon(new ImageIcon(getClass().getResource("/Imagenes/palo.png")));
        panelCentro.add(lblBase);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new AhorcadoGUI().setVisible(true);
    }
}
