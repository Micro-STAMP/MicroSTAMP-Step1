package microstamp.step1.exception;

public class Step1NotFoundException extends RuntimeException{

    public Step1NotFoundException(){
        super();
    }

    public Step1NotFoundException(final String message){
        super(message);
    }

}
