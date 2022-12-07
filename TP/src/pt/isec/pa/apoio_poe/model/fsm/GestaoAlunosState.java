package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class GestaoAlunosState extends AppStateAdapter {
    protected GestaoAlunosState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.GESTAO_ALUNOS;
    }

    @Override
    public boolean fase1() {
        changeState(AppState.FASE1);
        return true;
    }

    public boolean add(String nomeFich){
        app.lerAlunos(nomeFich);
        changeState(AppState.GESTAO_ALUNOS);
        return true;
    }

    public boolean eliminarAluno(long nr){
        if(app.eliminarAluno(nr)){
            changeState(AppState.GESTAO_ALUNOS);
            return true;
        }
        return false;
    }


    public String listar(){
        return app.listaAlunos();
    }

    @Override
    public boolean escreverAlunos(String nome) {
        if(app.escreverAlunos(nome)){
            changeState(AppState.GESTAO_ALUNOS);
            return true;
        }
        return false;
    }
}
