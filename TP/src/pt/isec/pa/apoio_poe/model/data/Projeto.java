package pt.isec.pa.apoio_poe.model.data;

/**
 * Classe que representa o objeto "Projeto" que extende da classe "Proposta".
 * O 1º construtor recebe como argumentos o Id da proposta,
 * o título, o ramo e o email do docente responsável pelo projeto.
 * O 2º construtor recebe tudo o que o anterior recebe
 * e também o número do aluno que foi associado a este projeto.
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class Projeto extends Proposta {
    private String ramo;
    private String emailDocente;


    public Projeto(String id, String ramo, String titulo, String emailDocente) {
        super(id, titulo);
        this.ramo =  ramo;
        this.emailDocente = emailDocente;
    }

    public Projeto(String id, String ramo, String titulo, String emailDocente, long n_aluno){
        super(id, titulo, n_aluno);
        this.ramo =  ramo;
        this.emailDocente = emailDocente;
    }

    @Override
    public String toString() {
        return "Projeto: \n" + super.toString() + ", ramo = " + ramo + ", emailDocente = " + emailDocente;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    @Override
    public String getRamo() {
        return ramo;
    }
}
