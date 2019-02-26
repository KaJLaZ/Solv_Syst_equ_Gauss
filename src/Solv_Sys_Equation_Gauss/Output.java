package Solv_Sys_Equation_Gauss;

import java.math.BigDecimal;
import java.util.Formatter;

public abstract class Output {
    private static int amount_num_aft_dot;
    public static  void SetAmount_num_aft_dot(int amount) { amount_num_aft_dot = amount; }

    static void Show_Matrix(float[][] matrix) {
        BigDecimal num;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                num = new BigDecimal(matrix[i][j]).setScale(amount_num_aft_dot);
                System.out.printf("%10s", num.toString());
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

   static void Infinite_Decisions(float[][] matrix) {
        BigDecimal num;
        System.out.println("Solution of the system of equations");
        int i = 0;
        int j = 0;
        for (; i < matrix.length; i++) {
            j=0;
            for (; j < matrix.length; j++) {
                num = new BigDecimal(matrix[i][j]).setScale(amount_num_aft_dot);
                if (matrix[i][j] == 1)
                    System.out.printf("%s + ", ((char)(97+j)));
                else if (matrix[i][j] > 0)
                    System.out.printf("%s%s + ", num.toString(), ((char)(97+j)));
                else if (matrix[i][j] < 0) {
                   num = num.multiply(new BigDecimal(-1));
                    System.out.printf("\b\b- %s%s + ", num.toString(), ((char) (97 + j)));
                }
                else
                    System.out.printf("%s + ", num.toString());
            }
            num = new BigDecimal(matrix[i][j]).setScale(amount_num_aft_dot);
            System.out.printf("\b\b= %s\n", num.toString());
        }
        System.out.print("");
    }
    static void ShowResult(float[] result){
        BigDecimal num;
        for (int i = 0; i < result.length; i++) {
            num = new BigDecimal(result[i]).setScale(amount_num_aft_dot);
            System.out.printf("%s = %s\n", ((char) (97 + i)), num.toString());
        }
    }
}
