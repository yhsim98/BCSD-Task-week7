package exception;

public class ConflictException extends Exception{
    private String message;

    public ConflictException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
