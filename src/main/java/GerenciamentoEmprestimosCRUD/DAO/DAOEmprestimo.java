package GerenciamentoEmprestimosCRUD.DAO;

import GerenciamentoEmprestimosCRUD.Modelo.Emprestimo;
import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;
import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DAOEmprestimo {
    //Início da classe de conexão//
    public static ArrayList index() throws SQLException {
        String SQL =  "SELECT E.id, F.ferramenta, P.nome, E.dataOut, E.dataIn FROM emprestimo AS E LEFT JOIN ferramenta AS F ON E.ferramentaId=F.id LEFT JOIN pessoa AS P ON E.pessoaId=P.id ORDER BY E.id DESC;";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        ArrayList lista = new ArrayList();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                ArrayList listaEmprestimos = new ArrayList();
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String nomef = res.getString("ferramenta");
                Date dataOut = res.getDate("dataOut");
                Date dataIn = res.getDate("dataIn");
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nome);
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setNome(nomef);
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setDataOut(dataOut);
                emprestimo.setDataIn(dataIn);
                emprestimo.setId(id);
                listaEmprestimos.add(emprestimo);
                listaEmprestimos.add(ferramenta);
                listaEmprestimos.add(pessoa);
                lista.add(listaEmprestimos);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public static Emprestimo show( int id ) throws SQLException {
        String SQL =  "SELECT * FROM emprestimo WHERE id=?;";
        Emprestimo emprestimo = null;
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt( 1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int pessoaId = res.getInt("pessoaId");
                int ferramentaId = res.getInt("ferramentaId");
                Date dataOut = res.getDate("dataOut");
                Date dataIn = res.getDate("dataIn");
                emprestimo = new Emprestimo(id, pessoaId, ferramentaId, dataOut, dataIn);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return emprestimo;
    }

    public static void create(int pessoaId, int ferramentaId, Date dataOut, Date dataIn) throws SQLException {
        String SQL =  "INSERT INTO emprestimo (pessoaId, ferramentaId, dataOut, dataIn) VALUES (?, ?, ?, ?)";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, pessoaId);
            stmt.setInt(2, ferramentaId);
            stmt.setDate(3, (java.sql.Date) dataOut);
            stmt.setDate(4, (java.sql.Date) dataIn);
            int update = stmt.executeUpdate();
            if ( update > 0) {
                JOptionPane.showMessageDialog(null,"Empréstimo cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                con.close();
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void updateDateOut(int pessoaId, int ferramentaId, java.sql.Date dataIn) throws SQLException {
        String SQL =  "UPDATE emprestimo SET dataIn=? WHERE pessoaId=? AND ferramentaId=?";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try{
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setDate(1, dataIn);
            stmt.setInt( 2, pessoaId);
            stmt.setInt( 3, ferramentaId);
            stmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void destroy(int id ) throws SQLException {
        String SQL =  "DELETE FROM emprestimo WHERE id=?";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar os dados?" ,"Deseja realmente excluir?", JOptionPane.YES_NO_OPTION) == 0 ) {
            try{
                assert con != null;
                PreparedStatement stmt = con.prepareStatement(SQL);
                stmt.setInt( 1, id);
                stmt.execute();
                con.close();
                JOptionPane.showMessageDialog(null, "Os dados foram apagados!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static ArrayList relatorio(int filter) throws SQLException {
        String SQL = "SELECT E.id, P.nome, P.telefone, P.email, F.ferramenta, F.marca, F.preco, E.dataOut, E.dataIn FROM emprestimo AS E INNER JOIN pessoa AS P ON E.pessoaId=P.id INNER JOIN ferramenta as F ON E.ferramentaId=F.id " ;
        if (filter == 0) { //default
            SQL += "ORDER BY E.id DESC;";
        }
        if (filter == 1) {//emprestado
            SQL += "WHERE E.dataIn IS NULL ORDER BY E.id DESC;";
        }
        if (filter == 2) {//devolvido
            SQL += "WHERE E.dataIn IS NOT NULL ORDER BY E.id DESC;";
        }

        Connection con = ConexaoMySQL.getConexaoMySQL();
        ArrayList listaViewEmprestimos = new ArrayList();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                ArrayList listaObejetos = new ArrayList();
                //id nome telefone email ferramenta marca preco dataOut dataIn
                int id = res.getInt("id");
                Date dataOut = res.getDate("dataOut");
                Date dataIn = res.getDate("dataIn");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");
                String email = res.getString("email");
                String ferramenta = res.getString("ferramenta");
                String marca = res.getString("marca");
                double preco = res.getDouble("preco");
                //Cria as models
                Emprestimo Emprestimo = new Emprestimo();
                Pessoa Pessoa = new Pessoa();
                Ferramenta Ferramenta = new Ferramenta();
                //Add data ao emprestimo
                Emprestimo.setId(id);
                Emprestimo.setDataIn(dataIn);
                Emprestimo.setDataOut(dataOut);
                //Add data a pessoa
                Pessoa.setNome(nome);
                Pessoa.setTelefone(telefone);
                Pessoa.setEmail(email);
                //Add data a ferramenta
                Ferramenta.setNome(ferramenta);
                Ferramenta.setMarca(marca);
                Ferramenta.setPreco(preco);
                //Add as models a lista
                listaObejetos.add(Emprestimo);
                listaObejetos.add(Pessoa);
                listaObejetos.add(Ferramenta);
                listaViewEmprestimos.add(listaObejetos);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return listaViewEmprestimos;
    }

    public static ArrayList<Pessoa> emprestimoPessoas() throws SQLException {
        String SQL = "SELECT DISTINCTROW E.pessoaId,P.nome,E.dataIn FROM emprestimo AS E INNER JOIN pessoa AS P ON E.pessoaId=P.id WHERE E.dataIn IS NULL;";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int pessoaId = res.getInt("pessoaId");
                String nome = res.getString("nome");
                Pessoa pessoa = new Pessoa();
                pessoa.setId(pessoaId);
                pessoa.setNome(nome);
                listaPessoas.add(pessoa);
            }
            con.close();
            return listaPessoas;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return listaPessoas;
    }

    public static ArrayList emprestimoFerramenta(int pessoaId) throws SQLException {
        String SQL = "SELECT `emprestimo`.`id`,`pessoa`.`id` AS `pessoaId`, `pessoa`.`nome`,`ferramenta`.`id`AS `ferramentaId`, `ferramenta`.`ferramenta`, ferramenta.marca ,ferramenta.emprestado FROM `pessoa` INNER JOIN `emprestimo` ON `emprestimo`.`pessoaId` = `pessoa`.`id` INNER JOIN `ferramenta` ON `emprestimo`.`ferramentaId` = `ferramenta`.`id` WHERE `emprestimo`.`pessoaId`=? AND ferramenta.emprestado = 1  AND emprestimo.dataIn IS NULL;";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Ferramenta> listaFerramentas = new ArrayList<>();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt(1, pessoaId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int ferramentaId = res.getInt("ferramentaId");
                String nome = res.getString("ferramenta");
                String marca = res.getString("marca");
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setId(ferramentaId);
                ferramenta.setNome(nome);
                ferramenta.setMarca(marca);
                listaFerramentas.add(ferramenta);
            }
            con.close();
            return listaFerramentas;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return listaFerramentas;
    }


    public static boolean update(Emprestimo emprestimo) {
        String SQL;
        Connection con = ConexaoMySQL.getConexaoMySQL();

        int id = emprestimo.getId();
        int idFerramenta = emprestimo.getFerrentaId();
        int idPessoa = emprestimo.getPessoaId();
        Date dataOut = emprestimo.getDataOut();
        Date dataIn = emprestimo.getDataIn();

        if (dataIn == null || dataIn.equals("")){
            SQL =  "UPDATE emprestimo SET pessoaId=?, ferramentaId=?, dataOut=?, dataIn=NULL WHERE id=?";
            dataIn = null;
        }else{
            SQL =  "UPDATE emprestimo SET pessoaId=?, ferramentaId=?, dataOut=?, dataIn=? WHERE id=?";
        }

        if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar os dados?" ,"Confirmação", JOptionPane.YES_NO_OPTION) == 0 ) {
            try {
                assert con != null;
                PreparedStatement stmt = con.prepareStatement(SQL);
                stmt.setInt(1, idPessoa);
                stmt.setInt(2, idFerramenta);
                stmt.setDate(3, (java.sql.Date) dataOut);
                if (dataIn != null) {
                    stmt.setDate(4, (java.sql.Date) dataIn);
                    stmt.setInt(5, id);
                }else {
                    stmt.setInt(4, id);
                }
                stmt.executeUpdate();
                con.close();
                return true;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e){
                throw new NullPointerException();
            }
        }
        return false;
    }

    public static ArrayList relatorioAmigos() throws SQLException {
        ArrayList<Pessoa> listaPessoas = DAOPessoa.index();
        ArrayList<Pessoa> view = new ArrayList();

        //ID", "Nome", "Telefone", "Email", "Total Emp.", "Ferramentas Devolvidas", "Ferramentas Emprestadas "
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try{
            assert con != null;
            for (Pessoa pessoa : listaPessoas) {
                //TOTAL DE EMPRESTIMOS
                //TOTAL DE EMPRESTIMOS NAO DEVOLVIDOS
                int id = pessoa.getId();;
                String SQLTotalDevolvido = "SELECT COUNT(*) As quantidade FROM emprestimo WHERE pessoaId = ? AND dataIn IS NOT NULL";
                String SQLTotalEmp = "SELECT COUNT(*) As quantidade FROM emprestimo WHERE pessoaId = ?";

                PreparedStatement stmtTotal = con.prepareStatement(SQLTotalEmp);
                stmtTotal.setInt(1, id);

                PreparedStatement stmtDevolvido = con.prepareStatement(SQLTotalDevolvido);
                stmtDevolvido.setInt(1, id);

                ResultSet totalEmprestimos = stmtTotal.executeQuery();

                int totalEmprestimo = 0;
                int totalDevolvido = 0;

                if(totalEmprestimos.next()){
                    totalEmprestimo = totalEmprestimos.getInt(1);
                }

                ResultSet totalEmprestimosDevolvido = stmtDevolvido.executeQuery();

                if(totalEmprestimosDevolvido.next()){
                    totalDevolvido = totalEmprestimosDevolvido.getInt(1);
                }

                pessoa.setTotalEmprestimos(totalEmprestimo);
                pessoa.setTotalDevolvidos(totalDevolvido);

                view.add(pessoa);
            }
            con.close();
            return view;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        return view;
    }
}
