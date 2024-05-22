package GerenciamentoEmprestimosCRUD.Modelo;

public class Pessoa {
    // Atributos da classe Pessoa
    private int id; // inicializa a variável Identificador da pessoa
    private String nome; //inicializa a variável Nome da pessoa
    private String telefone; // inicializa a variável Telefone da pessoa
    private String email; // inicializa a variável Email da pessoa
    private int totalEmprestimos; // inicializa a variável Total de empréstimos feitos pela pessoa
    private int totalDevolvidos; // inicializa a variável Total de devoluções feitas pela pessoa

    // Construtor padrão sem argumentos
    public Pessoa() {
        // Chama o construtor parametrizado com valores padrão
        this(0, "", "", "");
    }

    // Construtor parametrizado
    public Pessoa(int id, String nome, String telefone, String email) {
        this.id = id; // atribui valor a id
        this.nome = nome; // atribui valor a nome
        this.telefone = telefone; // atribui valor a telefone
        this.email = email; // atribui valor a email
        this.totalEmprestimos = 0; // atribui valor a totalEmprestimos com 0
        this.totalDevolvidos = 0; // atribui valor a totalDevolvidos com 0
    }

    // Métodos getter e setter para o atributo id
    public int getId() {
        return id; // Retorna o valor do atributo id
    }

    public void setId(int id) {
        this.id = id; // Define o valor do atributo id
    }

    // Métodos getter e setter para o atributo nome
    public String getNome() {
        return nome; // Retorna o valor do atributo nome
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o valor do atributo nome
    }

    // Métodos getter e setter para o atributo telefone
    public String getTelefone() {
        return telefone; // Retorna o valor do atributo telefone
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; // Define o valor do atributo telefone
    }

    // Métodos getter e setter para o atributo email
    public String getEmail() {
        return email; // Retorna o valor do atributo email
    }

    public void setEmail(String email) {
        this.email = email; // Define o valor do atributo email
    }

    // Métodos getter e setter para o atributo totalEmprestimos
    public int getTotalEmprestimos() {
        return totalEmprestimos; // Retorna o total de empréstimos feitos pela pessoa
    }

    public void setTotalEmprestimos(int total) {
        this.totalEmprestimos = total; // Define o total de empréstimos feitos pela pessoa
    }

    // Métodos getter e setter para o atributo totalDevolvidos
    public int getTotalDevolvidos() {
        return totalDevolvidos; // Retorna o total de devoluções feitas pela pessoa
    }

    public void setTotalDevolvidos(int total) {
        this.totalDevolvidos = total; // Define o total de devoluções feitas pela pessoa
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        // Retorna uma string com o nome da pessoa
        return this.nome;
    }
}
