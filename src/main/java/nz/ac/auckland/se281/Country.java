package nz.ac.auckland.se281;

public class Country {
  private String name;
  private String continent;
  private int taxFee;

  public Country(String name, String continent, int taxFee) {
    this.name = name;
    this.continent = continent;
    this.taxFee = taxFee;
  }

  @Override
  public String toString() {
    return "Country{"
        + "name='"
        + name
        + '\''
        + ", continent='"
        + continent
        + '\''
        + ", taxFee="
        + taxFee
        + '}';
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Country other = (Country) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }
}