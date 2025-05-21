import static java.lang.System.exit;

public class MacroCalculator {
    private static final byte FAT_AUTO_PERCENTAGE = 30;
    private static final byte PROTEIN_AUTO_PERCENTAGE = 20;
    private static final byte CARBS_AUTO_PERCENTAGE = 50;
    static final int MAX_PERCENTAGE_VALUE = 100;

    private static float gramsOfCarb;
    private static float gramsOfProtein;
    private static float gramsOfFat;

    private byte carbohydratePercentage;
    private byte proteinPercentage;
    private byte fatPercentage;

    private int targetCalories;

    public MacroCalculator() {
        this.carbohydratePercentage = CARBS_AUTO_PERCENTAGE;
        this.proteinPercentage = PROTEIN_AUTO_PERCENTAGE;
        this.fatPercentage = FAT_AUTO_PERCENTAGE;
    }

    public MacroCalculator(
            byte carbohydratePercentage,
            byte proteinPercentage,
            byte fatPercentage) {
        this.carbohydratePercentage = carbohydratePercentage;
        this.proteinPercentage = proteinPercentage;
        this.fatPercentage = fatPercentage;
    }

    public byte getFatAutoPercentage() {
        return FAT_AUTO_PERCENTAGE;
    }

    public byte getProteinAutoPercentage() {
        return PROTEIN_AUTO_PERCENTAGE;
    }

    public byte getCarbsAutoPercentage() {
        return CARBS_AUTO_PERCENTAGE;
    }

    public float getGramsOfCarb () {
        return gramsOfCarb;
    }

    public float getGramsOfProtein () {
        return gramsOfProtein;
    }

    public float getGramsOfFat () {
        return gramsOfFat;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public void autoCalculation() {
        performCalculation(carbohydratePercentage, proteinPercentage, fatPercentage);
    }

    public void manualCalculation() {
        if (!isPercentageValid())
            exit(1);

        performCalculation(carbohydratePercentage, proteinPercentage, fatPercentage);
    }

    private void performCalculation(byte carbPercent, byte proteinPercent, byte fatPercent) {
        gramsOfCarb = getMacroCalories(carbPercent, 4);
        gramsOfProtein = getMacroCalories(proteinPercent, 4);
        gramsOfFat = getMacroCalories(fatPercent, 9);
    }

    private float getMacroCalories(byte macroPercentage, int caloriesPerGram) {
        float macroCalories = (targetCalories * (float) macroPercentage) / MAX_PERCENTAGE_VALUE;
        return macroCalories / caloriesPerGram;
    }

    private boolean isPercentageValid() {
        if (carbohydratePercentage + proteinPercentage + fatPercentage != MAX_PERCENTAGE_VALUE) {
            System.out.println("Percentages must total " + MAX_PERCENTAGE_VALUE + "%.");
            return false;
        }
        else
            return true;
    }
}