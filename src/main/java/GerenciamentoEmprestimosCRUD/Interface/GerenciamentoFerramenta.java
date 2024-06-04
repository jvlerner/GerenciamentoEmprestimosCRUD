package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOFerramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
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

public class GerenciamentoFerramenta extends JFrame {
    private JPanel contentPane;
    private JButton buttonApagar;
    private JButton buttonSair;
    private JTable tableFerramentas;
    private JButton buttonEditar;

    static Color headerBackground = new Color(15, 15, 15);
    static Color text = new Color(218, 218, 218);
    static Color background = new Color(38, 38, 38);

    public GerenciamentoFerramenta() throws SQLException {
        setContentPane(contentPane);
        setSize(900, 600);
        setTitle("Gerenciamento Ferramentas");
        setLocationRelativeTo(null);

        tableFerramentas.getTableHeader().setDefaultRenderer(new Menu.HeaderColor());
        tableFerramentas.setForeground(text);
        tableFerramentas.setBackground(background);
        tableFerramentas.setSelectionBackground(headerBackground);
        tableFerramentas.setSelectionForeground(Color.white);
        tableFerramentas.setShowVerticalLines(true);
        tableFerramentas.setShowHorizontalLines(true);
        tableFerramentas.setRowHeight(25);
        tableFerramentas.setCellSelectionEnabled(false);
        tableFerramentas.setRowSelectionAllowed(true);
        tableFerramentas.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null}
                },
                new String[]{
                        "ID", "Ferramenta", "Marca", "Preço", "Status"
                }) {
            final boolean[] canEdit = new boolean[]{
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
                    deleteFerramenta(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    editFerramenta(e);
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

    private void editFerramenta(ActionEvent e) throws SQLException {
        int id = 0;

        if (this.tableFerramentas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para editar", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            id = Integer.parseInt(this.tableFerramentas.getValueAt(this.tableFerramentas.getSelectedRow(), 0).toString());
            JFrame edit = new EditarFerramenta(id);
            edit.setVisible(true);
            dataTable();
        }
    }

    private void deleteFerramenta(ActionEvent e) throws SQLException {
        try {
            int id = 0;

            if (this.tableFerramentas.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Primeiro selecione uma linha para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                id = Integer.parseInt(this.tableFerramentas.getValueAt(this.tableFerramentas.getSelectedRow(), 0).toString());

                if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta ferramenta?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    DAOFerramenta.destroy(id);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            dataTable();
        }
    }

    public void dataTable() throws SQLException {
        DefaultTableModel tableFerramentasModel = (DefaultTableModel) this.tableFerramentas.getModel();
        tableFerramentasModel.setNumRows(0);
        ArrayList<Ferramenta> listaFerramentas = DAOFerramenta.index();
        if (listaFerramentas.isEmpty()) {
            return;
        }
        for (Ferramenta ferramenta : listaFerramentas) {
            String emprestado = "";
            if (ferramenta.isEmprestado()) {
                emprestado = "Emprestado";
            } else {
                emprestado = "Disponível";
            }
            tableFerramentasModel.addRow(new Object[]{
                    ferramenta.getId(),
                    ferramenta.getNome(),
                    ferramenta.getMarca(),
                    ferramenta.getPreco(),
                    emprestado
            });
        }
        tableFerramentas.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

}
