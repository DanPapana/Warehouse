package code.UI;
import code.logic.Facility.Warehouse;
import code.logic.Facility.Store;
import code.logic.Transport.Truck;
import code.logic.Transport.Van;
import code.logic.Transport.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;

class LoadingUnloading {

    private JPanel loadingPanel = new JPanel();
    private JTextPane facilityDescription = new FacilityManagement().getDescriptionPane();
    private JTextPane vehicleDescription = new VehicleManagement().getDescriptionPane();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Warehouse> warehouses = new ArrayList<>();
    private ArrayList<Store> stores = new ArrayList<>();
    private JComboBox vehicleList = new JComboBox<>();
    private JComboBox warehouseList = new JComboBox<>();
    private JComboBox storeList = new JComboBox<>();
    private JRadioButton loadButton = new JRadioButton("Loading");
    private JRadioButton unloadButton = new JRadioButton("Unloading");

    LoadingUnloading() {

        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));
        JPanel addPanel = new JPanel();
        JPanel loadPanel = new JPanel(new FlowLayout());

        loadButton.setSelected(true);
        storeList.setVisible(false);

        ButtonGroup loadGroup = new ButtonGroup();
        loadGroup.add(loadButton);
        loadGroup.add(unloadButton);

        loadPanel.add(loadButton);
        loadPanel.add(unloadButton);

        vehicleDescription.setPreferredSize(new Dimension(150, 135));
        facilityDescription.setPreferredSize(new Dimension(150, 135));

        vehicleList.setPreferredSize(new Dimension(100, 20));
        warehouseList.setPreferredSize(new Dimension(100, 20));
        storeList.setPreferredSize(new Dimension(100, 20));
        ((JLabel) warehouseList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) storeList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JPanel facilityList = new JPanel();
        facilityList.add(warehouseList, 2, 0);
        facilityList.add(storeList, 1, 0);

        addPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        addPanel.add(vehicleList);
        addPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        addPanel.add(facilityList);
        addPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        JButton commenceButton = new JButton("COMMENCE", new ImageIcon(MainFrame.class.getResource("/resources/dog.jpg")));
        addPanel.add(commenceButton);

        JPanel descriptionPanel = new JPanel(new FlowLayout());
        JPanel labelPanel = new JPanel(new FlowLayout());
        JPanel mainDescription = new JPanel();
        mainDescription.setLayout(new BoxLayout(mainDescription, BoxLayout.Y_AXIS));

        labelPanel.add(new JLabel("Vehicle Description:"));
        labelPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        labelPanel.add(new JLabel("Facility Description:"));

        descriptionPanel.add(vehicleDescription);
        descriptionPanel.add(Box.createRigidArea(new Dimension(60, 0)));
        descriptionPanel.add(facilityDescription);

        mainDescription.add(labelPanel);
        mainDescription.add(descriptionPanel);

        loadingPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        loadingPanel.add(loadPanel);
        loadingPanel.add(addPanel);
        loadingPanel.add(mainDescription);

        storeList.addActionListener(new addFacilityDescription());
        warehouseList.addActionListener(new addFacilityDescription());
        vehicleList.addActionListener(new addVehicleDescription());
        commenceButton.addActionListener(new commenceButtonListener());

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                storeList.setVisible(false);
                warehouseList.setVisible(true);
                warehouseList.removeAllItems();
                for (Warehouse warehouse : warehouses) {
                    warehouseList.addItem(warehouse.getName());
                }
            }
        });

        unloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                warehouseList.setVisible(false);
                storeList.setVisible(true);
                storeList.removeAllItems();
                for (Store store : stores) {
                    storeList.addItem(store.getName());
                }
            }
        });
    }

    class addFacilityDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (unloadButton.isSelected()) {
                    storeList = (JComboBox) event.getSource();
                    Store store = stores.get(storeList.getSelectedIndex());

                    facilityDescription.setText(" 😎 S T O R E 😎\n\n 👀 Name:\n" + store.getName() + "\n 👀 Country:\n" +
                            store.getLocation().getCountry() + "\n 👀 City:\n" + store.getLocation().getCity());
                } else {
                    warehouseList = (JComboBox) event.getSource();
                    Warehouse warehouse = warehouses.get(warehouseList.getSelectedIndex());

                    facilityDescription.setText(" 😎 WAREHOUSE 😎\n\n 👀 Name:\n" + warehouse.getName() + "\n 👀 Country:\n" +
                            warehouse.getLocation().getCountry() + "\n 👀 City:\n" +
                            warehouse.getLocation().getCity() + "\n 👀 Capacity:\n" +
                            warehouse.getCapacity());
                }
            } catch (Exception e) {
                facilityDescription.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
            }
        }
    }

    class addVehicleDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                    vehicleList = (JComboBox) event.getSource();
                    Vehicle vehicle = vehicles.get(vehicleList.getSelectedIndex());

                    if (vehicle instanceof Van){
                            Van van = (Van) vehicle;

                    vehicleDescription.setText("GET IN THE VAN KIDDO\n\n 👀 REGISTRATION:\n" + van.getRegistrationNumber() +
                            "\n 👀 SPEED:       " + van.getAverageSpeed() + "\n 👀 VOLUME:     " + van.getVehiclesMaxVolume() +
                            "\n 👀 CAPACITY:      " + van.getCargoAmount() + "\n 👀 MAX WEIGHT:   " + van.getMaxWeight() +
                            "\n 👀 FOOD TYPE:    " + van.getFoodType() + "    " + van.getTakenSpace() + "% FULL");
                } else {
                        Truck truck = (Truck) vehicle;
                    vehicleDescription.setText(" 😎 T R U C K 😎\n\n 👀 👀 REGISTRATION:\n" + truck.getRegistrationNumber() +
                            "\n 👀 SPEED:       " + truck.getAverageSpeed() + "\n 👀 VOLUME:     " + truck.getVehiclesMaxVolume() +
                            "\n 👀 CAPACITY:      " + truck.getCargoAmount() + "\n 👀 MAX WEIGHT:   " + truck.getMaxWeight() +
                            "\n 👀 NO OF TRAILERS:       " + truck.getNumberOfTrailers() + "   " + truck.getTakenSpace() + "% FULL");
                }
            } catch (Exception e) {
                vehicleDescription.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
            }
        }
    }

    class commenceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (vehicles.get(vehicleList.getSelectedIndex()).getCargoAmount() > 0) {

                    if (loadButton.isSelected()) {

                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Vehicle " + vehicleList.getItemAt(vehicleList.getSelectedIndex())
                                + " was loaded from Warehouse " + warehouseList.getItemAt(warehouseList.getSelectedIndex()));
                        pw.close();

                            warehouses.get(warehouseList.getSelectedIndex()).acceptVehicle(vehicles.get(vehicleList.getSelectedIndex()));
                    } else {

                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Vehicle " + vehicleList.getItemAt(vehicleList.getSelectedIndex())
                                + " was unloaded into Store " + storeList.getItemAt(storeList.getSelectedIndex()));
                        pw.close();

                            stores.get(storeList.getSelectedIndex()).acceptVehicle(vehicles.get(vehicleList.getSelectedIndex()));
                    }
                } else {
                    vehicleDescription.setText("VEHICLE IS EMPTY");

                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [WARNING] VEHICLE IS EMPTY");
                    pw.close();
                }
            } catch (Exception e) {
                vehicleDescription.setText(e.toString());
                try {
                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [WARNING] " + e);
                    pw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    void setLoading(ArrayList<Truck> trucks, ArrayList<Van> vans, ArrayList<Store> stores, ArrayList<Warehouse> warehouses) {
        this.warehouses = warehouses;
        this.stores = stores;

        vehicles.addAll(trucks);
        vehicles.addAll(vans);

        storeList.removeAllItems();
        warehouseList.removeAllItems();
        vehicleList.removeAllItems();

        for (Warehouse warehouse : warehouses) {
            warehouseList.addItem(warehouse.getName());
        }
        for (Store store : stores) {
            storeList.addItem(store.getName());
        }
        for (Truck truck : trucks) {
            vehicleList.addItem(truck.getRegistrationNumber());
        }
        for (Van van : vans) {
            vehicleList.addItem(van.getRegistrationNumber());
        }
    }

    JPanel getPanel() {
        return loadingPanel;
    }
}