package GerenciamentoEmprestimosCRUD.Principal;

import GerenciamentoEmprestimosCRUD.Interface.Menu;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Principal {
    // metodo principal da aplicação
    public static void main(String[] args) throws SQLException {
        // decora a aparência padrão das janelas JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        // da a padronização da interface no UIManager
        UIDefaults uiDefaults = UIManager.getDefaults();
        // define a cor base do nimbus
        uiDefaults.put("nimbusBase", new Color(255, 255, 255));
        // define a cor nimbus blue grey
        uiDefaults.put("nimbusBlueGrey", new Color(36, 38, 44));
        // define a cor do controle
        uiDefaults.put("control", new Color(213, 213, 213));

        try {
            //  navega pelos estilos de aparência que estão gravados
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                // se o nome do estilo for Nimbus
                if ("Nimbus".equals(info.getName())) {
                    // define o estilo de aparencia para o Nimbus
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                    // sai do loop depois de encontrar o Nimbus
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        new Menu();
    }
}
