package data;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;

public class TheTableModelCity extends AbstractTableModel {
    private  data.GroupCity data;

    public TheTableModelCity(data.GroupCity group){
        this.data =group;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        for (int i = 0; i < data.getCount(); i++) {
            rowCount++;
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        City city = data.getCity(rowIndex);
        switch (columnIndex) {
            case 0:
                return city.getId();
            case 1:
                return city.getCity();
            case 2:
                return city.getOrg_id();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Город";
            case 2:
                return "ID компании";
        }
        return "";
    }

    public void reload_table() {
        fireTableDataChanged();
    }
    public void delete(int ind) throws SQLException {
        this.data.remove(ind);
        fireTableDataChanged(); //Обновление таблицы после удаления
    }
}
