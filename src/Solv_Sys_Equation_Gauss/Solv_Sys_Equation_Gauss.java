package Solv_Sys_Equation_Gauss;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Solv_Sys_Equation_Gauss {
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        String temp_series[];
        float temp_matrix[][];
        StringBuilder audit = new StringBuilder("^[-]?(([0-9]+[a])|([0-9]+[.][0-9]+[a])|([a]))?");
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
                    matrix.SetVeriables(sc.nextInt());
                    if (matrix.GetVeriables() <= 0)
                        continue;
                    break;
                }
                else {
                    System.out.println("Input amount of equations: ");
                    sc.next();
                    continue;
                }
            }
        for (int i = 0; i < matrix.GetVeriables() - 1; i++)
            audit.append(String.format("[+|-]?(([0-9]*[%s])|([0-9]+[.][0-9]+[%s])|([%s]))?", 98 + i, 98 + i, 98 + i));
        audit.append(String.format("[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))$"));

        temp_matrix = new float[matrix.GetVeriables()][matrix.GetVeriables()+1];
//Example
        System.out.println("Input system of equations like this\n");
        System.out.println("Where the first veriable will be a, second b, and so on\n");
        for (int i = 0; i < matrix.GetVeriables(); i++) {
            for (int j = 0; j < matrix.GetVeriables(); j++) {
                System.out.printf("%d%s+", j + 1, ((char)(97+j)));
            }
            System.out.printf("\b=%d\n", i + 1);
        }
//Input values
        while (counter < matrix.GetVeriables()) {
            System.out.printf("\n Input %dth equation\n", counter + 1);
            String string_input = sc.next();
            matcher = pattern.matcher(string_input);
            sup_matcher = sup.matcher(string_input);
            int j = 0;

            if (matcher.find() == true && sup_matcher.find() == true) {
                j = 0;
                for (; j < matrix.GetVeriables(); j++) {
                    series = Pattern.compile(String.format("[-]?(([0-9]*[%s])|([0-9]*[.][0-9]*[%s]))",((char)(97+j)), ((char)(97+j))));
                    ser = series.matcher(string_input);
                    if (ser.find() == true) {
                        temp_series = ser.group().split(String.format("%s", ((char)(97+j))));
                        if (temp_series.length == 0 ) {
                            temp_matrix[counter][j] = 1;
                        }
                        else if (temp_series[0].equals("-")) {
                            temp_matrix[counter][j] = -1;
                        }
                        else
                            temp_matrix[counter][j] = Float.valueOf(temp_series[0]);
                    } else
                        temp_matrix[counter][j] = 0;
                }
                series2 = Pattern.compile("[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))");
                ser2 = series2.matcher(string_input);
                ser2.find();
                temp_series = ser2.group().split("[=]{1}");
                temp_matrix[counter][j] = Float.valueOf(temp_series[1]);
            }
            else {
                System.out.println("\nincorrect input try again");
                continue;
            }
            counter += 1;
        }
        matrix.SetMatrix(temp_matrix);
        matrix.Show_Matrix();
//Transposition equations
        matrix.Transposition_equations();
//Solving system of equations
        matrix.Solving();
        int i = 0;
        float result[] = new float[matrix.GetVeriables()];
        for (; i < matrix.GetVeriables(); i++)
            result[i] = 1;
        i = matrix.GetVeriables();
        while(i > 0) {
            float temp = 0;
            for (int j = 0; j < matrix.GetVeriables(); j++) {
                if (j != i - 1)
                temp += matrix.GetMatrix()[i - 1][j] * result[j] * -1;
            }
            temp += matrix.GetMatrix()[i - 1][matrix.GetVeriables()];
            if (matrix.GetMatrix()[i - 1][i - 1] != 0)
                result[i - 1] = temp / matrix.GetMatrix()[i - 1][i - 1];
           else
                result[i - 1] = temp;
            i -= 1;
        }
        for(i = 0; i < matrix.GetVeriables() ; i++)
            System.out.printf("%s=%.2f ", ((char)(97+i)), result[i] );
}
    }

