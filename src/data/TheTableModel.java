package data;
import javax.swing.table.AbstractTableModel;
public class TheTableModel extends AbstractTableModel {
    private data.Group data;
    private static int organization = -1;
    public TheTableModel(data.Group group) {
        this.data = group;
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        for (int i = 0; i < data.getCount(); i++) {
            Organization currentorganization = data.getOrg(i);
            switch (organization) {
                case 1:
                    if (currentorganization instanceof InsuranceCompany) {//InsuranceCompany
                        rowCount++;
                    }
                    break;
                case 2:
                    if (currentorganization instanceof AircraftFactory) {//AircraftFactory
                        rowCount++;
                    }
                    break;
                case 3:
                    if (currentorganization instanceof ShipbuildingCompany) {//ShipbuildingCompany
                        rowCount++;
                    }
            }
        }
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                data.getOrg(rowIndex).setName((String) aValue);
                break;
            case 1:
                Organization b1 = data.getOrg(rowIndex);
                if (b1 instanceof InsuranceCompany/*InsuranceCompany*/) {/*ShipbuildingCompany*/
                    ((InsuranceCompany) b1).setBenefit((String) aValue);
                }
                break;
            case 2:
                Organization b2 = data.getOrg(rowIndex);
                if (b2 instanceof AircraftFactory/*ShipbuildingCompany*/) {/*AircraftFactory*/
                    ((AircraftFactory) b2).setBenefit((int) aValue);
                }
                break;
            case 3:
                Organization b3 = data.getOrg(rowIndex);
                if (b3 instanceof ShipbuildingCompany/*AircraftFactory*/) {/*InsuranceCompany*/
                    ((ShipbuildingCompany) b3).setBenefit((int) aValue);
                }
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "id";
            case 2:
                return "Тип блага";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.getOrg(rowIndex).getName();
            case 1: {
                return data.getOrg(rowIndex).getId();
            }
            case 2: {
                switch (organization) {
                    case 1:
                        Organization b1 = data.getOrg(rowIndex);
                        if (b1 instanceof InsuranceCompany /*AircraftFactory*/)/*InsuranceCompany*/
                            return ((InsuranceCompany) b1).getBenefit();
                        else
                            return null;
                    case 2:
                        Organization b2 = data.getOrg(rowIndex);
                        if (b2 instanceof AircraftFactory /*ShipbuildingCompany*/)/*AircraftFactory*/
                            return ((AircraftFactory) b2).getBenefit();
                        else
                            return null;
                    case 3:
                        Organization b3 = data.getOrg(rowIndex);
                        if (b3 instanceof ShipbuildingCompany/*InsuranceCompany*/)/*ShipbuildingCompany*/
                            return ((ShipbuildingCompany) b3).getBenefit();
                        else
                            return null;
                }
            }
        }
        return "default";
    }

    public void delete(int ind) {
        this.data.remove(ind);
        fireTableDataChanged(); //Обновление таблицы после удаления
    }

    public void reload_table() {
        fireTableDataChanged();
    }
    public static void setorganization(int organizationIN) {
        organization = organizationIN;
    }
}