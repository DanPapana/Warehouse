package src.logic;

/** @author Slavoj Zizek
 * @version 1
*/

public class Location implements java.io.Serializable {

    private String country;
    private String city;

    public Location () {}

    public Location(String newCountry, String newCity) {
        this.country = newCountry;
        this.city = newCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return city + " " + country;
    }
}