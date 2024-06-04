package GerenciamentoEmprestimosCRUD.DAO;

//Classes necessárias para uso de Banco de dados//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Início da classe de conexão//
public class ConexaoMySQL {
    public static String status = "Não conectou...";
    //Método Construtor da Classe//
    public ConexaoMySQL() {}
    //Método de Conexão//
    public static Connection getConexaoMySQL() {
        Connection connection;          //atributo do tipo Connection
        try {
            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//
            String serverName = "localhost";    //caminho do servidor do BD
            String mydatabase = "a3db";        //nome do seu banco de dados
            String port = "3306";           //porta do servidor do BD
            String url = "jdbc:mysql://" + serverName +":"+port+ "/" + mydatabase;
            String username = "root";        //nome de um usuário de seu BD
            String password = "";      //sua senha de acesso
            //jdbc:mysql://localhost:3306/a3db
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {

                status = ("Conectado");
            }
            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;

        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;

        } catch (Exception e) {
            //Testa sua conexão//
            System.out.println("Erro ao conectar ao Banco de Dados: " + e.getMessage());
            return null;
        }
    }

    //Método que retorna o status da sua conexão//
    public static String statusConection() {
        return status;
    }
}
