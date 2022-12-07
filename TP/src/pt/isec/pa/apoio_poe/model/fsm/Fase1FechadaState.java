package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase1FechadaState extends AppStateAdapter{
    protected Fase1FechadaState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public String listarAlunos(){
        return app.listaAlunos();
    }

    @Override
    public String listarDocentes(){
        return app.listaDocentes();
    }

    @Override
    public String listarPropostas(){
        return app.listaPropostas();
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
    public AppState getState() {
        return AppState.FASE1FECHADA;
    }
}
