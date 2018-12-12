package id.co.koperasisyariah212.ui.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.NominalTopUp;
import id.co.koperasisyariah212.domain.OperatorUmum;
import id.co.koperasisyariah212.ui.activities.DashboardActivity;
import id.co.koperasisyariah212.ui.constant.RequestPermissionCode;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;
import id.co.koperasisyariah212.ui.helper.RequestPermissionHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembelianVoucherPulsaFragment extends FragmentHelper {

    private TextInputLayout inputLblJenisVoucher, inputLblNominal, inputLblPhone, inputLblPin;
    private TextInputEditText inputTxtPhone, inputTxtPin;
    private AutoCompleteTextView inputTxtJenisVoucher, inputTxtNominal;
    private Button btnTestKoneksi, btnKirim, btnCekStatus;
    private ImageButton btnContact;
    private List<Field> fields;
    private List<OperatorUmum> operatorUmums = new ArrayList<>();
    private List<NominalTopUp> nominalTopUps = new ArrayList<>();
    RequestPermissionHandler requestPermissionHandler;
    private final int REQUEST_CONTACT = 111;

    public PembelianVoucherPulsaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        requestPermissionHandler = new RequestPermissionHandler();
        return inflater.inflate(R.layout.fragment_pembelian_voucher_pulsa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        prepareDataOperator();
        ArrayAdapter adapterOperatorUmum = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, operatorUmums);

        prepareDataNominal();
        ArrayAdapter adapterNominal = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, nominalTopUps);

        inputLblJenisVoucher = (TextInputLayout) view.findViewById(R.id.input_lbl_jenis_voucher);
        inputTxtJenisVoucher = (AutoCompleteTextView) view.findViewById(R.id.input_txt_jenis_voucher);
        Field jenisVoucher = new Field(inputLblJenisVoucher, inputTxtJenisVoucher);
        fields.add(jenisVoucher);
        inputTxtJenisVoucher.setAdapter(adapterOperatorUmum);
        inputTxtJenisVoucher.setOnTouchListener(new SprinnerTouchListener(inputTxtJenisVoucher));

        inputLblPhone = (TextInputLayout) view.findViewById(R.id.input_lbl_phone);
        inputTxtPhone = (TextInputEditText) view.findViewById(R.id.input_txt_phone);
        Field phone = new Field(inputLblPhone, inputTxtPhone);
        fields.add(phone);
        btnContact = (ImageButton) view.findViewById(R.id.btn_contact);

        inputLblNominal = (TextInputLayout) view.findViewById(R.id.input_lbl_nominal);
        inputTxtNominal = (AutoCompleteTextView) view.findViewById(R.id.input_txt_nominal);
        Field nominal = new Field(inputLblNominal, inputTxtNominal);
        fields.add(nominal);
        inputTxtNominal.setAdapter(adapterNominal);
        inputTxtNominal.setOnTouchListener(new SprinnerTouchListener(inputTxtNominal));

        inputLblPin = (TextInputLayout) view.findViewById(R.id.input_lbl_pin);
        inputTxtPin = (TextInputEditText) view.findViewById(R.id.input_txt_pin);
        Field pin = new Field(inputLblPin, inputTxtPin);
        fields.add(pin);

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
            public void onClick(View v) {
                if(!hasEmpty(fields)){
                    resetForm(fields);
                    Toast.makeText(getActivity(), "Kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasPermission(Manifest.permission.READ_CONTACTS)){
                    showContact();
                } else {
                    requestPermissionHandler.requestPermission(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, RequestPermissionCode.REQUEST_READ_CONTACTS, new RequestPermissionHandler.RequestPermissionListener() {
                        @Override
                        public void onSuccess() {
                            showContact();
                        }
                        @Override
                        public void onFailed() {
                            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CONTACT :
                if (resultCode == Activity.RESULT_OK) inputTxtPhone.setText(fetchContact(data.getData()));
                break;
        }
    }

    private void prepareDataOperator(){
        OperatorUmum operatorUmum1 = new OperatorUmum();
        operatorUmum1.setName("Telkomsel");
        OperatorUmum operatorUmum2 = new OperatorUmum();
        operatorUmum2.setName("Indosat");
        OperatorUmum operatorUmum3 = new OperatorUmum();
        operatorUmum3.setName("Smartfreen");
        operatorUmums.add(operatorUmum1);
        operatorUmums.add(operatorUmum2);
        operatorUmums.add(operatorUmum3);
    }

    private void prepareDataNominal() {
        NominalTopUp nominalTopUp1 = new NominalTopUp();
        nominalTopUp1.setNominal("Rp. 20.000,-");
        NominalTopUp nominalTopUp2 = new NominalTopUp();
        nominalTopUp2.setNominal("Rp. 25.000,-");
        NominalTopUp nominalTopUp3 = new NominalTopUp();
        nominalTopUp3.setNominal("Rp. 50.000,-");
        NominalTopUp nominalTopUp4 = new NominalTopUp();
        nominalTopUp4.setNominal("Rp. 100.000,-");
        nominalTopUps.add(nominalTopUp1);
        nominalTopUps.add(nominalTopUp2);
        nominalTopUps.add(nominalTopUp3);
        nominalTopUps.add(nominalTopUp4);
    }

    private void showContact(){
        Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_CONTACT);
    }

    private String fetchContact(Uri contactData){
        String phoneNumber = "";

        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor phones = getActivity().getContentResolver().query(contactData, projection, null, null, null);

        if(phones.getCount() > 0)
            while (phones.moveToNext())
                phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        phones.close();

        return phoneNumber;
    }

}
