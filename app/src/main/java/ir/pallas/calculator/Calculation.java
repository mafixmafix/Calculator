package ir.pallas.calculator;

import org.mariuszgromada.math.mxparser.Expression;

/**
 * Copyright (C), Mafix - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Mafix <Ahangarani.amir@gmail.com>, on 11/10/2017.
 */

public class Calculation {

    private static String currentExpression;
    private static String currentResult;
    private CalculationResult calculationResult;
    private Expression expression;

    public Calculation() {
        expression = new Expression();
    }

    public void setCalculationResultListener(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        currentExpression = "";
        currentResult = "";
    }

    /**
     * Delete a single character from currentExpression, unless empty
     * "" - invalid
     * 589 - valid
     * 694*248 - valid
     */
    public void deleteCharacter() {
        if (currentExpression.length() > 0) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResult.onExpressionChanged(currentExpression, true);
        } else {
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
    }

    /**
     * Delete entire expression unless empty
     * "" - invalid
     */
    public void deleteExpression() {
        if (currentExpression.equals("")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
        } else {
            currentExpression = "";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    /**
     * Append number to currentExpression if valid:
     * "0" & number is 0 - invalid
     * "12345678909876543" (more than 16ch) - invalid
     *
     * @param number Append to currentExpression
     */
    public void appendNumber(String number) {
        if (currentExpression.startsWith("0") && number.equals("0")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
        } else {
            if (currentExpression.length() <= 16) {
                currentExpression += number;
                calculationResult.onExpressionChanged(currentExpression, true);
            } else {
                calculationResult.onExpressionChanged("Expression Too Long", false);
            }
        }
    }

    /**
     * Append an Operator to currentExpression, if valid:
     * 44 - valid
     * 26+36 - valid
     * 42+ - invalid
     * "" - invalid
     *
     * @param operator one of:
     *                 "+"
     *                 "/"
     *                 "-"
     *                 "*"
     */
    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)) {
            currentExpression += operator;
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    /**
     * See type comment for appendOperator
     */
    public void appendDecimal() {
        if (validateExpression(currentExpression)) {
            currentExpression += ".";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    /**
     * if currentExpression passes checks, pass currentExpression to symbols object,
     * then return the result.
     */
    public void performEvaluation() {
        if (validateExpression(currentExpression)) {
            expression.setExpressionString(currentExpression);
            Double result = expression.calculate();
            currentExpression = Double.toString(result);
            calculationResult.onExpressionChanged(currentExpression, true);
        } else {
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
    }

    /**
     * Helper function to validate expressions against the following checks:
     * "" - invalid
     * 56484 - valid
     *
     * @param expression arithmetic expression
     * @return result
     */
    private boolean validateExpression(String expression) {
        boolean result = true;
        if (expression.endsWith("+") ||
                expression.endsWith("-") ||
                expression.endsWith("*") ||
                expression.endsWith("/")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
            result = false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChanged("Empty Expression", false);
            result = false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChanged("Expression Too Long", false);
            result = false;
        }
        return result;
    }

    interface CalculationResult {
        void onExpressionChanged(String result, boolean successful);
    }
}
