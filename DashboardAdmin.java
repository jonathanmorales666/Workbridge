import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class DashboardAdmin extends JPanel {

    private final Color COLOR_MENU      = Color.decode("#243A69");
    private final Color COLOR_SECUNDARIO= Color.decode("#5B88A5");
    private final Color COLOR_ACENTO    = Color.decode("#9B73A6");
    private final Color COLOR_FONDO     = Color.decode("#D4CDC5");

    public DashboardAdmin() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Dashboard Administrativo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(new EmptyBorder(20, 40, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelTarjetas = new JPanel(new GridLayout(2, 3, 20, 20));
        panelTarjetas.setBackground(Color.WHITE);
        panelTarjetas.setBorder(new EmptyBorder(20, 40, 40, 40));

        try (Connection con = ConexionDB.getConexion()) {
            panelTarjetas.add(crearTarjeta("Usuarios totales",    contarQuery(con, "SELECT COUNT(*) FROM usuarios"),              COLOR_SECUNDARIO));
            panelTarjetas.add(crearTarjeta("Trabajadores",        contarQuery(con, "SELECT COUNT(*) FROM usuarios WHERE rol='trabajador'"), Color.decode("#4A90D9")));
            panelTarjetas.add(crearTarjeta("Empresas registradas",contarQuery(con, "SELECT COUNT(*) FROM empresas"),               COLOR_MENU));
            panelTarjetas.add(crearTarjeta("Empresas pendientes", contarQuery(con, "SELECT COUNT(*) FROM empresas WHERE estado_verificacion='pendiente'"), new Color(200, 130, 30)));
            panelTarjetas.add(crearTarjeta("Vacantes activas",    contarQuery(con, "SELECT COUNT(*) FROM vacantes WHERE estado='activa'"), new Color(34, 139, 34)));
            panelTarjetas.add(crearTarjeta("Postulaciones totales",contarQuery(con, "SELECT COUNT(*) FROM postulaciones"),         COLOR_ACENTO));
        } catch (SQLException ex) {
            panelTarjetas.add(new JLabel("Error al conectar: " + ex.getMessage()));
        }

        add(panelTarjetas, BorderLayout.CENTER);
    }

    private String contarQuery(Connection con, String sql) {
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return String.valueOf(rs.getInt(1));
        } catch (SQLException ex) { return "?"; }
        return "0";
    }

    private JPanel crearTarjeta(String titulo, String valor, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblValor.setForeground(Color.WHITE);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(lblValor, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Dashboard Admin");
        f.setSize(1200, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new DashboardAdmin());
        f.setVisible(true);
    }
}