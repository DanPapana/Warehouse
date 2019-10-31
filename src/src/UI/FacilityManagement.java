package src.UI;
import src.logic.Facility.Store;
import src.logic.Facility.Warehouse;
import src.logic.Location;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.awt.Color.CYAN;
import static java.awt.Color.WHITE;

class FacilityManagement {

    private JButton createButton = new JButton("CREATE", new ImageIcon(MainFrame.class.getResource("/resources/dog.jpg")));
    private JButton deleteButton = new JButton("DELETE", new ImageIcon(MainFrame.class.getResource("/resources/delete.jpg")));
    private JButton editButton = new JButton("EDIT", new ImageIcon(MainFrame.class.getResource("/resources/edit.jpg")));
    private JPanel businessPanel = new JPanel();
    private JRadioButton storeButton = new JRadioButton("         Store");
    private JRadioButton warehouseButton = new JRadioButton("Warehouse");
    private JTextField countryText = new JTextField(15);
    private JTextField cityText = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextField capacityText = new JTextField();
    private JTextPane descriptionPane = new JTextPane();
    private JComboBox warehouseList = new JComboBox<>();
    private JComboBox storeList = new JComboBox<>();
    private ArrayList<Store> stores = new ArrayList<>();
    private ArrayList<Warehouse> warehouses = new ArrayList<>();

    FacilityManagement() {

        JPanel radioPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        storeButton.setSelected(true);

        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        ButtonGroup group = new ButtonGroup();
        group.add(storeButton);
        group.add(warehouseButton);
        radioPanel.add(storeButton);
        radioPanel.add(warehouseButton);

        JPanel facilityList = new JPanel();
        facilityList.add(warehouseList, 2, 0);
        facilityList.add(storeList, 1, 0);
        warehouseList.setPreferredSize(new Dimension(140, 20));
        storeList.setPreferredSize(new Dimension(140, 20));
        ((JLabel) warehouseList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) storeList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        radioPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        radioPanel.add(facilityList);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        inputPanel.add(new JLabel("Name"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(nameText);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Country"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(countryText);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("City"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(cityText);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Capacity"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(capacityText);
        capacityText.setEnabled(false);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(editButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(deleteButton);

        buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descriptionLabel = new JLabel("Item details:");
        descriptionPane.setEditable(false);
        descriptionPane.setPreferredSize(new Dimension(150, 130));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(descriptionLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(descriptionPane);

        businessPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        businessPanel.add(radioPanel);
        businessPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        businessPanel.add(inputPanel);
        businessPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        businessPanel.add(buttonPanel);

        storeButton.setHorizontalTextPosition(SwingConstants.LEFT);
        warehouseButton.setHorizontalTextPosition(SwingConstants.LEFT);
        storeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        warehouseButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        warehouseList.setVisible(false);
        descriptionPane.setBackground(Color.getHSBColor(0, 0, 97));
        createButton.addActionListener(new createButtonListener());
        warehouseList.addActionListener(new addItemDescription());
        storeList.addActionListener(new addItemDescription());

        deleteButton.addActionListener(new deleteButtonListener());
        editButton.addActionListener(new editButtonListener());

        capacityText.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                if (capacityText.getText().length() >= 4)
                    e.consume();
            }

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(capacityText.getText());
                } catch (Exception e) {
                    capacityText.setText(capacityText.getText().substring(0, capacityText.getText().length() - 1));
                }
            }
        });

        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                capacityText.setEnabled(false);
                storeList.setVisible(true);
                warehouseList.setVisible(false);
            }
        });

        warehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                capacityText.setEnabled(true);
                storeList.setVisible(false);
                warehouseList.setVisible(true);
            }
        });
    }

    class addItemDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (storeButton.isSelected()) {

                    storeList = (JComboBox) event.getSource();
                    Store store = stores.get(storeList.getSelectedIndex());

                    descriptionPane.setText(" 😎 S T O R E 😎\n\n 👀 Name:\n" + store.getName() + "\n 👀 Country:\n" +
                            store.getLocation().getCountry() + "\n 👀 City:\n" + store.getLocation().getCity());
                } else {

                    warehouseList = (JComboBox) event.getSource();
                    Warehouse warehouse = warehouses.get(warehouseList.getSelectedIndex());

                    descriptionPane.setText(" 😎 WAREHOUSE 😎\n\n 👀 Name:\n" + warehouse.getName() + "\n 👀 Country:\n" +
                            warehouse.getLocation().getCountry() + "\n 👀 City:\n" +
                            warehouse.getLocation().getCity() + "\n 👀 Capacity:\n" + warehouse.getCapacity());
                }
            } catch (Exception e) {
                descriptionPane.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
            }
        }
    }

    class editButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if (editButton.getText().equals("EDIT")) {

                warehouseList.setEnabled(false);
                storeList.setEnabled(false);
                storeButton.setEnabled(false);
                warehouseButton.setEnabled(false);
                descriptionPane.setText("\n\n\n\n            🔧  EDITING  🔧");
                createButton.setEnabled(false);
                deleteButton.setEnabled(false);
                editButton.setText("SAVE");
                nameText.setBackground(CYAN);
                countryText.setBackground(CYAN);
                cityText.setBackground(CYAN);

                try {
                    if (storeButton.isSelected()) {

                        int index = storeList.getSelectedIndex();

                        nameText.setText(getStores().get(index).getName());
                        countryText.setText(getStores().get(index).getLocation().getCountry());
                        cityText.setText(getStores().get(index).getLocation().getCity());
                        stores.remove(index);
                        storeList.removeItemAt(index);

                    } else {

                        capacityText.setBackground(CYAN);
                        int index = warehouseList.getSelectedIndex();

                        nameText.setText(getWarehouses().get(index).getName());
                        countryText.setText(getWarehouses().get(index).getLocation().getCountry());
                        cityText.setText(getWarehouses().get(index).getLocation().getCity());
                        capacityText.setText(String.valueOf(getWarehouses().get(index).getCapacity()));
                        warehouses.remove(index);
                        warehouseList.removeItemAt(index);

                    }
                } catch (Exception e) {
                    descriptionPane.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
                }

            } else {

                warehouseList.setEnabled(true);
                storeList.setEnabled(true);

                storeButton.setEnabled(true);
                warehouseButton.setEnabled(true);

                editButton.setText("EDIT");
                createButton.setEnabled(true);
                deleteButton.setEnabled(true);

                createButton.doClick();

                nameText.setBackground(WHITE);
                countryText.setBackground(WHITE);
                cityText.setBackground(WHITE);
                if (warehouseButton.isSelected()) {
                    capacityText.setBackground(WHITE);
                }
            }
        }
    }

    class deleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (storeButton.isSelected()) {

                    stores.remove(storeList.getSelectedIndex());

                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Store " +
                                storeList.getItemAt(storeList.getSelectedIndex()) + " is deleted");
                        pw.close();

                    storeList.removeItemAt(storeList.getSelectedIndex());
                    if (storeList.getSelectedIndex() > storeList.getItemCount()) {
                        storeList.setSelectedIndex(storeList.getSelectedIndex() + 1);
                    }
                    if (storeList.getItemCount() < 1) {
                        storeList.removeAllItems();
                    }
                } else {

                    warehouses.remove(warehouseList.getSelectedIndex());


                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Warehouse " +
                                storeList.getItemAt(storeList.getSelectedIndex()) + " is deleted");
                        pw.close();

                    warehouseList.removeItemAt(warehouseList.getSelectedIndex());
                    if (warehouseList.getSelectedIndex() > warehouseList.getItemCount()) {
                        warehouseList.setSelectedIndex(warehouseList.getSelectedIndex() + 1);
                    }
                    if (warehouseList.getItemCount() < 1) {
                        warehouseList.removeAllItems();
                    }
                }
            } catch (Exception e) {
                descriptionPane.setText(" 🔪🔪🔪  YOU STOP THAT RIGHT THERE YOU CRIMINAL SCUM  🔪🔪🔪");

                try {
                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [WARNING] YOU CAN'T DELETE THAT");
                    pw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    class createButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (!countryText.getText().isEmpty() && (!cityText.getText().isEmpty())
                    && (!nameText.getText().isEmpty())) {

                if (warehouseButton.isSelected() && (!capacityText.getText().isEmpty()) &&
                        MainFrame.isInteger(capacityText.getText())) {

                    Location new_location = new Location(countryText.getText(), cityText.getText());
                    warehouses.add(new Warehouse(nameText.getText(), new_location,
                            Integer.parseInt(capacityText.getText())));

                    warehouseList.addItem(nameText.getText());
                    warehouseList.setSelectedIndex(warehouseList.getItemCount() - 1);

                    try {
                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Warehouse " + nameText.getText() + " created");
                        pw.close();
                    } catch (IOException ignored) {
                    }

                } else {

                    Location new_location = new Location(countryText.getText(), cityText.getText());
                    stores.add(new Store(nameText.getText(), new_location));

                    storeList.addItem(nameText.getText());
                    storeList.setSelectedIndex(storeList.getItemCount() - 1);

                    try {
                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Store " + nameText.getText() + " created");
                        pw.close();
                    } catch (IOException ignored) {
                    }
                }
            } else {
                descriptionPane.setText("    🤷‍ 💉 🤷 EMPTY 🤷‍ 💉 🤷‍");

                try {
                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [WARNING] SOME OF THE FIELDS ARE EMPTY");
                    pw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    JTextPane getDescriptionPane() {
        return descriptionPane;
    }

    ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    ArrayList<Store> getStores() {
        return stores;
    }

    void setWarehouses(ArrayList<Warehouse> warehouses) {
        this.warehouses = warehouses;
        warehouseList.removeAllItems();
        for (Warehouse warehouse : warehouses) {
            warehouseList.addItem(warehouse.getName());
        }
    }

    void setStores(ArrayList<Store> stores) {
        this.stores = stores;
        storeList.removeAllItems();
        for (Store store : stores) {
            storeList.addItem(store.getName());
        }
    }

    JPanel getPanel() {
        return businessPanel;
    }
}
