package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOPessoa;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class GerenciamentoPessoas extends JFrame {
    private JPanel contentPane;
    private JButton buttonApagar;
    private JButton buttonSair;
    private JTable tablePessoas;
    private JButton buttonEditar;

    static Color headerBackground = new Color(15, 15, 15);
    static Color text = new Color(218, 218, 218);
    static Color background = new Color(38, 38, 38);

    public GerenciamentoPessoas() throws SQLException {
        setContentPane(contentPane);
        setSize(900, 600);
        setTitle("Gerenciamento de Pessoas");
        setLocationRelativeTo(null);

        tablePessoas.getTableHeader().setDefaultRenderer(new Menu.HeaderColor());
        tablePessoas.setForeground(text);
        tablePessoas.setBackground(background);
        tablePessoas.setSelectionBackground(headerBackground);
        tablePessoas.setSelectionForeground(Color.white);
        tablePessoas.setShowVerticalLines(true);
        tablePessoas.setShowHorizontalLines(true);
        tablePessoas.setRowHeight(25);
        tablePessoas.setCellSelectionEnabled(false);
        tablePessoas.setRowSelectionAllowed(true);

        tablePessoas.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                },
                new String[]{
                        "ID", "Nome", "Telefone", "Email"
                }) {
            boolean[] canEdit = new boolean[]{
                    false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        dataTable();

        buttonApagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deletePessoa(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    editPessoa(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void editPessoa(ActionEvent e) throws SQLException {
        int id = 0;

        if (this.tablePessoas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para editar", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            id = Integer.parseInt(this.tablePessoas.getValueAt(this.tablePessoas.getSelectedRow(), 0).toString());
            JFrame edit = new EditarPessoa(id);
            edit.setVisible(true);
            dataTable();
        }
    }

    private void deletePessoa(ActionEvent e) throws SQLException {
        try {
            int id = 0;

            if (this.tablePessoas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                id = Integer.parseInt(this.tablePessoas.getValueAt(this.tablePessoas.getSelectedRow(), 0).toString());

                if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta pessoa?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    DAOPessoa.destroy(id);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            dataTable();
        }
    }

    public void dataTable() throws SQLException {
        DefaultTableModel tablePessoasModel = (DefaultTableModel) this.tablePessoas.getModel();
        tablePessoasModel.setNumRows(0);
        ArrayList<Pessoa> listaPessoas = DAOPessoa.index();

        if (listaPessoas.isEmpty()) {
            return;
        }

        for (Pessoa pessoa : listaPessoas) {
            tablePessoasModel.addRow(new Object[]{
                    pessoa.getId(),
                    pessoa.getNome(),
                    pessoa.getTelefone(),
                    pessoa.getEmail()
            });
        }
        tablePessoas.getColumnModel().getColumn(0).setPreferredWidth(1);
    }
}
