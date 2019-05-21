package empleado.errores;

public class usuarioException extends Exception {

    private int codigoError;

    static void userIncorrectException() {
    }

    public usuarioException(String message) {

        super(message);

    }
    
    public usuarioException(String message, codigoError codigoError) {

        super(message);
        this.codigoError=codigoError.getCodigoError();

    }

    public usuarioException(String mensaje, Throwable causa) {
        super(mensaje, causa);

    }

    public usuarioException(String mensaje, Throwable causa, codigoError codigoError) {
        super(mensaje, causa);
        this.codigoError = codigoError.getCodigoError();

    }

    public int getCodigoError() {
        return codigoError;
    }

}
