import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class GestionHabilidades extends JFrame {

    private final Color COLOR_MENU   = Color.decode("#243A69");
    private final Color COLOR_SEC    = Color.decode("#5B88A5");
    private final Color COLOR_ACENTO = Color.decode("#9B73A6");
    private final Color COLOR_FONDO  = Color.decode("#D4CDC5");
    private final Color COLOR_BLANCO = Color.WHITE;

    public GestionHabilidades() {
        setTitle("Work Bridge - Gestión de Habilidades");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(crearSidebarAdmin(), BorderLayout.WEST);

        JPanel contenido = new JPanel();
        contenido.setBackground(new Color(245, 247, 250));
        contenido.setLayout(null);

        JLabel lblTitulo = new JLabel("Gestión de Habilidades");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(30, 15, 500, 35);
        contenido.add(lblTitulo);

        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearSidebarAdmin() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(230, 900));
        sidebar.setBackground(COLOR_MENU);
        sidebar.setLayout(null);

        JLabel logo = new JLabel("<html><b><font color='white' size='13'>Work<br>Bridge</font></b></html>");
        logo.setBounds(20, 20, 160, 60);
        sidebar.add(logo);

        String[] items = {"Dashboard", "Reportes"};
        int y = 118;
        for (String item : items) {
            JButton btn = crearBtn(item, false);
            btn.setBounds(10, y, 210, 36);
            sidebar.add(btn);
            y += 44;
        }

        return sidebar;
    }

    private JButton crearBtn(String texto, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setBackground(activo ? COLOR_ACENTO : COLOR_MENU);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return btn;
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
        SwingUtilities.invokeLater(() -> new GestionHabilidades().setVisible(true));
    }
}
