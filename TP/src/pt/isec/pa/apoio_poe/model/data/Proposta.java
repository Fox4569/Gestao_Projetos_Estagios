package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

/**
 * Classe abstrata que representa o objeto "Proposta".
 * O 1º construtor recebe como argumentos o Id da proposta,
 * o título.
 * O 2º construtor recebe tudo o que o anterior recebe
 * e também o número do aluno que foi associado a esta
 * proposta
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public abstract class Proposta implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String titulo;
    private long nAluno = -1;

    public Proposta(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Proposta(String id, String titulo, long nAluno){
        this.id = id;
        this.titulo = titulo;
        this.nAluno = nAluno;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", titulo = " + titulo + ", nAluno = " + nAluno;
    }

    public String getId() {
        return id;
    }

    public long getnAluno() {
        return nAluno;
    }

    public abstract String getRamo();

    public String getTitulo() {
        return titulo;
    }
}
