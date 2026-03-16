package exceptions;


public class TurnoOcupadoException extends RuntimeException {
    public TurnoOcupadoException(String mensaje) {
        super(mensaje);
    }
}
