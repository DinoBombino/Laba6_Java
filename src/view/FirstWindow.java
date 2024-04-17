package view;

import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame {

    TheTableModel myTableModel;
    TheTableModelCity myTableModelCity;
    //Кнопочки
    JButton button_insurancecompany;
    JButton button_shipbuildingcompany;
    JButton button_aircraftfactory;
    JButton button_search;
    JButton button_city;
    public FirstWindow() {
        super("Главная страница");
        setSize(800, 200); //Размер
        //pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Закрытие окна
        this.setLocationRelativeTo(null); //Окно посередине
        init();
    }

    public void init() {
        // Инициализируем модель таблицы. Дублировались строки
        this.myTableModel = new TheTableModel(new Group());
        this.myTableModelCity = new TheTableModelCity(new GroupCity());
        // Создаем панель с кнопками
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Отступы между кнопками

        // Создаем первую кнопку
        this.button_insurancecompany/*insurancecompany*/ = new JButton("Страховая компания");
        this.button_insurancecompany.setBackground(Color.GRAY);
        this.button_insurancecompany.setForeground(Color.WHITE);
        this.button_insurancecompany.setPreferredSize(new Dimension(200, 30));// Устанавливаем размер кнопки
        buttonPanel.add(button_insurancecompany, gbc);


        // Создаем вторую кнопку
        this.button_shipbuildingcompany/*shipbuildingcompany*/ = new JButton("Судостроительная компания");
        this.button_shipbuildingcompany.setBackground(Color.GRAY);
        this.button_shipbuildingcompany.setForeground(Color.WHITE);
        this.button_shipbuildingcompany.setPreferredSize(new Dimension(300, 30));// Устанавливаем размер кнопки
        gbc.gridx = 1; // Перемещаемся на следующий столбец
        buttonPanel.add(button_shipbuildingcompany, gbc);

        // Создаем третью кнопку
        this.button_aircraftfactory/*aircraftfactory*/ = new JButton("Авиазавод");
        this.button_aircraftfactory.setBackground(Color.GRAY);
        this.button_aircraftfactory.setForeground(Color.WHITE);
        this.button_aircraftfactory.setPreferredSize(new Dimension(200, 30));// Устанавливаем размер кнопки
        gbc.gridx = 2; // Перемещаемся на следующий столбец
        buttonPanel.add(button_aircraftfactory, gbc);

        // Переходим на следующую строку
        gbc.gridy = 1;
        gbc.gridx = 1; // Возвращаемся на первый столбец

        // Создаем четвертую кнопку
        this.button_search = new JButton("Поиск по номеру ID");
        this.button_search.setBackground(Color.GRAY);
        this.button_search.setForeground(Color.WHITE);
        this.button_search.setPreferredSize(new Dimension(200, 30));// Устанавливаем размер кнопки
        gbc.gridwidth = 1; // Занимаем три столбца
        buttonPanel.add(button_search, gbc);

        // Переходим на следующую строку
        gbc.gridy = 2;
        gbc.gridx = 1; // Возвращаемся на первый столбец

        this.button_city = new JButton("City");
        this.button_city.setBackground(Color.GRAY);
        this.button_city.setForeground(Color.WHITE);
        this.button_city.setPreferredSize(new Dimension(300, 30));// Устанавливаем размер кнопки
        gbc.gridx = 1; // Перемещаемся на следующий столбец
        buttonPanel.add(button_city, gbc);

        // Добавляем панель с кнопками в центр окна
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.CENTER);

        // Добавляем слушателей
        addListeners();

        // Устанавливаем видимость окна
        setVisible(true);
    }

    public void addListeners() {
        //Добавим слушателей на кнопки
        button_insurancecompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insurancecompany();
            }
        });
        button_shipbuildingcompany.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shipbuildingcompany();
            }
        });
        button_aircraftfactory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aircraftfactory();
            }
        });

        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search_method();
            }
        });
        button_city.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                city();
            }
        });
    }
    /*public void insurancecompany() {
        myTableModel.setorganization(3);
        Group.sortList(3);
        new WindowInsuranceCompany(this, myTableModel);
    }
    public void shipbuildingcompany() {
        myTableModel.setorganization(2);
        Group.sortList(2);
        new WindowShipbuildingCompany(this, myTableModel);
    }
    public void aircraftfactory() {
        myTableModel.setorganization(1);
        Group.sortList(1);
        new WindowAircraftFactory(this, myTableModel);
    }*/
    public void insurancecompany() {
        myTableModel.setorganization(1);
        Group.sortList(1);
        new WindowInsuranceCompany(this, myTableModel);
    }
    public void shipbuildingcompany() {
        myTableModel.setorganization(3);
        Group.sortList(3);
        new WindowShipbuildingCompany(this, myTableModel);
    }
    public void aircraftfactory() {
        myTableModel.setorganization(2);
        Group.sortList(2);
        new WindowAircraftFactory(this, myTableModel);
    }
    public void search_method() {
        new WindowZSearch();    //Z затем чтобы внизу списка было
    }
    public void city() {
        System.out.println(myTableModelCity);
        new WindowCity(this, myTableModelCity);
    }
}