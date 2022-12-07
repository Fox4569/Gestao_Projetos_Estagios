package pt.isec.pa.apoio_poe.model.data;

/**
 * Classe que representa o objeto "Autoproposta" que extende da classe "Proposta".
 * O construtor recebe como argumentos o Id da proposta,
 * o título e o numero do aluno que auto propôs a proposta.
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class Autoproposta extends Proposta{

    public Autoproposta(String id, String titulo, long n_aluno) {
        super(id, titulo, n_aluno);
    }

    @Override
    public String toString() {
        return  "Autoproposta: \n" + super.toString();
    }

    @Override
    public String getRamo() {
        return null;
    }
}
