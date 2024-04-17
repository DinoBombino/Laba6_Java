package data;

public abstract class Organization {
    private String name;
    private int id;

    public Organization(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public abstract void sail();

    public int getId() {
        return id;
    }
    /*public static int getOrganizationType_AircraftFactory(Organization org) {
        if (org instanceof AircraftFactory) {
            return 1;
        } else if (org instanceof ShipbuildingCompany) {
            return 2;
        } else if (org instanceof InsuranceCompany) {
            return 3;
        } else {
            return 0;
        }
    }
    public static int getOrganizationType_ShipbuildingCompany(Organization org) {
        if (org instanceof ShipbuildingCompany) {
            return 1;
        } else if (org instanceof AircraftFactory) {
            return 2;
        } else if (org instanceof InsuranceCompany) {
            return 3;
        } else {
            return 0;
        }
    }
    public static int getOrganizationType_InsuranceCompany(Organization org) {
        if (org instanceof InsuranceCompany) {
            return 1;
        } else if (org instanceof AircraftFactory) {
            return 2;
        } else if (org instanceof ShipbuildingCompany) {
            return 3;
        } else {
            return 0;
        }
    }*/
    public static int getOrganizationType_AircraftFactory(Organization org) {
        if (org instanceof AircraftFactory) {
            return 1;
        } else if (org instanceof ShipbuildingCompany) {
            return 2;
        } else if (org instanceof InsuranceCompany) {
            return 3;
        } else {
            return 0;
        }
    }
    public static int getOrganizationType_ShipbuildingCompany(Organization org) {
        if (org instanceof ShipbuildingCompany) {
            return 1;
        } else if (org instanceof AircraftFactory) {
            return 2;
        } else if (org instanceof InsuranceCompany) {
            return 3;
        } else {
            return 0;
        }
    }
    public static int getOrganizationType_InsuranceCompany(Organization org) {
        if (org instanceof InsuranceCompany) {
            return 1;
        } else if (org instanceof AircraftFactory) {
            return 2;
        } else if (org instanceof ShipbuildingCompany) {
            return 3;
        } else {
            return 0;
        }
    }
}