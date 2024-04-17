package data;

import DB.DBWorker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class Group {
    private static ArrayList<Organization> organizations = new ArrayList<>();

    public Group() {
        /*organizations.add(new InsuranceCompany("Страховая1", 0, "ОСАГО"));
        organizations.add(new ShipbuildingCompany("Верфь1", 1, "Дредноут"));
        organizations.add(new AircraftFactory("Авиазавод1", 2, "Истребитель"));*/
        try {
            //Добавляем в список все корабли из БД
            organizations.addAll(DBWorker.getAircraftFactory());
            organizations.addAll(DBWorker.getInsuranceCompany());
            organizations.addAll(DBWorker.getShipbuildingCompany());

            //Добавить в список все рейсы из БД
            //flights.addAll(DBWorker.getAllFlights());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static boolean addAircraftFactory(String name, int id, String type_of_benefit) {
        for (Organization organization : organizations) {
            if (organization.getId() == id) {
                return false; // Завершаем метод
            }
        }
        AircraftFactory aircraftfactory = new AircraftFactory(name, id, type_of_benefit);
        organizations.add(aircraftfactory);
        System.out.println("OK");
        return true;
    }
    public static boolean addShipbuildingCompany(String name, int id, String type_of_benefit) {
        for (Organization organization : organizations) {
            if (organization.getId() == id) {
                return false; // Завершаем метод
            }
        }
        ShipbuildingCompany shipbuildingcompany = new ShipbuildingCompany(name, id, type_of_benefit);
        organizations.add(shipbuildingcompany);
        return true;
    }
    public static boolean addInsuranceCompany(String name, int id, String type_of_benefit) {

        for (Organization organization : organizations) {
            if (organization.getId() == id) {
                return false; // Завершаем метод
            }
        }
        InsuranceCompany insurancecompany = new InsuranceCompany(name, id, type_of_benefit);
        organizations.add(insurancecompany);
        return true;
    }*/

    public static boolean addAircraftFactory(String name, int id, String type_of_benefit) {

        for (Organization boat : organizations) {
            if (boat.getId() == id) {
                System.out.println("Организация с таким ID уже существует.");
                return false;
            }
        }
        AircraftFactory aircraftfactory = new AircraftFactory(name, id, type_of_benefit);
        organizations.add(aircraftfactory);
        System.out.println("Авиазавод успешно добавлен.");
        try {
            DBWorker.addAircraftFactory(aircraftfactory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean addInsuranceCompany(String name, int id, String benefit) {
        for (Organization boat : organizations) {
            if (boat.getId() == id) {
                System.out.println("Организация с таким ID уже существует.");
                return false;
            }
        }
        InsuranceCompany insurancecompany = new InsuranceCompany(name, id, benefit);
        organizations.add(insurancecompany);
        System.out.println("Страховая успешно добавлена.");
        try {
            DBWorker.addInsuranceCompany(insurancecompany);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean addShipbuildingCompany(String name, int id, String type_of_benefit) {
        for (Organization boat : organizations) {
            if (boat.getId() == id) {
                System.out.println("Организация с таким ID уже существует.");
                return false;
            }
        }
        ShipbuildingCompany shipbuildingcompany = new ShipbuildingCompany(name, id, type_of_benefit);
        organizations.add(shipbuildingcompany);
        System.out.println("Верфь успешно добавлен.");
        try {
            DBWorker.addShipbuildingCompany(shipbuildingcompany);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public int getCount() {
        return this.organizations.size();
    }

    public static Organization getorganization(int index) {
        return organizations.get(index);
    }
    public static Organization getorganizationByID(int index) {
        for (Organization organization : organizations) {
            if (organization.getId() == index)
                return organization;
        }
        return null;
    }
    public static Organization getOrg(int index) {
        return organizations.get(index);
    }
    public void remove(int ind) {
        Organization org = getOrg(ind);
        int ind_BD = org.getId();
        if (org instanceof AircraftFactory) {
            try {
                DBWorker.deleteAircraftFactory(ind_BD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (org instanceof InsuranceCompany) {
            try {
                DBWorker.deleteInsuranceCompany(ind_BD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (org instanceof ShipbuildingCompany) {
            try {
                DBWorker.deleteShipbuildingCompany(ind_BD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Неизвестный объект для удаления");
        }
        this.organizations.remove(ind);
    }
    public static String getNextAvailableId() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Organization organization : organizations) {
            ids.add(organization.getId());
        }
        int nextId = 0;
        while (ids.contains(nextId)) {
            nextId++;
        }
        return String.valueOf(nextId);
    }

    public static boolean searchMethod(int id) {
        for (int i = 0; i < organizations.size(); i++) {
            Organization organization = organizations.get(i);
            if (organization.getId() == id) {
                // Обменять местами объекты
                Organization firstorganization = organizations.get(0);
                organizations.set(0, organization);
                organizations.set(i, firstorganization);
                //System.out.println(organizations.get(0));
                return true; // Завершить метод после перемещения объекта
            }
        }
        return false;
    }

    public static boolean str_is_null(String str) {
        return str.isEmpty();
    }
    public static void sortList(int sort) {
        switch (sort) {
            case 1:
                //organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_AircraftFactory));
                organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_InsuranceCompany));
                break;
            case 2:
                //organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_ShipbuildingCompany));
                organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_AircraftFactory));
                break;
            case 3:
                //organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_InsuranceCompany));
                //organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_AircraftFactory));
                organizations.sort(Comparator.comparingInt(Organization::getOrganizationType_ShipbuildingCompany));
                break;
        }
    }
    public static String[] getIdCity() {
        String[] str = new String[organizations.size()];

        int i = 0;
        for (Organization org : organizations) {
            str[i] = Integer.toString(org.getId());
            i++;
        }
        return str;
    }
}