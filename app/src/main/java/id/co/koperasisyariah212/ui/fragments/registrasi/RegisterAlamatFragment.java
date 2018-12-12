package id.co.koperasisyariah212.ui.fragments.registrasi;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.DataManager;
import id.co.koperasisyariah212.domain.Kecamatan;
import id.co.koperasisyariah212.domain.Kelurahan;
import id.co.koperasisyariah212.domain.Kota;
import id.co.koperasisyariah212.domain.Provinsi;
import id.co.koperasisyariah212.domain.Register;
import id.co.koperasisyariah212.response.ListKecamatanResponse;
import id.co.koperasisyariah212.response.ListKelurahanResponse;
import id.co.koperasisyariah212.response.ListKotaResponse;
import id.co.koperasisyariah212.response.ListProvinsiResponse;
import id.co.koperasisyariah212.service.HttpService;
import id.co.koperasisyariah212.ui.fragments.RegisterFragment;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;
import id.co.koperasisyariah212.ui.helper.ProvinsiHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterAlamatFragment extends FragmentHelper implements BlockingStep {

    private HttpService httpService = new HttpService();
    private List<Provinsi> provinsis = new ArrayList<>();

    private AutoCompleteTextView txtProvinsi, txtProvinsiKorespondensi,
            txtKota, txtKecamatan, txtKelurahan,
            txtKotaKoresPondensi, txtKecamatanKorespondensi,
            txtKelurahanKorespondensi;
    private List<Field> fields;
    private TextInputLayout inputProvinsi, inputProvinsiKorespondensi,
            inputKota, inputKecamatan, inputKelurahan, inputKotaKorespondensi,
            inputKecamatanKorespondensi, inputKelurahanKorespondensi;
    private TextInputEditText inputTxtProvinsi, inputTxtProvinsiKorespondensi,
            inputTxtKota, inputTxtKecamatan, inputTxtKelurahan,
            inputTxtKotaKorespondensi, inputTxtKecamatanKorespondensi,
            inputTxtKelurahanKorespondensi;
    private CheckBox cbAlamat;
    private CardView cvKorespondensi;

    private ArrayAdapter<Provinsi> adapterProvinsiKtp;
    private ArrayAdapter<Provinsi> adapterProvinsiKorespondensi;
    private ArrayAdapter<Kota> adapterKotaKtp;
    private ArrayAdapter<Kota> adapterKotaKorespondensi;
    private ArrayAdapter<Kecamatan> adapterKecamatan;
    private ArrayAdapter<Kecamatan> adapterKecamatanKorespondensi;
    private ArrayAdapter<Kelurahan> adapterKelurahan;
    private ArrayAdapter<Kelurahan> adapterKelurahanKorespondensi;
    private DataManager dataManager;

    Register register = new Register();



    public RegisterAlamatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_alamat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();
        cvKorespondensi = (CardView) view.findViewById(R.id.cv_korespondensi);
        inputProvinsi = (TextInputLayout) view.findViewById(R.id.input_lbl_provinsi);
        txtProvinsi = (AutoCompleteTextView) view.findViewById(R.id.input_txt_provinsi);
        inputKota = (TextInputLayout) view.findViewById(R.id.input_lbl_kota);
        txtKota = (AutoCompleteTextView) view.findViewById(R.id.input_txt_kota);
        inputProvinsiKorespondensi = (TextInputLayout) view.findViewById(R.id.input_lbl_provinsi_korespondensi);
        txtProvinsiKorespondensi = (AutoCompleteTextView) view.findViewById(R.id.input_txt_provinsi_korespondensi);
        inputKecamatan = (TextInputLayout) view.findViewById(R.id.input_lbl_kecamatan);
        txtKecamatan = (AutoCompleteTextView) view.findViewById(R.id.input_txt_kecamatan);
        inputKelurahan = (TextInputLayout) view.findViewById(R.id.input_lbl_kelurahan);
        txtKelurahan = (AutoCompleteTextView) view.findViewById(R.id.input_txt_kelurahan);
        inputKotaKorespondensi = (TextInputLayout) view.findViewById(R.id.input_lbl_kota_korespondensi);
        txtKotaKoresPondensi = (AutoCompleteTextView) view.findViewById(R.id.input_txt_kota_korespondensi);
        inputKecamatanKorespondensi = (TextInputLayout) view.findViewById(R.id.input_lbl_kecamatan_korespondensi);
        txtKecamatanKorespondensi = (AutoCompleteTextView) view.findViewById(R.id.input_txt_kecamatan_korespondensi);
        inputKelurahanKorespondensi = (TextInputLayout) view.findViewById(R.id.input_lbl_kelurahan_korespondensi);
        txtKelurahanKorespondensi = (AutoCompleteTextView) view.findViewById(R.id.input_txt_kelurahan_korespondensi);

        cbAlamat = (CheckBox) view.findViewById(R.id.cb_alamat);
        cbAlamat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbAlamat.isChecked()){
                    cvKorespondensi.setVisibility(View.GONE);
                } else {
                    cvKorespondensi.setVisibility(View.VISIBLE);
                }
            }
        });

        txtProvinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Provinsi p = adapterProvinsiKtp.getItem(i);
                new RegisterAlamatFragment.GetKotaByProvinsiKtpExe(p.getId()).execute();
                Log.i("selected", "provinsi : " + p);
//                provinsiHelper.setProvinsi(p);
                register.setProvinsi(p);
            }
        });
        txtProvinsiKorespondensi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Provinsi p2 = adapterProvinsiKorespondensi.getItem(i);
                new RegisterAlamatFragment.GetKotaByProvinsiKorespondensiExe(p2.getId()).execute();
            }
        });
        txtKota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kota k = adapterKotaKtp.getItem(i);
                new RegisterAlamatFragment.GetKecamatanByKotaExe(k.getId()).execute();
            }
        });
        txtKotaKoresPondensi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kota k2 = adapterKotaKorespondensi.getItem(i);
                new RegisterAlamatFragment.GetKecamatanByKotaKorespondensiExe(k2.getId()).execute();
            }
        });
        txtKecamatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kecamatan kecamatan = adapterKecamatan.getItem(i);
                new RegisterAlamatFragment.GetKelurahanByKecamatan(kecamatan.getId()).execute();
            }
        });
        txtKecamatanKorespondensi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kecamatan kecamatan2 = adapterKecamatanKorespondensi.getItem(i);
                new RegisterAlamatFragment.GetKelurahanByKecamatanKorespondensiExe(kecamatan2.getId()).execute();
            }
        });


        Field field1 = new Field(inputProvinsi, inputTxtProvinsi);
        fields.add(field1);
        txtProvinsi.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtProvinsi));
        new RegisterAlamatFragment.GetProvinsiKtpExe().execute();

        Field field2 = new Field(inputProvinsiKorespondensi, inputTxtProvinsiKorespondensi);
        fields.add(field2);
        txtProvinsiKorespondensi.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtProvinsiKorespondensi));
        new RegisterAlamatFragment.GetProvinsiKorespondensiExe().execute();

        Field field3 = new Field(inputKota, inputTxtKota);
        fields.add(field3);
        txtKota.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtKota));

        Field field4 = new Field(inputKecamatan, inputTxtKecamatan);
        fields.add(field4);
        txtKecamatan.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtKecamatan));

        Field field5 = new Field(inputKelurahan, inputTxtKelurahan);
        fields.add(field5);
        txtKelurahan.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtKelurahan));

        Field field7 = new Field(inputKecamatanKorespondensi, inputTxtKecamatanKorespondensi);
        fields.add(field7);
        txtKecamatanKorespondensi.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtKecamatanKorespondensi));

        Field field8 = new Field(inputKotaKorespondensi, inputTxtKotaKorespondensi);
        fields.add(field8);
        txtKotaKoresPondensi.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtKotaKoresPondensi));

        Field field9 = new Field(inputKelurahanKorespondensi, inputTxtKelurahanKorespondensi);
        fields.add(field9);
        txtKelurahanKorespondensi.setOnTouchListener(new FragmentHelper.SprinnerTouchListener(txtKelurahanKorespondensi));
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

                Register register = dataManager.getData();
                Log.i("data", "umum :"  + register);

                dataManager.getData();


                dataManager.saveData(register);
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

    private class GetProvinsiKtpExe extends AsyncTask<Void, Void, ListProvinsiResponse> {
//
        @Override
        protected ListProvinsiResponse doInBackground(Void... voids) {
            return httpService.getAllProvinsi();
        }

        @Override
        protected void onPostExecute(ListProvinsiResponse listProvinsiResponse) {
            super.onPostExecute(listProvinsiResponse);
            adapterProvinsiKtp = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listProvinsiResponse.getData());
            txtProvinsi.setAdapter(adapterProvinsiKtp);
        }
    }

    private class GetProvinsiKorespondensiExe extends AsyncTask<Void, Void, ListProvinsiResponse>{

        @Override
        protected ListProvinsiResponse doInBackground(Void... voids) {
            return httpService.getAllProvinsi();
        }

        @Override
        protected void onPostExecute(ListProvinsiResponse listProvinsiResponse) {
            super.onPostExecute(listProvinsiResponse);
            adapterProvinsiKorespondensi = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listProvinsiResponse.getData());
            txtProvinsiKorespondensi.setAdapter(adapterProvinsiKorespondensi);
        }
    }

    private class GetKotaByProvinsiKtpExe extends AsyncTask<Void, Void, ListKotaResponse>{

        String idProvinsi;

        public GetKotaByProvinsiKtpExe(String idProvinsi) {
            this.idProvinsi = idProvinsi;
        }

        @Override
        protected ListKotaResponse doInBackground(Void... voids) {
            return httpService.getKotaByProvinsi(idProvinsi);
        }

        @Override
        protected void onPostExecute(ListKotaResponse listKotaResponse) {
            super.onPostExecute(listKotaResponse);
            adapterKotaKtp = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listKotaResponse.getData());
            txtKota.setAdapter(adapterKotaKtp);
        }
    }

    private class GetKotaByProvinsiKorespondensiExe extends AsyncTask<Void, Void, ListKotaResponse>{

        String idProvinsi;

        public GetKotaByProvinsiKorespondensiExe(String idProvinsi) {
            this.idProvinsi = idProvinsi;
        }

        @Override
        protected ListKotaResponse doInBackground(Void... voids) {
            return httpService.getKotaByProvinsi(idProvinsi);
        }

        @Override
        protected void onPostExecute(ListKotaResponse listKotaResponse) {
            super.onPostExecute(listKotaResponse);
            adapterKotaKorespondensi = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listKotaResponse.getData());
            txtKotaKoresPondensi.setAdapter(adapterKotaKorespondensi);
        }
    }

    private class GetKecamatanByKotaExe extends AsyncTask<Void, Void, ListKecamatanResponse>{

        String idKota;

        public GetKecamatanByKotaExe(String idKota) {
            this.idKota = idKota;
        }

        @Override
        protected ListKecamatanResponse doInBackground(Void... voids) {
            return httpService.getKecamatanByKota(idKota);
        }

        @Override
        protected void onPostExecute(ListKecamatanResponse listKecamatanResponse) {
            super.onPostExecute(listKecamatanResponse);
            adapterKecamatan = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listKecamatanResponse.getData());
            txtKecamatan.setAdapter(adapterKecamatan);

        }
    }

    private class GetKecamatanByKotaKorespondensiExe extends AsyncTask<Void, Void, ListKecamatanResponse>{

        String idKota;

        public GetKecamatanByKotaKorespondensiExe(String idKota) {
            this.idKota = idKota;
        }

        @Override
        protected ListKecamatanResponse doInBackground(Void... voids) {
            return httpService.getKecamatanByKota(idKota);
        }

        @Override
        protected void onPostExecute(ListKecamatanResponse listKecamatanResponse) {
            super.onPostExecute(listKecamatanResponse);
            adapterKecamatanKorespondensi = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listKecamatanResponse.getData());
            txtKecamatanKorespondensi.setAdapter(adapterKecamatanKorespondensi);

        }
    }

    private class GetKelurahanByKecamatan extends AsyncTask<Void, Void, ListKelurahanResponse>{

        String idKecamatan;

        public GetKelurahanByKecamatan(String idKecamatan) {
            this.idKecamatan = idKecamatan;
        }

        @Override
        protected ListKelurahanResponse doInBackground(Void... voids) {
            return httpService.getKelurahanByKecamatan(idKecamatan);
        }

        @Override
        protected void onPostExecute(ListKelurahanResponse listKelurahanResponse) {
            super.onPostExecute(listKelurahanResponse);
            adapterKelurahan = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listKelurahanResponse.getData());
            txtKelurahan.setAdapter(adapterKelurahan);

        }
    }

    private class GetKelurahanByKecamatanKorespondensiExe extends AsyncTask<Void, Void, ListKelurahanResponse>{

        String idKecamatan;

        public GetKelurahanByKecamatanKorespondensiExe(String idKecamatan) {
            this.idKecamatan = idKecamatan;
        }

        @Override
        protected ListKelurahanResponse doInBackground(Void... voids) {
            return httpService.getKelurahanByKecamatan(idKecamatan);
        }

        @Override
        protected void onPostExecute(ListKelurahanResponse listKelurahanResponse) {
            super.onPostExecute(listKelurahanResponse);
            adapterKelurahanKorespondensi = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listKelurahanResponse.getData());
            txtKelurahanKorespondensi.setAdapter(adapterKelurahanKorespondensi);
        }
    }

}
