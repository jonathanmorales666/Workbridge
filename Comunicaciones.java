import javax.swing.*;
import java.awt.*;

public class Comunicaciones extends JFrame {

    private final Color COLOR_MENU   = Color.decode("#243A69");
    private final Color COLOR_SEC    = Color.decode("#5B88A5");
    private final Color COLOR_ACENTO = Color.decode("#9B73A6");
    private final Color COLOR_FONDO  = Color.decode("#D4CDC5");
    private final Color COLOR_BLANCO = Color.WHITE;

    public Comunicaciones() {
        setTitle("Work Bridge - Comunicaciones");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(crearSidebar("Comunicaciones"), BorderLayout.WEST);

        JPanel contenido = new JPanel();
        contenido.setBackground(new Color(245, 247, 250));
        contenido.setLayout(null);

        JLabel lblTitulo = new JLabel("Comunicaciones");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(30, 15, 400, 35);
        contenido.add(lblTitulo);

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

        String[] items = {"Inicio", "Mi empresa", "Mis Vacantes", "Postulaciones", "Entrevistas", "Comunicaciones", "Notificaciones"};
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
        SwingUtilities.invokeLater(() -> new Comunicaciones().setVisible(true));
    }
}
