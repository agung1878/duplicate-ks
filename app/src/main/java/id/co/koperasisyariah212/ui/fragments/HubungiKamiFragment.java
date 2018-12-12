package id.co.koperasisyariah212.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.CustomerCenter;
import id.co.koperasisyariah212.ui.helper.Field;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;


public class HubungiKamiFragment extends FragmentHelper {

    private TextInputEditText input_txt_center, input_txt_compose_message;
    private TextInputLayout input_lbl_center, input_lbl_compose_message;
    private Button bt_kirim_hubungi_kami;
    private RadioButton rb_sms_center, rb_email;
    private AutoCompleteTextView inputTxtCenter;
    private List<Field> fields;
    private List<CustomerCenter> customerCenters = new ArrayList<>();
    public HubungiKamiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setTitle("Hubungi Kami");
        return inflater.inflate(R.layout.fragment_hubungi_kami, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fields = new ArrayList<>();

        prepareData();
        ArrayAdapter adapterCustomerCenter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, customerCenters);

        input_lbl_center = (TextInputLayout) view.findViewById(R.id.input_lbl_center);
        inputTxtCenter = (AutoCompleteTextView) view.findViewById(R.id.input_txt_center);
        Field field = new Field(input_lbl_center, input_txt_center);
        fields.add(field);
        inputTxtCenter.setAdapter(adapterCustomerCenter);
        inputTxtCenter.setOnTouchListener(new SprinnerTouchListener(inputTxtCenter));

        input_lbl_compose_message = (TextInputLayout) view.findViewById(R.id.input_lbl_compose_message);
        input_txt_compose_message = (TextInputEditText) view.findViewById(R.id.input_txt_compose_message);
        Field field1 = new Field(input_lbl_compose_message, input_txt_compose_message);
        fields.add(field1);

        rb_sms_center = (RadioButton) view.findViewById(R.id.rb_sms_center);
        rb_email = (RadioButton) view.findViewById(R.id.rb_email);

        bt_kirim_hubungi_kami = (Button) view.findViewById(R.id.btn_kirim_hubungi_kami);
        bt_kirim_hubungi_kami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasEmpty(fields)) {
                    resetForm(fields);
                    Toast.makeText(getActivity(), "Kirim", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        private void prepareData(){
            CustomerCenter customerCenter1 = new CustomerCenter();
            customerCenter1.setName("CC Wilayah");
            customerCenter1.setNumber("1");
            CustomerCenter customerCenter2 = new CustomerCenter();
            customerCenter2.setName("CC Wilayah");
            customerCenter2.setNumber("2");
            customerCenters.add(customerCenter1);
            customerCenters.add(customerCenter2);

    }
}
