
package empleado.errores;


public class contraseñaException extends Exception {
    
    private int codigoError;

    static void passwordIncorrectException() {
    }

    public contraseñaException(String message) {

        super(message);

    }
    public contraseñaException(String message,codigoError codigoError ) {

        super(message);
        this.codigoError = codigoError.getCodigoError();

    }

    public contraseñaException(String mensaje, Throwable causa) {
        super(mensaje, causa);

    }

    public contraseñaException(String mensaje, Throwable causa, codigoError codigoError) {
        super(mensaje, causa);
        this.codigoError = codigoError.getCodigoError();

    }

    public int getCodigoError() {
        return codigoError;
    }
    
}
