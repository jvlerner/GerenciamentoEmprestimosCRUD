package GerenciamentoEmprestimosCRUD.Interface;

//Importando requisitos//
import GerenciamentoEmprestimosCRUD.DAO.DAOEmprestimo;
import GerenciamentoEmprestimosCRUD.DAO.DAOFerramenta;
import GerenciamentoEmprestimosCRUD.DAO.DAOPessoa;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//Cria tela principal do aplicativo 
public class Menu extends JFrame {
    //Cria coponentes da tela
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JComboBox comboFerramenta1;
    private JComboBox comboPessoa1;
    private JComboBox comboFerramenta2;
    private JComboBox comboPessoa2;
    private JButton buttonRegistrar2;
    private JButton buttonRegistrar1;
    private JLabel labelPessoa1;
    private JLabel labelFerramenta1;
    private JLabel labelFerramenta2;
    private JLabel labelPessoa2;
    private JPanel panel0;
    //Cores do menu
    static Color menuTextWhite = new Color(213, 213, 213);
    static Color menuTextBlack = new Color(35, 35, 35);

    //Inicializa tela menu
    public Menu() throws SQLException {
        //CRIA O FRAME E INICIA SETA OS COMPONENTES
        JFrame frame = new JFrame("Sistema de Gerenciamento de Empréstimos");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900, 660);
        frame.setLocationRelativeTo(null);

        //INICIALIZANDO COMPONENTES DO MENU+
        JMenuBar menuBar = new JMenuBar();
        Font fontMenuBar = new Font("Segoe UI", Font.PLAIN, 14);

        Border borderMenuBar = BorderFactory.createMatteBorder(1, 1, 5, 5, Color.DARK_GRAY);
        JMenu cadastrar = new JMenu("Cadastrar");
        JMenu gerenciar = new JMenu("Gerenciar");
        JMenu relatorio = new JMenu("Relatórios");
        JMenu mais = new JMenu("Mais");
        JMenuItem cadastrarPessoa = new JMenuItem("Pessoa");
        JMenuItem cadastrarFerramenta = new JMenuItem("Ferramenta");
        JMenuItem gerenciarPessoa = new JMenuItem("Pessoas");
        JMenuItem gerenciarFerramentas = new JMenuItem("Ferramentas");
        JMenuItem gerenciarEmprestimos = new JMenuItem("Empréstimos");
        JMenuItem relatorioEmprestimos = new JMenuItem("Emprestimos");
        JMenuItem relatorioPessoas = new JMenuItem("Pessoas");
        JMenuItem sobre = new JMenuItem("Sobre");
        JMenuItem sair = new JMenuItem("Sair");
        //MANIPULANDO COMPONENTES MENU
        menuBar.setOpaque(true);
        frame.setJMenuBar(menuBar);
        cadastrar.setBackground(Color.DARK_GRAY);
        cadastrar.setForeground(menuTextWhite);
        cadastrar.setFont(fontMenuBar);
        cadastrar.setBorder(borderMenuBar);
        gerenciar.setBackground(Color.DARK_GRAY);
        gerenciar.setForeground(menuTextWhite);
        gerenciar.setFont(fontMenuBar);
        gerenciar.setBorder(borderMenuBar);
        relatorio.setBackground(Color.DARK_GRAY);
        relatorio.setForeground(menuTextWhite);
        relatorio.setFont(fontMenuBar);
        relatorio.setBorder(borderMenuBar);
        mais.setBackground(Color.DARK_GRAY);
        mais.setForeground(menuTextWhite);
        mais.setFont(fontMenuBar);
        mais.setBorder(borderMenuBar);
        //ADICIONA OS BOTOES NO MENU
        menuBar.add(cadastrar);
        menuBar.add(gerenciar);
        menuBar.add(relatorio);
        menuBar.add(mais);
        //MANIPULANDO LISTA
        cadastrarPessoa.setFont(fontMenuBar);
        cadastrarPessoa.setForeground(menuTextBlack);
        cadastrarPessoa.setBorder(borderMenuBar);
        cadastrarFerramenta.setFont(fontMenuBar);
        cadastrarFerramenta.setForeground(menuTextBlack);
        cadastrarFerramenta.setBorder(borderMenuBar);
        gerenciarPessoa.setFont(fontMenuBar);
        gerenciarPessoa.setForeground(menuTextBlack);
        gerenciarPessoa.setBorder(borderMenuBar);
        gerenciarFerramentas.setFont(fontMenuBar);
        gerenciarFerramentas.setForeground(menuTextBlack);
        gerenciarFerramentas.setBorder(borderMenuBar);
        gerenciarEmprestimos.setFont(fontMenuBar);
        gerenciarEmprestimos.setForeground(menuTextBlack);
        gerenciarEmprestimos.setBorder(borderMenuBar);
        relatorioEmprestimos.setFont(fontMenuBar);
        relatorioEmprestimos.setForeground(menuTextBlack);
        relatorioEmprestimos.setBorder(borderMenuBar);
        relatorioPessoas.setFont(fontMenuBar);
        relatorioPessoas.setForeground(menuTextBlack);
        relatorioPessoas.setBorder(borderMenuBar);
        sobre.setFont(fontMenuBar);
        sobre.setForeground(menuTextBlack);
        sobre.setBorder(borderMenuBar);
        sair.setFont(fontMenuBar);
        sair.setForeground(menuTextBlack);
        sair.setBorder(borderMenuBar);
        //ADICIONA OS COMPONENTES A LISTA
        cadastrar.add(cadastrarPessoa);
        cadastrar.add(cadastrarFerramenta);
        gerenciar.add(gerenciarPessoa);
        gerenciar.add(gerenciarFerramentas);
        gerenciar.add(gerenciarEmprestimos);
        relatorio.add(relatorioEmprestimos);
        relatorio.add(relatorioPessoas);
        mais.add(sobre);
        mais.add(sair);

        //CARREGA OS DADOS DA TELA PRINCIPAL
        dataPessoas();
        dataFerramentas();
        dataDevPessoas();
        dataDevFerramentas(null);

        frame.setVisible(true);

        //ACTIONS DA TELA PRINCIPAL
        buttonRegistrar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboPessoa1.getSelectedItem() == "Selecione uma pessoa") {
                    JOptionPane.showMessageDialog(mainPanel, "Selecione uma pessoa");
                } else if (comboFerramenta1.getSelectedItem() == "Nenhuma Ferramenta Disponível") {
                    JOptionPane.showMessageDialog(mainPanel, "Nenhuma ferramenta está disponível");
                } else if (comboFerramenta1.getSelectedItem() == "Selecione uma Ferramenta") {
                    JOptionPane.showMessageDialog(mainPanel, "Selecione um Ferramenta");
                } else {
                    Pessoa selectPessoa = (Pessoa) comboPessoa1.getSelectedItem();
                    Ferramenta selectFerramenta = (Ferramenta) comboFerramenta1.getSelectedItem();
                    if (selectPessoa != null && selectFerramenta != null) {
                        ArrayList<Pessoa> listaPessoas = null;
                        try {
                            listaPessoas = DAOEmprestimo.emprestimoPessoas();
                            for (Pessoa pessoa : listaPessoas) {
                                if (pessoa.getId() == selectPessoa.getId()) {
                                    JOptionPane.showMessageDialog(mainPanel, "Está pessoa já tem um empréstimo em aberto!\n Acesse Relatórios>Empréstimos para mais informações.");
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        try {
                            int idPessoa = selectPessoa.getId();
                            int idFerramenta = selectFerramenta.getId();
                            long millis = System.currentTimeMillis();
                            java.sql.Date dataIn = new java.sql.Date(millis);
                            Date dataOut = null;
                            DAOFerramenta.block(idFerramenta);
                            DAOEmprestimo.create(idPessoa, idFerramenta, dataIn, dataOut);
                            dataFerramentas();
                            dataDevPessoas();
                            dataDevFerramentas(null);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        buttonRegistrar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboPessoa2.getSelectedItem() == "Não há ferramentas emprestadas") {
                    JOptionPane.showMessageDialog(mainPanel, "Selecione uma Pessoa e uma Ferramenta\n para registrar uma devolução!");
                } else if (comboPessoa2.getSelectedItem() != null && comboFerramenta2.getSelectedItem() != null) {
                    try {
                        Pessoa selectPessoa = (Pessoa) comboPessoa2.getSelectedItem();
                        Ferramenta selectFerramenta = (Ferramenta) comboFerramenta2.getSelectedItem();
                        int idPessoa = selectPessoa.getId();
                        int idFerramenta = selectFerramenta.getId();
                        long millis = System.currentTimeMillis();
                        java.sql.Date dataOut = new java.sql.Date(millis);
                        DAOFerramenta.unblock(idFerramenta);
                        DAOEmprestimo.updateDateOut(idPessoa, idFerramenta, dataOut);
                        dataFerramentas();
                        dataDevPessoas();
                        dataDevFerramentas(null);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        comboPessoa2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboPessoa2.getSelectedItem() != "Não há ferramentas emprestadas") {
                    Pessoa selectPessoa = (Pessoa) comboPessoa2.getSelectedItem();
                    if (selectPessoa != null) {
                        try {
                            dataDevFerramentas(selectPessoa);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        cadastrarPessoa.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame view = new CadastrarPessoa();
                        view.setVisible(true);
                        try {
                            dataPessoas();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        cadastrarFerramenta.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame view = new CadastrarFerramenta();
                        view.setVisible(true);
                        try {
                            dataFerramentas();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        gerenciarPessoa.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JFrame view = new GerenciamentoPessoas();
                            view.setVisible(true);
                            dataPessoas();
                            dataDevPessoas();
                            dataDevFerramentas(null);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        gerenciarFerramentas.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JFrame view = new GerenciamentoFerramenta();
                            view.setVisible(true);
                            dataFerramentas();
                            dataDevPessoas();
                            dataDevFerramentas(null);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        gerenciarEmprestimos.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JFrame view = new GerenciamentoEmprestimos();
                            view.setVisible(true);
                            dataFerramentas();
                            dataDevPessoas();
                            dataDevFerramentas(null);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        relatorioEmprestimos.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JFrame view = new RelatorioEmprestimos();
                            view.setVisible(true);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        relatorioPessoas.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JFrame view = new RelatorioPessoas();
                            view.setVisible(true);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );

        sobre.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame view = new Sobre();
                        view.setVisible(true);
                    }
                }
        );

        sair.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                            System.exit(0);
                        }
                    }
                }
        );
    }

    @SuppressWarnings("unchecked")
    public void dataPessoas() throws SQLException {
        try {
            comboPessoa1.removeAllItems();
            ArrayList<Pessoa> listaPessoas = DAOPessoa.index();

            if (listaPessoas.isEmpty()) {
                comboPessoa1.addItem("Nenhuma Pessoa Cadastrada");
            } else {
                for (Pessoa pessoa : listaPessoas) {
                    comboPessoa1.addItem(pessoa);
                }
            }
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("unchecked")
    public void dataFerramentas() throws SQLException {
        try {
            comboFerramenta1.removeAllItems();
            ArrayList<Ferramenta> listaferramentas = DAOFerramenta.indexNaoEmprestado();

            if (listaferramentas.isEmpty()) {
                comboFerramenta1.addItem("Nenhuma Ferramenta Disponível");
            } else {
                comboFerramenta1.addItem("Selecione uma Ferramenta");
                for (Ferramenta ferramenta : listaferramentas) {
                    comboFerramenta1.addItem(ferramenta);
                }
            }
        } catch (Exception ignored) {
        }
    }


    @SuppressWarnings("unchecked")
    public void dataDevPessoas() throws SQLException {
        try {
            comboPessoa2.removeAllItems();
            ArrayList<Pessoa> listaPessoas = DAOEmprestimo.emprestimoPessoas();

            if (listaPessoas.isEmpty()) {
                comboPessoa2.addItem("Não há ferramentas emprestadas");
            } else {
                for (Pessoa pessoa : listaPessoas) {
                    comboPessoa2.addItem(pessoa);
                }
            }
        } catch (Exception ignored) {
        }
    }

    @SuppressWarnings("unchecked")
    public void dataDevFerramentas(Pessoa pessoa) throws SQLException {
        try {
            comboFerramenta2.removeAllItems();

            if (pessoa != null) {
                ArrayList<Ferramenta> listaferramentas = DAOEmprestimo.emprestimoFerramenta(pessoa.getId());
                for (Ferramenta ferramenta : listaferramentas) {
                    comboFerramenta2.addItem(ferramenta);
                }
            } else {
                comboFerramenta2.addItem("Selecione uma pessoa acima");
            }
        } catch (Exception ignored) {
        }
    }

    static Color headerText = new Color(218, 218, 218);
    static Color headerBackground = new Color(15, 15, 15);

    static public class HeaderColor extends DefaultTableCellRenderer {
        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable jTable, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(jTable, value, selected, focused, row, column);
            setBackground(headerBackground);
            setForeground(headerText);
            return this;
        }
    }
}
