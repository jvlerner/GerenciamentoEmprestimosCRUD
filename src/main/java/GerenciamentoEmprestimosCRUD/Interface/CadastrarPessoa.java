package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOPessoa;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class CadastrarPessoa extends JFrame {
    private JPanel contentPane;
    private JPanel pane;
    private JTextField txtEmail;
    private JButton buttonCadastrar;
    private JLabel labelTelefone;
    private JTextField txtTelefone;
    private JLabel labelEmail;
    private JTextField txtNome;
    private JLabel labelNome;
    private JButton buttonCancelar;

    public CadastrarPessoa() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setSize(500, 250);
        setResizable(false);
        setTitle("Cadastrar Pessoa");

        buttonCancelar.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );

        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String telefone = txtTelefone.getText();
                String email = txtEmail.getText();
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nome);
                pessoa.setTelefone(telefone);
                pessoa.setEmail(email);

                if (Objects.equals(nome, "")) {
                    JOptionPane.showMessageDialog(null, "Insira um nome para pessoa!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Objects.equals(telefone, "")) {
                    JOptionPane.showMessageDialog(null, "Insira um telefone!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Objects.equals(email, "")) {
                    JOptionPane.showMessageDialog(null, "Insira um email!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    DAOPessoa.create(pessoa);
                    txtNome.setText(null);
                    txtTelefone.setText(null);
                    txtEmail.setText(null);
                } catch (SQLException er) {
                    JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
