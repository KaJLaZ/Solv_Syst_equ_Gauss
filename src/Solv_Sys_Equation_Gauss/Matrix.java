package Solv_Sys_Equation_Gauss;

public class Matrix {
    private float matrix[][];
    private int veriables;

    public float[][] GetMatrix() {
        return matrix;
    }
    public void SetMatrix(float[][] matrix) {
        this.matrix = matrix;
    }
    public int GetVeriables() {
        return veriables;
    }
    public void SetVeriables(int veriables) {
        this.veriables = veriables;
    }



     void Show_Matrix() {
        for (int i = 0; i < veriables; i++) {
            System.out.print("| ");
            for (int j = 0; j <veriables; j++) {
                System.out.printf("{%.2f}", matrix[i][j]);
            }
            System.out.print(" |\n");
        }
        System.out.println("");
    }

     void Infinite_Decisions( String string) {
        System.out.print(string);
        int i = 0;
        int j = 0;
        for (; i < veriables; i++) {
            j=0;
            for (; j < veriables; j++) {
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
    //Solving
     float[] Division(float series[], float number) {
        float new_series[] = new float[series.length];
        for (int i = 0; i < series.length; i++) {
            if (series[i] != 0)
                new_series[i] = (series[i] / number);
            else
                new_series[i] = 0;
        }
        return new_series;
    }

     float[] Multiplication_Series(float series[], float number) {
        float new_series[] = new float[series.length];
        for (int i = 0; i < series.length; i++)
            new_series[i] = (series[i] * number);
        return new_series;
    }

     float[] Subtraction_Of_Series(float f_ser[], float s_ser[]) {
        float new_series[] = new float[f_ser.length];
        for (int i = 0; i < f_ser.length; i++)
            new_series[i] = (f_ser[i] - s_ser[i]);
        return new_series;
    }

     float[][] Transposition_equations() {
        int product = 1;
        for (int i = 0; i < veriables; i++)
            product *= matrix[i][i];
        if (product == 0) {
            for (int i = 0; i < veriables; i++) {
                if (matrix[i][i] == 0) {
                    int j = 1;
                    while (j < veriables) {
                        if (matrix[j][i] != 0 && matrix[i][j] != 0) {
                            float temp_list[] = matrix[i];
                            matrix[i] = matrix[j];
                            matrix[j] = temp_list;
                            j = veriables;
                            continue;
                        }
                        j += 1;
                    }
                }
            }
            product = 1;
            for (int i = 1; i < veriables; i++)
                product *= matrix[i][i];
            if (product == 0) {
                Infinite_Decisions("Solution of the system of equations\n");
                System.exit(0);
            }
            Show_Matrix();
        }
        return matrix;
    }
    void Solving(){
        for (int i = 0; i < veriables; i++) {
            int j = i + 1;
            while (j < veriables) {
                if (matrix[i][i] != 0) {
                    float[] number = Multiplication_Series(matrix[i], matrix[j][i]);
                    number = Division(number, matrix[i][i]);
                    matrix[j] = Subtraction_Of_Series(matrix[j], number);
                    Show_Matrix();
                }
                else
                    Transposition_equations();
                j += 1;
            }
            if (matrix[veriables - 1][veriables - 1] == 0)
                Transposition_equations();
        }
    }
}
