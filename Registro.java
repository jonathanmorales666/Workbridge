import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Registro extends JFrame {

    private final Color COLOR_MENU     = Color.decode("#243A69");
    private final Color COLOR_SEC      = Color.decode("#5B88A5");
    private final Color COLOR_ACENTO   = Color.decode("#9B73A6");
    private final Color COLOR_FONDO    = Color.decode("#D4CDC5");
    private final Color COLOR_BLANCO   = Color.WHITE;

    public Registro() {
        setTitle("Work Bridge - Crear cuenta");
        setSize(1280, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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

        // Selector de tipo
        JLabel lblTipo = new JLabel("Tipo de cuenta");
        lblTipo.setBounds(60, 110, 200, 20);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelDer.add(lblTipo);

        JToggleButton btnTrabajador = new JToggleButton("Trabajador");
        btnTrabajador.setBounds(60, 132, 150, 32);
        btnTrabajador.setBackground(COLOR_MENU);
        btnTrabajador.setForeground(COLOR_BLANCO);
        btnTrabajador.setFocusPainted(false);
        panelDer.add(btnTrabajador);

        JToggleButton btnEmpresa = new JToggleButton("Empresa");
        btnEmpresa.setBounds(215, 132, 150, 32);
        btnEmpresa.setBackground(COLOR_FONDO);
        btnEmpresa.setForeground(Color.DARK_GRAY);
        btnEmpresa.setFocusPainted(false);
        panelDer.add(btnEmpresa);

        // Campos
        String[] labels = {"Nombre", "Apellido", "Correo electrónico", "Teléfono (opcional)"};
        int y = 185;
        for (String label : labels) {
            JLabel lbl = new JLabel(label);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lbl.setBounds(60, y, 280, 18);
            panelDer.add(lbl);
            JTextField tf = new JTextField();
            tf.setBounds(60, y + 20, 480, 32);
            tf.setBackground(new Color(245, 245, 245));
            tf.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panelDer.add(tf);
            y += 68;
        }

        JLabel lblPass = new JLabel("Contraseña");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setBounds(60, y, 200, 18);
        panelDer.add(lblPass);
        JPasswordField pfPass = new JPasswordField();
        pfPass.setBounds(60, y + 20, 480, 32);
        pfPass.setBackground(new Color(245, 245, 245));
        pfPass.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDer.add(pfPass);
        y += 68;

        JLabel lblPass2 = new JLabel("Confirmar contraseña");
        lblPass2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass2.setBounds(60, y, 220, 18);
        panelDer.add(lblPass2);
        JPasswordField pfPass2 = new JPasswordField();
        pfPass2.setBounds(60, y + 20, 480, 32);
        pfPass2.setBackground(new Color(245, 245, 245));
        pfPass2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDer.add(pfPass2);
        y += 68;

        JCheckBox chkTerminos = new JCheckBox("Acepto los Términos de uso y Política de privacidad");
        chkTerminos.setBounds(60, y, 400, 25);
        chkTerminos.setBackground(COLOR_BLANCO);
        chkTerminos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelDer.add(chkTerminos);
        y += 40;

        JButton btnRegistrar = new JButton("Crear cuenta");
        btnRegistrar.setBounds(60, y, 480, 38);
        btnRegistrar.setBackground(COLOR_MENU);
        btnRegistrar.setForeground(COLOR_BLANCO);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        panelDer.add(btnRegistrar);
        y += 50;

        JLabel lblLogin = new JLabel("¿Ya tienes cuenta? Ingresar");
        lblLogin.setBounds(60, y, 300, 20);
        lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLogin.setForeground(COLOR_SEC);
        panelDer.add(lblLogin);

        add(panelDer, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> new Registro().setVisible(true));
    }
}
