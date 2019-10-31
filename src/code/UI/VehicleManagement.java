package code.UI;
import code.logic.Food.FoodItemType;
import code.logic.Transport.Truck;
import code.logic.Transport.Van;

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

import static java.awt.Color.*;

class VehicleManagement {

    private JPanel vehiclePanel = new JPanel();
    private JButton createButton = new JButton("CREATE", new ImageIcon(MainFrame.class.getResource("/resources/dog.jpg")));
    private JButton editButton = new JButton("EDIT", new ImageIcon(MainFrame.class.getResource("/resources/edit.jpg")));
    private JButton deleteButton = new JButton("DELETE", new ImageIcon(MainFrame.class.getResource("/resources/delete.jpg")));
    private JRadioButton freshButton = new JRadioButton("Fresh");
    private JRadioButton frozenButton = new JRadioButton("Frozen");
    private JRadioButton vanButton = new JRadioButton("Van");
    private JRadioButton truckButton = new JRadioButton("Truck");
    private JTextPane descriptionPane = new JTextPane();
    private JComboBox vanList = new JComboBox<>();
    private JComboBox truckList = new JComboBox<>();
    private JTextField registeredText = new JTextField(10);
    private JTextField speedText = new JTextField(10);
    private JTextField volumeText = new JTextField(10);
    private JTextField capacityText = new JTextField(10);
    private JTextField trailerText = new JTextField(10);
    private JTextField weightText = new JTextField(10);
    private ArrayList<Van> vans = new ArrayList<>();
    private ArrayList<Truck> trucks = new ArrayList<>();


    VehicleManagement() {

        JPanel radioPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel freshRadioPanel = new JPanel();
        JPanel OKRadio = new JPanel();

        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        freshRadioPanel.setLayout(new BoxLayout(freshRadioPanel, BoxLayout.Y_AXIS));
        OKRadio.setLayout(new BoxLayout(OKRadio, BoxLayout.Y_AXIS));

        freshButton.setSelected(true);
        vanButton.setSelected(true);

        ButtonGroup freshGroup = new ButtonGroup();
        freshGroup.add(freshButton);
        freshGroup.add(frozenButton);
        freshRadioPanel.add(freshButton);
        freshRadioPanel.add(frozenButton);

        ButtonGroup vehicleGroup = new ButtonGroup();
        vehicleGroup.add(vanButton);
        vehicleGroup.add(truckButton);
        radioPanel.add(vanButton);
        radioPanel.add(truckButton);

        JPanel vehicleList = new JPanel();
        vehicleList.add(truckList, 2, 0);
        vehicleList.add(vanList, 1, 0);
        truckList.setPreferredSize(new Dimension(140, 20));
        vanList.setPreferredSize(new Dimension(140, 20));
        ((JLabel) truckList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel) vanList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        radioPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        radioPanel.add(vehicleList);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        inputPanel.add(new JLabel("Registration"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(registeredText);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Speed"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(speedText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Volume"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(volumeText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Capacity"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(capacityText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Maximum Weight"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(weightText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        inputPanel.add(new JLabel("Number of Trailers"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(trailerText);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(editButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(deleteButton);

        descriptionPane.setEditable(false);
        descriptionPane.setPreferredSize(new Dimension(150, 120));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(descriptionPane);

        OKRadio.add(radioPanel);
        OKRadio.add(Box.createRigidArea(new Dimension(0, 20)));
        OKRadio.add(freshRadioPanel);
        OKRadio.add(Box.createRigidArea(new Dimension(0, 20)));
        OKRadio.add(vehicleList);

        buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        vanButton.setHorizontalTextPosition(SwingConstants.LEFT);
        truckButton.setHorizontalTextPosition(SwingConstants.LEFT);
        vanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        truckButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        vehiclePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        vehiclePanel.add(OKRadio);
        vehiclePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        vehiclePanel.add(inputPanel);
        vehiclePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        vehiclePanel.add(buttonPanel);

        trailerText.setBackground(null);
        trailerText.setEnabled(false);
        truckList.setVisible(false);
        descriptionPane.setBackground(Color.getHSBColor(0, 0, 97));

        speedText.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(speedText.getText());
                } catch (Exception e) {
                    speedText.setText(speedText.getText().substring(0, speedText.getText ().length() - 1));
                }
            }
        });

        volumeText.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(volumeText.getText());
                } catch (Exception e) {
                    volumeText.setText(volumeText.getText().substring(0, volumeText.getText ().length() - 1));
                }
            }
        });

        capacityText.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(capacityText.getText());
                } catch (Exception e) {
                    capacityText.setText(capacityText.getText().substring(0, capacityText.getText ().length() - 1));
                }
            }
        });

        weightText.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(weightText.getText());
                } catch (Exception e) {
                    weightText.setText(weightText.getText().substring(0, weightText.getText ().length() - 1));
                }
            }
        });

        trailerText.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(trailerText.getText());
                } catch (Exception e) {
                    trailerText.setText(trailerText.getText().substring(0, trailerText.getText ().length() - 1));
                }
            }
        });

        vanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                trailerText.setBackground(null);
                trailerText.setEnabled(false);
                vanList.setVisible(true);
                truckList.setVisible(false);
                freshButton.setEnabled(true);
                frozenButton.setEnabled(true);
            }
        });

        truckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                trailerText.setBackground(WHITE);
                trailerText.setEnabled(true);
                vanList.setVisible(false);
                truckList.setVisible(true);
                freshButton.setEnabled(false);
                frozenButton.setEnabled(false);
            }
        });

        truckList.addActionListener(new addItemDescription());
        vanList.addActionListener(new addItemDescription());
        createButton.addActionListener(new createButtonListener());
        editButton.addActionListener(new editButtonListener());
        deleteButton.addActionListener(new IndependentVehicleDelete(vanButton, vanList, truckList, descriptionPane));
    }


    class addItemDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (vanButton.isSelected()) {
                    vanList = (JComboBox) event.getSource();
                    Van van = vans.get(vanList.getSelectedIndex());

                    descriptionPane.setText("GET IN THE VAN KIDDO\n\n 👀 REGISTRATION:\n" + van.getRegistrationNumber() +
                            "\n 👀 SPEED:       " + van.getAverageSpeed() + "\n 👀 VOLUME:     " + van.getVehiclesMaxVolume() +
                            "\n 👀 CAPACITY:      " + van.getCargoAmount() + "\n 👀 MAX WEIGHT:   " + van.getMaxWeight() +
                            "\n 👀 FOOD TYPE:    " + van.getFoodType());
                } else {
                    truckList = (JComboBox) event.getSource();
                    Truck truck = trucks.get(truckList.getSelectedIndex());

                    descriptionPane.setText(" 😎 T R U C K 😎\n\n 👀 👀 REGISTRATION:\n" + truck.getRegistrationNumber() +
                            "\n 👀 SPEED:       " + truck.getAverageSpeed() + "\n 👀 VOLUME:     " + truck.getVehiclesMaxVolume() +
                            "\n 👀 CAPACITY:      " + truck.getCargoAmount() + "\n 👀 MAX WEIGHT:   " + truck.getMaxWeight() +
                            "\n 👀 NO OF TRAILERS:       " + truck.getNumberOfTrailers());
                }
            } catch (Exception e) {
                descriptionPane.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");

                try {
                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [WARNING] Some of the fields are empty");
                    pw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    class editButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if (editButton.getText().equals("EDIT")) {

                truckList.setEnabled(false);
                vanList.setEnabled(false);

                vanButton.setEnabled(false);
                truckButton.setEnabled(false);
                descriptionPane.setText("\n\n\n\n            🔧  EDITING  🔧");
                createButton.setEnabled(false);
                deleteButton.setEnabled(false);
                editButton.setText("SAVE");

                registeredText.setBackground(CYAN);
                speedText.setBackground(CYAN);
                volumeText.setBackground(CYAN);
                capacityText.setBackground(CYAN);
                weightText.setBackground(CYAN);

                try {
                    if (vanButton.isSelected()) {
                        int index = vanList.getSelectedIndex();

                        registeredText.setText(getVans().get(index).getRegistrationNumber());
                        speedText.setText(String.valueOf(getVans().get(index).getAverageSpeed()));
                        volumeText.setText(String.valueOf(getVans().get(index).getVolume()));
                        capacityText.setText(String.valueOf(getVans().get(index).getCargoAmount()));
                        weightText.setText(String.valueOf(getVans().get(index).getMaxWeight()));

                        if(getVans().get(index).getFoodType().equals(FoodItemType.FRESH)) {
                            freshButton.setSelected(true);
                            freshButton.setBackground(CYAN);
                        } else {
                            frozenButton.setSelected(true);
                            frozenButton.setBackground(CYAN);
                        }

                        vans.remove(index);
                        vanList.removeItemAt(index);

                    } else {

                        freshButton.setEnabled(false);
                        frozenButton.setEnabled(false);

                        trailerText.setBackground(CYAN);
                        int index = truckList.getSelectedIndex();

                        registeredText.setText(getTrucks().get(index).getRegistrationNumber());
                        speedText.setText(String.valueOf(getTrucks().get(index).getAverageSpeed()));
                        volumeText.setText(String.valueOf(getTrucks().get(index).getVolume()));
                        capacityText.setText(String.valueOf(getTrucks().get(index).getCargoAmount()));
                        weightText.setText(String.valueOf(getTrucks().get(index).getMaxWeight()));
                        trailerText.setText(String.valueOf(getTrucks().get(index).getNumberOfTrailers()));

                        trucks.remove(index);
                        truckList.removeItemAt(index);
                    }
                } catch (Exception e) {
                    descriptionPane.setText("\n\n\n\n   🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
                    try {
                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [WARNING] Some of the fields are empty");
                        pw.close();
                    } catch (IOException ignored) {
                    }
                }

            } else {

                truckList.setEnabled(true);
                vanList.setEnabled(true);

                vanButton.setEnabled(true);
                truckButton.setEnabled(true);

                editButton.setText("EDIT");
                createButton.setEnabled(true);
                deleteButton.setEnabled(true);

                registeredText.setBackground(WHITE);
                speedText.setBackground(WHITE);
                volumeText.setBackground(WHITE);
                capacityText.setBackground(WHITE);
                weightText.setBackground(WHITE);

                if (truckButton.isSelected()) {
                    trailerText.setBackground(WHITE);
                } else {
                    freshButton.setBackground(null);
                    frozenButton.setBackground(null);
                    trailerText.setBackground(null);
                }
                createButton.doClick();
            }
        }
    }

    class createButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (!registeredText.getText().isEmpty() && (!speedText.getText().isEmpty()) && (!volumeText.getText().isEmpty())
                    && (!capacityText.getText().isEmpty()) && MainFrame.isInteger(capacityText.getText())
                    && MainFrame.isFloat(speedText.getText()) && MainFrame.isInteger(volumeText.getText())) {

                if (truckButton.isSelected() && MainFrame.isInteger(trailerText.getText())
                        && (!trailerText.getText().isEmpty())) {

                    trucks.add(new Truck(registeredText.getText(), Float.parseFloat(speedText.getText()),
                            Integer.parseInt(volumeText.getText()), Integer.parseInt(capacityText.getText()),
                            Integer.parseInt(weightText.getText()), Integer.parseInt(trailerText.getText())));

                    truckList.addItem(registeredText.getText());
                    truckList.setSelectedIndex(truckList.getItemCount() - 1);

                    try {
                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Truck " + registeredText.getText() + " created");
                        pw.close();
                    } catch (IOException ignored) {
                    }

                } else if (vanButton.isSelected()) {

                    Van new_van = new Van(registeredText.getText(), Float.parseFloat(speedText.getText()),
                            Integer.parseInt(volumeText.getText()), Integer.parseInt(capacityText.getText()),
                            Integer.parseInt(weightText.getText()));

                    if (freshButton.isSelected()) {
                        new_van.setFoodType(FoodItemType.FRESH);
                    } else {
                        new_van.setFoodType(FoodItemType.FROZEN);
                    }

                    vans.add(new_van);

                    vanList.addItem(registeredText.getText());
                    vanList.setSelectedIndex(vanList.getItemCount() - 1);

                    try {
                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Van " + registeredText.getText() + " created");
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

                    pw.println(LocalTime.now() + " [WARNING] Some of the fields are empty in the Vehicle class");
                    pw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    ArrayList<Van> getVans() {
        return vans;
    }

    ArrayList<Truck> getTrucks() {
        return trucks;
    }

    void setVans(ArrayList<Van> vans) {
        this.vans = vans;
        vanList.removeAllItems();
        for (Van van : vans) {
            vanList.addItem(van.getRegistrationNumber());
        }
    }

    void setTrucks(ArrayList<Truck> trucks) {
        this.trucks = trucks;
        truckList.removeAllItems();
        for (Truck truck : trucks) {
            truckList.addItem(truck.getRegistrationNumber());
        }
    }

    JTextPane getDescriptionPane() {
        return descriptionPane;
    }

    JPanel getPanel() {
        return vehiclePanel;
    }
}
