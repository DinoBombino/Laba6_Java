package view;

import data.Group;
import data.GroupCity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCity{

    public AddCity(JDialog jDialog) {
        JDialog dialog = new JDialog(jDialog, "Добавление города", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(jDialog);

        // Создаем и добавляем текстовые поля для ввода
        JTextField Field_id = new JTextField();
        JTextField Field_city = new JTextField();
        //JTextField Field_arrival = new JTextField();


        JComboBox Field_city_id = new JComboBox(Group.getIdCity());
        Field_id.setText(GroupCity.getNextAvailableId());
        Field_id.setEditable(false); // Запретить редактирование текстового поля
        Field_id.setFocusable(false); // Запретить фокусировку на текстовом поле


        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID:"));
        panel.add(Field_id);
        panel.add(new JLabel("Город:"));
        panel.add(Field_city);
        /*panel.add(new JLabel("Место прибытия:"));
        panel.add(Field_arrival);*/
        panel.add(new JLabel("ID орг-и:"));
        panel.add(Field_city_id);

        // Создаем кнопки "Добавить" и "Отмена"
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String T_id = Field_id.getText();
                String T_city = Field_city.getText();
                String T_city_id;
                try {
                    T_city_id = (Field_city_id.getSelectedItem().toString());
                }
                catch (java.lang.NullPointerException ex) {
                    new ErrorDialog("Нет городов", jDialog);
                    return;
                }

                // Проверка на пустые поля
                if (GroupCity.str_is_null(T_id) || GroupCity.str_is_null(T_city) || GroupCity.str_is_null(T_city_id)) {
                    new ErrorDialog("Пожалуйста, заполните все данные.", jDialog);
                    return;
                }
                int I_id;
                String I_city_id;
                try {
                    I_id = Integer.parseInt(T_id);
                } catch (NumberFormatException ex) {
                    new ErrorDialog("Неверно введены численные данные.", dialog);
                    return;
                }

                // Проверка на уникальность id
                if (!data.GroupCity.addCity(I_id, T_city, Integer.parseInt(T_city_id))) {
                    System.out.println(I_id);
                    new ErrorDialog("Город с таким id существует.", jDialog);
                    return;
                }

                // Закрываем диалоговое окно после добавления
                dialog.dispose();
            }

        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Закрываем диалоговое окно без добавления
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Добавляем панель с полями ввода и панель с кнопками на диалоговое окно
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Устанавливаем диалоговое окно видимым
        dialog.setVisible(true);
    }
}
