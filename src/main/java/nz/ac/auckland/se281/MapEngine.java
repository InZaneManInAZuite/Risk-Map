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

    ArrayList<Country> listCountry = new ArrayList<>();

    // Obtain all the countries as nodes and their individual information
    for (String countryData : countries) {

      // Split the country data into its individual components
      String[] CountrySplit = countryData.split(",");
      String name = CountrySplit[0];
      String continent = CountrySplit[1];
      int taxFee = Integer.parseInt(CountrySplit[2]);
      Country country = new Country(name, continent, taxFee);

      // Add the country to the adjacency list and country list
      adjCountry.putIfAbsent(country, new ArrayList<>());
      listCountry.add(country);
    }

    // Obtain all the edges between countries
    for (String adjData : adjacencies) {
      // Split the adjacency data into its individual components
      String[] adjSplit = adjData.split(",");
      Country country = null;
      String countryName = adjSplit[0];
      int numAdj = adjSplit.length - 1;

      // Find the country in the list of countries
      for (Country c : listCountry) {
        if (c.getName().equals(countryName)) {
          country = c;
          break;
        }
      }

      System.out.println(country.toString());

      // Add the adjacent countries to the adjacency list
      for (int i = 1; i <= numAdj; i++) {
        String adjCountryName = adjSplit[i];

        for (Country c : listCountry) {
          if (c.getName().equals(adjCountryName)) {
            adjCountry.get(country).add(c);
            System.out.println("\t" + c.toString());
            break;
          }
        }
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
