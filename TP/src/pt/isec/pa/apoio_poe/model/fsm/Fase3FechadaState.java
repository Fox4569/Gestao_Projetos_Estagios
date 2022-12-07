package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase3FechadaState extends AppStateAdapter{
    protected Fase3FechadaState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE3FECHADA;
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
    public boolean fase2() {
        changeState(AppState.FASE2FECHADA);     //Como para a Fase3 estar fechada a Fase2 já tem de estar, já se sabe que a Fase2 está fechada
        return true;
    }

    @Override
    public boolean fase4() {
        changeState(AppState.FASE4);
        return true;
    }
}
