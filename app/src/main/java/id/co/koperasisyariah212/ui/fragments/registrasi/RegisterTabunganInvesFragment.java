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
import android.widget.TextView;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.service.HttpService;
import id.co.koperasisyariah212.ui.adapters.ListTabunganInvestasiAdapter;
import id.co.koperasisyariah212.ui.adapters.ListWakafAdapter;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTabunganInvesFragment extends FragmentHelper implements BlockingStep {

    private HttpService httpService = new HttpService();
    private List<Field> fields;
    private TextView tvJudulWakaf;
    private RecyclerView rvTabunganInvestasi, rvWakaf;
    private ListTabunganInvestasiAdapter listTabunganInvestasi;
    private ListWakafAdapter listWakafAdapter;

    public RegisterTabunganInvesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        listTabunganInvestasi = new ListTabunganInvestasiAdapter();
        listWakafAdapter = new ListWakafAdapter();
        return inflater.inflate(R.layout.fragment_register_tabungan_inves, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        tvJudulWakaf = (TextView) view.findViewById(R.id.tv_judul_wakaf);
        if (listWakafAdapter.getItemCount() > 0){
            tvJudulWakaf.setVisibility(View.VISIBLE);
        } else {
            tvJudulWakaf.setVisibility(View.GONE);
        }

        rvTabunganInvestasi = (RecyclerView) view.findViewById(R.id.rv_tabungan_investasi);
        rvTabunganInvestasi.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvTabunganInvestasi.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvTabunganInvestasi.addItemDecoration(decoration);
        rvTabunganInvestasi.setItemAnimator(new DefaultItemAnimator());
        rvTabunganInvestasi.setAdapter(listTabunganInvestasi);

        rvWakaf = (RecyclerView) view.findViewById(R.id.rv_wakaf);
        rvWakaf.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rvWakaf.setLayoutManager(lm);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvWakaf.addItemDecoration(dividerItemDecoration);
        rvWakaf.setItemAnimator(new DefaultItemAnimator());
        rvWakaf.setAdapter(listWakafAdapter);
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
        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

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
