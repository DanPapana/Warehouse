package code.UI;
import code.logic.Facility.Warehouse;
import code.logic.Food.FoodItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;

class FoodToWarehouse {

    private JPanel addFoodPanel = new JPanel();
    private JTextPane foodDescription = new FoodItemsManagement().getDescriptionPane();
    private JTextPane warehouseDescription = new FacilityManagement().getDescriptionPane();
    private JComboBox warehouseList = new JComboBox();
    private JComboBox foodList = new JComboBox();
    private ArrayList<Warehouse> warehouses = new ArrayList<>();
    private ArrayList<FoodItem> snacks = new ArrayList<>();

    FoodToWarehouse() {

        addFoodPanel.setLayout(new BoxLayout(addFoodPanel, BoxLayout.Y_AXIS));
        JPanel addPanel = new JPanel(new FlowLayout());

        addPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        addPanel.add(foodList);
        addPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        addPanel.add(warehouseList);
        addPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        JButton foodButton = new JButton("COMMENCE", new ImageIcon(MainFrame.class.getResource("/resources/dog.jpg")));
        addPanel.add(foodButton);

        foodList.setPreferredSize(new Dimension(100, 20));
        warehouseList.setPreferredSize(new Dimension(100, 20));

        JPanel description = new JPanel(new FlowLayout());
        JPanel labelPanel = new JPanel(new FlowLayout());
        JPanel mainDescription = new JPanel();
        mainDescription.setLayout(new BoxLayout(mainDescription, BoxLayout.Y_AXIS));

        labelPanel.add(new JLabel("Food Description:"), BorderLayout.EAST);
        labelPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        labelPanel.add(new JLabel("Warehouse Description:"), BorderLayout.WEST);

        foodDescription.setPreferredSize(new Dimension(150, 130));
        description.add(foodDescription);
        description.add(Box.createRigidArea(new Dimension(50, 0)));
        description.add(warehouseDescription);

        mainDescription.add(labelPanel);
        mainDescription.add(description);
        addFoodPanel.add(addPanel);
        addFoodPanel.add(mainDescription);

        foodList.addActionListener(new addFoodDescription());
        warehouseList.addActionListener(new addWarehouseDescription());
        foodButton.addActionListener(new commenceButtonListener());
    }

    class commenceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (foodList.getItemCount() > 0 && warehouseList.getItemCount() > 0) {
                try {
                    if (warehouses.get(warehouseList.getSelectedIndex()).returnTheNumberOfFoodItems() <
                            warehouses.get(warehouseList.getSelectedIndex()).getCapacity()) {

                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Food item " + foodList.getItemAt(foodList.getSelectedIndex())
                                + " was added into warehouse " + warehouseList.getItemAt(warehouseList.getSelectedIndex()));
                        pw.close();

                        int foodIndex = foodList.getSelectedIndex();
                        warehouses.get(warehouseList.getSelectedIndex()).addItem(snacks.get(foodIndex));
                        foodList.removeItemAt(foodIndex);
                        snacks.remove(foodIndex);
                    } else {
                        warehouseDescription.setText("NOT ENOUGH SPACE IN THE WAREHOUSE");
                    }
                } catch (Exception e) {
                    foodDescription.setText("NO SUCH ITEM");
                }
            }
        }
    }

    class addFoodDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                foodList = (JComboBox) event.getSource();
                FoodItem food = snacks.get(foodList.getSelectedIndex());

                foodDescription.setText("KIDS IN AFRICA COULD'VE EATEN THIS PANEL\n\n 👀 LABEL:      " + food.getLabel() +
                        "\n 👀 VOLUME:       " + food.getVolume() + "\n 👀 WEIGHT:       " + food.getWeight() +
                        "\n 👀 FOOD TYPE:   " + food.getFoodType() + "\n 👀 EXPIRATION DATE:\n" + food.getExpirationDate());

            } catch (Exception e) {
                foodDescription.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
            }
        }
    }

    class addWarehouseDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                warehouseList = (JComboBox) event.getSource();
                Warehouse warehouse = warehouses.get(warehouseList.getSelectedIndex());

                warehouseDescription.setText(" 😎 WAREHOUSE 😎\n\n 👀 Name:\n" + warehouse.getName() + "\n 👀 Country:\n" +
                        warehouse.getLocation().getCountry() + "\n 👀 City:\n" +
                        warehouse.getLocation().getCity() + "\n 👀 Capacity:\n" +
                        warehouse.getCapacity());
            } catch (Exception e) {
                warehouseDescription.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
            }
        }
    }

    void setAddFood(ArrayList<FoodItem> snacks, ArrayList<Warehouse> warehouses) {
        this.warehouses = warehouses;
        this.snacks = snacks;
        foodList.removeAllItems();
        warehouseList.removeAllItems();
        for (FoodItem snack : snacks) {
            foodList.addItem(snack.getLabel());
        }
        for (Warehouse warehouse : warehouses) {
            warehouseList.addItem(warehouse.getName());
        }
    }

    ArrayList<FoodItem> getSnacks() {
        return snacks;
    }

    JPanel getPanel() {
        return addFoodPanel;
    }
}