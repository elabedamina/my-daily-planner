public class HeureFinAvantHeureDebutException extends Exception {
    public String getMessage() {
        return "Erreur! L'heure de fin est avant l'heure du début";
    }
}
