package id.co.koperasisyariah212.ui.fragments.registrasi;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.DataManager;
import id.co.koperasisyariah212.domain.Register;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteRegistrasiFragment extends FragmentHelper implements BlockingStep {



    private DataManager dataManager;


    public CompleteRegistrasiFragment() {
        // Required empty public constructor

    }

    public static  CompleteRegistrasiFragment newInstance() {

        return new CompleteRegistrasiFragment();

    }

    private TextView tvNoKtp, tvNamaLengkap, tvNamaIbu, tvTempatlahir, tvTglLahir, tvEmail,
            tvPhone, tvJenisKelamin, tvPendapatan, tvPekerjaaan, tvProvinsiKtp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_registrasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvNoKtp = (TextView) view.findViewById(R.id.tv_no_ktp);
        tvNamaLengkap = (TextView) view.findViewById(R.id.tv_nama_lengkap);
        tvNamaIbu = (TextView) view.findViewById(R.id.tv_nama_ibu);
        tvTempatlahir = (TextView) view.findViewById(R.id.tv_tempat_lahir);
        tvTglLahir = (TextView) view.findViewById(R.id.tv_tanggal_lahir);
        tvEmail = (TextView) view.findViewById(R.id.tv_email);
        tvPhone = (TextView) view.findViewById(R.id.tv_phone);
        tvJenisKelamin = (TextView) view.findViewById(R.id.tv_jenis_kelamin);
        tvPendapatan = (TextView) view.findViewById(R.id.tv_pendapatan);
        tvPekerjaaan = (TextView) view.findViewById(R.id.tv_pekerjaan);
        tvProvinsiKtp = (TextView) view.findViewById(R.id.tv_provinsi_ktp);

    }

    @Override
    public  void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataManager) {
            dataManager = (DataManager) context;
        } else {
            throw new IllegalStateException("Activity must implement DataManager interface!");
        }
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

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {


       Register register = dataManager.getData();
       Log.i("no", "nik : " + register.getNik());
       Log.i("nama", "lengkap : " + register.getFullname());
       Log.i("pendapatan", "income : " + register.getIncome().toString());
       Log.i("get", "provinsi : " + register.getProvinsi().getId());
       tvNoKtp.setText(register.getNik());
       tvNamaLengkap.setText(register.getFullname());
       tvNamaIbu.setText(register.getIbuKandung());
       tvTempatlahir.setText(register.getTempatLahir());
       tvTglLahir.setText(register.getTanggalLahir());
       tvEmail.setText(register.getEmail());
       tvPhone.setText(register.getNoTelepon());
       tvJenisKelamin.setText(register.getJenisKelamin());
       tvPendapatan.setText(register.getIncome().toString());
       tvPekerjaaan.setText(register.getOccupation().toString());
       tvProvinsiKtp.setText(register.getProvinsi().toString());
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
