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

    public CadastrarPessoa() { //cria uma janela para cadastrar pessoas
        setContentPane(contentPane); //contém os elementos visuais da janela
        setLocationRelativeTo(null); //centraliza a janela quando exibida
        setSize(500, 250); //define o tamanho da janela
        setResizable(false); //impede a redefinição do tamanho da tela pelo usuário
        setTitle("Cadastrar Pessoa"); //define o título da janela

        buttonCancelar.addActionListener( //quando clicado, executa o método "ActionListener"
                new ActionListener() { //cria um novo objeto da classe ActionListener
                    public void actionPerformed(ActionEvent e) { //define o método actionPerfomed para quando o botão for clicado
                        dispose();
                    }
                }
        );

        buttonCadastrar.addActionListener(new ActionListener() { //
            @Override
            public void actionPerformed(ActionEvent e) { //define o método actionPerfomed para quando o botão for clicado
                String nome = txtNome.getText();
                String telefone = txtTelefone.getText();
                String email = txtEmail.getText();
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nome);
                pessoa.setTelefone(telefone);
                pessoa.setEmail(email);

                if (Objects.equals(nome, "")) { //define uma mensagem de erro no momento de cadastro da pessoa
                    JOptionPane.showMessageDialog(null, "Insira um nome para pessoa!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Objects.equals(telefone, "")) { //define uma mensagem de erro no momento de cadastro da pessoa
                    JOptionPane.showMessageDialog(null, "Insira um telefone!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Objects.equals(email, "")) { //define uma mensagem de erro no momento de cadastro da pessoa
                    JOptionPane.showMessageDialog(null, "Insira um email!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    DAOPessoa.create(pessoa); //cria o cadastro de uma pessoa
                    txtNome.setText(null);
                    txtTelefone.setText(null);
                    txtEmail.setText(null);
                } catch (SQLException er) { //exibe uma mensagem de erro no caso da exceção SQL
                    JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
