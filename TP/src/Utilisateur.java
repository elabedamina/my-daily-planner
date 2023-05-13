public class Utilisateur {
    private String pseudo;
    private Planing planing;
    private Preference preference;

    public Utilisateur(String pseudo, Planing planing, Preference preference) {
        this.pseudo = pseudo;
        this.planing = planing;
        this.preference = preference;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Planing getPlaning() {
        return planing;
    }

    public void setPlaning(Planing planing) {
        this.planing = planing;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

}
