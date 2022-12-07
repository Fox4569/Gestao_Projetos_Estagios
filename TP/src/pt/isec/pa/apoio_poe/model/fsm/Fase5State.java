package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase5State extends AppStateAdapter{
    protected Fase5State(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE5;
    }

    @Override
    public String listarAlunosComPropAtribuida() {
        return app.listaAlunosComPropAtribuida();
    }

    @Override
    public String listarPropNaoAtribuidas() {
        return app.listaPropNaoAtribuidas();
    }

    @Override
    public String listarPropAtribuidas() {
        return app.listaPropAtribuidas();
    }

    @Override
    public String dadosOrientadores() {
        return app.dadosOrientadores();
    }

    @Override
    public String listaAlunosSemPropAtribuidaComCandidatura() {
        return app.listaAlunosSemPropAtribuidaComCandidatura();
    }

    @Override
    public boolean exportarDadosFase4e5(String nome) {
        return app.exportarDadosFase4e5(nome);
    }
}
