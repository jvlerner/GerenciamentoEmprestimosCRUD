package GerenciamentoEmprestimosCRUD.Modelo;

public class Ferramenta {
    private int id;
    private String nome;
    private String marca;
    private double preco;
    private boolean emprestado;

    public Ferramenta() {
        this(0, "", "", 0.0, false);
    }

    public Ferramenta(int id, String nome, String marca, double preco, boolean emprestado) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.emprestado = emprestado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return nome + " - " + marca;
    }
}
