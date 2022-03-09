public class Vols {

  private final Aeroport source;
  private final Aeroport destination;
  private final String airline;
  public Vols(Aeroport source, Aeroport destination, String airline) {
    this.source = source;
    this.destination = destination;
    this.airline = airline;
  }
  public Aeroport getSource() {
    return source;
  }
  public Aeroport getDestination() {
    return destination;
  }
  public String getAirline() {
    return airline;
  }

  @Override
  public String toString() {
    return "Flight [source=" + source.getIata() + ", destination=" + destination.getIata() + ", airline=" + airline + "]";
  }
}
