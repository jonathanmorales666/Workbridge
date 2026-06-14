import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class GestionUsuarios extends JPanel {

    private final Color COLOR_MENU   = Color.decode("#243A69");
    private final Color COLOR_BLANCO = Color.WHITE;

    private DefaultTableModel modeloTabla;

    public GestionUsuarios() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel superior = new JPanel(null);
        superior.setBackground(Color.decode("#D4CDC5"));
        superior.setPreferredSize(new Dimension(0, 70));
        JLabel titulo = new JLabel("Gestión de Usuarios");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBounds(30, 20, 400, 30);
        superior.add(titulo);
        add(superior, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Apellido", "Correo", "Rol", "Registrado en"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(COLOR_MENU);
        tabla.getTableHeader().setForeground(COLOR_BLANCO);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scroll, BorderLayout.CENTER);

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0);
        String sql = "SELECT id, nombre, apellido, email, rol, creado_en FROM usuarios ORDER BY creado_en DESC";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("rol"),
                    rs.getTimestamp("creado_en")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Gestión de Usuarios");
        f.setSize(1200, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GestionUsuarios());
        f.setVisible(true);
    }
}