package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa o objeto "Candidatura".
 * O construtor recebe como argumentos o número do aluno
 * que fez a condidatura e um array com os id's das propostas.
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class Candidatura implements Serializable{
    private static final long serialVersionUID = 1L;

    private long n_aluno;
    private ArrayList<String> cod_prop;

    public Candidatura(long n_aluno, ArrayList<String> cod_prop) {
        this.n_aluno = n_aluno;
        this.cod_prop = new ArrayList<>(cod_prop);
    }

    @Override
    public String toString() {
        return "n_aluno = " + n_aluno +
                ", cod_prop = " + cod_prop;
    }

    public long getN_aluno() {
        return n_aluno;
    }

    public ArrayList<String> getCod_prop() {
        return cod_prop;
    }
}
