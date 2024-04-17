package DB;

import data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "company_database.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection conn;

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    public static void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Драйвер: " + meta.getDriverName());
                //createDB();
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения к БД: " + ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AircraftFactory> getAircraftFactory() throws SQLException{
        initDB();
        Statement statement = conn.createStatement();
        List<AircraftFactory> list = new ArrayList<AircraftFactory>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM aircraftfactory");
        while (resultSet.next()){
            list.add(new AircraftFactory(resultSet.getString("name"), resultSet.getInt("id"),
                resultSet.getString("type_of_benefit")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }

    public static List<InsuranceCompany> getInsuranceCompany() throws SQLException{
        initDB();
        Statement statement = conn.createStatement();
        List<InsuranceCompany> list = new ArrayList<InsuranceCompany>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM insurancecompany");
        while (resultSet.next()){
            list.add(new InsuranceCompany(resultSet.getString("name"), resultSet.getInt("id"),
                    resultSet.getString("benefit")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }

    public static List<ShipbuildingCompany> getShipbuildingCompany() throws SQLException{
        initDB();
        Statement statement = conn.createStatement();
        List<ShipbuildingCompany> list = new ArrayList<ShipbuildingCompany>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM shipbuildcompany");
        while (resultSet.next()){
            list.add(new ShipbuildingCompany(resultSet.getString("name"), resultSet.getInt("id"),
                    resultSet.getString("type_of_benefit")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }

    public static void deleteAircraftFactory(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM aircraftfactory WHERE aircraftfactory.id ="+id);
        statement.close();

        //Удаление рейсов, в которых участвовал удаляемый корабль
        while (GroupCity.deleteCity(id));
        Statement statement2 = conn.createStatement();
        statement2.execute("DELETE FROM city WHERE city.org_id ="+id);
        statement2.close();
        closeConnection();
    }

    public static void deleteInsuranceCompany(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM insurancecompany WHERE insurancecompany.id ="+id);
        statement.close();

        //Удаление рейсов, в которых участвовал удаляемый корабль
        while (GroupCity.deleteCity(id));
        Statement statement2 = conn.createStatement();
        statement2.execute("DELETE FROM city WHERE city.org_id ="+id);
        statement2.close();
        closeConnection();
    }

    public static void deleteShipbuildingCompany(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM shipbuildingcompany WHERE shipbuildingcompany.id ="+id);
        statement.close();

        //Удаление рейсов, в которых участвовал удаляемый корабль
        while (GroupCity.deleteCity(id));
        Statement statement2 = conn.createStatement();
        statement2.execute("DELETE FROM city WHERE city.org_id ="+id);
        statement2.close();
        closeConnection();
    }

    public static void addAircraftFactory(AircraftFactory aircraftfactory) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO aircraftfactory('id','name','type_of_benefit') " +
                        "VALUES(?,?,?)");
        statement.setObject(1, aircraftfactory.getId());
        statement.setObject(2, aircraftfactory.getName());
        statement.setObject(3, aircraftfactory.getBenefit());
        statement.execute();
        statement.close();
        closeConnection();
    }

    public static void addInsuranceCompany(InsuranceCompany insurancecompany) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO insurancecompany('id','name','benefit') " +
                        "VALUES(?,?,?)");
        statement.setObject(1, insurancecompany.getId());
        statement.setObject(2, insurancecompany.getName());
        statement.setObject(3, insurancecompany.getBenefit());
        statement.execute();
        statement.close();
        closeConnection();
    }

    public static void addShipbuildingCompany(ShipbuildingCompany shipbuildingcompany) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO shipbuildcompany('id','name','type_of_benefit') " +
                        "VALUES(?,?,?)");
        statement.setObject(1, shipbuildingcompany.getId());
        statement.setObject(2, shipbuildingcompany.getName());
        statement.setObject(3, shipbuildingcompany.getBenefit());
        statement.execute();
        statement.close();
        closeConnection();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<City> getAllCity() throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        List<City> list = new ArrayList<City>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM city");
        while (resultSet.next()) {
            list.add(new City(resultSet.getInt("id"), resultSet.getString("city"),
                    resultSet.getInt("org_id")));
        }
        resultSet.close();
        statement.close();
        closeConnection();
        return list;
    }
    public static void deleteCity(int id) throws SQLException {
        initDB();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM city WHERE city.id ="+id);
        closeConnection();
        statement.close();
    }
    public static void addCity(City city) throws SQLException {
        initDB();
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO city('id','city', 'org_id') " +
                        "VALUES(?,?,?)");
        statement.setObject(1, city.getId());
        statement.setObject(2, city.getCity());
        statement.setObject(3, city.getOrg_id());
        statement.execute();
        statement.close();
        closeConnection();
    }
}
