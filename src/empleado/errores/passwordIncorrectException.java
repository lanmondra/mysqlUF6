
package empleado.errores;


public class passwordIncorrectException extends Exception {
    
    private int codigoError;

    static void passwordIncorrectException() {
    }

    public passwordIncorrectException(String message) {

        super(message);

    }
    public passwordIncorrectException(String message,codigoError codigoError ) {

        super(message);
        this.codigoError = codigoError.getCodigoError();

    }

    public passwordIncorrectException(String mensaje, Throwable causa) {
        super(mensaje, causa);

    }

    public passwordIncorrectException(String mensaje, Throwable causa, codigoError codigoError) {
        super(mensaje, causa);
        this.codigoError = codigoError.getCodigoError();

    }

    public int getCodigoError() {
        return codigoError;
    }
    
}
