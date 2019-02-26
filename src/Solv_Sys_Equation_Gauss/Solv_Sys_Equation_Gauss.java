package Solv_Sys_Equation_Gauss;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Solv_Sys_Equation_Gauss extends Matrix_filling{
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        //Preparing for solving
        matrix.SetSize(Preparation_decision.InpIntVal("amount of equations", 1, Integer.MAX_VALUE));
        Output.SetAmount_num_aft_dot(Preparation_decision.InpIntVal("amount of numbers after dot", 0, 8));
        String audit = Preparation_decision.CreateAuditStr(matrix.GetSize());

        Preparation_decision.ShowExample(matrix.GetSize());

        Matrix_filling.Filling_Matrix(matrix, audit);
        //Search for a matrix solution
        matrix.Transposition_equations();
        matrix.Solving();

        matrix.Create_Result();
    }

    //Filling Matrix

}