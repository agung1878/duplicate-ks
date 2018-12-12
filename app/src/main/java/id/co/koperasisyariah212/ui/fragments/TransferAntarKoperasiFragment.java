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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.Rekening;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferAntarKoperasiFragment extends FragmentHelper {

    private TextInputLayout inputLblFrom, inputLblCode, inputLblTo, inputLblNominal,
            inputLblKeterangan, inputLblPass;
    private TextInputEditText inputTxtCode, inputTxtNominal, inputTxtKeterangan, inputTxtPass;
    private AutoCompleteTextView inputTxtFrom, inputTxtTo;
    private Button btnTestKoneksi, btnKirim;
    private List<Field> fields;
    private List<Rekening> rekenings = new ArrayList<>();
    public TransferAntarKoperasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setTitle("Transfer Sesama Koperasi");
        return inflater.inflate(R.layout.fragment_transfer_antar_koperasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        prepareData();
        ArrayAdapter adapterRekening = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, rekenings);

        inputLblFrom = (TextInputLayout) view.findViewById(R.id.input_lbl_from);
        inputTxtFrom = (AutoCompleteTextView) view.findViewById(R.id.input_txt_from);
        Field from = new Field(inputLblFrom, inputTxtFrom);
        fields.add(from);
        inputTxtFrom.setAdapter(adapterRekening);
        inputTxtFrom.setOnTouchListener(new SprinnerTouchListener(inputTxtFrom));

        inputLblCode = (TextInputLayout) view.findViewById(R.id.input_lbl_code);
        inputTxtCode = (TextInputEditText) view.findViewById(R.id.input_txt_code);
        Field code = new Field(inputLblCode, inputTxtCode);
        fields.add(code);

        inputLblTo = (TextInputLayout) view.findViewById(R.id.input_lbl_to);
        inputTxtTo = (AutoCompleteTextView) view.findViewById(R.id.input_txt_to);
        Field to = new Field(inputLblTo, inputTxtTo);
        fields.add(to);
        inputTxtTo.setAdapter(adapterRekening);
        inputTxtTo.setOnTouchListener(new SprinnerTouchListener(inputTxtTo));

        inputLblNominal = (TextInputLayout) view.findViewById(R.id.input_lbl_nominal);
        inputTxtNominal = (TextInputEditText) view.findViewById(R.id.input_txt_nominal);
        Field nominal = new Field(inputLblNominal, inputTxtNominal);
        fields.add(nominal);

        inputLblKeterangan = (TextInputLayout) view.findViewById(R.id.input_lbl_keterangan);
        inputTxtKeterangan = (TextInputEditText) view.findViewById(R.id.input_txt_keterangan);
        Field keterangan = new Field(inputLblKeterangan, inputTxtKeterangan);
        fields.add(keterangan);

        inputLblPass = (TextInputLayout) view.findViewById(R.id.input_lbl_pass);
        inputTxtPass = (TextInputEditText) view.findViewById(R.id.input_txt_pass);
        Field pass = new Field(inputLblPass, inputTxtPass);
        fields.add(pass);


        btnTestKoneksi = (Button) view.findViewById(R.id.btn_test_koneksi);
        btnKirim = (Button) view.findViewById(R.id.btn_kirim);

        btnTestKoneksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Test Koneksi", Toast.LENGTH_SHORT).show();
            }

        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasEmpty(fields)) {
                    resetForm(fields);
                    Toast.makeText(getActivity(), "kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        private void prepareData(){
            Rekening rekening1 = new Rekening();
            rekening1.setName("Dian");
            rekening1.setNumber("101010");
            Rekening rekening2 = new Rekening();
            rekening2.setName("Aji");
            rekening2.setNumber("202020");
            Rekening rekening3 = new Rekening();
            rekening3.setName("Agung");
            rekening3.setNumber("303030");
            rekenings.add(rekening1);
            rekenings.add(rekening2);
            rekenings.add(rekening3);

    }

}
