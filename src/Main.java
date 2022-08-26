import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter an expression / Введите выражение: ");

        String expression = scn.nextLine();
        String result = calc(expression);

        System.out.println(result);
    }

    public static String calc(String input) {
        Converter converter = new Converter();

        String[] data = input.split(" ");

        if (data.length != 3) {
            throw new NegativeArraySizeException("Invalid expression format");
        }

        String firstNumber = data[0];
        String operation = data[1];
        String secondNumber = data[2];

        boolean isRomanA = converter.isRoman(firstNumber);
        boolean isRomanB = converter.isRoman(secondNumber);

        boolean isSameFormat = isRomanA == isRomanB;
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)

        if (!isSameFormat) {
            throw new IllegalArgumentException("Числа должны быть в одном формате");
        }

        int a, b;
        //Определяем, римские ли это числа
        if (isRomanA) {
            //если римские, то конвертируем их в арабские
            //X+V
            //x-10
            //v - 5
            a = converter.romanToArabic(firstNumber);
            b = converter.romanToArabic(secondNumber);
        } else {
            //если арабские, конвертируем их из строки в число
            a = Integer.parseInt(firstNumber);
            b = Integer.parseInt(secondNumber);
        }

        if (a > 10 || b > 10 || a < 1 || b < 1) {
            throw new IllegalArgumentException("Numbers should be in range from [1 to 10]");
        }

        //выполняем с числами арифметическое действие
        int result = applyOperation(a, b, operation);

        // 15 -> XV

        return isRomanA ? converter.arabicToRoman(result) : String.valueOf(result);
    }

    private static int applyOperation(int a, int b, String operation) {
        int result;

        switch (operation) {
            case "+":
                result = a+b;
                break;
            case "-":
                result = a-b;
                break;
            case "*":
                result = a*b;
                break;
            case "/":
                result = a/b;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }

        return result;
    }
}