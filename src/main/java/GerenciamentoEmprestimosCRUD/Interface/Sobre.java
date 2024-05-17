package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.ConexaoMySQL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Sobre extends JFrame {
    private JPanel contentPane;
    private JButton buttonClose;
    private JLabel status;
    private JLabel gitHyperLink;
    private String text = "Acesse o Github";

    public Sobre() {
        setContentPane(contentPane);
        setTitle("Sobre");
        setSize(400, 290);
        setLocationRelativeTo(null);
        setResizable(false);
        String SQLstatus = ConexaoMySQL.statusConection();
        status.setText(SQLstatus);
        gitHyperLink.setForeground(Color.BLUE);
        gitHyperLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gitHyperLink.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/jlernerpagbank/A3_Java"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gitHyperLink.setText(text);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gitHyperLink.setText("<html><a href=''>" + text + "</a></html>");
            }

        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
