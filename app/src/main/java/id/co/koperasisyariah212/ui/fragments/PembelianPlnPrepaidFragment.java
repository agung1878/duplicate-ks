package id.co.koperasisyariah212.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembelianPlnPrepaidFragment extends FragmentHelper {

    TextInputLayout inputLblIdPelanggan, inputLblPin;
    TextInputEditText inputTxtIdPelanggan, inputTxtPin;
    Button btnTestKoneksi, btnCekStatus, btnKirim;


    public PembelianPlnPrepaidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pembelian PLN Prepaid");
        return inflater.inflate(R.layout.fragment_pembelian_pln_prepaid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputLblIdPelanggan = (TextInputLayout) view.findViewById(R.id.input_lbl_id_pelanggan);
        inputLblPin = (TextInputLayout) view.findViewById(R.id.input_lbl_pin);
        inputTxtIdPelanggan = (TextInputEditText) view.findViewById(R.id.input_txt_id_pelanggan);
        inputTxtPin = (TextInputEditText) view.findViewById(R.id.input_txt_pin);
        btnTestKoneksi = (Button) view.findViewById(R.id.btn_test_koneksi);
        btnCekStatus = (Button) view.findViewById(R.id.btn_cek_status);
        btnKirim = (Button) view.findViewById(R.id.btn_kirim);

        btnTestKoneksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Test Koneksi", Toast.LENGTH_SHORT).show();
            }
        });

        btnCekStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cek Status", Toast.LENGTH_SHORT).show();
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasError()){
                    resetForm();
                    Toast.makeText(getActivity(), "Kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean hasError(){
        boolean error = false;

        if (TextUtils.isEmpty(inputTxtIdPelanggan.getText().toString())){
            error = true;
            inputLblIdPelanggan.setError("Cannot be empty");
            inputTxtIdPelanggan.setError("Cannot be empty");
        }

        if (TextUtils.isEmpty(inputTxtPin.getText().toString())){
            error = true;
            inputLblPin.setError("Cannot be empty");
            inputTxtPin.setError("Cannot be empty");
        }

        return error;
    }

    private void resetForm(){
        inputLblPin.setError(null);
        inputLblIdPelanggan.setError(null);

        inputTxtIdPelanggan.setError(null);
        inputTxtPin.setError(null);
    }

}
