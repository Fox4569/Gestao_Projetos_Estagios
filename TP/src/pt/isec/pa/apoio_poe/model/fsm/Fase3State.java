package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.App;

public class Fase3State extends AppStateAdapter{
    protected Fase3State(AppContext context, App app){
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE3;
    }

    @Override
    public boolean atribuicaoAutoPropostasEDocentes() {
        app.atribuicaoAutoPropostasEDocentes();
        changeState(AppState.FASE3);
        return true;
    }

    @Override
    public boolean atribuicaoPropostas() {
        if(!app.isFase2fechada())
            return false;

        if(!app.atribuicaoPropostas())
            decideEmpate();
        else
            changeState(AppState.FASE3);

        return true;
    }

    @Override
    public boolean decideEmpate() {
        changeState(AppState.DECIDEEMPATE);
        return true;
    }

    @Override
    public boolean atribuicaoManual() {
        changeState(AppState.ATRIBUICAOMANUAL);
        return true;
    }

    @Override
    public boolean remocaoManualPropostas(int i) {
        boolean res = app.remocaoManualPropostas(i);
        changeState(AppState.FASE3);
        return res;
    }

    @Override
    public boolean remocaoTodasPropostas() {
        return app.remocaoTodasPropostas();
    }

    @Override
    public String listaRemocaoManualPropostas() {
        return app.listaRemocaoManualPropostas();
    }

    @Override
    public String listarAlunosAutoproposta() {
        return app.listaAlunosAutopropostas();
    }

    @Override
    public String listarAlunosComCand() {
        return app.listaAlunosComCand();
    }

    @Override
    public String listarAlunosComPropAtribuida() {
        return app.listaAlunosComPropAtribuida();
    }

    @Override
    public String listarAlunosSemPropAtribuida() {
        return app.listaAlunosSemPropAtribuida();
    }

    @Override
    public String listarAutopropostas() {
        return app.listaAutopropostas();
    }

    @Override
    public String listarPropDocentes() {
        return app.listaPropDocentes();
    }

    @Override
    public String listarPropAtribuidas() {
        return app.listaPropAtribuidas();
    }

    @Override
    public String listarPropNaoAtribuidas() {
        return app.listaPropNaoAtribuidas();
    }

    @Override
    public boolean exportarDadosFase3(String nome) {
        return app.exportarDadosFase3(nome);
    }

    @Override
    public boolean fechaFase() {
        if(app.fechaFase3()){
            changeState(AppState.FASE4);
            return true;
        }
        return false;
    }

    @Override
    public boolean fase2() {
        if(app.isFase2fechada())
            changeState(AppState.FASE2FECHADA);
        else
            changeState(AppState.FASE2);
        return true;
    }

    @Override
    public boolean fase4() {
        changeState(AppState.FASE4);
        return true;
    }

    @Override
    public boolean adicionaAtribuicaoProposta(int aluno, int propIndex) {
        return app.atribuicaoManualPropostas(aluno, propIndex);
    }

    @Override
    public boolean removeAtribuicaoProposta(Aluno al) {
        return app.removeAtribuicaoProposta(al);
    }
}
