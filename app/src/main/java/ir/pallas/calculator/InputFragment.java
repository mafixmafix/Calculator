package ir.pallas.calculator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private CalculatorContract.ForwardInputInteractionToPresenter forwardInteraction;

    public InputFragment() {
        // Required empty public constructor
    }

    public static InputFragment newInstance() {
        return new InputFragment();
    }

    public void setPresenter(
            CalculatorContract.ForwardInputInteractionToPresenter forwardInteraction
    ) {
        this.forwardInteraction = forwardInteraction;
    }

    @OnClick({
            R.id.btn_number_one, R.id.btn_number_two, R.id.btn_number_three, R.id.btn_number_four,
            R.id.btn_number_five, R.id.btn_number_six, R.id.btn_number_seven, R.id.btn_number_eight,
            R.id.btn_number_nine, R.id.btn_number_zero
    })
    public void onNumberClick(Button button) {
        forwardInteraction.onNumberClick(Integer.parseInt(button.getText().toString()));
    }

    @OnClick({
            R.id.btn_operator_add, R.id.btn_operator_divide, R.id.btn_operator_multiply,
            R.id.btn_operator_subtract
    })
    public void onOperatorClick(Button button) {
        forwardInteraction.onOperatorClick(button.getText().toString());
    }

    @OnClick(R.id.btn_decimal)
    public void onDecimalClick(Button button) {
        forwardInteraction.onDecimalClick();
    }

    @OnClick(R.id.btn_evaluate)
    public void onEvaluateClick(Button button) {
        forwardInteraction.onEvaluateClick();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
