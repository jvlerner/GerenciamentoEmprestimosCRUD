package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOPessoa;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
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

public class EditarPessoa extends JFrame {
    private JPanel contentPane;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JTextField txtNome;
    private JLabel labelNome;
    private JLabel labelEmail;
    private JLabel labelTelefone;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JCheckBox emprestadoCheckBox;

    public EditarPessoa(int id) throws SQLException {
        setContentPane(contentPane);
        setSize(400, 260);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Editar Pessoa");
        data(id);

        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    salvarPessoa(id);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void salvarPessoa(int id) throws SQLException {
        Pessoa pessoa = new Pessoa();

        if (txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você deve inserir um nome para a pessoa!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você deve inserir um email!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtTelefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você deve inserir um telefone!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            pessoa.setNome(txtNome.getText());
            pessoa.setEmail(txtEmail.getText());
            pessoa.setTelefone(txtTelefone.getText());
            pessoa.setId(id);
            if (DAOPessoa.update(pessoa)) {
                dispose();
            }
            ;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void data(int id) throws SQLException {
        Pessoa pessoa = DAOPessoa.show(id);
        txtNome.setText(pessoa.getNome());
        txtTelefone.setText(pessoa.getTelefone());
        txtEmail.setText(String.valueOf(pessoa.getEmail()));
    }

}

