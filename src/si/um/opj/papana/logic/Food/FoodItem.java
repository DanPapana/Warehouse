package si.um.opj.papana.logic.Food;

import java.time.LocalDate;

public class FoodItem implements java.io.Serializable {

    private String label;
    private int volume;
    private double weight;
    private LocalDate expirationDate;
    private FoodItemType foodType;

    public FoodItem() {}

    public FoodItem(String label) {
        this.label = label;
    }

    public FoodItem(String label, int volume, double weight, LocalDate expirationDate) {
        this(label);
        this.volume = volume;
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    public FoodItem(String label, int volume, double weight, LocalDate expirationDate, FoodItemType foodType) {
        this(label, volume, weight, expirationDate);
        this.foodType = foodType;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public FoodItemType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodItemType foodType) {
        this.foodType = foodType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label + " " + volume + " " + weight + " " + expirationDate + " " + foodType;
    }
}