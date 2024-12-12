package nl.bo.techiteasydeel1.exceptions;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(){
        super();
    }
    public RecordNotFoundException(String message)
    {
        super(message);
    }
}
