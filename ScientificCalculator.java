import java.util.Scanner;

class BasicCalculator {
    public double add(double a, double b) { return a + b; }
    public double subtract(double a, double b) { return a - b; }
    public double multiply(double a, double b) { return a * b; }
    public double divide(double a, double b) { return b == 0 ? Double.NaN : a / b; }
}

class AdvancedCalculator extends BasicCalculator {
    boolean degreeMode = true;

    public void toggleAngleMode() {
        degreeMode = !degreeMode;
        System.out.println("Mode: " + (degreeMode ? "Degrees" : "Radians"));
    }

    private double toRadians(double angle) {
        return degreeMode ? Math.toRadians(angle) : angle;
    }

    public double square(double x) { return x * x; }
    public double sqrt(double x) { return x < 0 ? Double.NaN : Math.sqrt(x); }
    public double power(double x, double y) { return Math.pow(x, y); }
    public double sin(double x) { return Math.sin(toRadians(x)); }
    public double cos(double x) { return Math.cos(toRadians(x)); }
    public double tan(double x) { return Math.tan(toRadians(x)); }
    public double arcsin(double x) { return degreeMode ? Math.toDegrees(Math.asin(x)) : Math.asin(x); }
    public double arccos(double x) { return degreeMode ? Math.toDegrees(Math.acos(x)) : Math.acos(x); }
    public double arctan(double x) { return degreeMode ? Math.toDegrees(Math.atan(x)) : Math.atan(x); }
    public double log(double x) { return Math.log10(x); }
    public double ln(double x) { return Math.log(x); }
    public double exp(double x) { return Math.exp(x); }
    public double reciprocal(double x) { return x == 0 ? Double.NaN : 1 / x; }
    public double percent(double x) { return x / 100; }
}

public class ScientificCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdvancedCalculator calc = new AdvancedCalculator();
        String input;

        System.out.println("🔢 Scientific Calculator (fx-991CW Style)");
        System.out.println("Type expressions like: 2 + 3 | 5 * 6 | sin 30 | log 100");
        System.out.println("Type 'mode' to toggle Degree/Radian, 'exit' to quit.\n");

        while (true) {
            System.out.print(">> ");
            input = sc.nextLine().trim().toLowerCase();
            if (input.equals("exit")) break;
            if (input.equals("mode")) {
                calc.toggleAngleMode();
                continue;
            }

            try {
                String[] parts = input.split("\\s+");
                double result = Double.NaN;

                if (parts.length == 3) {
                    double a = Double.parseDouble(parts[0]);
                    String op = parts[1];
                    double b = Double.parseDouble(parts[2]);

                    switch (op) {
                        case "+" -> result = calc.add(a, b);
                        case "-" -> result = calc.subtract(a, b);
                        case "*" -> result = calc.multiply(a, b);
                        case "/" -> result = calc.divide(a, b);
                        case "^" -> result = calc.power(a, b);
                        default -> System.out.println("Unknown operator.");
                    }

                } else if (parts.length == 2) {
                    String func = parts[0];
                    double val = Double.parseDouble(parts[1]);

                    switch (func) {
                        case "sin" -> result = calc.sin(val);
                        case "cos" -> result = calc.cos(val);
                        case "tan" -> result = calc.tan(val);
                        case "arcsin" -> result = calc.arcsin(val);
                        case "arccos" -> result = calc.arccos(val);
                        case "arctan" -> result = calc.arctan(val);
                        case "sqrt" -> result = calc.sqrt(val);
                        case "square" -> result = calc.square(val);
                        case "log" -> result = calc.log(val);
                        case "ln" -> result = calc.ln(val);
                        case "exp" -> result = calc.exp(val);
                        case "1/x" -> result = calc.reciprocal(val);
                        case "%" -> result = calc.percent(val);
                        default -> System.out.println("Unknown function.");
                    }

                } else {
                    System.out.println("Invalid input format.");
                    continue;
                }

                System.out.println("= " + result);
            } catch (Exception e) {
                System.out.println("Error: Invalid input or format.");
            }
        }

        System.out.println("Calculator closed.");
        sc.close();
    }
}