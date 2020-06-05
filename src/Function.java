public class Function {
    // Rosenbrock function
    // Min value equals 0 for x = 1, y = 1
    static double rosenbrock(double x, double y) {
        return Math.pow(1 - x, 2) + 100 * Math.pow((y - x*x), 2);
    }
}
