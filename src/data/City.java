package data;

public class City {
    private int id;
    private String city;
    private int org_id;

    public City(int id, String city, int org_id) {
        this.id = id;
        this.city = city;
        this.org_id = org_id;
    }
    public int getOrg_id() {
        return org_id;
    }

    public int getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
}
