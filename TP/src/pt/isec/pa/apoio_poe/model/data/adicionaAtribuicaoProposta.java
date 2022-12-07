package pt.isec.pa.apoio_poe.model.data;

public class adicionaAtribuicaoProposta extends CommandAdapter{
    private int propIndex;
    private int aluno;
    private Aluno al;

    public adicionaAtribuicaoProposta(App app, int aluno, int propIndex) {
        super(app);
        this.aluno = aluno;
        this.propIndex = propIndex;
    }

    @Override
    public boolean execute() {
        return app.atribuicaoManualPropostas(aluno, propIndex);
    }

    @Override
    public boolean undo() {
        return app.removeAtribuicaoProposta(al);
    }
}
