package exceptions;

public class DureeMinNonRespecteeException extends Exception {
    @Override
    public String getMessage() {
        return "Erreur! La durée minimale d'un créneau n'a pas été respectée.";
    }
}
