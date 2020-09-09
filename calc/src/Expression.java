public class Expression {
    public String result = "";
    public String ExpressionError = "";
    private String[] romanNumbers = new String[] {};

    public boolean checkOperands(String firstOperand, String secondOperand, String operator){
        String[] arabicNumbers = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        romanNumbers = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] operators = new String[] {"+", "-", "*", "/"};
        //промежуточный результат
        int res = 11;
        boolean romanSys = false;
        boolean expressionCompleted = false;
        //проверка первого операнда
        int positionFirstNumber = 0;
        boolean systemFirstNumber = false;
        boolean checkFirstNumber = false;
        // проверка воторого операнда
        int positionSecondNumber = 0;
        boolean systemSecondNumber = false;
        boolean checkSecondNumber = false;

        for (int i = 0; i < 10; i++) {
            //проверка первого операнда
            if (!checkFirstNumber) {
                if(firstOperand.equals(arabicNumbers[i])) {
                    positionFirstNumber = i;
                    systemFirstNumber = true;
                    checkFirstNumber = true;
                } else if (firstOperand.equals(romanNumbers[i])) {
                    positionFirstNumber = i;
                    checkFirstNumber = true;
                }
                if (i == 9) {
                    if (!checkFirstNumber) {
                        ExpressionError = "Первый операнд введен не верно или превышает ограничения";
                        return false;
                    }
                }
            }

            //проверка второго операнда
            if (!checkSecondNumber) {
                if(secondOperand.equals(arabicNumbers[i])) {
                    positionSecondNumber = i;
                    systemSecondNumber = true;
                    checkSecondNumber = true;
                } else if (secondOperand.equals(romanNumbers[i])) {
                    positionSecondNumber = i;
                    checkSecondNumber = true;
                }
                if (i == 9){
                    if (!checkSecondNumber) {
                        ExpressionError = "Второй операнд введен не верно или превышает ограничения";
                        return false;
                    }
                }
            }

            // проверка какая система исчисления
            if (i == 9 ) {
                if (!romanSys){
                    if (!systemFirstNumber && !systemSecondNumber) {
                        romanSys = true;
                    }
                }

                if (systemFirstNumber != systemSecondNumber){
                    ExpressionError = "Операнды из разных систем исчисления";
                    return false;
                } else {
                    if (romanSys){
                        firstOperand = arabicNumbers[positionFirstNumber];
                        secondOperand = arabicNumbers[positionSecondNumber];
                    }
                    expressionCompleted = true;
                }
            }

            if (expressionCompleted) {
                if (operators[0].equals(operator)) {
                    res = Integer.parseInt(firstOperand) + Integer.parseInt(secondOperand);
                } else if (operators[1].equals(operator)) {
                    res = Integer.parseInt(firstOperand) - Integer.parseInt(secondOperand);
                } else if (operators[2].equals(operator)) {
                    res = Integer.parseInt(firstOperand) * Integer.parseInt(secondOperand);
                } else if (operators[3].equals(operator)) {
                    res = Integer.parseInt(firstOperand) / Integer.parseInt(secondOperand);
                } else {
                    ExpressionError = "Ошибка операции: " + operator;
                    return false;
                }
            }

            //проверка
            if (i == 9) {
                if (!romanSys) {
                    result = String.valueOf(res);
                    return true;
                } else {
                    if(res > 11){
                        result = String.valueOf(res);
                        result = romansResult(result);
                        return true;
                    }
                    if (res <= 0) {
                        return false;
                    }
                    result = String.valueOf(romanNumbers[res - 1]);
                    return true;
                }
            }
        }
        return true;
    }

    private String romansResult(String res){
        String[] romansTwoNumbers = new String[] {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        if(res.length() == 3){
            res = romansTwoNumbers[9];
            return res;
        }

        String firstVal = String.valueOf(res.charAt(0));
        String secondVal = String.valueOf(res.charAt(1));

        if (Integer.parseInt(secondVal) == 0) {
            res = romansTwoNumbers[Integer.parseInt(firstVal)-1];
        } else {
            res = romansTwoNumbers[Integer.parseInt(firstVal)-1] + romanNumbers[Integer.parseInt(secondVal)-1];
        }

        return  res;
    }
}
