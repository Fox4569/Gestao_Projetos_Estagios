package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.model.fsm.AppState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Classe que funciona como "facade" da classe App.
 * Esta classe limita-se a redirecionar as chamadas
 * das funções para a classe "App".
 *
 * @author João Baptista
 * @author Pedro Sequeira
 */

public class AppManager {
    private AppContext fsm;
    App app;
    CommandManager cm;
    PropertyChangeSupport pcs;

    private static final String FILENAME = "app.bin";

    public AppManager() {
        this.fsm = new AppContext();
        this.app = new App();
        this.cm = new CommandManager();
        this.pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public boolean lerAlunos(String nomeFich){
        boolean res = app.lerAlunos(nomeFich);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean eliminarAluno(long nr){
        boolean res = app.eliminarAluno(nr);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean lerDocentes(String nomeFich){
        boolean res = app.lerDocentes(nomeFich);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean eliminarDocente(String email){
        boolean res = app.eliminarDocente(email);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean lerPropostas(String nomeFich){
        boolean res = app.lerPropostas(nomeFich);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean eliminarProposta(String id){
        boolean res = app.eliminarProposta(id);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean lerCandidaturas(String nomeFich){
        boolean res = app.lerCandidaturas(nomeFich);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean eliminarCandidatura(long nr){
        boolean res = app.eliminarCandidatura(nr);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean escreverAlunos(String nome) {
        return app.escreverAlunos(nome);
    }

    public boolean escreverDocentes(String nome) {
        return app.escreverDocentes(nome);
    }

    public boolean escreverPropostas(String nome) {
        return app.escreverPropostas(nome);
    }

    public boolean fechaFase(){
        boolean res = fsm.fechaFase();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listarAlunos(){
        String res = app.listaAlunos();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listarDocentes(){
        String res = app.listaDocentes();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listarPropostas(){
        String res = app.listaPropostas();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listarPropSemCand(){
        return app.listaPropSemCand();
    }

    public String listarPropComCand(){
        return app.listaPropComCand();
    }

    public String listarAlunosComCand(){
        return app.listaAlunosComCand();
    }

    public String listarAlunosSemCand(){
        return app.listaAlunosSemCand();
    }

    public String listarAlunosAutoproposta(){
        return app.listaAlunosAutopropostas();
    }

    public String listaAutopropostas(){
        return app.listaAutopropostas();
    }

    public String listaPropDocentes(){
        return app.listaPropDocentes();
    }

    public String listarCandidaturas(){
        return app.listaCandidaturas();
    }

    public boolean escreverCandidaturas(String nome){
        return app.escreverCandidaturas(nome);
    }

    public boolean atribuicaoAutoPropostasEDocentes(){
        boolean res = app.atribuicaoAutoPropostasEDocentes();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean atribuicaoPropostas(){
        boolean res = app.atribuicaoPropostas();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean atribuicaoManualPropostas(int propIndex, int aluno){
        boolean res = app.atribuicaoManualPropostas(propIndex, aluno);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean verificaTamanhos(){
        return app.verificaTamanhos();
    }

    public String listaAtribuicaoManualPropostas(){
        return app.listaAtribuicaoManualPropostas();
    }

    public boolean remocaoTodasPropostas(){
        boolean res = app.remocaoTodasPropostas();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public boolean remocaoManualPropostas(int i){
        boolean res = app.remocaoManualPropostas(i);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listaRemocaoManualPropostas(){
        return app.listaRemocaoManualPropostas();
    }

    public String listarAlunosComPropAtribuida(){
        return app.listaAlunosComPropAtribuida();
    }

    public String listarAlunosSemPropAtribuida() {
        return app.listaAlunosSemPropAtribuida();
    }

    public String listarPropAtribuidas(){
        return app.listaPropAtribuidas();
    }

    public String listarPropNaoAtribuidas(){
        return app.listaPropNaoAtribuidas();
    }

    public boolean exportarDadosFase3(String nome){
        return app.exportarDadosFase3(nome);
    }

    public boolean atribuicaoAutoDocentes(){
        boolean res = app.atribuicaoAutoDocentes();
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listaAtribuicaoOrientadorAluno(){
        return app.listaAtribuicaoOrientadorAluno();
    }

    public boolean atribuicaoOrientadorAluno(int orientador, int aluno){
        boolean res = app.atribuicaoOrientadorAluno(orientador, aluno);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public String listarAlunosComOrientador(){
        return app.listaAlunosComOrientador();
    }

    public String listarAlunosSemOrientador(){
        return app.listaAlunosSemOrientador();
    }

    public String listaOrientadoresEAlunos(){
        return app.listaOrientadoresEAlunos();
    }

    public String dadosOrientadores(){
        return app.dadosOrientadores();
    }

    public boolean exportarDadosFase4e5(String nome){
        return app.exportarDadosFase4e5(nome);
    }

    public String listaAlunosSemPropAtribuidaComCandidatura(){
        return app.listaAlunosSemPropAtribuidaComCandidatura();
    }

    public String listaAlunosEmpate(){
        return app.listaAlunosEmpate();
    }

    public boolean load() {        //Desserealização
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))){
            App newApp = (App) ois.readObject();
            app = newApp;

            return true;
        }catch (Exception e){
            System.err.println("ERRO!");
            return false;
        }
    }

    public boolean save() {        //Serealização
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            oos.writeObject(app);
            return true;
        }catch (Exception e){
            System.err.println("ERRO!");
            return false;
        }
    }

    public void adicionaAtribuicaoProposta(int aluno, int propIndex){
        cm.invokeCommand(new adicionaAtribuicaoProposta(app, aluno, propIndex));
    }

    public void removeAtribuicaoProposta(int aluno, int propIndex){
        cm.invokeCommand(new removeAtribuicaoProposta(app, aluno, propIndex));
    }

    public boolean undo(){
        return cm.undo();
    }

    public boolean redo(){
        return cm.redo();
    }

    public AppState getState(){
        return fsm.getState();
    }

    public void fase1(){
        fsm.fase1();
        pcs.firePropertyChange(null,null,null);
    }

    public void fase2(){
        fsm.fase2();
        pcs.firePropertyChange(null,null,null);
    }

    public void fase3(){
        fsm.fase3();
        pcs.firePropertyChange(null,null,null);
    }

    public void fase4(){
        fsm.fase4();
        pcs.firePropertyChange(null,null,null);
    }

    public void fase5(){
        fsm.fase5();
        pcs.firePropertyChange(null,null,null);
    }

    public void faseGestaoAlunos(){
        fsm.alunos();
        pcs.firePropertyChange(null,null,null);
    }

    public void faseGestaoDocentes(){
        fsm.docentes();
        pcs.firePropertyChange(null,null,null);
    }

    public void faseGestaoPropostas(){
        fsm.propostas();
        pcs.firePropertyChange(null,null,null);
    }

    public void faseGestaoCandidaturas(){
        fsm.candidaturas();
        pcs.firePropertyChange(null,null,null);
    }

    public void decideEmpate() {
        fsm.decideEmpate();
        pcs.firePropertyChange(null,null,null);
    }

    public void atribuicaoManual(){
        fsm.atribuicaoManual();
        pcs.firePropertyChange(null,null,null);

    }

    public boolean atribuicaoDecideEmpate(int aluno, int prop){
        boolean res = app.AtribuicaodecideEmpate(aluno, prop);
        pcs.firePropertyChange(null,null,null);
        return res;
    }

    public void orientadores() {
        fsm.orientadores();
        pcs.firePropertyChange(null,null,null);
    }

    public void esperaEscolha() {
        fsm.esperaEscolha();
        pcs.firePropertyChange(null,null,null);
    }

    public HashMap<String, Integer> getTopDocentes(){
        return app.getTopDocentes();
    }

    public HashMap<String, Integer> getEstagiosPorRamos(){
        return app.getEstagiosPorRamos();
    }

    public int getAlunos(){
        return app.getAlunos();
    }

    public int getDocentes() {
        return app.getDocentes();
    }

    public int getPropostas(){
        return app.getPropostas();
    }

    public int getCandidaturas(){
        return app.getCandidaturas();
    }

    public int getAlunosComPropAtribuida() {
        return app.getAlunosComPropAtribuida();
    }

    public int getAlunosComOrientador(){
        return app.getAlunosComOrientador();
    }

    public boolean adicionarAluno(long n_aluno, String nome, String email, String curso, String ramo, double classificacao, boolean EeP){
        return app.adicionarAluno(n_aluno, nome, email, curso, ramo, classificacao, EeP);
    }

    public boolean adicionarDocente(String nome, String email){
        return app.adicionarDocente(nome, email);
    }

    public boolean adicionarProjetoCAluno(String id, String ramo, String titulo, String email, long aluno){
        return app.adicionarProjetoCAluno(id, ramo, titulo, email, aluno);
    }

    public boolean adicionarProjetoSAluno(String id, String ramo, String titulo, String email){
        return app.adicionarProjetoSAluno(id, ramo, titulo, email);
    }

    public boolean adicionarEstagioCAluno(String id, String ramo, String titulo, String idAco, long aluno){
        return app.adicionarEstagioCAluno(id, ramo, titulo, idAco, aluno);
    }

    public boolean adicionarEstagioSAluno(String id, String ramo, String titulo, String idAco){
        return app.adicionarEstagioSAluno(id, ramo, titulo, idAco);
    }

    public boolean adicionarAutoprop(String id, String titulo, long nAluno){
        return app.adicionarAutoprop(id, titulo, nAluno);
    }
}
