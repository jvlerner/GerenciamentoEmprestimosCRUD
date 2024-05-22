package GerenciamentoEmprestimosCRUD.Modelo;

public class Ferramenta {
    // Atributos da classe Ferramenta
    private int id; // inicializa a variavél Identificador da ferramenta
    private String nome; // inicializa a variável Nome da ferramenta
    private String marca; // inicializa a variável Marca da ferramenta
    private double preco; // inicializa a variável Preço da ferramenta
    private boolean emprestado; // Indica se a ferramenta está emprestada

    // Construtor padrão sem argumentos
    public Ferramenta() {
        // Chama o construtor parametrizado com valores padrão
        this(0, "", "", 0.0, false);
    }

    // Construtor parametrizado
    public Ferramenta(int id, String nome, String marca, double preco, boolean emprestado) {
        this.id = id; //atribui valor a id
        this.nome = nome; // atribui valor a nome
        this.marca = marca; // atribui valor a marca
        this.preco = preco; // atribui valor a preco
        this.emprestado = emprestado; // atribui valor a emprestado
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

    // Métodos getter e setter para o atributo marca
    public String getMarca() {
        return marca; // Retorna o valor do atributo marca
    }

    public void setMarca(String marca) {
        this.marca = marca; // Define o valor do atributo marca
    }

    // Métodos getter e setter para o atributo preco
    public double getPreco() {
        return preco; // Retorna o valor do atributo preco
    }

    public void setPreco(double preco) {
        this.preco = preco; // Define o valor do atributo preco
    }

    // Métodos getter e setter para o atributo emprestado
    public boolean isEmprestado() {
        return emprestado; // Retorna se a ferramenta está emprestada
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado; // Define se a ferramenta está emprestada
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        // Retorna uma string com o nome e a marca da ferramenta
        return nome + " - " + marca;
    }
}
