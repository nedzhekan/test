import java.lang.*;

public class getValues {
    public String firstOperand = "";
    public String secondOperand = "";
    public String operator = "";
    public String getValuesError = "";

    public boolean removeVoidValue(String str){
        int length = str.length();
        String[] operators = new String[] {"+", "-", "*", "/"};
        int positionOperator = 0;
        boolean afterOperator = false;

        for (int i = 0; i < length; i++){
            String check = String.valueOf(str.charAt(i));

            if (!check.trim().isEmpty()) {
                //for находим позицию оператора
                for (int j = 0; j < 4; j++) {
                    if (check.trim().equals(operators[j]) && !afterOperator) {
                        operator = check;
                        positionOperator = i;
                        afterOperator = true;
                    } else if (check.trim().equals(operators[j]) && !operator.equals("")) {
                        getValuesError = "Введено два оператора";
                        return false;
                    } else if ((i == length - 1 && operator.equals(""))) {
                        getValuesError = "Оператор не найден";
                        return false;
                    }
                }
                // получаем первое и второе значение отедльно
                if (!afterOperator) {
                    firstOperand += check;
                } else if (positionOperator != i) {
                    secondOperand += check;
                }
                if (i == length - 1 && (firstOperand.equals("") || secondOperand.equals(""))) {
                    getValuesError = "Один из операндов не введен";
                    return false;
                }
            }
        }

        return true;
    }
}
