package id.co.koperasisyariah212.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembayaranBpjsFragment extends FragmentHelper {

    TextInputLayout inputLblIdPelanggan, inputLblBulanBayar, inputLblPin;
    TextInputEditText inputTxtIdPelanggan, inputTxtBulanBayar, inputTxtPin;
    Button btnTestKoneksi, btnCekStatus, btnKirim;
    private List<Field> fields;


    public PembayaranBpjsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("BPJS");
        return inflater.inflate(R.layout.fragment_pembayaran_bpjs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        inputLblIdPelanggan = (TextInputLayout) view.findViewById(R.id.input_lbl_id_pelanggan_bpjs);
        inputTxtIdPelanggan = (TextInputEditText) view.findViewById(R.id.input_txt_id_pelanggan_bpjs);
        Field idPelanggan = new Field(inputLblIdPelanggan, inputTxtIdPelanggan);
        fields.add(idPelanggan);

        inputLblBulanBayar = (TextInputLayout) view.findViewById(R.id.input_lbl_bulan_bayar_bpjs);
        inputTxtBulanBayar = (TextInputEditText) view.findViewById(R.id.input_txt_bulan_bayar_bpjs);
        Field bulanBayar = new Field(inputLblBulanBayar, inputTxtBulanBayar);
        fields.add(bulanBayar);

        inputLblPin = (TextInputLayout) view.findViewById(R.id.input_lbl_pin_bpjs);
        inputTxtPin = (TextInputEditText) view.findViewById(R.id.input_txt_pin_bpjs);
        Field pin = new Field(inputLblPin, inputTxtPin);
        fields.add(pin);

        btnTestKoneksi = (Button) view.findViewById(R.id.btn_test_koneksi_bpjs);
        btnCekStatus = (Button) view.findViewById(R.id.btn_cek_status_bpjs);
        btnKirim = (Button) view.findViewById(R.id.btn_kirim_bpjs);

        btnTestKoneksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Test Koneksi", Toast.LENGTH_SHORT).show();
            }
        });

        btnCekStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Cek Status", Toast.LENGTH_SHORT).show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasEmpty(fields)) {
                    resetForm(fields);
                    Toast.makeText(getActivity(), "Kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
