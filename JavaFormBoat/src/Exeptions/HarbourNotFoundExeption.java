package Exeptions;

import java.io.IOException;

public class HarbourNotFoundExeption extends RuntimeException {

    public HarbourNotFoundExeption(int i) {
        super( "Не найдена лодка по месту " + i );
    }
}
