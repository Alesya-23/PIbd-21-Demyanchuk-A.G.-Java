package Exeptions;

public class HarbourAlreadyHaveException extends RuntimeException {
    public HarbourAlreadyHaveException(){
        super("На парковке уже есть такая лодка");
    }
}
