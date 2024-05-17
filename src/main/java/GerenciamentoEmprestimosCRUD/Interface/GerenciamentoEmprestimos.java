package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOEmprestimo;
import GerenciamentoEmprestimosCRUD.Modelo.Emprestimo;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class GerenciamentoEmprestimos extends JFrame {
    private JPanel contentPane;
    private JButton buttonApagar;
    private JButton buttonSair;
    private JTable tableEmprestimos;
    private JButton buttonEditar;

    static Color headerBackground = new Color(15, 15, 15);
    static Color text = new Color(218, 218, 218);
    static Color background = new Color(38, 38, 38);

    public GerenciamentoEmprestimos() throws SQLException {
        setContentPane(contentPane);
        setSize(900, 600);
        setTitle("Gerenciamento Empréstimos");
        setLocationRelativeTo(null);

        tableEmprestimos.getTableHeader().setDefaultRenderer(new Menu.HeaderColor());
        tableEmprestimos.setForeground(text);
        tableEmprestimos.setBackground(background);
        tableEmprestimos.setSelectionBackground(headerBackground);
        tableEmprestimos.setSelectionForeground(Color.white);
        tableEmprestimos.setShowVerticalLines(true);
        tableEmprestimos.setShowHorizontalLines(true);
        tableEmprestimos.setRowHeight(25);
        tableEmprestimos.setCellSelectionEnabled(false);
        tableEmprestimos.setRowSelectionAllowed(true);

        tableEmprestimos.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                },
                new String[]{
                        "ID", "Ferramenta", "Pessoa", "Data Empréstimo", "Data Entrega"
                }) {
            boolean[] canEdit = new boolean[]{
                    false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        dataTable();

        buttonApagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteEmprestimo(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    editEmprestimo(e);
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

    private void editEmprestimo(ActionEvent e) throws SQLException {
        int id = 0;

        if (this.tableEmprestimos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para editar", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            id = Integer.parseInt(this.tableEmprestimos.getValueAt(this.tableEmprestimos.getSelectedRow(), 0).toString());
            Emprestimo emprestimo = DAOEmprestimo.show(id);
            JFrame edit = new EditarEmprestimos(emprestimo);
            edit.setVisible(true);
            dataTable();
        }
    }

    private void deleteEmprestimo(ActionEvent e) throws SQLException {
        try {
            int id = 0;

            if (this.tableEmprestimos.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Primeiro selecione um empréstimo para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                id = Integer.parseInt(this.tableEmprestimos.getValueAt(this.tableEmprestimos.getSelectedRow(), 0).toString());
                DAOEmprestimo.destroy(id);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            dataTable();
        }
    }

    public void dataTable() throws SQLException {
        DefaultTableModel tableEmprestimossModel = (DefaultTableModel) this.tableEmprestimos.getModel();
        tableEmprestimossModel.setNumRows(0);
        ArrayList lista = DAOEmprestimo.index();

        if (lista.isEmpty()) {
            return;
        }
        for (Object listaObjetos : lista) {
            ArrayList listaModels = (ArrayList) listaObjetos;
            ArrayList row = new ArrayList() {
                {
                    add(0);
                    add(1);
                    add(2);
                    add(3);
                    add(4);
                }

                ;
            };
            for (Object Model : listaModels) {
                if (Model instanceof Emprestimo) {
                    Emprestimo emp = (Emprestimo) Model;
                    int id = emp.getId();
                    Date datain = (Date) emp.getDataIn();
                    Date dataout = (Date) emp.getDataOut();
                    row.set(0, id);
                    row.set(4, datain);
                    row.set(3, dataout);
                }

                if (Model instanceof Ferramenta) {
                    Ferramenta ferramenta = (Ferramenta) Model;
                    String thisferramenta = ferramenta.getNome();
                    row.set(1, thisferramenta);
                }

                if (Model instanceof Pessoa) {
                    Pessoa pessoa = (Pessoa) Model;
                    String nome = pessoa.getNome();
                    row.set(2, nome);
                }
            }
            tableEmprestimossModel.addRow(row.toArray());
        }
        tableEmprestimos.getColumnModel().getColumn(0).setPreferredWidth(1);
    }
}
