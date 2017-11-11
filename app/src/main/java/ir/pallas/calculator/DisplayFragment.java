package ir.pallas.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements CalculatorContract.PublishToView {


    @BindView(R.id.lbl_display_expression)
    TextView displayExpression;
    @BindView(R.id.lbl_display_result)
    TextView displayResult;
    private CalculatorContract.ForwardDisplayInteractionToPresenter forwardInteraction;

    public DisplayFragment() {
        // Required empty public constructor
    }

    public static DisplayFragment newInstance() {
        return new DisplayFragment();
    }

    public void setPresenter(
            CalculatorContract.ForwardDisplayInteractionToPresenter forwardInteraction
    ) {
        this.forwardInteraction = forwardInteraction;
    }

    @OnClick(R.id.imb_delete)
    public void onDeleteShortClick() {
        forwardInteraction.onDeleteShortClick();
    }

    @OnLongClick(R.id.imb_delete)
    public boolean onDeleteLongClick() {
        forwardInteraction.onDeleteLongClick();
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showResult(String result) {
        displayResult.setText(result);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
