package GerenciamentoEmprestimosCRUD.DAO;

import GerenciamentoEmprestimosCRUD.Modelo.Ferramenta;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOFerramenta {

    //Metodo que retorna todos obejetos da tabela ferramenta disponiveis
    public static ArrayList index() throws SQLException {
        String SQL =  "SELECT * FROM ferramenta ORDER BY id DESC";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        Ferramenta ferramenta;
        ArrayList<Ferramenta> listaFerramentas = new ArrayList<Ferramenta>();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("ferramenta");
                String marca  = res.getString("marca");
                double preco = res.getDouble("preco");
                boolean emprestado = res.getBoolean("emprestado");
                ferramenta = new Ferramenta(id, nome, marca, preco, emprestado);
                listaFerramentas.add(ferramenta);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        }
        return listaFerramentas;
    }
    public static ArrayList indexNaoEmprestado() throws SQLException {
        String SQL =  "SELECT * FROM ferramenta WHERE emprestado=false";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        Ferramenta ferramenta;
        ArrayList<Ferramenta> listaFerramentas = new ArrayList();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("ferramenta");
                String marca  = res.getString("marca");
                double preco = res.getDouble("preco");
                boolean emprestado = res.getBoolean("emprestado");
                ferramenta = new Ferramenta(id, nome, marca, preco, emprestado);
                listaFerramentas.add(ferramenta);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return listaFerramentas;
    }

    public static ArrayList indexEmprestado() throws SQLException {
        String SQL =  "SELECT * FROM ferramenta WHERE emprestado=true";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        Ferramenta ferramenta = new Ferramenta();
        ArrayList<Ferramenta> listaFerramentas = new ArrayList<Ferramenta>();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String nome = res.getString("ferramenta");
                String marca  = res.getString("marca");
                double preco = res.getDouble("preco");
                boolean emprestado = res.getBoolean("emprestado");
                ferramenta = new Ferramenta(id, nome, marca, preco, emprestado);
                listaFerramentas.add(ferramenta);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return listaFerramentas;
    }

    public static Ferramenta show( int id ) throws SQLException {
        String SQL =  "SELECT * FROM ferramenta WHERE id=?;";
        Ferramenta ferramenta = null;
        Connection con = ConexaoMySQL.getConexaoMySQL();
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt( 1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                String nome = res.getString("ferramenta");
                String marca  = res.getString("marca");
                double preco = res.getDouble("preco");
                boolean emprestado = res.getBoolean("emprestado");
                ferramenta = new Ferramenta(id, nome, marca, preco, emprestado);
            }
            con.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return ferramenta;
    }

    public static void create(Ferramenta Ferramenta) throws SQLException {
        String SQL =  "INSERT INTO ferramenta (ferramenta, marca, preco, emprestado) VALUES (?, ?, ?, ?)";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        String ferramenta = Ferramenta.getNome();
        String marca = Ferramenta.getMarca();
        double preco = Ferramenta.getPreco();
        boolean emprestado = false;
        try{
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setString(1, ferramenta);
            stmt.setString(2, marca);
            stmt.setDouble(3, preco);
            stmt.setBoolean(4, emprestado);
            int update = stmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null,"Ferramenta cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    public static boolean update(Ferramenta Ferramenta) throws SQLException {
        String SQL =  "UPDATE ferramenta SET ferramenta=?, marca=?, preco=?, emprestado=? WHERE id=?";
        Connection con = ConexaoMySQL.getConexaoMySQL();
        String nome = Ferramenta.getNome();
        String marca = Ferramenta.getMarca();
        double preco = Ferramenta.getPreco();
        boolean emprestado = Ferramenta.isEmprestado();
        int id = Ferramenta.getId();
        if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar os dados?" ,"Confirmação", JOptionPane.YES_NO_OPTION) == 0 ) {
            try {
                assert con != null;
                PreparedStatement stmt = con.prepareStatement(SQL);
                stmt.setString(1, nome);
                stmt.setString(2, marca);
                stmt.setDouble(3, preco);
                stmt.setBoolean(4, emprestado);
                stmt.setInt(5, id);
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

    public static void destroy(int id ) throws SQLException {
        String SQL =  "DELETE FROM ferramenta WHERE id=?";
        try{
            Connection con = ConexaoMySQL.getConexaoMySQL();
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setInt( 1, id);
            stmt.execute();
            con.close();
            JOptionPane.showMessageDialog(null, "Os dados foram apagados!", "Alerta", JOptionPane.INFORMATION_MESSAGE);
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    public static void block(int id) throws SQLException {
        String SQL =  "UPDATE ferramenta SET emprestado=? WHERE id=?";
        try {
            Connection con = ConexaoMySQL.getConexaoMySQL();
            assert con != null;
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setBoolean(1, true);
            stmt.setInt(2, id);
            stmt.execute();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    public static void unblock(int id) throws SQLException {
        String SQL =  "UPDATE ferramenta SET emprestado=? WHERE id=?";
        try{
            Connection con = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(SQL);
            stmt.setBoolean( 1, false);
            stmt.setInt( 2, id);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    public static double value() throws SQLException {
        String SQL =  "SELECT preco FROM ferramenta";
        try {
            Connection con = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            double totalValue = 0.0;
            while (res.next()) {
                double preco = res.getDouble("preco");
                totalValue += preco;
            }
            con.close();
            return totalValue;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return 0;
    }

    public static int emp() throws SQLException {
        try{
            String SQL =  "SELECT id FROM ferramenta where emprestado=true";
            Connection con = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            int totalEmp = 0;
            while (res.next()) {
                totalEmp += 1;
            }
            con.close();
            return totalEmp;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return 0;
    }

    public static int disp() throws SQLException {
        try {
            String SQL = "SELECT id FROM ferramenta where emprestado=false";
            Connection con = ConexaoMySQL.getConexaoMySQL();
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet res = stmt.executeQuery();
            int totalEmp = 0;
            while (res.next()) {
                totalEmp += 1;
            }
            con.close();
            return totalEmp;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        return 0;
    }

}
