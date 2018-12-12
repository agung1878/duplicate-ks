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
public class PembayaranPlnPostpaidFragment extends FragmentHelper {

    TextInputLayout inputLblIdPelanggan, inputLblPin;
    TextInputEditText inputTxtIdPelanggan, inputTxtPin;
    Button btnTestKoneksi, btnCekStatus, btnKirim;
    private List<Field> fields;


    public PembayaranPlnPostpaidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("PLN Postpaid");
        return inflater.inflate(R.layout.fragment_pembayaran_pln_postpaid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        inputLblIdPelanggan = (TextInputLayout) view.findViewById(R.id.input_lbl_id_pelanggan_pln_postpaid);
        inputTxtIdPelanggan = (TextInputEditText) view.findViewById(R.id.input_txt_id_pelanggan_pln_postpaid);
        Field idPelanggan = new Field(inputLblIdPelanggan, inputTxtIdPelanggan);
        fields.add(idPelanggan);

        inputLblPin = (TextInputLayout) view.findViewById(R.id.input_lbl_pin_pln_postpaid);
        inputTxtPin = (TextInputEditText) view.findViewById(R.id.input_txt_pin_pln_postpaid);
        Field pin = new Field(inputLblPin, inputTxtPin);
        fields.add(pin);

        btnTestKoneksi = (Button) view.findViewById(R.id.btn_test_koneksi_pln_postpaid);
        btnCekStatus = (Button) view.findViewById(R.id.btn_cek_status_pln_postpaid);
        btnKirim = (Button) view.findViewById(R.id.btn_kirim_pln_postpaid);

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
