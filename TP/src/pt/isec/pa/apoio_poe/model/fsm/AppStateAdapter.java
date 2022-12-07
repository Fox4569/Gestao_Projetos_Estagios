package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.App;
import pt.isec.pa.apoio_poe.model.data.Proposta;

/**
 * Classe que tem todas as funções padrão da máquina de estados.
 * Esta classe limita-se a retornar o valor por defeito das funções.
 *
 * @Author: João Baptista
 * @Author: Pedro Sequeira
 */

abstract public class AppStateAdapter implements IAppState{
    protected AppContext context;
    protected App app;

    protected AppStateAdapter(AppContext context, App app){
        this.context = context;
        this.app = app;
    }

    protected void changeState(AppState newState){
        context.changeState(newState.createState(context, app));
    }

    @Override
    public boolean add(String nomeFich) {
        return false;
    }

    @Override
    public String listar() {
        return null;
    }

    @Override
    public boolean escreverAlunos(String nome){
        return false;
    }

    @Override
    public boolean eliminarAluno(long nr) {
        return false;
    }

    @Override
    public boolean escreverDocentes(String nome) {
        return false;
    }

    @Override
    public boolean eliminarDocente(String email) {
        return false;
    }

    @Override
    public boolean escreverPropostas(String nome) {
        return false;
    }

    @Override
    public boolean eliminarProposta(String id) {
        return false;
    }

    @Override
    public String listarAlunos() {
        return null;
    }

    @Override
    public String listarDocentes() {
        return null;
    }

    @Override
    public String listarPropostas() {
        return null;
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean alunos() {
        return false;
    }

    @Override
    public boolean docentes() {
        return false;
    }

    @Override
    public boolean propostas() {
        return false;
    }

    @Override
    public boolean candidaturas() {
        return false;
    }

    @Override
    public boolean fase1() {
        return false;
    }

    @Override
    public boolean fechaFase() {
        return false;
    }

    @Override
    public boolean fase2() {
        return false;
    }

    @Override
    public boolean fase3() {
        return false;
    }

    @Override
    public boolean decideEmpate() {
        return false;
    }

    @Override
    public boolean atribuicaoManual() {
        return false;
    }

    @Override
    public boolean fase4() {
        return false;
    }

    @Override
    public boolean fase5() {
        return false;
    }

    @Override
    public String listarPropComCand() {
        return null;
    }

    @Override
    public String listarPropSemCand() {
        return null;
    }

    @Override
    public String listarAlunosComCand() {
        return null;
    }

    @Override
    public String listarAlunosSemCand() {
        return null;
    }

    @Override
    public String listarAlunosAutoproposta() {
        return null;
    }

    public String listarAutopropostas(){
        return null;
    }

    @Override
    public String listarPropDocentes() {
        return null;
    }

    @Override
    public String listarCandidaturas() {
        return null;
    }

    @Override
    public boolean escreverCandidaturas(String nome) {
        return false;
    }

    @Override
    public boolean eliminarCandidatura(long nr) {
        return false;
    }

    @Override
    public boolean atribuicaoAutoPropostasEDocentes() {
        return false;
    }

    @Override
    public boolean atribuicaoPropostas() {
        return false;
    }

    @Override
    public boolean atribuicaoManualPropostas(int propIndex, int aluno) {
        return false;
    }

    @Override
    public String listaAtribuicaoManualPropostas() {
        return null;
    }

    @Override
    public boolean remocaoTodasPropostas() {
        return false;
    }

    @Override
    public boolean remocaoManualPropostas(int i) {
        return false;
    }

    @Override
    public String listaRemocaoManualPropostas() {
        return null;
    }

    @Override
    public String listarAlunosComPropAtribuida() {
        return null;
    }

    @Override
    public String listarAlunosSemPropAtribuida() {
        return null;
    }

    @Override
    public String listarPropAtribuidas() {
        return null;
    }

    @Override
    public String listarPropNaoAtribuidas() {
        return null;
    }

    @Override
    public boolean exportarDadosFase3(String nome) {
        return false;
    }

    @Override
    public boolean orientadores() {
        return false;
    }

    @Override
    public boolean esperaEscolha() {
        return false;
    }

    @Override
    public boolean atribuicaoAutoDocentes() {
        return false;
    }

    @Override
    public String listaAtribuicaoOrientadorAluno() {
        return null;
    }

    @Override
    public boolean atribuicaoOrientadorAluno(int orientador, int aluno) {
        return false;
    }

    @Override
    public String listarAlunosComOrientador() {
        return null;
    }

    @Override
    public String listarAlunosSemOrientador() {
        return null;
    }

    @Override
    public String listaOrientadoresEAlunos() {
        return null;
    }

    @Override
    public String dadosOrientadores() {
        return null;
    }

    @Override
    public boolean exportarDadosFase4e5(String nome) {
        return false;
    }

    @Override
    public String listaAlunosSemPropAtribuidaComCandidatura() {
        return null;
    }

    @Override
    public String listaAlunosEmpate() {
        return null;
    }

    @Override
    public boolean atribuicaodecideEmpate(int aluno, int prop) {
        return false;
    }

    @Override
    public boolean adicionaAtribuicaoProposta(Aluno aluno, Proposta proposta) {
        return false;
    }

    @Override
    public boolean removeAtribuicaoProposta(Aluno aluno, Proposta proposta) {
        return false;
    }

    public boolean adicionaAtribuicaoProposta(int aluno, int propIndex){
        return false;
    }

    public boolean removeAtribuicaoProposta(Aluno al){
        return false;
    }
}
