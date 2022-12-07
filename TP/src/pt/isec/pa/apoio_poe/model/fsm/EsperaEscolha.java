package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class EsperaEscolha extends  AppStateAdapter{
    protected EsperaEscolha(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public String listaAtribuicaoOrientadorAluno() {
        return app.listaAtribuicaoOrientadorAluno();
    }

    @Override
    public boolean atribuicaoOrientadorAluno(int orientador, int aluno) {
        app.atribuicaoOrientadorAluno(orientador, aluno);
        changeState(AppState.GESTAO_ORIENTADORES);
        return true;
    }

    @Override
    public boolean orientadores() {
        changeState(AppState.GESTAO_ORIENTADORES);
        return true;
    }

    @Override
    public AppState getState() {
        return AppState.ESPERAESCOLHA;
    }
}
