import DB.DBWorker;
import view.FirstWindow;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBWorker.initDB();
        FirstWindow window = new FirstWindow();
    }
}

//organization, insurance company, shipbuilding company, aircraft factory;
//организация, страховая компания, судостроительная компания, авиазавод;