package nz.ac.auckland.se281;

import java.util.*;

/** This class is the main entry point. */
public class MapEngine {

  private Map<Country, List<Country>> adjCountries;
  private ArrayList<Country> listCountries = new ArrayList<>();

  public MapEngine() {
    // add other code here if you want
    this.adjCountries = new HashMap<>();

    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // Obtain all the countries as nodes and their individual information
    for (String countryData : countries) {

      // Split the country data into its individual components
      String[] CountrySplit = countryData.split(",");
      String name = CountrySplit[0];
      String continent = CountrySplit[1];
      int taxFee = Integer.parseInt(CountrySplit[2]);
      Country country = new Country(name, continent, taxFee);

      // Add the country to the adjacency list and country list
      adjCountries.putIfAbsent(country, new ArrayList<>());
      listCountries.add(country);
    }

    // Obtain all the adjacent countries for each country as edges
    for (String adjData : adjacencies) {
      // Split the adjacency data into its individual components
      String[] adjSplit = adjData.split(",");
      Country country = null;
      String countryName = adjSplit[0];
      int numAdj = adjSplit.length - 1;

      // Find the country to be evaluated in the list of countries
      country = findCountry(countryName);
      // System.out.println(country.toString());

      // Obtain the adjacent country to this country and add to map
      for (int i = 1; i <= numAdj; i++) {
        String adjCountryName = adjSplit[i];
        adjCountries.get(country).add(findCountry(adjCountryName));
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {

    // Ask the user for the country name
    MessageCli.INSERT_COUNTRY.printMessage();

    // Ask user for a valid country name
    Country country = null;
    String countryName = null;
    while (country == null) {
      try {
        countryName = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
        country = findCountry(countryName);
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(countryName);
      }
    }

    // Print the country information
    MessageCli.COUNTRY_INFO.printMessage(
        country.getName(), country.getContinent(), country.strTaxFee());
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}

  private Country findCountry(String countryName) throws CountryNotFoundException {
    for (Country c : listCountries) {
      if (c.getName().equals(countryName)) {
        return c;
      }
    }
    throw new CountryNotFoundException();
  }
}
