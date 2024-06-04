package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOPessoa;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class GerenciamentoPessoas extends JFrame {
    private JPanel contentPane;
    private JButton buttonApagar;
    private JButton buttonSair;
    private JTable tablePessoas;
    private JButton buttonEditar;

    static Color headerBackground = new Color(15, 15, 15); //define uma cor para o fundo do cabeçalho
    static Color text = new Color(218, 218, 218); //define uma cor para o texto
    static Color background = new Color(38, 38, 38); //define uma cor geral para o fundo da interface

    public GerenciamentoPessoas() throws SQLException { //Construtor da classe GerenciamentoPessoas, que pode ter uma exceção SQLException
        setContentPane(contentPane); //define o painel de conteúdo para esta janela
        setSize(900, 600); //define o tamanho da janela
        setTitle("Gerenciamento de Pessoas"); //define o título da janela
        setLocationRelativeTo(null); //centraliza a janela na tela

        tablePessoas.getTableHeader().setDefaultRenderer(new Menu.HeaderColor()); //define um renderizador personalizado para o cabeçalho da tela
        tablePessoas.setForeground(text); //define a cor do texto da tabela
        tablePessoas.setBackground(background); //define a cor do fundo da tabela
        tablePessoas.setSelectionBackground(headerBackground); //define a cor do fundo para células selecionadas
        tablePessoas.setSelectionForeground(Color.white); //define a cor do texto das células selecionadas na tabela como branco
        tablePessoas.setShowVerticalLines(true); //define que as linhas verticais entre as células da tabela devem ser exibidas
        tablePessoas.setShowHorizontalLines(true); //define que as linhas horizontais entre as células da tabela devem ser exibidas
        tablePessoas.setRowHeight(25); //define a altura das linhas da tabela
        tablePessoas.setCellSelectionEnabled(false); //define que as células individuais da tabela não podem ser selecionadas
        tablePessoas.setRowSelectionAllowed(true); //define que as linhas da tabela podem ser selecionadas

        tablePessoas.setModel(new DefaultTableModel( //define o modelo da tabela
                new Object[][]{
                        {null, null, null, null}, //array representando os dados iniciais da tabela
                },
                new String[]{
                        "ID", "Nome", "Telefone", "Email" //array representando os nomes das colunas da tabela
                }) {
            boolean[] canEdit = new boolean[]{ //sobrescreve o método isCellEditable para permitir ou não a edição das células
                    false, true, true, true //define quais colunas podem ser editadas
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            } //retorna se a célula na posição especificada é editável
        });

        dataTable(); //preenche os dados da tabela

        buttonApagar.addActionListener(new ActionListener() { //adiciona um ActionListener ao botão "buttonApagar" para lidar com o evento de clique
            public void actionPerformed(ActionEvent e) {
                try {
                    deletePessoa(e); //exclui uma entrada da tabela
                } catch (SQLException ex) {
                    throw new RuntimeException(ex); //lança uma exceção se ocorrer um erro durante a exclusão
                }
            }
        });

        buttonEditar.addActionListener(new ActionListener() { //adiciona um ActionListener ao botão "buttonEditar" para lidar com o evento de clique
            public void actionPerformed(ActionEvent e) {
                try {
                    editPessoa(e); //edita uma entrada na tabela
                } catch (SQLException ex) {
                    throw new RuntimeException(ex); //lança uma exceção em caso de erro
                }
            }
        });

        buttonSair.addActionListener(new ActionListener() { //adiciona um ActionListener ao botão "buttonSair" para lidar com o evento de clique
            public void actionPerformed(ActionEvent e) {
                dispose();
            } //fecha a janela (frame) onde o botão está localizado
        });
    }

    private void editPessoa(ActionEvent e) throws SQLException { //método para a edição de uma pessoa
        int id = 0;

        if (this.tablePessoas.getSelectedRow() == -1) { //verifica se uma linha da tabela está selecionada
            JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para editar", "Erro", JOptionPane.ERROR_MESSAGE); //define uma mensagem de erro caso não tenha linha selecionada
        } else {
            id = Integer.parseInt(this.tablePessoas.getValueAt(this.tablePessoas.getSelectedRow(), 0).toString()); //obtém o id da pessoa
            JFrame edit = new EditarPessoa(id); //abre uma nova janela para editar pessoa
            edit.setVisible(true);
            dataTable(); //atualiza os dados da tabela
        }
    }

    private void deletePessoa(ActionEvent e) throws SQLException { //método para excluir uma pessoa
        try {
            int id = 0;

            if (this.tablePessoas.getSelectedRow() == -1) { //verifica se alguma linha está selecionada
                JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para excluir", "Erro", JOptionPane.ERROR_MESSAGE); //exibe mensagem de erro se não houver linha selecionada
            } else {
                id = Integer.parseInt(this.tablePessoas.getValueAt(this.tablePessoas.getSelectedRow(), 0).toString()); //obtém o id da pessoa

                if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta pessoa?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) { //exibe uma mensagem para confirmar a exclusão
                    DAOPessoa.destroy(id); //exclui a pessoa do banco de dados
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); //exibe uma mensagem para caso ocorra algum erro na exclusão
        } finally {
            dataTable(); //atualiza a tabela
        }
    }

    public void dataTable() throws SQLException { //método para preencher os dados da tabela
        DefaultTableModel tablePessoasModel = (DefaultTableModel) this.tablePessoas.getModel(); //obtém o modelo da tabela
        tablePessoasModel.setNumRows(0); //limpa todas as linhas da tabela
        ArrayList<Pessoa> listaPessoas = DAOPessoa.index(); //obtém a lista de pessoas do banco de dados

        if (listaPessoas.isEmpty()) { //verifica se a tabela está vazia
            return; //se estiver vazia, retorna sem nada
        }

        for (Pessoa pessoa : listaPessoas) { //adiciona cada pessoa á tabela
            tablePessoasModel.addRow(new Object[]{
                    pessoa.getId(),
                    pessoa.getNome(),
                    pessoa.getTelefone(),
                    pessoa.getEmail()
            });
        }
        tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(1); //define a largura preferencial da primeira coluna para 1 pixel
    }

}
