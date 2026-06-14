import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Notificaciones extends JPanel {

    private final Color COLOR_MENU   = Color.decode("#243A69");
    private final Color COLOR_ACENTO = Color.decode("#9B73A6");
    private final Color COLOR_BLANCO = Color.WHITE;

    private JPanel panelLista;

    public Notificaciones() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JLabel lblTitulo = new JLabel("Notificaciones");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(new Color(245, 247, 250));

        JScrollPane scroll = new JScrollPane(panelLista);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);

        cargarNotificaciones();
    }

    private void cargarNotificaciones() {
        panelLista.removeAll();
        // En integración real, filtrar por el usuario logueado con WHERE usuario_id = ?
        String sql = "SELECT titulo, mensaje, leida, creado_en FROM notificaciones ORDER BY creado_en DESC LIMIT 50";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                boolean leida  = rs.getBoolean("leida");
                String titulo  = rs.getString("titulo");
                String mensaje = rs.getString("mensaje");
                String fecha   = rs.getTimestamp("creado_en").toString();
                panelLista.add(crearTarjeta(titulo, mensaje, fecha, leida));
            }
            if (!hayResultados) {
                JLabel lbl = new JLabel("  No hay notificaciones.");
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lbl.setForeground(Color.GRAY);
                panelLista.add(lbl);
            }
        } catch (SQLException ex) {
            panelLista.add(new JLabel("  Error al cargar notificaciones: " + ex.getMessage()));
        }
        panelLista.revalidate();
        panelLista.repaint();
    }

    private JPanel crearTarjeta(String titulo, String mensaje, String fecha, boolean leida) {
        JPanel card = new JPanel(null);
        card.setBackground(leida ? COLOR_BLANCO : new Color(232, 240, 254));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        card.setPreferredSize(new Dimension(0, 80));
        card.setBorder(BorderFactory.createMatteBorder(0, leida ? 0 : 4, 1, 0,
                leida ? Color.LIGHT_GRAY : Color.decode("#243A69")));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setBounds(20, 10, 700, 20);
        card.add(lblTitulo);

        JLabel lblMsg = new JLabel(mensaje != null ? mensaje : "");
        lblMsg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMsg.setForeground(Color.DARK_GRAY);
        lblMsg.setBounds(20, 30, 700, 18);
        card.add(lblMsg);

        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFecha.setForeground(Color.GRAY);
        lblFecha.setBounds(20, 52, 400, 16);
        card.add(lblFecha);

        return card;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Notificaciones");
        f.setSize(900, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Notificaciones());
        f.setVisible(true);
    }
}