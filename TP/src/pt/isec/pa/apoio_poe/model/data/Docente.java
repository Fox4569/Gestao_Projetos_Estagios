package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

/**
 * Classe que representa o objeto "Docentes".
 * O construtor recebe como argumentos o nome
 * e o email do docente
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class Docente implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;

    public Docente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    @Override
    public String toString() {
        return "nome = " + nome + ", email = " + email;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
