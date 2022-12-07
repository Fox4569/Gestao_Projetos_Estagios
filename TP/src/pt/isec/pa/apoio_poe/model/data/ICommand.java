package pt.isec.pa.apoio_poe.model.data;

public interface ICommand {
    boolean execute();
    boolean undo();
}
