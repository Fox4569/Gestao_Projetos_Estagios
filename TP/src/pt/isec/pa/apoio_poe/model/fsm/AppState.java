package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public enum AppState {
    GESTAO_ALUNOS, GESTAO_DOCENTES, GESTAO_PROPOSTAS, FASE1, FASE1FECHADA, GESTAO_CANDIDATURAS, FASE2, FASE2FECHADA, FASE3, DECIDEEMPATE, ATRIBUICAOMANUAL, FASE3FECHADA, FASE4, GESTAO_ORIENTADORES, ESPERAESCOLHA, FASE5;

    //factory
    IAppState createState(AppContext context, App app){
        return switch (this){
            case FASE1 -> new Fase1State(context, app);
            case FASE1FECHADA -> new Fase1FechadaState(context, app);
            case GESTAO_ALUNOS -> new GestaoAlunosState(context, app);
            case GESTAO_DOCENTES -> new GestaoDocentesState(context, app);
            case GESTAO_PROPOSTAS -> new GestaoPropostasState(context, app);
            case FASE2 -> new Fase2State(context, app);
            case FASE2FECHADA -> new Fase2FechadaState(context, app);
            case GESTAO_CANDIDATURAS -> new GestaoCandidaturasState(context, app);
            case FASE3 -> new Fase3State(context, app);
            case DECIDEEMPATE -> new DecideEmpate(context, app);
            case ATRIBUICAOMANUAL -> new AtribuicaoManualProp(context, app);
            case FASE3FECHADA -> new Fase3FechadaState(context, app);
            case FASE4 -> new Fase4State(context, app);
            case GESTAO_ORIENTADORES -> new GestaoOrientadoresState(context, app);
            case ESPERAESCOLHA -> new EsperaEscolha(context, app);
            case FASE5 -> new Fase5State(context, app);
        };
    }
}
