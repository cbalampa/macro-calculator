public class MacroReport {
    private MacroCalculator calculator;

    public MacroReport(MacroCalculator calculator) {
        this.calculator = calculator;
    }

    public void printResults(byte carbsPercent, byte proteinPercent, byte fatPercent) {
        System.out.println("For a total of " + calculator.getTargetCalories() + " calories with a " + carbsPercent +"% carbs, "
                + proteinPercent +"% protein and " + fatPercent + "% fat breakdown, you'll need:");

        System.out.println("\t• " + String.format("%.2f", calculator.getGramsOfCarb()) + " grams of carbs"
                + "\n\t• " + String.format("%.2f", calculator.getGramsOfProtein()) + " grams of protein"
                + "\n\t• " + String.format("%.2f", calculator.getGramsOfFat()) + " grams of fat");
    }
}