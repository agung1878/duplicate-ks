package id.co.koperasisyariah212.ui.fragments.registrasi;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.DataManager;
import id.co.koperasisyariah212.domain.Income;
import id.co.koperasisyariah212.domain.Occupation;
import id.co.koperasisyariah212.domain.Register;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;
import id.co.koperasisyariah212.ui.helper.RealPathUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterInformasiUmumFragment extends FragmentHelper implements BlockingStep {

    private static final String DATA = "register";
    private ImageView imageThumbnail;
    private ImageButton btnImagePicker, btnDatePicker;
    private TextInputEditText imageName;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageThumbnailUri;
    private boolean mReadExternalPermissionGranted = false;
    private final static int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    private RadioButton rbLaki, rbPerempuan;
    private RadioGroup rgJenisKelamin;

    private List<Occupation> occupations = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();

    private AutoCompleteTextView txtOccupation, txtIncome;
    private List<Field> fields;
    private TextInputLayout inputOccupation, inputIncome;

    private DataManager dataManager;

    private TextInputEditText txtNamaLengkap, txtNamaIbu, txtTempatLahir, txtTanggalLahir, txtEmail, txtPhone, txtNoKtp;

    int score = 0;
    public static RegisterInformasiUmumFragment newInstance(){
        return new RegisterInformasiUmumFragment();
    }

    public RegisterInformasiUmumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (!mReadExternalPermissionGranted)
            getReadExternalPermission();

        return inflater.inflate(R.layout.fragment_register_informasi_umum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
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

        txtTanggalLahir = (TextInputEditText) view.findViewById(R.id.input_txt_tgl_lahir);
        btnDatePicker = (ImageButton) view.findViewById(R.id.btn_pick_date);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        txtEmail = (TextInputEditText) view.findViewById(R.id.input_txt_email);
        txtNamaLengkap = (TextInputEditText) view.findViewById(R.id.input_txt_nama_lengkap);
        txtNamaIbu = (TextInputEditText) view.findViewById(R.id.input_txt_nama_ibu);
        txtTempatLahir = (TextInputEditText) view.findViewById(R.id.input_txt_tempat_lahir);
        txtPhone = (TextInputEditText) view.findViewById(R.id.input_txt_no_hp);
        txtNoKtp = (TextInputEditText) view.findViewById(R.id.input_txt_no_ktp);

    }

    private void getScore(int current_score) {
        score += current_score;
    }

    private void showDatePicker() {
        SelectDateFragment date = new SelectDateFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            txtTanggalLahir.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };

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

                Register register = new Register();

                register.setNik(txtNoKtp.getText().toString());
                register.setFullname(txtNamaLengkap.getText().toString());
                register.setIbuKandung(txtNamaIbu.getText().toString());
                register.setTempatLahir(txtTempatLahir.getText().toString());
                register.setTanggalLahir(txtTanggalLahir.getText().toString());


                final RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.rg_jenis_kelamin);
                int radioId = radioGroup.getCheckedRadioButtonId();
                RadioButton singleButton = (RadioButton) getView().findViewById(radioId);
                register.setJenisKelamin(singleButton.getText().toString());
                Log.i("Jenis", "kelamin : " + register.getJenisKelamin());

                register.setEmail(txtEmail.getText().toString());
                register.setNoTelepon(txtPhone.getText().toString());

                if (txtOccupation.getText().toString().equals("PEGAWAI_SIPIL")) {
                    register.setOccupation(Occupation.PEGAWAI_SIPIL);
                } else if (txtOccupation.getText().toString().equals("KARYAWAN_SWASTA")) {
                    register.setOccupation(Occupation.KARYAWAN_SWASTA);
                } else if (txtOccupation.getText().toString().equals("PENGUSAHA")) {
                    register.setOccupation(Occupation.PENGUSAHA);
                } else if (txtOccupation.getText().toString().equals("PEDAGANG")) {
                    register.setOccupation(Occupation.PEDAGANG);
                } else if (txtOccupation.getText().toString().equals("PETANI")) {
                    register.setOccupation(Occupation.PETANI);
                } else if (txtOccupation.getText().toString().equals("LAINNYA")) {
                    register.setOccupation(Occupation.LAINNYA);
                }

                if (txtIncome.getText().toString().equals("KURANG_DARI_5_JUTA_PER_BULAN")){
                    register.setIncome(Income.KURANG_DARI_5_JUTA_PER_BULAN);
                }else if (txtIncome.getText().toString().equals("KURANG_DARI_10_JUTA_PER_BULAN")){
                    register.setIncome(Income.KURANG_DARI_10_JUTA_PER_BULAN);
                }else if (txtIncome.getText().toString().equals("LEBIH_DARI_10_JUTA_PER_BULAN")){
                    register.setIncome(Income.LEBIH_DARI_10_JUTA_PER_BULAN);
                }


                dataManager.saveData(register); //kayaknya data manager ini yg jadi masalah, kalo diilangin gk bisa ngeset ama nge get di akhir


                callback.goToNextStep();

            }
        }, 1000L);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
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
        Toast.makeText(getContext(), "Hayo ada error tuh", Toast.LENGTH_SHORT).show();
    }

}
