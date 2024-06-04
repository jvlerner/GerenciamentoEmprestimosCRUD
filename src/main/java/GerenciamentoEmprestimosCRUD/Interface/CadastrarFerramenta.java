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

    public CadastrarFerramenta() { //cria uma janela para cadastrar as ferramentas
        setContentPane(contentPane); //contém os elementos visuais da janela
        setLocationRelativeTo(null); //centraliza a janela quando exibida
        setSize(500, 260); //define o tamanho da janela
        setResizable(false); //impede a redefinição do tamanho da tela pelo usuário
        setTitle("Cadastrar Ferramenta"); //define o título da janela

        buttonCancelar.addActionListener( //quando clicado, executa o método "ActionListener"
                new ActionListener() { //cria um novo objeto da classe ActionListener
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    } //
                }
        );

        buttonCadastrarFerramenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //define o método actionPerfomed para quando o botão for clicado
                double preco;
                String nome = txtNomeFerramenta.getText();
                String marca = txtMarca.getText();

                if (nome.isEmpty()) { //define uma mensagem de erro no momento de cadastro das ferramentas
                    JOptionPane.showMessageDialog(null, "Insira um nome para a ferramenta!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (marca.isEmpty()) { //define uma mensagem de erro no momento de cadastro das ferramentas
                    JOptionPane.showMessageDialog(null, "Insira uma marca!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (txtCusto.getText().isEmpty()) { //define uma mensagem de erro no momento de cadastro das ferramentas
                    JOptionPane.showMessageDialog(null, "Insira um preço!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    preco = Double.parseDouble(txtCusto.getText().replace(",", "."));
                    Ferramenta ferramenta = new Ferramenta(); //cria um novo objeto da classe Ferramenta
                    ferramenta.setNome(nome);
                    ferramenta.setMarca(marca);
                    ferramenta.setPreco(preco);
                    DAOFerramenta.create(ferramenta);
                    txtNomeFerramenta.setText(""); //altera o nome da ferramenta
                    txtMarca.setText(""); //altera a marca da ferramenta
                    txtCusto.setText(""); //altera o valor da ferramenta
                } catch (SQLException ex) { //cria uma exceção SQL
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); //define uma mensagem de erro para a exceção SQL
                } catch (NumberFormatException ex) { //captura uma exceção NumberFormat e resolve dentro desse bloco de código
                    if (Objects.equals(ex.getMessage(), "empty String")) {
                        JOptionPane.showMessageDialog(null, "Insira um preço. Ex: '199.90' ou '199,90'", "Preço não foi inserido", JOptionPane.ERROR_MESSAGE); //define uma mensagem de erro/solução para a exceção
                    } else {
                        JOptionPane.showMessageDialog(null, "Insira um preço válido. Ex: '199.90' ou '199,90'", "Preço inserido não suportado", JOptionPane.ERROR_MESSAGE); //define uma mensagem de erro/solução para a exceção
                        txtCusto.setText("");
                    }
                }
            }
        });
    }
}
