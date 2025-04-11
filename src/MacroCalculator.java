import java.text.NumberFormat;
import java.util.Scanner;

import static java.lang.System.exit;

public class MacroCalculator {
    static Scanner userInput = new Scanner(System.in);
    static int targetCalories = 0;

    public static void AutoCalculation() {
        System.out.println("[DEBUG] Auto calculation...");

        float caloriesFromCarbs;
        float caloriesFromProtein;
        float caloriesFromFat;

        caloriesFromCarbs = targetCalories * 0.5f;
        System.out.println("[DEBUG] Calories from carbs: " + caloriesFromCarbs);
        float gramsOfCarb = caloriesFromCarbs / 4;

        caloriesFromProtein = targetCalories * 0.2f;
        System.out.println("[DEBUG] Calories from protein: " + caloriesFromProtein);
        float gramsOfProtein = caloriesFromProtein / 4;

        caloriesFromFat = targetCalories * 0.3f;
        System.out.println("[DEBUG] Calories from fat: " + caloriesFromFat);
        float gramsOfFat = caloriesFromFat / 9;

        System.out.println("So, for a total of " + targetCalories + " calories with a 50% carb, 20% protein, and 30% fat breakdown, you would need:");

        System.out.println(String.format("%.2f", gramsOfCarb) + " grams of carbs"
                + "\n" + String.format("%.2f", gramsOfProtein) + " grams of protein"
                + "\n" + String.format("%.2f", gramsOfFat) + " grams of fat");
    }

    public static void ManualCalculation() {
        byte carbohydratePercentage = 0;
        byte proteinPercentage = 0;
        byte fatPercentage = 0;

        NumberFormat numberFormat = null;

        System.out.println("[DEBUG] Manual calculation...");
        while(true) {
            System.out.print("Carbohydrate percentage: ");
            carbohydratePercentage = userInput.nextByte();
            if (carbohydratePercentage >= 0 && carbohydratePercentage <= 100)
                break;
        }

        while(true) {
            System.out.print("Protein percentage: ");
            proteinPercentage = userInput.nextByte();
            if (proteinPercentage >= 0 && proteinPercentage <= 100)
                break;
        }

        while(true) {
            System.out.print("Fat percentage: ");
            fatPercentage = userInput.nextByte();
            if (fatPercentage >= 0 && fatPercentage <= 100)
                break;
        }

        if (carbohydratePercentage + proteinPercentage + fatPercentage != 100) {
            System.out.println("The sum of the provided percentages is not equal to 100%. Please ensure the percentages add up to 100%.");
            exit(1);
        }

        System.out.println("[DEBUG] Carbs: " + carbohydratePercentage
                + "\nProtein: " + proteinPercentage
                + "\nFat: " + fatPercentage);

        float caloriesFromCarbs;
        float caloriesFromProtein;
        float caloriesFromFat;

        caloriesFromCarbs = targetCalories * ((float) carbohydratePercentage / 100);
        System.out.println("[DEBUG] Calories from carbs: " + caloriesFromCarbs);
        float gramsOfCarb = caloriesFromCarbs / 4;

        caloriesFromProtein = targetCalories * ((float)proteinPercentage / 100);
        System.out.println("[DEBUG] Calories from protein: " + caloriesFromProtein);
        float gramsOfProtein = caloriesFromProtein / 4;

        caloriesFromFat = targetCalories * ((float)fatPercentage / 100);
        System.out.println("[DEBUG] Calories from fat: " + caloriesFromFat);
        float gramsOfFat = caloriesFromFat / 9;

        System.out.println("So, for a total of " + targetCalories + " calories with a " +
                carbohydratePercentage + "% carb, " + proteinPercentage + "% protein, and " + fatPercentage + "% fat breakdown, you would need:");

        System.out.println(String.format("%.2f", gramsOfCarb) + " grams of carbs"
                + "\n" + String.format("%.2f", gramsOfProtein) + " grams of protein"
                + "\n" + String.format("%.2f", gramsOfFat) + " grams of fat");

        carbohydratePercentage = (byte) ((caloriesFromCarbs / targetCalories) * 100);
        proteinPercentage = (byte) ((caloriesFromProtein / targetCalories) * 100);
        fatPercentage = (byte) ((caloriesFromFat / targetCalories) * 100);

        System.out.println("[DEBUG] So, for a total of " + targetCalories + " calories with a " +
                carbohydratePercentage + "% carb, " + proteinPercentage + "% protein, and " + fatPercentage + "% fat breakdown, you would need:");
    }

    public static void main(String[] args) {
        /*
        Each gram of macronutrient produces a specific number of calories:
            1 gram of carbohydrate = 4 calories.
            1 gram of protein = 4 calories.
            1 gram of fat = 9 calories.
        */

        byte calculationMethodOption = 1;

        while(true) {
            System.out.print("Enter your target calories: ");
            targetCalories = userInput.nextInt();
            if (targetCalories > 0)
                break;
            System.out.println("Wrong input! Please try again.");
        }

        while(true) {
            System.out.println("Choose calculation method: "
                    + "\n1) Auto"
                    + "\n2) Manual");
            calculationMethodOption = userInput.nextByte();
            if (calculationMethodOption == 1 || calculationMethodOption == 2)
                break;
            System.out.println("Wrong input! Select any of the available options.");
        }

        if (calculationMethodOption == 1)
            AutoCalculation();
        else
            ManualCalculation();
    }
}