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
public class GantiPinFragment extends FragmentHelper {



    private TextInputLayout inputLblPinLama, inputLblPinBaru, inputLblKonfirmasiPin;
    private TextInputEditText inputTxtPinLama, inputTxtPinBaru, inputTxtKonfirmasiPin;
    private Button btnSimpan;
    private List <Field> fields;

    public GantiPinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setTitle("Ganti Pin");
        return inflater.inflate(R.layout.fragment_ganti_pin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        inputLblPinBaru = (TextInputLayout) view.findViewById(R.id.input_lbl_pin_baru);
        inputTxtPinBaru = (TextInputEditText) view.findViewById(R.id.input_txt_pin_baru);
        Field pinBaru = new Field(inputLblPinBaru, inputTxtPinBaru);
        fields.add(pinBaru);

        inputLblPinLama = (TextInputLayout) view.findViewById(R.id.input_lbl_pin_lama);
        inputTxtPinLama = (TextInputEditText) view.findViewById(R.id.input_txt_pin_lama);
        Field pinLama = new Field(inputLblPinLama, inputTxtPinLama);
        fields.add(pinLama);

        inputLblKonfirmasiPin = (TextInputLayout) view.findViewById(R.id.input_lbl_konfirmasi_pin);
        inputTxtKonfirmasiPin = (TextInputEditText) view.findViewById(R.id.input_txt_konfirmasi_pin);
        Field konfirmasiPin = new Field(inputLblKonfirmasiPin, inputTxtKonfirmasiPin);
        fields.add(konfirmasiPin);

        btnSimpan = (Button) view.findViewById(R.id.btn_simpan);

    btnSimpan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!hasEmpty(fields)){
                resetForm(fields);
                Toast.makeText(getActivity(), "Simpan", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
}
