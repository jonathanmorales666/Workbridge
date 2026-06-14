import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionUsuarios extends JFrame {

    public GestionUsuarios() {
        Color BEIGE = Color.decode("#D4CDC5");
        Color AZUL_CLARO = Color.decode("#5B88A5");
        Color AZUL_OSCURO = Color.decode("#243A69");
        Color MORADO = Color.decode("#9B73A6");

        setTitle("Gestión de Usuarios");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, 350, 1080);
        menu.setBackground(AZUL_OSCURO);

        JLabel logo = new JLabel();
        logo.setBounds(25, 20, 300, 130);
        menu.add(logo);

        String[] botones = {
                "Inicio",
                "Mi perfil",
                "Buscar Vacantes",
                "Mis postulaciones",
                "Entrevistas",
                "Notificaciones",
                "Documentos"
        };

        int y = 180;

        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setBounds(20, y, 280, 40);
            btn.setBackground(AZUL_OSCURO);
            btn.setForeground(Color.WHITE);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            menu.add(btn);
            y += 50;
        }

        add(menu);

        JPanel principal = new JPanel();
        principal.setLayout(null);
        principal.setBounds(350, 0, 1570, 1080);
        principal.setBackground(Color.WHITE);

        JPanel superior = new JPanel();
        superior.setLayout(null);
        superior.setBounds(0, 0, 1570, 70);
        superior.setBackground(BEIGE);

        JLabel titulo = new JLabel("Gestión de Usuarios");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBounds(30, 20, 300, 30);

        superior.add(titulo);
        principal.add(superior);

        add(principal);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestionUsuarios();
        });
    }
}
