package GerenciamentoEmprestimosCRUD.Principal;

import GerenciamentoEmprestimosCRUD.Interface.Menu;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Principal {
    // metodo principal da aplicação
    public static void main(String[] args) throws SQLException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("nimbusBase", new Color(255, 255, 255));
        uiDefaults.put("nimbusBlueGrey", new Color(36, 38, 44));
        uiDefaults.put("control", new Color(213, 213, 213));

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        new Menu();
    }
}
