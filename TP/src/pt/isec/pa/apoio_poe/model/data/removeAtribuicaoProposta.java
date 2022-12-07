package pt.isec.pa.apoio_poe.model.data;

public class removeAtribuicaoProposta extends CommandAdapter{
    private int aluno;
    private int propIndex;
    private Aluno al;

    public removeAtribuicaoProposta(App app, int aluno, int propIndex) {
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
