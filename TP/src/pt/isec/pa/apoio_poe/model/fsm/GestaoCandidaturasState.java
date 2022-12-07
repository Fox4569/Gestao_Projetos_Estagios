package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class GestaoCandidaturasState extends AppStateAdapter{

    protected GestaoCandidaturasState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.GESTAO_CANDIDATURAS;
    }

    @Override
    public boolean add(String nomeFich) {
        app.lerCandidaturas(nomeFich);
        changeState(AppState.GESTAO_CANDIDATURAS);
        return true;
    }

    @Override
    public boolean escreverCandidaturas(String nome) {
        return app.escreverCandidaturas(nome);
    }

    @Override
    public boolean eliminarCandidatura(long nr) {
        if(app.eliminarCandidatura(nr)) {
            changeState(AppState.FASE2);
            return true;
        }
        return false;
    }

    @Override
    public String listar() {
        return app.listaCandidaturas();
    }

    @Override
    public boolean fase2() {
        changeState(AppState.FASE2);
        return true;
    }
}
