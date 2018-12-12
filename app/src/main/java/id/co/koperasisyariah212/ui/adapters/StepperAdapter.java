package id.co.koperasisyariah212.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import id.co.koperasisyariah212.domain.Register;
import id.co.koperasisyariah212.ui.fragments.registrasi.CompleteRegistrasiFragment;
import id.co.koperasisyariah212.ui.fragments.registrasi.RegisterAlamatFragment;
import id.co.koperasisyariah212.ui.fragments.registrasi.RegisterInformasiUmumFragment;
import id.co.koperasisyariah212.ui.fragments.registrasi.RegisterSimpananFragment;
import id.co.koperasisyariah212.ui.fragments.registrasi.RegisterTabunganInvesFragment;

public class StepperAdapter extends AbstractFragmentStepAdapter {
    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";
    private static final String DATA = "register";

    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                final RegisterInformasiUmumFragment step1 = new RegisterInformasiUmumFragment();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b1);
                return step1;
            case 1:
                final RegisterAlamatFragment step2 = new RegisterAlamatFragment();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step2.setArguments(b2);
                return step2;
            case 2:
                final RegisterSimpananFragment step3 = new RegisterSimpananFragment();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                step3.setArguments(b3);
                return step3;
            case 3:
                final RegisterTabunganInvesFragment step4 = new RegisterTabunganInvesFragment();
                Bundle b4 = new Bundle();
                b4.putInt(CURRENT_STEP_POSITION_KEY, position);
                step4.setArguments(b4);
                return step4;
            case 4:
                final CompleteRegistrasiFragment step5 = new CompleteRegistrasiFragment();
                Bundle b5 = new Bundle();
                b5.putInt(CURRENT_STEP_POSITION_KEY, position);
                step5.getArguments();
                return step5;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position){
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Informasi Umum") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Alamat") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Simpanan Koperasi") //can be a CharSequence instead
                        .create();
            case 3:
                return new StepViewModel.Builder(context)
                        .setTitle("Tabungan Investasi") //can be a CharSequence instead
                        .create();
            case 4:
                return new StepViewModel.Builder(context)
                        .setTitle("Complete") //can be a CharSequence instead
                        .create();
        }
        return null;
    }
}
