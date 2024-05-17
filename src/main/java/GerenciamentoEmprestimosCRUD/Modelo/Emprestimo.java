package GerenciamentoEmprestimosCRUD.Modelo;

import java.util.Date;

public class Emprestimo {
    // Atributos da classe Emprestimo
    private int id; // Identificador do empréstimo
    private int pessoaId; // Identificador da pessoa que fez o empréstimo
    private int ferramentaId; // Identificador da ferramenta emprestada
    private Date dataOut; // Data de início do empréstimo
    private Date dataIn; // Data de devolução do empréstimo

    // Construtor padrão sem argumentos
    public Emprestimo() {
        // Chama o construtor parametrizado com valores padrão
        this(0, 0, 0, new Date(), new Date());
    }

    // Construtor parametrizado
    public Emprestimo(int id, int pessoaId, int ferramentaId, Date dataOut, Date dataIn) {
        this.id = id; // Inicializa o atributo id
        this.pessoaId = pessoaId; // Inicializa o atributo pessoaId
        this.ferramentaId = ferramentaId; // Inicializa o atributo ferramentaId
        this.dataOut = dataOut; // Inicializa o atributo dataOut
        this.dataIn = dataIn; // Inicializa o atributo dataIn
    }

    // Métodos getter e setter para o atributo id
    public int getId() {
        return id; // Retorna o valor do atributo id
    }

    public void setId(int id) {
        this.id = id; // Define o valor do atributo id
    }

    // Métodos getter e setter para o atributo pessoaId
    public int getPessoaId() {
        return pessoaId; // Retorna o valor do atributo pessoaId
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId; // Define o valor do atributo pessoaId
    }

    // Métodos getter e setter para o atributo ferramentaId
    public int getFerramentaId() {
        return ferramentaId; // Retorna o valor do atributo ferramentaId
    }

    public void setFerramentaId(int ferramentaId) {
        this.ferramentaId = ferramentaId; // Define o valor do atributo ferramentaId
    }

    // Métodos getter e setter para o atributo dataOut
    public Date getDataOut() {
        return dataOut; // Retorna a data de início do empréstimo
    }

    public void setDataOut(Date dataOut) {
        this.dataOut = dataOut; // Define a data de início do empréstimo
    }

    // Métodos getter e setter para o atributo dataIn
    public Date getDataIn() {
        return dataIn; // Retorna a data de devolução do empréstimo
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn; // Define a data de devolução do empréstimo
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        // Retorna uma string com o id, dataOut e dataIn
        return id + " " + dataOut + " " + dataIn;
    }
}
