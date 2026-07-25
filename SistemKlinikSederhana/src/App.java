import database.DatabaseConnection;
import ui.LoginFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        DatabaseConnection.initializeDatabase();
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
