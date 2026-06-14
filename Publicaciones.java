import javax.swing.*;
import java.awt.*;

public class Publicaciones extends JFrame {

    private final Color COLOR_MENU   = Color.decode("#243A69");
    private final Color COLOR_SEC    = Color.decode("#5B88A5");
    private final Color COLOR_ACENTO = Color.decode("#9B73A6");
    private final Color COLOR_FONDO  = Color.decode("#D4CDC5");
    private final Color COLOR_BLANCO = Color.WHITE;

    public Publicaciones() {
        setTitle("Work Bridge - Publicaciones");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(crearSidebar("Inicio"), BorderLayout.WEST);

        JPanel contenido = new JPanel();
        contenido.setBackground(new Color(245, 247, 250));
        contenido.setLayout(null);

        JLabel lblTitulo = new JLabel("Inicio");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(30, 15, 300, 35);
        contenido.add(lblTitulo);

        JLabel lblFecha = new JLabel("Domingo, 7 de junio de 2026");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFecha.setForeground(Color.GRAY);
        lblFecha.setBounds(30, 48, 300, 18);
        contenido.add(lblFecha);

        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearSidebar(String activo) {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(230, 900));
        sidebar.setBackground(COLOR_MENU);
        sidebar.setLayout(null);

        JLabel logo = new JLabel("<html><b><font color='white' size='13'>Work<br>Bridge</font></b></html>");
        logo.setBounds(20, 20, 160, 60);
        sidebar.add(logo);

        String[] items = {"Inicio", "Mi perfil", "Buscar Vacantes", "Mis postulaciones", "Entrevistas", "Notificaciones", "Documentos"};
        int y = 110;
        for (String item : items) {
            JButton btn = new JButton(item);
            btn.setBounds(10, y, 210, 36);
            btn.setBackground(item.equals(activo) ? COLOR_ACENTO : COLOR_MENU);
            btn.setForeground(Color.WHITE);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            sidebar.add(btn);
            y += 44;
        }

        JLabel lblSist = new JLabel("Sistema");
        lblSist.setBounds(15, y + 10, 200, 16);
        lblSist.setForeground(Color.GRAY);
        lblSist.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        sidebar.add(lblSist);

        JButton btnConf = new JButton("Configuracion");
        btnConf.setBounds(10, y + 30, 210, 36);
        btnConf.setBackground(COLOR_MENU);
        btnConf.setForeground(Color.WHITE);
        btnConf.setBorderPainted(false);
        btnConf.setFocusPainted(false);
        btnConf.setHorizontalAlignment(SwingConstants.LEFT);
        sidebar.add(btnConf);

        return sidebar;
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
        SwingUtilities.invokeLater(() -> new Publicaciones().setVisible(true));
    }
}
