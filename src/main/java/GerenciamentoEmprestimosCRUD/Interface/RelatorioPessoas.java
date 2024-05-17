package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOEmprestimo;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class RelatorioPessoas extends JFrame {
    private JPanel contentPane;
    private JButton buttonAtualizar;
    private JButton buttonSair;
    private JTable tableRelatorio;
    private JLabel totalEmp;
    private JLabel totalDevolvido;
    private JLabel totalNaoDevolvido;
    private JLabel lblNome;
    private JTextField txtProcurarNome;
    static Color headerBackground = new Color(15, 15, 15);
    static Color text = new Color(218, 218, 218);
    static Color background = new Color(38, 38, 38);

    public RelatorioPessoas() throws SQLException {
        setContentPane(contentPane);
        setSize(1100, 600);
        setTitle("Relatório de Pessoas");
        setLocationRelativeTo(null);

        tableRelatorio.getTableHeader().setDefaultRenderer(new Menu.HeaderColor());
        tableRelatorio.setForeground(text);
        tableRelatorio.setBackground(background);
        tableRelatorio.setSelectionBackground(headerBackground);
        tableRelatorio.setSelectionForeground(Color.white);
        tableRelatorio.setShowVerticalLines(true);
        tableRelatorio.setShowHorizontalLines(true);
        tableRelatorio.setRowHeight(25);
        tableRelatorio.setCellSelectionEnabled(false);
        tableRelatorio.setRowSelectionAllowed(true);

        DefaultTableModel model;
        tableRelatorio.setModel(model = new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                },
                new String[]{
                        "ID", "Nome", "Telefone", "Email", "Total Emp.", "Ferramentas Devolvidas", "Ferramentas Emprestadas"
                }) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        tableRelatorio.setRowSorter(sorter);


        dataTable();

        buttonAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String text = txtProcurarNome.getText();

                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    } catch (PatternSyntaxException pse) {
                        System.out.println("Não foi possivel filtrar");
                    }
                }

                try {
                    dataTable();
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

    public void dataTable() throws SQLException {
        DefaultTableModel tableModel = (DefaultTableModel) this.tableRelatorio.getModel();
        tableModel.setNumRows(0);
        ArrayList<Pessoa> lista = null;
        lista = DAOEmprestimo.relatorioAmigos();

        if (lista.isEmpty()) {
            return;
        }

        int totalEmprestadoNumber = 0;
        int totalDevolvidoNumber = 0;
        int totalNaoDevolvidoNumber = 0;

        for (Pessoa pessoa : lista) {
            int totalEmp = pessoa.getTotalEmprestimos();
            int totalDev = pessoa.getTotalDevolvidos();
            int totalNDev = totalEmp - totalDev;

            totalEmprestadoNumber += totalEmp;
            totalDevolvidoNumber += totalDev;
            totalNaoDevolvidoNumber += totalNDev;

            tableModel.addRow(new Object[]{
                    pessoa.getId(),
                    pessoa.getNome(),
                    pessoa.getTelefone(),
                    pessoa.getEmail(),
                    totalEmp,
                    totalDev,
                    totalNDev
            });
        }
        tableRelatorio.getColumnModel().getColumn(0).setPreferredWidth(1);
        tableRelatorio.getColumnModel().getColumn(1).setPreferredWidth(15);
        tableRelatorio.getColumnModel().getColumn(2).setPreferredWidth(9);

        totalEmp.setText("Total de Empréstimos Realizados: " + totalEmprestadoNumber);

        totalDevolvido.setText("Total de Empréstimos Devolvidos: " + totalDevolvidoNumber);

        totalNaoDevolvido.setText("Total Empréstimos Não Devolvidos: " + totalNaoDevolvidoNumber);
    }
}

