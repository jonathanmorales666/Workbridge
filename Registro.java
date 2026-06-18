import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.util.UUID;

public class Registro extends JPanel {

    private final Color COLOR_MENU  = Color.decode("#243A69");
    private final Color COLOR_SEC   = Color.decode("#5B88A5");
    private final Color COLOR_ACENTO= Color.decode("#9B73A6");
    private final Color COLOR_FONDO = Color.decode("#D4CDC5");
    private final Color COLOR_BLANCO= Color.WHITE;

    private JTextField tfNombre, tfApellido, tfCorreo, tfTelefono;
    private JPasswordField pfPass, pfPass2;
    private JToggleButton btnTrabajador, btnEmpresa;

    public Registro() {

        setLayout(new BorderLayout());

        // ---- PANEL IZQUIERDO (branding) ----
        JPanel panelIzq = new JPanel();
        panelIzq.setPreferredSize(new Dimension(420, 800));
        panelIzq.setBackground(COLOR_MENU);
        panelIzq.setLayout(null);

        JLabel lblMarca = new JLabel("<html><b><font size='16' color='white'>Work</font><br><font size='16' color='white'>Bridge</font></b></html>");
        lblMarca.setBounds(40, 50, 200, 80);
        panelIzq.add(lblMarca);

        JLabel lblSlogan = new JLabel("<html><font color='white'><b>Conectamos talento.</b><br>Generamos Oportunidades.<br><br>La plataforma profesional de Guatemala<br>donde empresas líderes y el mejor talento<br>se encuentran, conectan y crecen juntos.</font></html>");
        lblSlogan.setBounds(40, 160, 340, 200);
        panelIzq.add(lblSlogan);

        JLabel lblStats = new JLabel("<html><font color='white'><b>+8,400</b> Profesionales activos<br><b>+1,200</b> Empresas registradas<br><b>+3,500</b> Vacantes publicadas</font></html>");
        lblStats.setBounds(40, 600, 340, 100);
        panelIzq.add(lblStats);

        add(panelIzq, BorderLayout.WEST);

        // ---- PANEL DERECHO (formulario) ----
        JPanel panelDer = new JPanel();
        panelDer.setBackground(COLOR_BLANCO);
        panelDer.setLayout(null);

        JLabel lblTitulo = new JLabel("Crear cuenta nueva");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(60, 40, 400, 35);
        panelDer.add(lblTitulo);

        JLabel lblSub = new JLabel("Ingresa tus datos para registrarte");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(Color.GRAY);
        lblSub.setBounds(60, 75, 350, 20);
        panelDer.add(lblSub);

        // Tipo cuenta
        JLabel lblTipo = new JLabel("Tipo de cuenta");
        lblTipo.setBounds(60, 110, 200, 20);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelDer.add(lblTipo);

        btnTrabajador = new JToggleButton("Trabajador");
        btnTrabajador.setBounds(60, 132, 150, 32);
        btnTrabajador.setBackground(COLOR_MENU);
        btnTrabajador.setForeground(COLOR_BLANCO);
        btnTrabajador.setFocusPainted(false);
        btnTrabajador.setSelected(true);
        panelDer.add(btnTrabajador);

        btnEmpresa = new JToggleButton("Empresa");
        btnEmpresa.setBounds(215, 132, 150, 32);
        btnEmpresa.setBackground(COLOR_FONDO);
        btnEmpresa.setForeground(Color.DARK_GRAY);
        btnEmpresa.setFocusPainted(false);
        panelDer.add(btnEmpresa);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(btnTrabajador);
        grupo.add(btnEmpresa);

        // Campos
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(60, 185, 280, 18);
        panelDer.add(lblNombre);

        tfNombre = new JTextField();
        tfNombre.setBounds(60, 205, 480, 32);
        panelDer.add(tfNombre);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(60, 253, 280, 18);
        panelDer.add(lblApellido);

        tfApellido = new JTextField();
        tfApellido.setBounds(60, 273, 480, 32);
        panelDer.add(tfApellido);

        JLabel lblCorreo = new JLabel("Correo electrónico");
        lblCorreo.setBounds(60, 321, 280, 18);
        panelDer.add(lblCorreo);

        tfCorreo = new JTextField();
        tfCorreo.setBounds(60, 341, 480, 32);
        panelDer.add(tfCorreo);

        JLabel lblTel = new JLabel("Teléfono (opcional)");
        lblTel.setBounds(60, 389, 280, 18);
        panelDer.add(lblTel);

        tfTelefono = new JTextField();
        tfTelefono.setBounds(60, 409, 480, 32);
        panelDer.add(tfTelefono);

        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setBounds(60, 457, 200, 18);
        panelDer.add(lblPass);

        pfPass = new JPasswordField();
        pfPass.setBounds(60, 477, 480, 32);
        panelDer.add(pfPass);

        JLabel lblPass2 = new JLabel("Confirmar contraseña");
        lblPass2.setBounds(60, 525, 220, 18);
        panelDer.add(lblPass2);

        pfPass2 = new JPasswordField();
        pfPass2.setBounds(60, 545, 480, 32);
        panelDer.add(pfPass2);

        JCheckBox chkTerminos = new JCheckBox("Acepto los Términos de uso y Política de privacidad");
        chkTerminos.setBounds(60, 593, 400, 25);
        chkTerminos.setBackground(COLOR_BLANCO);
        panelDer.add(chkTerminos);

        JButton btnRegistrar = new JButton("Crear cuenta");
        btnRegistrar.setBounds(60, 633, 480, 38);
        btnRegistrar.setBackground(COLOR_MENU);
        btnRegistrar.setForeground(COLOR_BLANCO);
        btnRegistrar.setFocusPainted(false);
        panelDer.add(btnRegistrar);

        add(panelDer, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarUsuario(chkTerminos));
    }

    private void registrarUsuario(JCheckBox chkTerminos) {

        String nombre   = tfNombre.getText().trim();
        String apellido = tfApellido.getText().trim();
        String correo   = tfCorreo.getText().trim();
        String telefono = tfTelefono.getText().trim();
        String pass     = new String(pfPass.getPassword());
        String pass2    = new String(pfPass2.getPassword());
        String rol      = btnEmpresa.isSelected() ? "reclutador" : "trabajador";

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios.");
            return;
        }

        if (!pass.equals(pass2)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        if (!chkTerminos.isSelected()) {
            JOptionPane.showMessageDialog(this, "Debes aceptar los términos.");
            return;
        }

        String sql = "INSERT INTO usuarios (id, nombre, apellido, email, password_hash, telefono, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, correo);
            ps.setString(5, pass);
            ps.setString(6, telefono.isEmpty() ? null : telefono);
            ps.setString(7, rol);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Cuenta creada exitosamente");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}