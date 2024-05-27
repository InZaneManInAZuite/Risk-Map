package nz.ac.auckland.se281;

import java.util.*;

/** This class is the main entry point. */
public class MapEngine {

  private Map<Country, List<Country>> adjCountries;
  private List<Country> listCountries = new ArrayList<>();

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
  public void showRoute() {

    // Initialize variables
    Country sourceCountry = null;
    Country destinationCountry = null;
    String tempCountryName = null;

    // Ask the user for valid source country name
    MessageCli.INSERT_SOURCE.printMessage();
    while (sourceCountry == null) {
      try {
        tempCountryName = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
        sourceCountry = findCountry(tempCountryName);
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(tempCountryName);
      }
    }

    // Ask user for a valid destination country name
    MessageCli.INSERT_DESTINATION.printMessage();
    while (destinationCountry == null) {
      try {
        tempCountryName = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
        destinationCountry = findCountry(tempCountryName);
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(tempCountryName);
      }
    }

    // Check if the source and destination countries are the same
    if (sourceCountry.equals(destinationCountry)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }

    // Find the radial route from the source to the destination
    List<Country> radialRoute = breathFirstTraversal(sourceCountry);
    radialRoute = radialRoute.subList(0, radialRoute.indexOf(destinationCountry) + 1);

    // Find the fastest route from the source to the destination
    Country currentCountry = destinationCountry;
    Stack<Country> fastestRoute = new Stack<>();
    fastestRoute.add(currentCountry);
    while (!currentCountry.equals(sourceCountry)) {
      Country earliestCountry = earliestCall(currentCountry, radialRoute);
      fastestRoute.add(earliestCountry);
      currentCountry = earliestCountry;
    }

    printCountryArray(fastestRoute);
  }

  private void printCountryArray(List<Country> countries) {
    System.out.println("Country Array: ");
    for (Country c : countries) {
      System.out.println("\t" + c.getName());
    }
  }

  private Country earliestCall(Country country, List<Country> radialRoute) {
    List<Country> adj = this.adjCountries.get(country);
    for (Country c : radialRoute) {
      if (adj.contains(c)) {
        return c;
      }
    }
    return null;
  }

  private List<Country> breathFirstTraversal(Country root) {
    List<Country> visited = new ArrayList<>();
    Queue<Country> queue = new LinkedList<>();
    queue.add(root);
    visited.add(root);
    while (!queue.isEmpty()) {
      Country node = queue.poll();
      for (Country n : adjCountries.get(node)) {
        if (!visited.contains(n)) {
          visited.add(n);
          queue.add(n);
        }
      }
    }
    return visited;
  }

  private Country findCountry(String countryName) throws CountryNotFoundException {
    for (Country c : listCountries) {
      if (c.getName().equals(countryName)) {
        return c;
      }
    }
    throw new CountryNotFoundException();
  }
}
