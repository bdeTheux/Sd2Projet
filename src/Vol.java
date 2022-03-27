/**
 * @author de Theux Boris, Di Tomasso Alessio
 */

public class Vol {

  private final String airline;
  private final String source;
  private final String destination;
  private double distance;

  public Vol(String airline, String source, String destination, double distance) {
    this.source = source;
    this.destination = destination;
    this.airline = airline;
    this.distance = distance;
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
  public double getDistance() {
    return distance;
  }


  @Override
  public String toString() {
    return "Flight [source=" + source + ", destination=" + destination + ", airline=" + airline + ", distance=" + distance + "]";
  }
}
