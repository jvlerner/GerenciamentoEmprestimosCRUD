package GerenciamentoEmprestimosCRUD.DAO;

import GerenciamentoEmprestimosCRUD.Modelo.Pessoa;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOPessoa {

    public static ArrayList<Pessoa> index() throws SQLException {
        String SQL =  "SELECT * FROM pessoa ORDER BY id DESC";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        Pessoa pessoa;
        ArrayList<Pessoa> listaPessoas = new ArrayList<>();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone  = res.getString("telefone");
                String email = res.getString("email");
                pessoa = new Pessoa(id, nome, telefone, email);
                listaPessoas.add(pessoa);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao bando de dados!","Erro",JOptionPane.ERROR_MESSAGE);
        }
        return listaPessoas;
    }

    public static Pessoa show(int id ) throws SQLException {
        String SQL =  "SELECT * FROM pessoa WHERE id=?;";
        Pessoa pessoa = null;
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt( 1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");
                String email = res.getString("email");
                pessoa = new Pessoa(id, nome, telefone, email);
            }
            con.close();
            return pessoa;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            return pessoa;
        }
    }

    public static void create(Pessoa pessoa) throws SQLException {
        String nome = pessoa.getNome();
        String telefone = pessoa.getTelefone();
        String email = pessoa.getEmail();
        String SQL =  "INSERT INTO pessoa (nome, telefone, email) VALUES (?, ?, ?)";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try {
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, email);
            stmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLIntegrityConstraintViolationException e){
            if (e.getErrorCode()==1062){
                JOptionPane.showMessageDialog(null, "Este número ou email já existe!","Erro",JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean update(Pessoa pessoa ) throws SQLException {
        String SQL =  "UPDATE pessoa SET nome=?, telefone=?, email=? WHERE id=?";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        String nome = pessoa.getNome();
        String telefone = pessoa.getTelefone();
        String email = pessoa.getEmail();
        int id = pessoa.getId();
        if ( JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar os dados?" ,"Confirmação", JOptionPane.YES_NO_OPTION) == 0 ){
            try{
                PreparedStatement stmt = con.prepareStatement(SQL);
                stmt.setString(1, nome);
                stmt.setString(2, telefone);
                stmt.setString(3, email);
                stmt.setInt( 4, id);
                stmt.executeUpdate();
                con.close();
                JOptionPane.showMessageDialog(null,"Os dados foram alterados com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public static void destroy(int id ) throws SQLException {
        String SQL =  "DELETE FROM pessoa WHERE id=?";
        Connection con = ConexaoMySQL.getConexaoMySQL();
            try{
                assert con != null;
                PreparedStatement stmt = con.prepareStatement(SQL);
                stmt.setInt( 1, id);
                stmt.execute();
                con.close();
                JOptionPane.showMessageDialog(null,"Pessoa deletada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
            }
    }


}
