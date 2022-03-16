public class Vol {

  private final String airline;
  private final String source;
  private final String destination;

  public Vol(String airline, String source, String destination) {
    this.source = source;
    this.destination = destination;
    this.airline = airline;
  }
  public String getSource() {
    return source;
  }
  public String getDestination() {
    return destination;
  }
  public String getAirline() {
    return airline;
  }

  @Override
  public String toString() {
    return "Flight [source=" + source + ", destination=" + destination + ", airline=" + airline + "]";
  }
}
