package javaaa;
public class FinancialForecasting {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double currentValue, double growthRate, int periods) {
        // Base case: if no more periods left, return current value
        if (periods <= 0) {
            return currentValue;
        }
        
        // Recursive case: calculate next period's value and recurse
        double nextValue = currentValue * (1 + growthRate);
        return calculateFutureValue(nextValue, growthRate, periods - 1);
    }

    // Optimized version using memoization to avoid redundant calculations
    public static double calculateFutureValueMemo(double currentValue, double growthRate, int periods, double[] memo) {
        if (periods <= 0) {
            return currentValue;
        }
        
        // Check if we've already computed this period
        if (memo[periods] != 0) {
            return memo[periods];
        }
        
        // Compute and store the result
        double result = calculateFutureValueMemo(currentValue * (1 + growthRate), growthRate, periods - 1, memo);
        memo[periods] = result;
        return result;
    }

    public static void main(String[] args) {
        double initialInvestment = 1000.0; // $1000 initial investment
        double annualGrowthRate = 0.05;   // 5% annual growth rate
        int years = 10;                   // 10 years projection
        
        // Standard recursive approach
        double futureValue = calculateFutureValue(initialInvestment, annualGrowthRate, years);
        System.out.printf("Future value after %d years (recursive): $%.2f%n", years, futureValue);
        
        // Optimized recursive approach with memoization
        double[] memo = new double[years + 1];
        double futureValueMemo = calculateFutureValueMemo(initialInvestment, annualGrowthRate, years, memo);
        System.out.printf("Future value after %d years (memoized): $%.2f%n", years, futureValueMemo);
        
        // Validation with direct formula
        double directValue = initialInvestment * Math.pow(1 + annualGrowthRate, years);
        System.out.printf("Future value after %d years (direct formula): $%.2f%n", years, directValue);
    }
}
