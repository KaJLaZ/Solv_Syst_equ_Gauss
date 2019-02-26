package Solv_Sys_Equation_Gauss;

public class Matrix {
    private float matrix[][];
    private int size;
    int col_pos = 0;
    int row_pos = 0;

    public int GetSize() {
        return size;
    }
    public void SetSize(int veriables)
    {
        this.size = veriables;
        matrix = new float[veriables][veriables+1];
    }

     void Add(float number) {
         matrix[col_pos][row_pos] = number;
         row_pos++;
         if (row_pos > size) {
             row_pos = 0;
             col_pos++;
         }
         if (col_pos == size)
             col_pos = 0;
     }

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
        Output.Show_Matrix(matrix);
        int product = 1;
        for (int i = 0; i < size; i++)
            product *= matrix[i][i];
        if (product == 0) {
            for (int i = 0; i < size; i++) {
                if (matrix[i][i] == 0) {
                    int j = 1;
                    while (j < size) {
                        if (matrix[j][i] != 0 && matrix[i][j] != 0) {
                            float temp_list[] = matrix[i];
                            matrix[i] = matrix[j];
                            matrix[j] = temp_list;
                            j = size;
                            continue;
                        }
                        j += 1;
                    }
                }
            }
            product = 1;
            for (int i = 1; i < size; i++)
                product *= matrix[i][i];
            if (product == 0) {
                Output.Infinite_Decisions(matrix);
                System.exit(0);
            }
            Output.Show_Matrix(matrix);
        }
        return matrix;
    }
    void Solving(){
        for (int i = 0; i < size; i++) {
            int j = i + 1;
            while (j < size) {
                if (matrix[i][i] != 0) {
                    float[] number = Multiplication_Series(matrix[i], matrix[j][i]);
                    number = Division(number, matrix[i][i]);
                    matrix[j] = Subtraction_Of_Series(matrix[j], number);
                    Output.Show_Matrix(matrix);
                }
                else
                    Transposition_equations();
                j += 1;
            }
            if (matrix[size - 1][size - 1] == 0)
                Transposition_equations();
        }
    }
    void Create_Result(){
        int i = 0;
        float result[] = new float[size];
        for (; i < size; i++)
            result[i] = 1;
        i = size;
        while (i > 0) {
            float temp = 0;
            for (int j = 0; j < size; j++) {
                if (j != i - 1)
                    temp += matrix[i - 1][j] * result[j] * -1;
            }
            temp += matrix[i - 1][size];
            if (matrix[i - 1][i - 1] != 0)
                result[i - 1] = temp / matrix[i - 1][i - 1];
            else
                result[i - 1] = temp;
            i--;
        }
    Output.ShowResult(result);
    }
}
