// PerfilPublicoTrabajador.java
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PerfilPublicoTrabajador extends JPanel {

    private final Color COLOR_MENU = Color.decode("#243A69");

    public PerfilPublicoTrabajador() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JLabel lblTitulo = new JLabel("Perfil del Candidato");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelInfo = new JPanel(null);
        panelInfo.setBackground(Color.WHITE);
        add(panelInfo, BorderLayout.CENTER);

        // Carga el primer trabajador disponible como ejemplo
        // En integración real recibir el usuario_id como parámetro del constructor
        cargarPerfil(panelInfo);
    }

    private void cargarPerfil(JPanel panel) {
        String sql = "SELECT u.nombre, u.apellido, u.email, u.departamento, u.municipio, " +
                     "p.resumen, p.nivel_experiencia, p.modalidad_preferida, p.disponible " +
                     "FROM usuarios u JOIN perfiles_trabajador p ON u.id = p.usuario_id LIMIT 1";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String nombre    = rs.getString("nombre") + " " + rs.getString("apellido");
                String email     = rs.getString("email");
                String ubicacion = rs.getString("departamento") + ", " + rs.getString("municipio");
                String resumen   = rs.getString("resumen");
                String nivel     = rs.getString("nivel_experiencia");
                String modalidad = rs.getString("modalidad_preferida");
                boolean disponible = rs.getBoolean("disponible");

                JLabel lNombre = new JLabel(nombre);
                lNombre.setFont(new Font("Segoe UI", Font.BOLD, 20));
                lNombre.setBounds(40, 30, 500, 30);
                panel.add(lNombre);

                agregarCampo(panel, "Correo:",      email,     80);
                agregarCampo(panel, "Ubicación:",   ubicacion, 120);
                agregarCampo(panel, "Nivel:",       nivel,     160);
                agregarCampo(panel, "Modalidad:",   modalidad, 200);
                agregarCampo(panel, "Disponible:",  disponible ? "Sí" : "No", 240);

                JLabel lResumen = new JLabel("<html><b>Resumen:</b><br>" + (resumen != null ? resumen : "Sin resumen") + "</html>");
                lResumen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                lResumen.setBounds(40, 290, 700, 80);
                panel.add(lResumen);
            } else {
                JLabel l = new JLabel("No se encontró perfil de trabajador.");
                l.setBounds(40, 40, 400, 30);
                panel.add(l);
            }
        } catch (SQLException ex) {
            JLabel l = new JLabel("Error al cargar perfil: " + ex.getMessage());
            l.setBounds(40, 40, 600, 30);
            panel.add(l);
        }
    }

    private void agregarCampo(JPanel panel, String etiqueta, String valor, int y) {
        JLabel lbl = new JLabel("<html><b>" + etiqueta + "</b> " + (valor != null ? valor : "—") + "</html>");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setBounds(40, y, 600, 25);
        panel.add(lbl);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Perfil Trabajador");
        f.setSize(900, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new PerfilPublicoTrabajador());
        f.setVisible(true);
    }
}