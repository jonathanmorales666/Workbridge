import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class WorkBridgeApp extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, Component> screens;

    public WorkBridgeApp() {
        setTitle("Work Bridge - Sistema Unificado");
        setSize(1400, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        screens = new HashMap<>();

        initializeScreens();

        add(mainPanel);

        mostrarPantalla("registro");

        setVisible(true);
    }

    private void initializeScreens() {

        // Pantallas principales del trabajador
        screens.put("registro", new Registro());
        screens.put("publicaciones", new Publicaciones());
        screens.put("perfilTrabajador", new PerfilPublicoTrabajador());
        screens.put("documentos", new GestionDocumentos());
        screens.put("notificaciones", new Notificaciones());
        screens.put("comunicaciones", new Comunicaciones());

        // Pantallas del administrador
        screens.put("empresas", new VerificacionEmpresas());
        screens.put("habilidades", new GestionHabilidades());

        // Pantallas de gestión
        screens.put("dashboardAdmin", new DashboardAdmin());
        screens.put("gestionUsuarios", new GestionUsuarios());

        // Dashboard empresarial
        screens.put("dashboardEmpresa", crearDashboardEmpresa());

        // Agregar pantallas al CardLayout
        for (Map.Entry<String, Component> entry : screens.entrySet()) {
            mainPanel.add(entry.getValue(), entry.getKey());
        }
    }

    private JPanel crearDashboardEmpresa() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Dashboard Empresarial");
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    public void mostrarPantalla(String nombrePantalla) {
        if (screens.containsKey(nombrePantalla)) {
            cardLayout.show(mainPanel, nombrePantalla);
        } else {
            System.err.println("Pantalla no encontrada: " + nombrePantalla);
        }
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            WorkBridgeApp app = new WorkBridgeApp();
            app.setVisible(true);
        });
    }
}