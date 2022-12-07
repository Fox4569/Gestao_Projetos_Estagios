package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IAppState {

    boolean alunos();
    boolean docentes();
    boolean propostas();
    boolean fase1();
    boolean fechaFase();
    boolean fase2();
    boolean fase3();
    boolean decideEmpate();
    boolean atribuicaoManual();
    boolean fase4();
    boolean orientadores();
    boolean esperaEscolha();
    boolean fase5();

    boolean add(String nomeFich);
    boolean eliminarAluno(long nr);
    boolean eliminarDocente(String email);
    boolean eliminarProposta(String id);
    String listar();
    boolean escreverAlunos(String nome);
    boolean escreverDocentes(String nome);
    boolean escreverPropostas(String nome);
    String listarAlunos();
    String listarDocentes();
    String listarPropostas();
    boolean load();
    boolean save();

    boolean candidaturas();

    String listarPropSemCand();
    String listarPropComCand();
    String listarAlunosComCand();
    String listarAlunosSemCand();
    String listarAlunosAutoproposta();
    String listarAutopropostas();
    String listarPropDocentes();
    String listarCandidaturas();
    boolean escreverCandidaturas(String nome);
    boolean eliminarCandidatura(long nr);

    boolean atribuicaoAutoPropostasEDocentes();
    boolean atribuicaoPropostas();
    boolean atribuicaoManualPropostas(int propIndex, int aluno);
    String listaAtribuicaoManualPropostas();
    boolean remocaoTodasPropostas();
    boolean remocaoManualPropostas(int i);
    String listaRemocaoManualPropostas();
    String listarAlunosComPropAtribuida();
    String listarAlunosSemPropAtribuida();
    String listarPropAtribuidas();
    String listarPropNaoAtribuidas();
    boolean exportarDadosFase3(String nome);

    boolean atribuicaoAutoDocentes();
    String listaAtribuicaoOrientadorAluno();
    boolean atribuicaoOrientadorAluno(int orientador, int aluno);
    String listarAlunosComOrientador();
    String listarAlunosSemOrientador();
    String listaOrientadoresEAlunos();
    String dadosOrientadores();
    boolean exportarDadosFase4e5(String nome);

    String listaAlunosSemPropAtribuidaComCandidatura();

    AppState getState();

    String listaAlunosEmpate();
    boolean atribuicaodecideEmpate(int aluno, int prop);

    boolean adicionaAtribuicaoProposta(Aluno aluno, Proposta proposta);
    boolean removeAtribuicaoProposta(Aluno aluno, Proposta proposta);
}
