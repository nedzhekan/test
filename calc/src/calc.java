import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.*;

public class calc {
    public static void main(String[] args){
        getValues getValues = new getValues();
        Expression Expression = new Expression();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        boolean check = false;

        try {
            str = input.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // получаем операнды и оператор и проверяем их по шаблону пример: a + b
        check = getValues.removeVoidValue(str);

        if (!check) {
            System.out.println(getValues.getValuesError);
            System.exit(0);
        }

        //проверка правильности операндов (на ограничения, одну систему исчесления и тд)
        //Проведение операции и вывод результата
        check = Expression.checkOperands(getValues.firstOperand, getValues.secondOperand, getValues.operator);

        if (!check) {
            System.out.println(Expression.ExpressionError);
            System.exit(0);
        }

        //вывод
        System.out.printf("Результат: %s \n", Expression.result);
    }


}
