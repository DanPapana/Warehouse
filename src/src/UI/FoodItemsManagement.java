package src.UI;
import src.logic.Food.FoodItem;
import src.logic.Food.FoodItemType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.awt.Color.CYAN;
import static java.awt.Color.WHITE;

class FoodItemsManagement {

    private JPanel foodPanel = new JPanel();
    private JRadioButton freshButton = new JRadioButton("Fresh");
    private JRadioButton frozenButton = new JRadioButton("Frozen");
    private JTextField expirationYear = new JTextField("YYYY", 3);
    private JTextField expirationMonth = new JTextField("MM", 2);
    private JTextField expirationDay = new JTextField("DD", 2);
    private JTextPane descriptionPane = new JTextPane();
    private JTextField labelText = new JTextField(9);
    private JTextField volumeText = new JTextField(9);
    private JTextField weightText = new JTextField(9);
    private JButton createButton = new JButton("CREATE", new ImageIcon(MainFrame.class.getResource("/resources/dog.jpg")));
    private JButton editButton = new JButton("EDIT", new ImageIcon(MainFrame.class.getResource("/resources/edit.jpg")));
    private JButton deleteButton = new JButton("DELETE", new ImageIcon(MainFrame.class.getResource("/resources/delete.jpg")));
    private ArrayList<FoodItem> snacks = new ArrayList<>();
    private JComboBox foodList = new JComboBox<>();

    void setSnacks(ArrayList<FoodItem> snacks) {
        this.snacks = snacks;
        foodList.removeAllItems();
        for (FoodItem snack : snacks) {
            foodList.addItem(snack.getLabel());
        }
    }

    ArrayList<FoodItem> getSnacks() {
        return snacks;
    }

    JTextPane getDescriptionPane() {
        return descriptionPane;
    }

    JPanel getPanel() {
        return foodPanel;
    }

    FoodItemsManagement() {

        JPanel radioPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        freshButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(freshButton);
        group.add(frozenButton);
        radioPanel.add(freshButton);
        radioPanel.add(frozenButton);

        radioPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        radioPanel.add(foodList);
        
        inputPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        inputPanel.add(new JLabel("Label"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(labelText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        inputPanel.add(new JLabel("Volume"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(volumeText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        inputPanel.add(new JLabel("Weight"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(weightText);

        inputPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel datePanel = new JPanel(new FlowLayout());
        
        volumeText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (volumeText.getText().length() >= 5)
                    e.consume();
            }
            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(volumeText.getText());
                } catch (Exception e) {
                    volumeText.setText(volumeText.getText().substring(0, volumeText.getText ().length() - 1));
                }
            }
        });

        weightText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (weightText.getText().length() >= 5)
                    e.consume();
            }
            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(weightText.getText());
                } catch (Exception e) {
                    weightText.setText(weightText.getText().substring(0, weightText.getText ().length() - 1));
                }
            }
        });

        expirationYear.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (expirationYear.getText().length() >= 4)
                    e.consume();
            }
            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(expirationYear.getText());
                } catch (Exception e) {
                    expirationYear.setText(expirationYear.getText().substring(0, expirationYear.getText ().length() - 1));
                }
            }
        });

        expirationMonth.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                if (expirationMonth.getText().length() >= 2)
                    e.consume();
            }
            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(expirationMonth.getText());
                } catch (Exception e) {
                    expirationMonth.setText(expirationMonth.getText().substring(0, expirationMonth.getText ().length() - 1));
                }
            }
        });

        expirationDay.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (expirationDay.getText().length() >= 2)
                    e.consume();
            }

            public void keyReleased(KeyEvent evt) {
                try {
                    Long.parseLong(expirationDay.getText());
                } catch (Exception e) {
                    expirationDay.setText(expirationDay.getText().substring(0, expirationDay.getText ().length() - 1));
                }
            }
        });

        expirationYear.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                expirationYear.setText("20");
            }
        });

        expirationMonth.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                expirationMonth.setText("");
            }
        });

        expirationDay.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                expirationDay.setText("");
            }
        });

        datePanel.add(expirationYear);
        datePanel.add(expirationMonth);
        datePanel.add(expirationDay);
        inputPanel.add(new JLabel("Expiration Date"));
        inputPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        inputPanel.add(datePanel);

        buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(createButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(editButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(deleteButton);

        descriptionPane.setEditable(false);
        descriptionPane.setPreferredSize(new Dimension(110, 130));
        descriptionPane.setBackground(Color.getHSBColor(0, 0, 97));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(descriptionPane);

        foodPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        foodPanel.add(radioPanel);
        foodPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        foodPanel.add(inputPanel);
        foodPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        foodPanel.add(buttonPanel);

        foodList.addActionListener(new addItemDescription());
        createButton.addActionListener(new createButtonListener());
        editButton.addActionListener(new editButtonListener());
        deleteButton.addActionListener(new deleteButtonListener());
    }

    class createButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if ((!labelText.getText().isEmpty()) && (!weightText.getText().isEmpty())
                    && (!volumeText.getText().isEmpty()) && (!expirationYear.getText().isEmpty()) &&
                    (!expirationMonth.getText().isEmpty()) && (!expirationDay.getText().isEmpty()) &&
                    MainFrame.isFloat(weightText.getText()) && MainFrame.isInteger(expirationYear.getText()) &&
                    MainFrame.isInteger(expirationMonth.getText()) && MainFrame.isInteger(expirationDay.getText())) {

                try {
                    FoodItem new_food = new FoodItem(labelText.getText(), Integer.parseInt(volumeText.getText()),
                            Float.parseFloat(weightText.getText()), LocalDate.of(Integer.parseInt(expirationYear.getText()),
                            Integer.parseInt(expirationMonth.getText()), Integer.parseInt(expirationDay.getText())));

                    if (freshButton.isSelected()) {
                        new_food.setFoodType(FoodItemType.FRESH);
                    } else {
                        new_food.setFoodType(FoodItemType.FROZEN);
                    }

                    snacks.add(new_food);
                    foodList.addItem(labelText.getText());
                    foodList.setSelectedIndex(foodList.getItemCount() - 1);

                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [REMARK] Food item " + labelText.getText() + " created");
                        pw.close();

                } catch (Exception e) {
                    if (foodList.getItemCount() > 2)
                    descriptionPane.setText("💢 WRONG DATE FORMAT 💢");
                    try {
                        FileWriter fw = new FileWriter("logFile.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);

                        pw.println(LocalTime.now() + " [WARNING] The date format is wrong for the food item");
                        pw.close();
                    } catch (IOException ignored) {
                    }
                }
            } else {
                descriptionPane.setText("🤷‍ 💉 🤷 EMPTY 🤷 💉‍ 🤷‍");

                try {
                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [WARNING] Some fields are empty in food item");
                    pw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    class addItemDescription implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                    foodList = (JComboBox) event.getSource();
                    FoodItem food = snacks.get(foodList.getSelectedIndex());

                    descriptionPane.setText("KIDS IN AFRICA COULD'VE EATEN THIS PANEL\n\n 👀 LABEL:      " + food.getLabel() +
                            "\n 👀 VOLUME:       " + food.getVolume() + "\n 👀 WEIGHT:       " + food.getWeight() +
                            "\n 👀 FOOD TYPE:   " + food.getFoodType() + "\n 👀 EXPIRATION DATE:\n" + food.getExpirationDate());

            } catch (Exception e) {
                descriptionPane.setText("\n\n\n\n        🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
            }
        }
    }


    class editButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if (editButton.getText().equals("EDIT")) {

                foodList.setEnabled(false);

                descriptionPane.setText("\n\n\n\n            🔧  EDITING  🔧");
                createButton.setEnabled(false);
                deleteButton.setEnabled(false);
                editButton.setText("SAVE");

                labelText.setBackground(CYAN);
                weightText.setBackground(CYAN);
                volumeText.setBackground(CYAN);
                expirationYear.setBackground(CYAN);
                expirationMonth.setBackground(CYAN);
                expirationDay.setBackground(CYAN);

                try {

                    int index = foodList.getSelectedIndex();

                    labelText.setText(getSnacks().get(index).getLabel());
                    weightText.setText(String.valueOf(getSnacks().get(index).getWeight()));
                    volumeText.setText(String.valueOf(getSnacks().get(index).getVolume()));
                    expirationYear.setText(String.valueOf(getSnacks().get(index).getExpirationDate().getYear()));
                    expirationMonth.setText(String.valueOf(getSnacks().get(index).getExpirationDate().getMonthValue()));
                    expirationDay.setText(String.valueOf(getSnacks().get(index).getExpirationDate().getDayOfMonth()));

                    if (getSnacks().get(index).getFoodType().equals(FoodItemType.FRESH)) {
                        freshButton.setSelected(true);
                        freshButton.setBackground(CYAN);
                    } else {
                        frozenButton.setSelected(true);
                        frozenButton.setBackground(CYAN);
                    }

                    snacks.remove(index);
                    foodList.removeItemAt(index);
                } catch (Exception e) {
                    descriptionPane.setText("\n\n\n\n   🔧 🔧 🔧 🔧 🔧 🔧 🔧 🔧");
                }

            } else {

                foodList.setEnabled(true);
                editButton.setText("EDIT");
                createButton.setEnabled(true);
                deleteButton.setEnabled(true);

                labelText.setBackground(WHITE);
                weightText.setBackground(WHITE);
                volumeText.setBackground(WHITE);
                expirationYear.setBackground(WHITE);
                expirationMonth.setBackground(WHITE);
                expirationDay.setBackground(WHITE);

                freshButton.setBackground(null);
                frozenButton.setBackground(null);

                createButton.doClick();
            }
        }
    }

    class deleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                snacks.remove(foodList.getSelectedIndex());

                    FileWriter fw = new FileWriter("logFile.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(LocalTime.now() + " [REMARK] Food item " +
                            foodList.getItemAt(foodList.getSelectedIndex()) + " is deleted");
                    pw.close();

                foodList.removeItemAt(foodList.getSelectedIndex());
                if (foodList.getSelectedIndex() > foodList.getItemCount()) {
                    foodList.setSelectedIndex(foodList.getSelectedIndex() + 1);
                }
                if (foodList.getItemCount() < 1) {
                    foodList.removeAllItems();
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
}