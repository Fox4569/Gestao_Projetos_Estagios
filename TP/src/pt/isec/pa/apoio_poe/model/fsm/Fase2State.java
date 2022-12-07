package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase2State extends AppStateAdapter{
    protected Fase2State(AppContext context, App app){
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE2;
    }

    @Override
    public boolean candidaturas() {
        changeState(AppState.GESTAO_CANDIDATURAS);
        return true;
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
    public boolean fechaFase() {
        if(app.fechaFase2()) {
            changeState(AppState.FASE3);
            return true;
        }
        return false;
    }

    @Override
    public boolean fase3() {
        changeState(AppState.FASE3);
        return true;
    }

    @Override
    public boolean fase1() {
        if(app.isFase1fechada())
            changeState(AppState.FASE1FECHADA);
        else
            changeState(AppState.FASE1);
        return true;
    }
}
