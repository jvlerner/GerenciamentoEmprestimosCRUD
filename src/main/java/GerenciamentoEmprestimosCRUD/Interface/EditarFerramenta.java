package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOFerramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

public class EditarFerramenta extends JFrame {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField txtNome;
    private JCheckBox emprestadoCheckBox;
    private JLabel labelNome;
    private JLabel labelPreco;
    private JLabel labelMarca;
    private JTextField txtMarca;
    private JTextField txtPreco;
    private JLabel labelExemplo;

    public EditarFerramenta(int id) throws SQLException {
        setContentPane(contentPane);
        setSize(400, 260);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Editar Ferramenta");
        data(id);

        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    salvarFerramenta(id);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelar();
                ;
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cancelar();
            }
        });
    }

    private void salvarFerramenta(int id) throws SQLException {
        Ferramenta ferramenta = new Ferramenta();
        double preco;

        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você deve inserir um nome para ferramenta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtPreco.getText().isEmpty() || Objects.equals(txtPreco.getText(), "0")) {
            JOptionPane.showMessageDialog(null, "Você deve inserir um preço, acima de 0!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtMarca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você deve inserir uma marca!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            preco = Double.parseDouble(txtPreco.getText());
            ferramenta.setNome(txtNome.getText());
            ferramenta.setPreco(preco);
            ferramenta.setMarca(txtMarca.getText());
            ferramenta.setEmprestado(emprestadoCheckBox.isSelected());
            ferramenta.setId(id);
            if (DAOFerramenta.update(ferramenta)) {
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            if (Objects.equals(ex.getMessage(), "empty String")) {
                JOptionPane.showMessageDialog(null, "Insira um preço. Ex: '199.90' ou '199,90'", "Preço não foi inserido", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Insira um preço válido. Ex: '199.90' ou '199,90'", "Preço inserido não suportado", JOptionPane.ERROR_MESSAGE);
                txtPreco.setText("");
            }
        }
    }

    private void cancelar() {
        dispose();
    }

    private void data(int id) throws SQLException {
        Ferramenta ferramenta = DAOFerramenta.show(id);
        txtNome.setText(ferramenta.getNome());
        txtMarca.setText(ferramenta.getMarca());
        txtPreco.setText(String.valueOf(ferramenta.getPreco()));
        emprestadoCheckBox.setSelected(ferramenta.isEmprestado());
    }

}
