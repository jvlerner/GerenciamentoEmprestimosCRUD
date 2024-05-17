package GerenciamentoEmprestimosCRUD.Modelo;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int pessoaId;
    private int ferramentaId;
    private Date dataOut;
    private Date dataIn;

    public Emprestimo() {
        this(0, 0, 0, new Date(),new Date());
    }

    public Emprestimo(int id, int pessoaId, int ferramentaId, Date dataOut, Date dataIn) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.ferramentaId = ferramentaId;
        this.dataOut = dataOut;
        this.dataIn = dataIn;
  ; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPessoaId(int id) {
        this.pessoaId = id;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setFerrentaId(int id) {
        this.ferramentaId = id;
    }

    public int getFerrentaId() {
        return ferramentaId;
    }

    public void setDataOut(Date dataOut) {
        this.dataOut = dataOut;
    }

    public Date getDataOut() {
        return dataOut;
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    public Date getDataIn() {
        return dataIn;
    }

    @Override
    public String toString() {
        return id + " " + dataOut + " " + dataIn;
    }
}
