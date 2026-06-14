import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class GestionDocumentos extends JPanel {

    private final Color COLOR_MENU   = Color.decode("#243A69");
    private final Color COLOR_BLANCO = Color.WHITE;

    private DefaultTableModel modeloTabla;

    public GestionDocumentos() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JLabel lblTitulo = new JLabel("Mis Documentos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Tipo", "Nombre de archivo", "Fecha de subida", "URL"};
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

        cargarDocumentos();
    }

    private void cargarDocumentos() {
        modeloTabla.setRowCount(0);
        // En integración real, filtrar con WHERE usuario_id = ? del usuario logueado
        String sql = "SELECT tipo, nombre_archivo, subido_en, url FROM documentos ORDER BY subido_en DESC";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getString("tipo"),
                    rs.getString("nombre_archivo"),
                    rs.getTimestamp("subido_en"),
                    rs.getString("url")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar documentos: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Mis Documentos");
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GestionDocumentos());
        f.setVisible(true);
    }
}