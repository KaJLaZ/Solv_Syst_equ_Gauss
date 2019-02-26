package Solv_Sys_Equation_Gauss;

import java.util.Scanner;

public abstract class Preparation_decision {
    static int InpIntVal(String description, int min, int max){
        Scanner sc = new Scanner(System.in);
        int value = 0;
        System.out.printf("Input %s: \n", description);
        while (true) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value < min || value > max)
                    continue;
                return value;
            } else {
                System.out.printf("Input %s: \n", description);
                sc.next();
                continue;
            }
        }
    }

    static void ShowExample(int size){
        System.out.println("Input system of equations like this\n");
        System.out.println("Where the first veriable will be a, second b, and so on\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d%s+", j + 1, ((char) (97 + j)));
            }
            System.out.printf("\b=%d\n", i + 1);
        }
    }

    static String CreateAuditStr(int size){
        StringBuilder audit = new StringBuilder("^[-]?(([0-9]+[a])|([0-9]+[.][0-9]+[a])|([a]))?");
        for (int i = 0; i < size - 1; i++)
            audit.append(String.format("[+|-]?(([0-9]*[%1$s])|([0-9]+[.][0-9]+[%1$s])|([%1$s]))?", ((char) (98 + i))));
        audit.append(String.format("[=]{1}[-]?(([0-9]+)|([0-9]+[.][0-9]+))$"));
        return audit.toString();
    }
}
