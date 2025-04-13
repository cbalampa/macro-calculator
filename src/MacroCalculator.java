import java.util.Scanner;

import static java.lang.System.exit;

public class MacroCalculator {
    static final int MIN_PERCENTAGE_VALUE = 0;
    static final int MAX_PERCENTAGE_VALUE = 100;
    static final byte CARBS_AUTO_PERCENTAGE = 50;
    static final byte PROTEIN_AUTO_PERCENTAGE = 20;
    static final byte FAT_AUTO_PERCENTAGE = 30;

    static float gramsOfCarb;
    static float gramsOfProtein;
    static float gramsOfFat;
    static int targetCalories = 0;
    static Scanner userInput = new Scanner(System.in);

    static double readNumber(String prompt) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt + ": ");
            value = scanner.nextFloat();
            if (value >= MIN_PERCENTAGE_VALUE && value <= MAX_PERCENTAGE_VALUE)
                break;
        }
        return value;
    }

    static float getMacroCalories(byte macroPercentage, int caloriesPerGram) {
        float macroCalories = (targetCalories * (float) macroPercentage) / MAX_PERCENTAGE_VALUE;
        return macroCalories / caloriesPerGram;
    }

    static void setTargetCalories() {
        while(true) {
            System.out.print("Enter your target calories: ");
            targetCalories = userInput.nextInt();
            if (targetCalories > 0)
                break;
            System.out.println("Wrong input! Please try again.");
        }
    }

    static void setCalculationMethod() {
        while(true) {
            System.out.println("Please choose a calculation method:\n1) Automatic\n2) Manual");
            byte calculationMethodOption = userInput.nextByte();
            if (calculationMethodOption == 1) {
                autoCalculation();
                break;
            } else if (calculationMethodOption == 2) {
                manualCalculation();
                break;
            }
            System.out.println("Wrong input! Select any of the available options.");
        }
    }

    static void printResults(byte carbsPercent, byte proteinPercent, byte fatPercent) {
        System.out.println("For a total of " + targetCalories + " calories with a " + carbsPercent +"% carbs, "
                + proteinPercent +"% protein, and " + fatPercent + "% fat breakdown, you'll need:");

        System.out.println(String.format("%.2f", gramsOfCarb) + " grams of carbs"
                + "\n" + String.format("%.2f", gramsOfProtein) + " grams of protein"
                + "\n" + String.format("%.2f", gramsOfFat) + " grams of fat");
    }

    static void autoCalculation() {
        System.out.println("[DEBUG] Auto calculation...");
        gramsOfCarb = getMacroCalories(CARBS_AUTO_PERCENTAGE, 4);
        gramsOfProtein = getMacroCalories(PROTEIN_AUTO_PERCENTAGE, 4);
        gramsOfFat = getMacroCalories(FAT_AUTO_PERCENTAGE, 9);

        printResults(CARBS_AUTO_PERCENTAGE, PROTEIN_AUTO_PERCENTAGE, FAT_AUTO_PERCENTAGE);
    }

    static void manualCalculation() {
        byte carbohydratePercentage = (byte) readNumber("Carbohydrate percentage");
        byte proteinPercentage = (byte) readNumber("Protein percentage");
        byte fatPercentage = (byte) readNumber("Fat percentage");

        if (carbohydratePercentage + proteinPercentage + fatPercentage != MAX_PERCENTAGE_VALUE) {
            System.out.println("Percentages must total " + MAX_PERCENTAGE_VALUE + "%.");
            exit(1);
        }

        gramsOfCarb = getMacroCalories(carbohydratePercentage, 4);
        gramsOfProtein = getMacroCalories(proteinPercentage, 4);
        gramsOfFat = getMacroCalories(fatPercentage, 9);

        printResults(carbohydratePercentage, proteinPercentage, fatPercentage);
    }

    public static void main(String[] args) {
        setTargetCalories();
        setCalculationMethod();
    }
}