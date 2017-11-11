package ir.pallas.calculator;

/**
 * Copyright (C), Mafix - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by Mafix <Ahangarani.amir@gmail.com>, on 11/7/2017.
 */

public interface CalculatorContract {

    //view handles these methods (DisplayFragment
    interface PublishToView {

        void showResult(String result);

        void showToastMessage(String message);
    }

    //passes click events from view(DisplayFragment), to the presenter
    interface ForwardDisplayInteractionToPresenter {

        void onDeleteShortClick();

        void onDeleteLongClick();
    }

    //passes click events from view(InputFragment), to the presenter
    interface ForwardInputInteractionToPresenter {

        void onNumberClick(int number);

        void onOperatorClick(String operator);

        void onDecimalClick();

        void onEvaluateClick();
    }
}
