package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase2FechadaState extends AppStateAdapter{
    protected Fase2FechadaState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE2FECHADA;
    }

    public String listarCandidaturas(){
        return app.listaCandidaturas();
    }

    @Override
    public String listarPropSemCand() {
        return app.listaPropSemCand();
    }

    @Override
    public String listarPropComCand() {
        return app.listaPropComCand();
    }

    @Override
    public String listarAlunosComCand() {
        return app.listaAlunosComCand();
    }

    @Override
    public String listarAlunosSemCand() {
        return app.listaAlunosSemCand();
    }

    @Override
    public String listarAlunosAutoproposta() {
        return app.listaAlunosAutopropostas();
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
    public boolean fase1() {
        changeState(AppState.FASE1FECHADA);    //Como para a Fase2 estar fechada a Fase1 já tem de estar, já se sabe que a Fase1 está fechada
        return true;
    }

    @Override
    public boolean fase3() {
        if(app.isFase3fechada())
            changeState(AppState.FASE3FECHADA);
        else
            changeState(AppState.FASE3);
        return true;
    }
}
