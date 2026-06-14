import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardAdmin extends JFrame {
    private final Color COLOR_FONDO = Color.decode("#D4CDC5");
    private final Color COLOR_SECUNDARIO = Color.decode("#5B88A5");
    private final Color COLOR_MENU = Color.decode("#243A69");
    private final Color COLOR_ACENTO = Color.decode("#9B73A6");

    public DashboardAdmin() {
        setTitle("Work Bridge - Dashboard Administrativo");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel menuLateral = new JPanel();
        menuLateral.setPreferredSize(new Dimension(300, 1080));
        menuLateral.setBackground(COLOR_MENU);
        menuLateral.setLayout(null);

        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(20, 20, 250, 120);
        menuLateral.add(lblLogo);

        String[] opciones = {
                "Inicio",
                "Mi perfil",
                "Buscar Vacantes",
                "Mis postulaciones",
                "Entrevistas",
                "Notificaciones",
                "Documentos"
        };

        int y = 180;

        for (String opcion : opciones) {
            JButton btn = new JButton(opcion);
            btn.setBounds(20, y, 240, 40);
            btn.setBackground(COLOR_MENU);
            btn.setForeground(Color.WHITE);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            menuLateral.add(btn);
            y += 50;
        }

        add(menuLateral, BorderLayout.WEST);

        JPanel contenido = new JPanel();
        contenido.setBackground(Color.WHITE);
        contenido.setLayout(null);

        JLabel titulo = new JLabel("Dashboard Administrativo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBounds(40, 20, 500, 40);
        contenido.add(titulo);

        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearTarjeta(String titulo, String valor, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 50));

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {}

        SwingUtilities.invokeLater(() -> {
            DashboardAdmin ventana = new DashboardAdmin();
            ventana.setVisible(true);
        });
    }
}
