package GerenciamentoEmprestimosCRUD.Interface;

import GerenciamentoEmprestimosCRUD.DAO.DAOEmprestimo;
import GerenciamentoEmprestimosCRUD.DAO.DAOFerramenta;
import GerenciamentoEmprestimosCRUD.DAO.DAOPessoa;
import GerenciamentoEmprestimosCRUD.Modelo.Emprestimo;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

public class EditarEmprestimos extends JFrame {
    private JPanel contentPane;
    private JLabel labelNome;
    private JLabel labelEmail;
    private JLabel labelTelefone;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private JComboBox comboPessoa;
    private JComboBox comboFerramenta;
    private JTextField txtDiaEmp;
    private JTextField txtMesEmp;
    private JTextField txtAnoEmp;
    private JTextField txtDiaDev;
    private JTextField txtMesDev;
    private JTextField txtAnoDev;

    public EditarEmprestimos(Emprestimo emprestimo) throws SQLException {
        setContentPane(contentPane);
        setTitle("Editar Emprestimos");
        setSize(500, 260);
        setLocationRelativeTo(null);
        setSize(400, 260);
        setResizable(false);
        data(emprestimo);

        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = emprestimo.getId();
                try {
                    salvarEmprestimo(id);
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
    }

    private void salvarEmprestimo(int id) throws SQLException {
        Pessoa pessoa = (Pessoa) comboPessoa.getSelectedItem();
        Ferramenta ferramenta = (Ferramenta) comboFerramenta.getSelectedItem();

        if (ferramenta == null) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma ferramenta!", "Erro", JOptionPane.ERROR_MESSAGE);
            comboFerramenta.grabFocus();
            return;
        }

        if (pessoa == null) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma pessoa!", "Erro", JOptionPane.ERROR_MESSAGE);
            comboPessoa.grabFocus();
            return;
        }

        if (Objects.equals(txtDiaEmp.getText(), "") || Objects.equals(txtMesEmp.getText(), "") || Objects.equals(txtAnoEmp.getText(), "")) {
            JOptionPane.showMessageDialog(null, "Você deve inserir uma data de empréstimo!", "Erro", JOptionPane.ERROR_MESSAGE);
            txtDiaEmp.grabFocus();
            return;
        }

        try {
            int diaemp = Integer.parseInt(txtDiaEmp.getText());
            if (diaemp > 31 || diaemp <= 0) {
                JOptionPane.showMessageDialog(null, "Você deve inserir um dia válido na data de empréstimo!", "Erro", JOptionPane.ERROR_MESSAGE);
                txtDiaEmp.grabFocus();
                return;
            }

            int mesemp = Integer.parseInt(txtMesEmp.getText());
            if (mesemp > 12 || mesemp <= 0) {
                JOptionPane.showMessageDialog(null, "Você deve inserir um mês válido na data de empréstimo!", "Erro", JOptionPane.ERROR_MESSAGE);
                txtMesEmp.grabFocus();
                return;
            }

            int anoemp = Integer.parseInt(txtAnoEmp.getText());
            if (anoemp > 3000 || anoemp <= 1900) {
                JOptionPane.showMessageDialog(null, "Você deve inserir um mês válido na data de empréstimo!", "Erro", JOptionPane.ERROR_MESSAGE);
                txtAnoEmp.grabFocus();
                return;
            }

            if (!Objects.equals(txtDiaDev.getText(), "") && !Objects.equals(txtMesDev.getText(), "") && !Objects.equals(txtAnoDev.getText(), "")) {
                int diadev = Integer.parseInt(txtDiaDev.getText());
                if (diadev > 31 || diadev <= 0) {
                    JOptionPane.showMessageDialog(null, "Você deve inserir um dia válido na data de devolução!", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtDiaDev.grabFocus();
                    return;
                }

                int mesdev = Integer.parseInt(txtMesDev.getText());
                if (mesdev > 12 || mesdev <= 0) {
                    JOptionPane.showMessageDialog(null, "Você deve inserir um mês válido na data de devolução!", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtMesDev.grabFocus();
                    return;
                }

                int anodev = Integer.parseInt(txtAnoDev.getText());
                if (anodev > 3000 || anodev <= 1900) {
                    JOptionPane.showMessageDialog(null, "Você deve inserir um ano válido na data de devolução!", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtAnoDev.grabFocus();
                    return;
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Você deve inserir uma data válida", "Erro", JOptionPane.ERROR_MESSAGE);
            txtDiaDev.grabFocus();
            return;
        }

        java.sql.Date dateIn;
        if (!Objects.equals(txtDiaDev.getText(), "") || !Objects.equals(txtMesDev.getText(), "") || !Objects.equals(txtAnoDev.getText(), "")) {
            if (!Objects.equals(txtDiaDev.getText(), "")) {
                JOptionPane.showMessageDialog(null, "Você deve inserir uma data válida", "Erro", JOptionPane.ERROR_MESSAGE);
                txtDiaDev.grabFocus();
                return;
            }
            if (!Objects.equals(txtMesDev.getText(), "")) {
                JOptionPane.showMessageDialog(null, "Você deve inserir uma data válida", "Erro", JOptionPane.ERROR_MESSAGE);
                txtMesDev.grabFocus();
                return;
            }
            if (!Objects.equals(txtAnoDev.getText(), "")) {
                JOptionPane.showMessageDialog(null, "Você deve inserir uma data válida", "Erro", JOptionPane.ERROR_MESSAGE);
                txtAnoDev.grabFocus();
                return;
            }
        }

        if (Objects.equals(txtDiaDev.getText(), "") && Objects.equals(txtMesDev.getText(), "") && Objects.equals(txtAnoDev.getText(), "")) {
            dateIn = null;
        } else {
            String dia = txtDiaDev.getText();
            String mes = txtMesDev.getText();
            String ano = txtAnoDev.getText();
            String date = ano + "-" + mes + "-" + dia;
            dateIn = java.sql.Date.valueOf(date);
        }

        String dia = txtDiaEmp.getText();
        String mes = txtMesEmp.getText();
        String ano = txtAnoEmp.getText();
        String dateOut = ano + "-" + mes + "-" + dia;

        int ferramentaId = ferramenta.getId();
        int idPessoa = pessoa.getId();
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(id);
        emprestimo.setPessoaId(idPessoa);
        emprestimo.setFerramentaId(ferramentaId);
        emprestimo.setDataOut(java.sql.Date.valueOf(dateOut));
        emprestimo.setDataIn(dateIn);
        if (DAOEmprestimo.update(emprestimo)) {
            dispose();
        }
    }

    private void data(Emprestimo emp) throws SQLException {
        int idPessoa = emp.getPessoaId();
        int idFerramenta = emp.getFerramentaId();
        comboPessoa.removeAllItems();
        ArrayList<Pessoa> listaPessoas = DAOPessoa.index();
        Pessoa selectedPessoa = DAOPessoa.show(idPessoa);

        if (listaPessoas.isEmpty()) {
            comboPessoa.addItem(selectedPessoa);
        } else {
            comboPessoa.addItem(selectedPessoa);
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa.getId() != idPessoa) {
                    comboPessoa.addItem(pessoa);
                }
            }
        }
        comboPessoa.setSelectedItem(1);
        comboFerramenta.removeAllItems();
        ArrayList<Ferramenta> listaferramentas = DAOFerramenta.index();
        Ferramenta selectedFerramenta = DAOFerramenta.show(idFerramenta);

        if (listaferramentas.isEmpty()) {
            comboFerramenta.addItem(selectedFerramenta);
        } else {
            comboFerramenta.addItem(selectedFerramenta);
            for (Ferramenta ferramenta : listaferramentas) {
                if (ferramenta.getId() != idFerramenta) {
                    comboFerramenta.addItem(ferramenta);
                }
            }
        }
        comboFerramenta.setSelectedItem(1);
        Date dataEmp = emp.getDataOut();
        Date dataDev = emp.getDataIn();

        if (dataEmp != null) {
            Calendar calendarEmp = Calendar.getInstance(TimeZone.getTimeZone("en-US"));
            calendarEmp.setTime(dataEmp);
            int dayEmp = calendarEmp.get(DAY_OF_MONTH);
            int monthEmp = calendarEmp.get(MONTH) + 1;
            int yearEmp = calendarEmp.get(Calendar.YEAR);
            txtDiaEmp.setText(String.valueOf(dayEmp));
            txtMesEmp.setText(String.valueOf(monthEmp));
            txtAnoEmp.setText(String.valueOf(yearEmp));
        }
        if (dataDev != null) {
            Calendar calendarDev = Calendar.getInstance(TimeZone.getTimeZone("en-US"));
            calendarDev.setTime(dataDev);
            int dayDev = calendarDev.get(DAY_OF_MONTH);
            int monthDev = calendarDev.get(MONTH) + 1;
            int yearDev = calendarDev.get(Calendar.YEAR);
            txtDiaDev.setText(String.valueOf(dayDev));
            txtMesDev.setText(String.valueOf(monthDev));
            txtAnoDev.setText(String.valueOf(yearDev));
        }
    }
}
