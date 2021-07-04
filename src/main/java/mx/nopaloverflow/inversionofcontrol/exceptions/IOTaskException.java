package mx.nopaloverflow.inversionofcontrol.exceptions;

public class IOTaskException extends Exception {

    public IOTaskException(final String msg) {
        super(msg);
    }

    public IOTaskException(final String msg, final Throwable reason) {
        super(msg, reason);
    }
}
