package ahorcadomain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static javax.swing.SwingConstants.CENTER;

public class AhorcadoGUI extends BaseFrame {

    public AhorcadoGUI() {
        super("Ahorcado", 940, 760);
    }
    private JuegoAhorcadoBase game;
    private final AdminPalabrasSecretas admin = new AdminPalabrasSecretas();

    private DefaultTableModel modeloLetras;
    private JTable tblLetras;

    private JComboBox<String> cboTipo;
    private JButton btnAdivinar, btnNuevo, btnSalir;

    private JTextField txtCaracter;
    private JLabel lblIntentos2, lblPalabra, lblMensaje;

    private JLabel lblBase, lblConector, lblPalo, lblCabeza, lblBrazos, lblPiernas;

    @Override
    protected void initComponents() {
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JLabel lblTitulo = crearLabel("Ahorcado", 0, 0, 0, 0, Font.BOLD, 32f);
        panelNorte.add(lblTitulo);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        modeloLetras = new DefaultTableModel(new Object[]{"Adivinadas", "Erradas"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblLetras = new JTable(modeloLetras);
        tblLetras.setRowHeight(24);
        tblLetras.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblLetras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        ((DefaultTableCellRenderer) tblLetras.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(CENTER);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(CENTER);
        tblLetras.getColumnModel().getColumn(0).setCellRenderer(center);
        tblLetras.getColumnModel().getColumn(1).setCellRenderer(center);

        JScrollPane spHistorial = new JScrollPane(tblLetras);
        spHistorial.setBounds(520, 290, 360, 280);
        panelCentro.add(spHistorial);

        JLabel lblIntentos = crearLabel("Intentos restantes: ", 50, 20, 260, 25, Font.BOLD, 22f);
        panelCentro.add(lblIntentos);

        lblIntentos2 = crearLabel("-", 300, 20, 100, 25, Font.BOLD, 22f);
        panelCentro.add(lblIntentos2);

        lblPalabra = crearLabel("", 540, 70, 320, 85, Font.BOLD, 22f);
        lblPalabra.setHorizontalAlignment(CENTER);
        panelCentro.add(lblPalabra);

        JLabel lblTipo = crearLabel("Modo de juego: ", 540, 20, 160, 25, Font.BOLD, 18f);
        panelCentro.add(lblTipo);

        String[] tipos = {"Fijo", "Azar"};
        cboTipo = new JComboBox<>(tipos);
        cboTipo.setBounds(700, 20, 180, 25);
        panelCentro.add(cboTipo);

        txtCaracter = crearTextField(660, 150, 80, 45);
        txtCaracter.setHorizontalAlignment(JTextField.CENTER);
        panelCentro.add(txtCaracter);

        btnAdivinar = crearBoton("Adivinar", 650, 210, 100, 40);
        panelCentro.add(btnAdivinar);

        lblMensaje = crearLabel("", 80, 580, 360, 30, Font.BOLD, 14f);
        lblMensaje.setHorizontalAlignment(CENTER);
        panelCentro.add(lblMensaje);

        btnNuevo = crearBoton("Nuevo", 550, 580, 140, 45);
        panelCentro.add(btnNuevo);

        btnSalir = crearBoton("Salir", 720, 580, 140, 45);
        panelCentro.add(btnSalir);

        lblBase = new JLabel();
        lblBase.setBounds(0, 450, 410, 45);
        lblBase.setIcon(new ImageIcon(getClass().getResource("/Imagenes/base.png")));
        panelCentro.add(lblBase);

        lblConector = new JLabel();
        lblConector.setBounds(85, -60, 500, 380);
        lblConector.setIcon(new ImageIcon(getClass().getResource("/Imagenes/conector.png")));
        panelCentro.add(lblConector);

        lblCabeza = new JLabel();
        lblCabeza.setBounds(290, 0, 500, 380);
        lblCabeza.setIcon(new ImageIcon(getClass().getResource("/Imagenes/cabeza2.png")));
        panelCentro.add(lblCabeza);

        lblBrazos = new JLabel();
        lblBrazos.setBounds(290, 50, 500, 380);
        lblBrazos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/brazos2.png")));
        panelCentro.add(lblBrazos);

        lblPiernas = new JLabel();
        lblPiernas.setBounds(290, 100, 500, 380);
        lblPiernas.setIcon(new ImageIcon(getClass().getResource("/Imagenes/piernas2.png")));
        panelCentro.add(lblPiernas);

        lblPalo = new JLabel();
        lblPalo.setBounds(65, 110, 500, 380);
        lblPalo.setIcon(new ImageIcon(getClass().getResource("/Imagenes/palo.png")));
        panelCentro.add(lblPalo);

        btnSalir.addActionListener(e -> System.exit(0));
        btnNuevo.addActionListener(e -> iniciarNuevoJuego());
        btnAdivinar.addActionListener(e -> procesarIntento());
        txtCaracter.addActionListener(e -> procesarIntento());

        setContentPane(panelPrincipal);
        ocultarPartes();
        iniciarNuevoJuego();
    }

    private void iniciarNuevoJuego() {
        String tipo = (String) cboTipo.getSelectedItem();
        if ("Fijo".equalsIgnoreCase(tipo)) {
            game = new JuegoAhorcadoFijo("programacion");
        } else {
            game = new JuegoAhorcadoAzar(admin);
        }

        game.inicializarPalabraSecreta();
        game.jugar();

        lblPalabra.setText(game.getPalabraActual());
        lblIntentos2.setText(String.valueOf(game.getIntentosRestantes()));
        lblMensaje.setText("Ingrese una letra.");

        modeloLetras.setRowCount(0);
        ocultarPartes();
        actualizarFigura(game.getErrores());

        txtCaracter.setText("");
        txtCaracter.setEnabled(true);
        btnAdivinar.setEnabled(true);
        txtCaracter.requestFocusInWindow();
    }

    private void procesarIntento() {
        if (game == null || game.juegoTerminado()) {
            return;
        }

        String s = txtCaracter.getText().trim();
        if (s.length() != 1 || !Character.isLetter(s.charAt(0))) {
            lblMensaje.setText("Entrada inválida: ingrese una sola letra.");
            txtCaracter.setText("");
            return;
        }
        char l = s.charAt(0);

        ResultadoIntento r = game.intentarConLetra(l);

        switch (r) {
            case INVALIDA: {
                lblMensaje.setText("Entrada inválida: ingrese una letra.");
                txtCaracter.setText("");
                return;
            }
            case REPETIDA: {
                lblMensaje.setText("Letra repetida.");
                txtCaracter.setText("");
                return;
            }
            case ACIERTO: {
                modeloLetras.addRow(new Object[]{String.valueOf(l), ""});
                lblMensaje.setText("¡Acierto!");
                break;
            }
            case ERROR: {
                modeloLetras.addRow(new Object[]{"", String.valueOf(l)});
                lblMensaje.setText("Incorrecta.");
                break;
            }
        }

        lblPalabra.setText(game.getPalabraActual());
        lblIntentos2.setText(String.valueOf(game.getIntentosRestantes()));
        actualizarFigura(game.getErrores());

        txtCaracter.setText("");

        if (game.juegoTerminado()) {
            txtCaracter.setEnabled(false);
            btnAdivinar.setEnabled(false);

            String resultado = game.hasGanado()
                    ? "¡Ganaste!"
                    : "Perdiste. La palabra era: " + game.getPalabraSecreta();

        }
    }

    private String revelarPalabra(JuegoAhorcadoBase g) {
        if (g instanceof JuegoAhorcadoFijo jf) {
            return jf.revelarPalabra();
        }
        if (g instanceof JuegoAhorcadoAzar ja) {
            return ja.revelarPalabra();
        }
        return "";
    }

    private void ocultarPartes() {
        lblBase.setVisible(false);
        lblConector.setVisible(false);
        lblPalo.setVisible(false);
        lblCabeza.setVisible(false);
        lblBrazos.setVisible(false);
        lblPiernas.setVisible(false);
    }

    private void actualizarFigura(int errores) {
        lblBase.setVisible(errores >= 1);
        lblConector.setVisible(errores >= 2);
        lblPalo.setVisible(errores >= 3);
        lblCabeza.setVisible(errores >= 4);
        lblBrazos.setVisible(errores >= 5);
        lblPiernas.setVisible(errores >= 6);
    }

    public static void main(String[] args) {
        new AhorcadoGUI().setVisible(true);
    }
}
