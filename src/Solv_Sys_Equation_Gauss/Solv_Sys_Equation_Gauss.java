package Solv_Sys_Equation_Gauss;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class Matrix {
   static void Show_Matrix(float matrix[][]) {
        for (int i = 0; i < matrix[0].length-1; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[0].length-2 + 1; j++) {
                System.out.printf("{%.2f}", matrix[i][j]);
            }
            System.out.print(" |\n");
        }
        System.out.println("");
    }

    static void Infinite_Decisions(float matrix[][], String string) {
        System.out.print(string);
        int i = 0;
        int j = 0;
        for (; i < matrix[0].length-1; i++) {
            j=0;
            for (; j < matrix[0].length-1; j++) {
                if (matrix[i][j] == 1)
                    System.out.printf("%s+", ((char)(97+j)));
                else if (matrix[i][j] > 0)
                    System.out.printf("%.2f%s+", matrix[i][j], ((char)(97+j)));
                else if (matrix[i][j] < 0)
                    System.out.printf("\b%.2f%s+", matrix[i][j], ((char)(97+j)));
                else
                    System.out.printf("%.2f+", matrix[i][j]);
            }
            if (matrix[i][j] == 0)
                System.out.printf("\b=%.2f\n", matrix[i][j]);
            else
                System.out.printf("\b=%.2f\n", matrix[i][j]);
        }
        System.out.print("");
    }

    static float[] Division(float series[], float number) {
        float new_series[] = new float[series.length];
        for (int i = 0; i < series.length; i++) {
            if (series[i] != 0)
                new_series[i] = (series[i] / number);
            else
                new_series[i] = 0;
        }
        return new_series;
    }

    static float[] Multiplication_Series(float series[], float number) {
        float new_series[] = new float[series.length];
        for (int i = 0; i < series.length; i++)
            new_series[i] = (series[i] * number);
        return new_series;
    }

    static float[] Subtraction_Of_Series(float f_ser[], float s_ser[]) {
        float new_series[] = new float[f_ser.length];
        for (int i = 0; i < f_ser.length; i++)
            new_series[i] = (f_ser[i] - s_ser[i]);
        return new_series;
    }

    static float[][] Transposition_equations(float matrix[][]) {
        int product = 1;
        for (int i = 0; i < matrix[0].length-1; i++)
            product *= matrix[i][i];
        if (product == 0) {
            for (int i = 0; i < matrix[0].length-1; i++) {
                if (matrix[i][i] == 0) {
                    int j = 1;
                    while (j < matrix[0].length-1) {
                        if (matrix[j][i] != 0 && matrix[i][j] != 0) {
                            float temp_list[] = matrix[i];
                            matrix[i] = matrix[j];
                            matrix[j] = temp_list;
                            j = matrix[0].length-1;
                            continue;
                        }
                        j += 1;
                    }
                }
            }
            product = 1;
            for (int i = 1; i < matrix[0].length-1; i++)
                product *= matrix[i][i];
            if (product == 0) {
                Infinite_Decisions(matrix, "Solution of the system of equations\n");
                System.exit(0);
            }
            Show_Matrix(matrix);
        }
        return matrix;
    }
}
public class Solv_Sys_Equation_Gauss {
    public static void main(String[] args) {
        int counter = 0;
        int veriables = 0;
        String temp_matrix[];
        StringBuilder audit = new StringBuilder("^[-]?(([0-9]+[a])|([0-9]+[.][0-9]+[a])|([a]))?");
        Scanner sc = new Scanner(System.in);

        Pattern pattern = Pattern.compile(audit.toString());
        Pattern sup = Pattern.compile("[-]?(([0-9]*[a-z])|([0-9]+[.][0-9]+)|([a-z]))[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))+");
        Pattern series;
        Pattern series2;
        Matcher matcher;
        Matcher sup_matcher;
        Matcher ser;
        Matcher ser2;
//Create matrix
        System.out.println("Input amount of equations: ");
        while (true) {
                if(sc.hasNextInt()) {
                    veriables = sc.nextInt();
                    if (veriables <= 0)
                        continue;
                    break;
                }
                else {
                    System.out.println("Input amount of equations: ");
                    sc.next();
                    continue;
                }
            }
        for (int i = 0; i < veriables - 1; i++)
            audit.append(String.format("[+|-]?(([0-9]*[%s])|([0-9]+[.][0-9]+[%s])|([%s]))?", 98 + i, 98 + i, 98 + i));
        audit.append(String.format("[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))$"));
        float matrix[][] = new float[veriables][veriables+1];
        System.out.println("Input system of equations like this\n");
        System.out.println("Where the first veriable will be a, second b, and so on\n");
//Example
        for (int i = 0; i < veriables; i++) {
            for (int j = 0; j < veriables; j++) {
                System.out.printf("%d%s+", j + 1, ((char)(97+j)));
            }
            System.out.printf("\b=%d\n", i + 1);
        }
//Input values
        while (counter < veriables) {
            System.out.printf("\n Input %dth equation\n", counter + 1);
            String string_input = sc.next();
            matcher = pattern.matcher(string_input);
            sup_matcher = sup.matcher(string_input);
            int j = 0;
            if (matcher.find() == true && sup_matcher.find() == true) {
                j = 0;
                for (; j < veriables; j++) {
                    series = Pattern.compile(String.format("[-]?(([0-9]*[%s])|([0-9]*[.][0-9]*[%s]))",((char)(97+j)), ((char)(97+j))));
                    ser = series.matcher(string_input);
                    if (ser.find() == true) {
                        temp_matrix = ser.group().split(String.format("%s", ((char)(97+j))));
                        if (temp_matrix.length == 0 ) {
                            matrix[counter][j] = 1;
                        }
                        else if (temp_matrix[0].length()== 1 && temp_matrix[0].charAt(0) == '-') {
                            matrix[counter][j] = -1;
                        }
                        else
                            matrix[counter][j] = Float.valueOf(temp_matrix[0]);
                    } else
                        matrix[counter][j] = 0;
                }
                series2 = Pattern.compile("[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))");
                ser2 = series2.matcher(string_input);
                ser2.find();
                temp_matrix = ser2.group().split("[=]{1}");
                matrix[counter][j] = Float.valueOf(temp_matrix[1]);
            } else {
                System.out.println("\nincorrect input try again");
                continue;
            }
            counter += 1;
        }
        Matrix.Show_Matrix(matrix);
//Transposition equations
        Matrix.Transposition_equations(matrix);
//Solving system of equations
        for (int i = 0; i < veriables; i++) {
            int j = i + 1;
            while (j < veriables) {
                if (matrix[i][i] != 0) {
                   float[] number = Matrix.Multiplication_Series(matrix[i], matrix[j][i]);
                    number = Matrix.Division(number, matrix[i][i]);
                    matrix[j] = Matrix.Subtraction_Of_Series(matrix[j], number);
                    Matrix.Show_Matrix(matrix);
                }
            else
                Matrix.Transposition_equations(matrix);
            j += 1;
            }
            if (matrix[veriables - 1][veriables - 1] == 0)
            Matrix.Transposition_equations(matrix);
        }
        int i = 0;
        float result[] = new float[veriables];
        for (; i < veriables; i++)
            result[i] = 1;
        i = veriables;
        while(i > 0) {
            float temp = 0;
            for (int j = 0; j < veriables; j++) {
                if (j != i - 1)
                temp += matrix[i - 1][j] * result[j] * -1;
            }
            temp += matrix[i - 1][veriables];
            if (matrix[i - 1][i - 1] != 0)
                result[i - 1] = temp / matrix[i - 1][i - 1];
           else
                result[i - 1] = temp;
            i -= 1;
        }
        for(i = 0; i < veriables ; i++)
            System.out.printf("%s=%.2f ", ((char)(97+i)), result[i] );
}
    }

