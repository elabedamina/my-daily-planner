package exceptions;
public class DateFinAvantDateDebutException  extends Exception{
    
    @Override
    public String getMessage() {
        return "Erreur! La date fin est avant la date du début";
    }
}
