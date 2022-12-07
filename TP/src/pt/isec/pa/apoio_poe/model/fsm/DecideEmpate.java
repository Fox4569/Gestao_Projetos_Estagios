package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class DecideEmpate extends AppStateAdapter {
    protected DecideEmpate(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.DECIDEEMPATE;
    }

    @Override
    public String listaAlunosEmpate() {
        return app.listaAlunosEmpate();
    }

    @Override
    public boolean atribuicaodecideEmpate(int aluno, int prop) {
        return app.AtribuicaodecideEmpate(aluno, prop);
    }

    @Override
    public boolean fase3() {
        changeState(AppState.FASE3);
        return true;
    }
}
