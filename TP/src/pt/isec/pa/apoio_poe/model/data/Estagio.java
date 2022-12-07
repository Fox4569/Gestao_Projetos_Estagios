package pt.isec.pa.apoio_poe.model.data;

/**
 * Classe que representa o objeto "Estágio" que extende da classe "Proposta".
 * O 1º construtor recebe como argumentos o Id da proposta,
 * o título, o ramo e o id do acolhimento onde será feito o estágio.
 * O 2º construtor recebe tudo o que o anterior recebe
 * e também o número do aluno que foi associado a este estágio.
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class Estagio extends Proposta {
    private String ramo;
    private String id_acolhimento;

    public Estagio(String id, String ramo, String titulo, String id_acolhimento) {
        super(id, titulo);
        this.ramo = ramo;
        this.id_acolhimento = id_acolhimento;
    }

    public Estagio(String id, String ramo, String titulo, String id_acolhimento, long n_aluno){
        super(id, titulo, n_aluno);
        this.ramo = ramo;
        this.id_acolhimento = id_acolhimento;
    }

    @Override
    public String toString() {
        return "Estagio: \n" + super.toString() + ", ramo = " + ramo + ", id_acolhimento = " + id_acolhimento;
    }

    @Override
    public String getRamo() {
        return ramo;
    }

    public String getId_acolhimento() {
        return id_acolhimento;
    }
}
