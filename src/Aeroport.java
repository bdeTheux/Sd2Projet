import java.util.Objects;

public class Aeroport {

    private String iata;
    private String nom;
    private String ville;
    private String pays;
    private double longitude;
    private double latitude;
    private double cout;

    public Aeroport(String iata, String nom, String ville, String pays, double longitude, double latitude) {
        this.iata = iata;
        this.nom = nom;
        this.ville = ville;
        this.pays = pays;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    //Getters
    public String getIata() {
        return iata;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout){
        this.cout = cout;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aeroport aeroport = (Aeroport) obj;
        return Objects.equals(iata, aeroport.iata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iata);
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "iata='" + iata + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
