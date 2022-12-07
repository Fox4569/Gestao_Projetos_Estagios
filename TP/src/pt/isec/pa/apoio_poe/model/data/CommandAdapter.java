package pt.isec.pa.apoio_poe.model.data;

abstract class CommandAdapter implements ICommand {
    protected App app;
    protected CommandAdapter(App app) {
        this.app = app;
    }
}
