package nz.ac.auckland.se281;

import java.util.*;

/** This class is the main entry point. */
public class MapEngine {

  private Map<Country, List<Country>> adjCountry;

  public MapEngine() {
    // add other code here if you want
    this.adjCountry = new HashMap<>();

    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures

    for (String countryData : countries) {
      // add code here
      String[] CountrySplit = countryData.split(",");
      String name = CountrySplit[0];
      String continent = CountrySplit[1];
      int taxFee = Integer.parseInt(CountrySplit[2]);
      Country country = new Country(name, continent, taxFee);

      adjCountry.putIfAbsent(country, new ArrayList<>());
      System.out.println(country.toString());
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
