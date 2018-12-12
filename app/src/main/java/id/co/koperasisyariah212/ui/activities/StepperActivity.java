package id.co.koperasisyariah212.ui.activities;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.DataManager;
import id.co.koperasisyariah212.domain.Register;
import id.co.koperasisyariah212.ui.adapters.StepperAdapter;

public class StepperActivity extends AppCompatActivity implements StepperLayout.StepperListener, DataManager {

    private StepperLayout mStepperLayout;
    private StepperAdapter mStepperAdapter;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private Register mData;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperAdapter = new StepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(this);

        mData = savedInstanceState != null ? (Register) savedInstanceState.getParcelable("register") : null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, mStepperLayout.getCurrentStepPosition());
        outState.putParcelable("register", (Parcelable) mData);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        final int currentStepPosition = mStepperLayout.getCurrentStepPosition();
        if (currentStepPosition > 0){
            mStepperLayout.onBackClicked();
        }else {
            finish();
        }
    }

    @Override
    public void onCompleted(View completeButton) {
        Toast.makeText(this, "onCompleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(VerificationError verificationError) {
        Toast.makeText(this, "onError! -> " + verificationError.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void saveData(Register data) {
        this.mData = data;
    }

    @Override
    public Register getData() {
        return this.mData;
    }
}
