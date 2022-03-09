import java.util.Objects;

public class Aeroport {

    private String iata;
    private String nom;
    private String ville;
    private String pays;
    private long longitude;
    private long latitude;

    public Aeroport(String iata, String nom, String ville, String pays, long longitude, long latitude) {
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

    public long getLongitude() {
        return longitude;
    }

    public long getLatitude() {
        return latitude;
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
