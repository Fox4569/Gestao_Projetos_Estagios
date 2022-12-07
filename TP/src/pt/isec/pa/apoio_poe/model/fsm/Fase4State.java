package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase4State extends AppStateAdapter{
    protected Fase4State(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE4;
    }

    @Override
    public boolean orientadores() {
        changeState(AppState.GESTAO_ORIENTADORES);
        return true;
    }

    @Override
    public boolean atribuicaoAutoDocentes() {
        app.atribuicaoAutoDocentes();
        changeState(AppState.FASE4);
        return true;
    }

    @Override
    public String listarAlunosComOrientador() {
        return app.listaAlunosComOrientador();
    }

    @Override
    public String listarAlunosSemOrientador() {
        return app.listaAlunosSemOrientador();
    }

    @Override
    public String dadosOrientadores() {
        return app.dadosOrientadores();
    }

    @Override
    public boolean fase3() {
        if(app.isFase3fechada())
            changeState(AppState.FASE3FECHADA);
        else
            changeState(AppState.FASE3);
        return true;
    }

    @Override
    public boolean fase5() {
        changeState(AppState.FASE5);
        return true;
    }

    @Override
    public boolean exportarDadosFase4e5(String nome) {
        return app.exportarDadosFase4e5(nome);
    }

    @Override
    public boolean fechaFase() {
        if(app.fechaFase4()){
            changeState(AppState.FASE5);
            return true;
        }
        return false;
    }
}
