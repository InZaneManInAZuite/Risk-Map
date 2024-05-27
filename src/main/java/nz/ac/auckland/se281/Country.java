package nz.ac.auckland.se281;

/** This class represents a country with a name, continent, and tax fee. */
public class Country {
  // Declare the fields of the country
  private final String name;
  private final String continent;
  private final int taxFee;

  /**
   * Create a new country with the given name, continent, and tax fee.
   *
   * @param name the name of the country.
   * @param continent the continent of the country.
   * @param taxFee the tax fee of the country.
   */
  public Country(String name, String continent, int taxFee) {
    this.name = name;
    this.continent = continent;
    this.taxFee = taxFee;
  }

  // Getter methods
  public String getName() {
    return name;
  }

  public String getContinent() {
    return continent;
  }

  public int getTaxFee() {
    return taxFee;
  }

  public String strTaxFee() {
    return String.valueOf(taxFee);
  }

  /**
   * Find the string representation of the country.
   *
   * @return the string representation of the country.
   */
  @Override
  public String toString() {
    // Return the country as a string of its name
    return this.name;
  }

  /**
   * Generate a hash code for the country.
   *
   * @return the hash code of the country.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  /**
   * Check if the object being compared is the same as the country.
   *
   * @param obj the object to compare.
   * @return true if the object is the same as the country, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    // Check if the object is the same as the country
    if (this == obj) {
      return true;
    }

    // Check if the object is null
    if (obj == null) {
      return false;
    }

    // Check if the object is an instance of Country
    if (getClass() != obj.getClass()) {
      return false;
    }

    // Check if the name of the country is the same
    Country other = (Country) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }
}
