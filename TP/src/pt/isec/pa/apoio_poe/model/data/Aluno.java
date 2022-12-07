package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa o objeto "Aluno".
 * O construtor recebe como argumentos o número de estudante do aluno,
 * o nome, o email, o curso, o ramo, a classificação e um boolean se
 * pode ou não frequentar estágios.
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    private long nr_estudante;
    private String nome;
    private String email;
    private String curso;
    private String ramo;
    private double classificacao;
    private boolean EeP;

    public Aluno(long nr_estudante, String nome, String email, String curso, String ramo, double classificacao, boolean eeP) {
        this.nr_estudante = nr_estudante;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classificacao = classificacao;
        EeP = eeP;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public String getRamo() {
        return ramo;
    }

    public boolean isEeP() {
        return EeP;
    }

    public long getNr_estudante() {
        return nr_estudante;
    }

    public String getEmail() {
        return email;
    }

    public double getClassificacao() {
        return classificacao;
    }

    @Override
    public String toString() {
        return "Nº Estudante = " + nr_estudante +
                ", nome = " + nome +
                ", email = " + email +
                ", curso = " + curso +
                ", ramo = " + ramo +
                ", classificacao = " + classificacao +
                ", EeP = " + EeP;
    }
}
