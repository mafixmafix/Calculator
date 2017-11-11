package ir.pallas.calculator;

/**
 * Copyright (C), Mafix - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Mafix <Ahangarani.amir@gmail.com>, on 11/7/2017.
 */

public class CalculatorPresenter implements CalculatorContract.ForwardDisplayInteractionToPresenter,
        CalculatorContract.ForwardInputInteractionToPresenter, Calculation.CalculationResult {

    private CalculatorContract.PublishToView publishResult;
    private Calculation calculation;

    public CalculatorPresenter(CalculatorContract.PublishToView publishResult) {
        this.publishResult = publishResult;
        this.calculation = new Calculation();
        calculation.setCalculationResultListener(this);
    }

    @Override
    public void onDeleteShortClick() {
        calculation.deleteCharacter();
    }

    @Override
    public void onDeleteLongClick() {
        calculation.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calculation.appendNumber(Integer.toString(number));
    }

    @Override
    public void onOperatorClick(String operator) {
        calculation.appendOperator(operator);
    }

    @Override
    public void onDecimalClick() {
        calculation.appendDecimal();
    }

    @Override
    public void onEvaluateClick() {
        calculation.performEvaluation();
    }

    @Override
    public void onExpressionChanged(String result, boolean successful) {
        if (successful) {
            publishResult.showResult(result);
        } else {
            publishResult.showToastMessage(result);
        }
    }
}
