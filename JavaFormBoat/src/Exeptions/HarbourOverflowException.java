package Exeptions;

public class HarbourOverflowException extends RuntimeException {
    public HarbourOverflowException() {
        super( "На парковке нет свободных мест" );
    }
}
