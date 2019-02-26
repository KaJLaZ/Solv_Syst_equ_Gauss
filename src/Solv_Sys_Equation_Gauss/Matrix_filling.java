package Solv_Sys_Equation_Gauss;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Matrix_filling {
    static void Filling_Matrix(Matrix matrix, String audit) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        Pattern pattern = Pattern.compile(audit);
        Pattern sup = Pattern.compile("[-]?(([0-9]*[a-z])|([0-9]+[.][0-9]+)|([a-z]))[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))+");
        Matcher matcher, sup_matcher;

        while (counter < matrix.GetSize()) {
            System.out.printf("\n Input %dth equation\n", counter + 1);
            String string_input = sc.next();
            matcher = pattern.matcher(string_input);
            sup_matcher = sup.matcher(string_input);

            if (matcher.find() == true && sup_matcher.find() == true) {
                Filling_veriables(matrix, string_input);
                Filling_results(matrix, string_input);
            }
            else {
                System.out.println("\nincorrect input try again");
                continue;
            }
            counter += 1;
        }
    }

    static void Filling_veriables(Matrix matrix, String input){
        String temp_series[];
        Pattern series;
        Matcher ser;

        for (int j = 0; j < matrix.GetSize(); j++) {
            series = Pattern.compile(String.format("[-]?(([0-9]*[%1$s])|([0-9]*[.][0-9]*[%1$s]))", ((char) (97 + j))));
            ser = series.matcher(input);
            if (ser.find() == true) {
                temp_series = ser.group().split(String.format("%s", ((char) (97 + j))));
                if (temp_series.length == 0)
                    matrix.Add(1);
                else if (temp_series[0].equals("-"))
                    matrix.Add(-1);
                else
                    matrix.Add(Float.valueOf(temp_series[0]));
            } else
                matrix.Add(0);
        }
    }

    static void Filling_results(Matrix matrix, String input){
        String temp_series[];
        Pattern series2 = Pattern.compile("[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))");
        Matcher ser2 = series2.matcher(input);

        ser2.find();
        temp_series = ser2.group().split("[=]{1}");
        matrix.Add(Float.valueOf(temp_series[1]));
    }
}
