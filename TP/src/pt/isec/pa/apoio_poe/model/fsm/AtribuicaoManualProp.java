package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class AtribuicaoManualProp extends AppStateAdapter{
    protected AtribuicaoManualProp(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.ATRIBUICAOMANUAL;
    }

    @Override
    public boolean atribuicaoManualPropostas(int indexProp, int aluno) {
        boolean res = app.atribuicaoManualPropostas(indexProp, aluno);
        changeState(AppState.FASE3);
        return res;
    }

    @Override
    public String listaAtribuicaoManualPropostas() {
        return app.listaAtribuicaoManualPropostas();
    }

    @Override
    public boolean fase3() {
        changeState(AppState.FASE3);
        return true;
    }
}
