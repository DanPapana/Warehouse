package code.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.zip.GZIPOutputStream;

public class MainFrame extends JFrame {

    private JPanel mamaPanel = new JPanel(new CardLayout());

    public MainFrame() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ignored) {
        }

        this.setTitle("The changing gardens of the world");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String BUSINESS = "FacilityManagement Facility Management";
        final String VEHICLE = "VehicleManagement Management";
        final String FOOD = "Food Items Management";
        final String ADDFOOD = "Add FoodItemsManagement into Warehouse";
        final String LOADFOOD = "Load/Unload Vehicles";

        FacilityManagement facilityManagement = new FacilityManagement();
        VehicleManagement vehicleManagement = new VehicleManagement();
        FoodItemsManagement foodItem = new FoodItemsManagement();
        FoodToWarehouse foodManagement = new FoodToWarehouse();
        LoadingUnloading loading = new LoadingUnloading();

        mamaPanel.add(facilityManagement.getPanel(), BUSINESS);
        mamaPanel.add(vehicleManagement.getPanel(), VEHICLE);
        mamaPanel.add(foodItem.getPanel(), FOOD);
        mamaPanel.add(foodManagement.getPanel(), ADDFOOD);
        mamaPanel.add(loading.getPanel(), LOADFOOD);

        JPanel comboBoxPane = new JPanel();
        String[] comboBoxItems = {BUSINESS, VEHICLE, FOOD, ADDFOOD, LOADFOOD};
        JComboBox<String> combo = new JComboBox<>(comboBoxItems);
        comboBoxPane.add(combo);

        JPanel pane = new JPanel(new BorderLayout());

        pane.add(comboBoxPane, BorderLayout.NORTH);
        pane.add(mamaPanel, BorderLayout.CENTER);

        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout cl = (CardLayout) (mamaPanel.getLayout());
                cl.show(mamaPanel, (String) e.getItem());
                foodManagement.setAddFood(foodItem.getSnacks(), facilityManagement.getWarehouses());
                foodItem.setSnacks(foodManagement.getSnacks());
                loading.setLoading(vehicleManagement.getTrucks(), vehicleManagement.getVans(),
                        facilityManagement.getStores(), facilityManagement.getWarehouses());
                new IndependentVehicleDelete().setVehicles(vehicleManagement.getVans(), vehicleManagement.getTrucks());
            }
        });

        try{
            File logFile = new File("logFile.txt");
            if(!logFile.exists()){
                logFile.createNewFile();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    FileOutputStream foodOutput = new FileOutputStream("Food.ser");
                    ObjectOutputStream foodOOS = new ObjectOutputStream(foodOutput);
                    foodOOS.writeObject(foodItem.getSnacks());
                    foodOOS.flush();
                    foodOOS.close();
                    foodOutput.close();

                    FileOutputStream vanOutput = new FileOutputStream("Vans.ser");
                    ObjectOutputStream vanOOS = new ObjectOutputStream(vanOutput);
                    vanOOS.writeObject(vehicleManagement.getVans());
                    vanOOS.flush();
                    vanOOS.close();
                    vanOutput.close();

                    FileOutputStream truckOutput = new FileOutputStream("Trucks.ser");
                    ObjectOutputStream truckOOS = new ObjectOutputStream(truckOutput);
                    truckOOS.writeObject(vehicleManagement.getTrucks());
                    truckOOS.flush();
                    truckOOS.close();
                    truckOutput.close();

                    FileOutputStream warehouseOutput = new FileOutputStream("Warehouses.ser");
                    ObjectOutputStream warehouseOOS = new ObjectOutputStream(warehouseOutput);
                    warehouseOOS.writeObject(facilityManagement.getWarehouses());
                    warehouseOOS.flush();
                    warehouseOOS.close();
                    warehouseOutput.close();

                    FileOutputStream storeOutput = new FileOutputStream("Stores.ser");
                    ObjectOutputStream storeOOS = new ObjectOutputStream(storeOutput);
                    storeOOS.writeObject(facilityManagement.getStores());
                    storeOOS.flush();
                    storeOOS.close();
                    storeOutput.close();

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
                e.getWindow().dispose();
            }
        });

        byte[] buffer = new byte[1024];

        try{
            GZIPOutputStream foodGOS = new GZIPOutputStream(new FileOutputStream("Food.gz"));
            FileInputStream foodFIS = new FileInputStream("Food.ser");
            int foodLength;
            while ((foodLength = foodFIS.read(buffer)) > 0) {
                foodGOS.write(buffer, 0, foodLength);
            }
            foodFIS.close();
            foodGOS.finish();
            foodGOS.close();

            GZIPOutputStream vanGOS = new GZIPOutputStream(new FileOutputStream("Vans.gz"));
            FileInputStream vanFIS = new FileInputStream("Vans.ser");
            int vanLength;
            while ((vanLength = vanFIS.read(buffer)) > 0) {
                vanGOS.write(buffer, 0, vanLength);
            }
            vanFIS.close();
            vanGOS.finish();
            vanGOS.close();

            GZIPOutputStream truckGOS = new GZIPOutputStream(new FileOutputStream("Trucks.gz"));
            FileInputStream truckFIS = new FileInputStream("Trucks.ser");
            int truckLength;
            while ((truckLength = truckFIS.read(buffer)) > 0) {
                truckGOS.write(buffer, 0, truckLength);
            }
            truckFIS.close();
            truckGOS.finish();
            truckGOS.close();

            GZIPOutputStream warehouseGOS = new GZIPOutputStream(new FileOutputStream("Warehouses.gz"));
            FileInputStream warehouseFIS = new FileInputStream("Warehouses.ser");
            int warehouseLength;
            while ((warehouseLength = warehouseFIS.read(buffer)) > 0) {
                warehouseGOS.write(buffer, 0, warehouseLength);
            }
            warehouseFIS.close();
            warehouseGOS.finish();
            warehouseGOS.close();

            GZIPOutputStream storeGOS = new GZIPOutputStream(new FileOutputStream("Stores.gz"));
            FileInputStream storeFIS = new FileInputStream("Stores.ser");
            int storeLength;
            while ((storeLength = storeFIS.read(buffer)) > 0) {
                storeGOS.write(buffer, 0, storeLength);
            }
            storeFIS.close();
            storeGOS.finish();
            storeGOS.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        this.add(pane);
        setVisible(true);
    }


    static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(s);
            isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
            //it is not
        }
        return isValidInteger;
    }

    static boolean isFloat(String s) {
        boolean isValidFloat = false;
        try
        {
            Float.parseFloat(s);
            isValidFloat = true;
        }
        catch (NumberFormatException ex)
        {
            //it is not
        }
        return isValidFloat;
    }
}