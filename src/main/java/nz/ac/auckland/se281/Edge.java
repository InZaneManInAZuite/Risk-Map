package nz.ac.auckland.se281;

public class Edge {
  private final String country1;
  private final String country2;

  public Edge(String country1, String country2, int distance) {
    this.country1 = country1;
    this.country2 = country2;
  }

  public String getCountry1() {
    return country1;
  }

  public String getCountry2() {
    return country2;
  }
}
