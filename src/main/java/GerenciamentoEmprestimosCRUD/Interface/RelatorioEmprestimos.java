package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOEmprestimo;
import GerenciamentoEmprestimosCRUD.DAO.DAOFerramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Emprestimo;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

public class RelatorioEmprestimos extends JFrame {
    private JPanel contentPane;
    private JButton buttonAtualizar;
    private JButton buttonSair;
    private JTable tableRelatorio;
    private JCheckBox emprestadoCheckBox;
    private JCheckBox disponívelCheckBox;
    private JLabel totalValue;
    private JLabel totalEmp;
    private JLabel totalDisp;
    private JTextField txtProcurarNome;
    private JLabel lblNome;

    static Color headerBackground = new Color(15, 15, 15);
    static Color text = new Color(218, 218, 218);
    static Color background = new Color(38, 38, 38);

    public RelatorioEmprestimos() throws SQLException {
        setContentPane(contentPane);
        setSize(1100, 600);
        setTitle("Relatório de Empréstimos");
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
                        {null, null, null, null, null, null, null, null, null},
                },
                new String[]{
                        "ID", "Nome", "Telefone", "Email", "Ferramenta", "Marca", "Preço", "Data Saida", "Data Devolução"
                }) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false
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
        int filter = 0;

        if (emprestadoCheckBox.isSelected() && !disponívelCheckBox.isSelected()) {
            filter = 1;
        }

        if (disponívelCheckBox.isSelected() && !emprestadoCheckBox.isSelected()) {
            filter = 2;
        }

        if (emprestadoCheckBox.isSelected() && disponívelCheckBox.isSelected()) {
            filter = 0;
        }

        DefaultTableModel tableFerramentasModel = (DefaultTableModel) this.tableRelatorio.getModel();
        tableFerramentasModel.setNumRows(0);
        double value = DAOFerramenta.value();
        totalValue.setText("Total em ferramentas: R$ " + String.format("%.2f", value).replace(".", ","));
        String empCount = String.valueOf(DAOFerramenta.emp());
        totalEmp.setText("Ferramentas Emprestadas: " + empCount);
        String dispCount = String.valueOf(DAOFerramenta.disp());
        totalDisp.setText("Ferramentas Disponíveis: " + dispCount);

        ArrayList lista = null;
        lista = DAOEmprestimo.relatorio(filter);

        if (lista != null) {
            for (Object listaObjetos : lista) {
                ArrayList listaModels = (ArrayList) listaObjetos;
                ArrayList row = new ArrayList() {
                    {
                        add(0);
                        add(1);
                        add(2);
                        add(3);
                        add(4);
                        add(5);
                        add(6);
                        add(7);
                        add(8);
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
                        row.set(8, datain);
                        row.set(7, dataout);
                    }

                    if (Model instanceof Ferramenta) {
                        Ferramenta ferramenta = (Ferramenta) Model;
                        String thisferramenta = ferramenta.getNome();
                        String marca = ferramenta.getMarca();
                        double preco = ferramenta.getPreco();
                        row.set(4, thisferramenta);
                        row.set(5, marca);
                        row.set(6, preco);
                    }

                    if (Model instanceof Pessoa) {
                        Pessoa pessoa = (Pessoa) Model;
                        String nome = pessoa.getNome();
                        String telefone = pessoa.getTelefone();
                        String email = pessoa.getEmail();
                        row.set(1, nome);
                        row.set(2, telefone);
                        row.set(3, email);
                    }
                }
                tableFerramentasModel.addRow(row.toArray());
            }
            tableRelatorio.getColumnModel().getColumn(0).setPreferredWidth(1);
        }
    }
}
