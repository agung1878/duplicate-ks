package id.co.koperasisyariah212.ui.fragments.registrasi;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.SimpananKoperasi;
import id.co.koperasisyariah212.service.HttpService;
import id.co.koperasisyariah212.ui.adapters.ListSimpananKoperasiAdapter;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterSimpananFragment extends FragmentHelper implements BlockingStep {

    private HttpService httpService = new HttpService();
    private List<Field> fields;
    private RecyclerView rvSimpananKoperasi;
    private ListSimpananKoperasiAdapter listSimpananKoperasiAdapter;
    private List<SimpananKoperasi> simpananKoperasis = new ArrayList<>();


    public RegisterSimpananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listSimpananKoperasiAdapter = new ListSimpananKoperasiAdapter();
        return inflater.inflate(R.layout.fragment_register_simpanan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fields = new ArrayList<>();

        rvSimpananKoperasi = (RecyclerView) view.findViewById(R.id.rv_simpanan_koperasi);
        rvSimpananKoperasi.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvSimpananKoperasi.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerDecor = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvSimpananKoperasi.addItemDecoration(dividerDecor);
        rvSimpananKoperasi.setItemAnimator(new DefaultItemAnimator());
        rvSimpananKoperasi.setAdapter(listSimpananKoperasiAdapter);
    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.goToNextStep();
            }
        }, 0L);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
