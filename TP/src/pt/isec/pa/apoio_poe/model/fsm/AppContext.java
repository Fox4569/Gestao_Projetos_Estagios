package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.App;
import pt.isec.pa.apoio_poe.model.data.Proposta;

/**
 * Classe que funciona como "facade" da máquina de estados.
 * Esta classe limita-se a redirecionar as chamadas das funções para a classe do estado ativo
 * em cada momento na aplicação.
 *
 * @Author: João Baptista
 * @Author: Pedro Sequeira
 */

public class AppContext {
    private App app;
    private IAppState state;

    public AppContext() {
        app = new App();
        state = AppState.FASE1.createState(this, app);
    }

    public AppState getState(){
        return state.getState();
    }

    public boolean alunos(){
        return state.alunos();
    }

    public boolean eliminarAluno(long nr) {
        return state.eliminarAluno(nr);
    }

    public boolean docentes(){
        return state.docentes();
    }

    public boolean eliminarDocente(String email){
        return state.eliminarDocente(email);
    }

    public boolean propostas(){
        return state.propostas();
    }

    public boolean eliminarProposta(String id){
        return state.eliminarProposta(id);
    }

    public boolean fase1(){
        return state.fase1();
    }

    public String listarAlunos(){
        return state.listarAlunos();
    }

    public String listarDocentes(){
        return state.listarDocentes();
    }

    public String listarPropostas(){
        return state.listarPropostas();
    }

    public boolean fechaFase(){
        return state.fechaFase();
    }

    public boolean fase2(){
        return state.fase2();
    }

    public boolean fase3(){
        return state.fase3();
    }

    public boolean decideEmpate(){
        return state.decideEmpate();
    }

    public boolean atribuicaoManual(){
        return state.atribuicaoManual();
    }

    public String listar(){
        return state.listar();
    }

    public boolean add(String nomeFich) {
        return state.add(nomeFich);
    }

    public boolean escreverAlunos(String nome){
        return state.escreverAlunos(nome);
    }

    public boolean escreverDocentes(String nome){
        return state.escreverDocentes(nome);
    }

    public boolean escreverPropostas(String nome){
        return state.escreverPropostas(nome);
    }

    public String listarPropSemCand(){
        return state.listarPropSemCand();
    }

    public String listarPropComCand(){
        return state.listarPropComCand();
    }

    public String listarAlunosComCand(){
        return state.listarAlunosComCand();
    }

    public String listarAlunosSemCand(){
        return state.listarAlunosSemCand();
    }

    public String listarAlunosAutoproposta(){
        return state.listarAlunosAutoproposta();
    }

    public String listaAutopropostas(){
        return state.listarAutopropostas();
    }

    public String listaPropDocentes(){
        return state.listarPropDocentes();
    }

    public boolean candidaturas(){
        return state.candidaturas();
    }

    public String listarCandidaturas(){
        return state.listarCandidaturas();
    }

    public boolean escreverCandidaturas(String nome){
        return state.escreverCandidaturas(nome);
    }

    public boolean eliminarCandidatura(long nr) {
        return state.eliminarCandidatura(nr);
    }

    public boolean atribuicaoAutoPropostasEDocentes(){
        return state.atribuicaoAutoPropostasEDocentes();
    }

    public boolean atribuicaoPropostas(){
        return state.atribuicaoPropostas();
    }

    public boolean atribuicaoManualPropostas(int propIndex, int aluno){
        return state.atribuicaoManualPropostas(propIndex, aluno);
    }

    public String listaAtribuicaoManualPropostas(){
        return state.listaAtribuicaoManualPropostas();
    }

    public boolean remocaoTodasPropostas(){
        return state.remocaoTodasPropostas();
    }

    public boolean remocaoManualPropostas(int i){
        return state.remocaoManualPropostas(i);
    }

    public String listaRemocaoManualPropostas(){
        return state.listaRemocaoManualPropostas();
    }

    public String listarAlunosComPropAtribuida(){
        return state.listarAlunosComPropAtribuida();
    }

    public String listarAlunosSemPropAtribuida() {
        return state.listarAlunosSemPropAtribuida();
    }

    public String listarPropAtribuidas(){
        return state.listarPropAtribuidas();
    }

    public String listarPropNaoAtribuidas(){
        return state.listarPropNaoAtribuidas();
    }

    public boolean exportarDadosFase3(String nome){
        return state.exportarDadosFase3(nome);
    }

    public boolean atribuicaoAutoDocentes(){
        return state.atribuicaoAutoDocentes();
    }

    public String listaAtribuicaoOrientadorAluno(){
        return state.listaAtribuicaoOrientadorAluno();
    }

    public boolean atribuicaoOrientadorAluno(int orientador, int aluno){
        return state.atribuicaoOrientadorAluno(orientador, aluno);
    }

    public boolean fase4(){
        return state.fase4();
    }

    public boolean orientadores(){
        return state.orientadores();
    }

    public boolean esperaEscolha(){
        return state.esperaEscolha();
    }

    public String listarAlunosComOrientador(){
        return state.listarAlunosComOrientador();
    }

    public String listarAlunosSemOrientador(){
        return state.listarAlunosSemOrientador();
    }

    public String listaOrientadoresEAlunos(){
        return state.listaOrientadoresEAlunos();
    }

    public String dadosOrientadores(){
        return state.dadosOrientadores();
    }

    public boolean exportarDadosFase4e5(String nome){
        return state.exportarDadosFase4e5(nome);
    }

    public boolean fase5(){
        return state.fase5();
    }

    public String listaAlunosSemPropAtribuidaComCandidatura(){
        return state.listaAlunosSemPropAtribuidaComCandidatura();
    }


    //package-private
    void changeState(IAppState newState){
        state = newState;
    }

    public String listaAlunosEmpate(){
        return state.listaAlunosEmpate();
    }

    public boolean atribuicaodecideEmpate(int aluno, int prop){
        return state.atribuicaodecideEmpate(aluno, prop);
    }

    boolean adicionaAtribuicaoProposta(Aluno aluno, Proposta proposta){
        return state.adicionaAtribuicaoProposta(aluno, proposta);
    }
    boolean removeAtribuicaoProposta(Aluno aluno, Proposta proposta){
        return state.removeAtribuicaoProposta(aluno, proposta);
    }
}
