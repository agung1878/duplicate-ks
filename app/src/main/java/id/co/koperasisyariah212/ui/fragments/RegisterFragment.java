package id.co.koperasisyariah212.ui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.Income;
import id.co.koperasisyariah212.domain.Kecamatan;
import id.co.koperasisyariah212.domain.Kelurahan;
import id.co.koperasisyariah212.domain.Kota;
import id.co.koperasisyariah212.domain.Occupation;
import id.co.koperasisyariah212.domain.Provinsi;
import id.co.koperasisyariah212.domain.SimpananKoperasi;
import id.co.koperasisyariah212.response.ListKecamatanResponse;
import id.co.koperasisyariah212.response.ListKelurahanResponse;
import id.co.koperasisyariah212.response.ListKotaResponse;
import id.co.koperasisyariah212.response.ListProvinsiResponse;
import id.co.koperasisyariah212.service.HttpService;
import id.co.koperasisyariah212.ui.adapters.ListSimpananKoperasiAdapter;
import id.co.koperasisyariah212.ui.adapters.ListTabunganInvestasiAdapter;
import id.co.koperasisyariah212.ui.adapters.ListWakafAdapter;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;
import id.co.koperasisyariah212.ui.helper.RealPathUtil;

public class RegisterFragment extends FragmentHelper {

    private HttpService httpService = new HttpService();
    private List<Provinsi> provinsis = new ArrayList<>();

    private List<Occupation> occupations = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();

    private AutoCompleteTextView txtJenisKelamin, txtKomunitas,
            txtProvinsi, txtProvinsiKorespondensi,
            txtKota, txtKecamatan, txtKelurahan, txtPendapatan, txtPekerjaan,
            txtKotaKoresPondensi, txtKecamatanKorespondensi,
            txtKelurahanKorespondensi, txtOccupation, txtIncome;
    private List<Field> fields;
    private TextInputLayout inputJenisKelamin, inputKomunitas,
            inputProvinsi, inputProvinsiKorespondensi,
            inputKota, inputKecamatan, inputKelurahan,
            inputPendapatan, inputPekerjaan, inputKotaKorespondensi,
            inputKecamatanKorespondensi, inputKelurahanKorespondensi,
            inputOccupation, inputIncome;
    private TextInputEditText inputTxtJenisKelamin, inputTxtKomunitas,
            inputTxtProvinsi, inputTxtProvinsiKorespondensi,
            inputTxtKota, inputTxtKecamatan, inputTxtKelurahan,
            inputTxtPendapatan, inputTxtPekerjaan, inputTxtKotaKorespondensi, inputTxtKecamatanKorespondensi,
            inputTxtKelurahanKorespondensi;
    private RadioButton rbLaki, rbPerempuan;
    private TextView tvJudulWakaf;
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

    private RecyclerView rvTabunganInvestasi, rvWakaf, rvSimpananKoperasi;
    private ListSimpananKoperasiAdapter listSimpananKoperasiAdapter;
    private ListTabunganInvestasiAdapter listTabunganInvestasi;
    private ListWakafAdapter listWakafAdapter;
    private List<SimpananKoperasi> simpananKoperasis = new ArrayList<>();

    private ImageView imageThumbnail;
    private ImageButton btnImagePicker;
    private TextInputEditText imageName;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageThumbnailUri;
    private boolean mReadExternalPermissionGranted = false;
    private final static int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setTitle("Register");

        if (!mReadExternalPermissionGranted)
            getReadExternalPermission();

        listSimpananKoperasiAdapter = new ListSimpananKoperasiAdapter();
        listTabunganInvestasi = new ListTabunganInvestasiAdapter();
        listWakafAdapter = new ListWakafAdapter();

        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageThumbnail = (ImageView) view.findViewById(R.id.image_thumbnail);
        btnImagePicker = (ImageButton) view.findViewById(R.id.btn_pick_image);
        btnImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT < 19) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                } else {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE_REQUEST);
                }
            }
        });

        fields = new ArrayList<>();
        cvKorespondensi = (CardView) view.findViewById(R.id.cv_korespondensi);
        rbLaki = (RadioButton) view.findViewById(R.id.rb_laki);

        ArrayAdapter adapterOccupation = new ArrayAdapter<Occupation>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, Occupation.values());
        txtOccupation = (AutoCompleteTextView) view.findViewById(R.id.input_txt_pekerjaan);
        inputOccupation = (TextInputLayout) view.findViewById(R.id.input_lbl_pekerjaan);
        Field occu = new Field(inputOccupation, txtOccupation);
        fields.add(occu);
        txtOccupation.setAdapter(adapterOccupation);
        txtOccupation.setOnTouchListener(new SprinnerTouchListener(txtOccupation));



        ArrayAdapter adapterIncome = new ArrayAdapter<Income>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, Income.values());
        inputIncome = (TextInputLayout) view.findViewById(R.id.input_lbl_pendapatan);
        txtIncome = (AutoCompleteTextView) view.findViewById(R.id.input_txt_pendapatan);
        Field income = new Field(inputIncome, txtIncome);
        fields.add(income);
        txtIncome.setAdapter(adapterIncome);
        txtIncome.setOnTouchListener(new SprinnerTouchListener(txtIncome));

        rbPerempuan = (RadioButton) view.findViewById(R.id.rb_perempuan);
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
        tvJudulWakaf = (TextView) view.findViewById(R.id.tv_judul_wakaf);
        if (listWakafAdapter.getItemCount() > 0){
            tvJudulWakaf.setVisibility(View.VISIBLE);
        } else {
            tvJudulWakaf.setVisibility(View.GONE);
        }
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
                new GetKotaByProvinsiKtpExe(p.getId()).execute();
            }
        });
        txtProvinsiKorespondensi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Provinsi p2 = adapterProvinsiKorespondensi.getItem(i);
                new GetKotaByProvinsiKorespondensiExe(p2.getId()).execute();
            }
        });
        txtKota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kota k = adapterKotaKtp.getItem(i);
                new GetKecamatanByKotaExe(k.getId()).execute();
            }
        });
        txtKotaKoresPondensi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kota k2 = adapterKotaKorespondensi.getItem(i);
                new GetKecamatanByKotaKorespondensiExe(k2.getId()).execute();
            }
        });
        txtKecamatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kecamatan kecamatan = adapterKecamatan.getItem(i);
                new GetKelurahanByKecamatan(kecamatan.getId()).execute();
            }
        });
        txtKecamatanKorespondensi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kecamatan kecamatan2 = adapterKecamatanKorespondensi.getItem(i);
                new GetKelurahanByKecamatanKorespondensiExe(kecamatan2.getId()).execute();
            }
        });


        Field field1 = new Field(inputProvinsi, inputTxtProvinsi);
        fields.add(field1);
        txtProvinsi.setOnTouchListener(new SprinnerTouchListener(txtProvinsi));
        new GetProvinsiKtpExe().execute();

        Field field2 = new Field(inputProvinsiKorespondensi, inputTxtProvinsiKorespondensi);
        fields.add(field2);
        txtProvinsiKorespondensi.setOnTouchListener(new SprinnerTouchListener(txtProvinsiKorespondensi));
        new GetProvinsiKorespondensiExe().execute();

        Field field3 = new Field(inputKota, inputTxtKota);
        fields.add(field3);
        txtKota.setOnTouchListener(new SprinnerTouchListener(txtKota));

        Field field4 = new Field(inputKecamatan, inputTxtKecamatan);
        fields.add(field4);
        txtKecamatan.setOnTouchListener(new SprinnerTouchListener(txtKecamatan));

        Field field5 = new Field(inputKelurahan, inputTxtKelurahan);
        fields.add(field5);
        txtKelurahan.setOnTouchListener(new SprinnerTouchListener(txtKelurahan));

        Field field7 = new Field(inputKecamatanKorespondensi, inputTxtKecamatanKorespondensi);
        fields.add(field7);
        txtKecamatanKorespondensi.setOnTouchListener(new SprinnerTouchListener(txtKecamatanKorespondensi));

        Field field8 = new Field(inputKotaKorespondensi, inputTxtKotaKorespondensi);
        fields.add(field8);
        txtKotaKoresPondensi.setOnTouchListener(new SprinnerTouchListener(txtKotaKoresPondensi));

        Field field9 = new Field(inputKelurahanKorespondensi, inputTxtKelurahanKorespondensi);
        fields.add(field9);
        txtKelurahanKorespondensi.setOnTouchListener(new SprinnerTouchListener(txtKelurahanKorespondensi));

        rvSimpananKoperasi = (RecyclerView) view.findViewById(R.id.rv_simpanan_koperasi);
        rvSimpananKoperasi.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvSimpananKoperasi.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerDecor = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvSimpananKoperasi.addItemDecoration(dividerDecor);
        rvSimpananKoperasi.setItemAnimator(new DefaultItemAnimator());
        rvSimpananKoperasi.setAdapter(listSimpananKoperasiAdapter);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null){
            Uri uri = data.getData();
            String name = getRealPath(getActivity(), uri).substring(getRealPath(getActivity(), uri).lastIndexOf("/")+1);
            Log.d("name", "image : " + name);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                imageThumbnail.setImageBitmap(bitmap);
                if (imageThumbnail != null){
                    imageThumbnail.setVisibility(View.VISIBLE);
                }
                imageThumbnailUri = Uri.fromFile(new File(getRealPath(getActivity(), uri)));
                imageName = (TextInputEditText) getView().findViewById(R.id.input_txt_upload_ktp);
                imageName.setText(name);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getRealPath(Context context, Uri fileUri) {
        String realPath;
        // SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(context, fileUri);
        }
        // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19) {
            realPath = RealPathUtil.getRealPathFromURI_API11to18(context, fileUri);
        }
        // SDK > 19 (Android 4.4) and up
        else {
            realPath = RealPathUtil.getRealPathFromURI_API19(context, fileUri);
        }
        return realPath;
    }

    private void getReadExternalPermission() {
        if (ContextCompat.checkSelfPermission(this.getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            mReadExternalPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
    }


    private class GetProvinsiKtpExe extends AsyncTask<Void, Void, ListProvinsiResponse>{

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
//
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
//
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
//
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
//
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
//
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
//
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
//
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
