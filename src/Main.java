public class Main {
    public static void main(String[] args) {
        MacroCalculator calculator;
        byte carbPercent;
        byte proteinPercent;
        byte fatPercent;

        int targetCalories = (int) Console.readNumber("Enter your target calories", 0);
        boolean isAuto = Console.readNumber("Please choose a calculation method:\n1) Automatic\n2) Manual", 1, 2) == 1;

        if (isAuto) {
            calculator = new MacroCalculator();
            calculator.setTargetCalories(targetCalories);
            calculator.autoCalculation();

            carbPercent = calculator.getCarbsAutoPercentage();
            proteinPercent = calculator.getProteinAutoPercentage();
            fatPercent = calculator.getFatAutoPercentage();
        } else {
            carbPercent = (byte) Console.readNumber("Carbohydrate percentage", 0, 100);
            proteinPercent = (byte) Console.readNumber("Protein percentage", 0, 100);
            fatPercent = (byte) Console.readNumber("Fat percentage", 0, 100);

            calculator = new MacroCalculator(carbPercent, proteinPercent, fatPercent);
            calculator.setTargetCalories(targetCalories);
            calculator.manualCalculation();
        }

        var report = new MacroReport(calculator);
        report.printResults(carbPercent, proteinPercent, fatPercent);
    }
}