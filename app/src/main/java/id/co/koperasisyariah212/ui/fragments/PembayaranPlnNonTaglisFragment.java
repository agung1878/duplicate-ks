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
public class PembayaranPlnNonTaglisFragment extends FragmentHelper {

    TextInputLayout inputLblNoRegistrasi, inputLblPin;
    TextInputEditText inputTxtNoRegistrasi, inputTxtPin;
    Button btnTestKoneksi, btnCekStatus, btnKirim;
    private List<Field> fields;

    public PembayaranPlnNonTaglisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("PLN Non Taglis");
        return inflater.inflate(R.layout.fragment_pembayaran_pln_non_taglis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        inputLblNoRegistrasi = (TextInputLayout) view.findViewById(R.id.input_lbl_no_registrasi_non_taglis);
        inputTxtNoRegistrasi = (TextInputEditText) view.findViewById(R.id.input_txt_no_registrasi_non_taglis);
        Field noRegistrasi = new Field(inputLblNoRegistrasi, inputTxtNoRegistrasi);
        fields.add(noRegistrasi);

        inputLblPin = (TextInputLayout) view.findViewById(R.id.input_lbl_pin_non_taglis);
        inputTxtPin = (TextInputEditText) view.findViewById(R.id.input_txt_pin_non_taglis);
        Field pin = new Field(inputLblPin, inputTxtPin);
        fields.add(pin);

        btnTestKoneksi = (Button) view.findViewById(R.id.btn_test_koneksi_non_taglis);
        btnCekStatus = (Button) view.findViewById(R.id.btn_cek_status_non_taglis);
        btnKirim = (Button) view.findViewById(R.id.btn_kirim_non_taglis);

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
