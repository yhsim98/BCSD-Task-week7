package exception;

public class UnauthorizedException extends Exception{
    private String message;

    public UnauthorizedException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
