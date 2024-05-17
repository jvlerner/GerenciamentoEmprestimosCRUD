package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOFerramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class CadastrarFerramenta extends JFrame {
    private JPanel contentPane;
    private JPanel cadFerramenta;
    private JTextField txtCusto;
    private JTextField txtMarca;
    private JTextField txtNomeFerramenta;
    private JButton buttonCadastrarFerramenta;
    private JLabel labelNomeFerramenta;
    private JLabel labelMarca;
    private JLabel labelCusto;
    private JLabel labelExemplo;
    private JButton buttonCancelar;

    public CadastrarFerramenta() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setSize(500, 260);
        setResizable(false);
        setTitle("Cadastrar Ferramenta");

        buttonCancelar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        buttonCadastrarFerramenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double preco;
                String nome = txtNomeFerramenta.getText();
                String marca = txtMarca.getText();

                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insira um nome para a ferramenta!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (marca.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insira uma marca!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (txtCusto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insira um preço!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    preco = Double.parseDouble(txtCusto.getText().replace(",", "."));
                    Ferramenta ferramenta = new Ferramenta();
                    ferramenta.setNome(nome);
                    ferramenta.setMarca(marca);
                    ferramenta.setPreco(preco);
                    DAOFerramenta.create(ferramenta);
                    txtNomeFerramenta.setText("");
                    txtMarca.setText("");
                    txtCusto.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    if (Objects.equals(ex.getMessage(), "empty String")) {
                        JOptionPane.showMessageDialog(null, "Insira um preço. Ex: '199.90' ou '199,90'", "Preço não foi inserido", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insira um preço válido. Ex: '199.90' ou '199,90'", "Preço inserido não suportado", JOptionPane.ERROR_MESSAGE);
                        txtCusto.setText("");
                    }
                }
            }
        });
    }
}
