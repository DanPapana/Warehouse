package si.um.opj.papana.logic.Food;

public enum FoodItemType {
    FRESH {
        @Override
        public String toString() {
            return "Fresh";
        }
    },
    FROZEN {
        @Override
        public String toString() {
            return "Frozen";
        }
    }
}