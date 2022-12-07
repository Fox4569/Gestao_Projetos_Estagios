package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class GestaoPropostasState extends AppStateAdapter {
    protected GestaoPropostasState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.GESTAO_PROPOSTAS;
    }

    @Override
    public boolean fase1() {
        changeState(AppState.FASE1);
        return true;
    }

    @Override
    public boolean add(String nomeFich) {
        app.lerPropostas(nomeFich);
        changeState(AppState.GESTAO_PROPOSTAS);
        return true;
    }

    @Override
    public boolean eliminarProposta(String id) {
        if(app.eliminarProposta(id)){
            changeState(AppState.GESTAO_PROPOSTAS);
            return true;
        }
        return false;
    }

    @Override
    public String listar() {
        return app.listaPropostas();
    }

    @Override
    public boolean escreverPropostas(String nome) {
        if(app.escreverPropostas(nome)) {
            changeState(AppState.GESTAO_PROPOSTAS);
            return true;
        }
        return false;
    }
}
