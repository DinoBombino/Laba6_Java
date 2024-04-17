package data;

import DB.DBWorker;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class GroupCity {
    private static ArrayList<City> cities = new ArrayList<>();

    public GroupCity() {
        try {
            cities.addAll(DBWorker.getAllCity());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addCity(int id, String town, int boat_id) {
        // Проверка наличия корабля с таким же id в списке
        for (City city : cities) {
            if (city.getId() == id) {
                System.out.println("Город с таким ID уже существует.");
                return false; // Завершаем метод, так как корабль уже существует
            }
        }
        City city = new City(id, town, boat_id);
        cities.add(city);
        System.out.println("Город успешно добавлен.");
        try {
            DBWorker.addCity(city);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean deleteCity(int id) {
        int indexToRemove = -1; // Инициализируем переменную для хранения индекса элемента для удаления
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getOrg_id() == id) {
                indexToRemove = i; // Нашли индекс элемента для удаления
                break;
            }
        }
        if (indexToRemove != -1) { // Если был найден элемент для удаления
            cities.remove(indexToRemove); // Удаляем элемент по найденному индексу
            return true;
        } else {
            return false;
        }
    }

    public int getCount() {
        return this.cities.size();
    }

    public static City getCity(int index) {
        return cities.get(index);
    }

    public void remove(int ind) throws SQLException {
        City city = getCity(ind);
        int ind_BD = city.getId();
        DBWorker.deleteCity(ind_BD);
        this.cities.remove(ind);
    }

    public static String getNextAvailableId() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (City city : cities) {
            ids.add(city.getId());
        }
        int nextId = 0;
        while (ids.contains(nextId)) {
            nextId++;
        }
        return String.valueOf(nextId);
    }

    public static boolean str_is_null(String str) {
        return str.isEmpty();
    }
}
