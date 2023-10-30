package mx.com.ananda.cronos.juno.exception;

public class UsuarioFoundException extends Exception{
    public UsuarioFoundException(){
        super("EL USUARIO CON ESE USERNAME YA EXISTE");
    }

    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}
