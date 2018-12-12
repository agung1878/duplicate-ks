package id.co.koperasisyariah212.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.koperasisyariah212.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembelianFragment extends Fragment {

    private PembelianPlnPrepaidFragment pembelianPlnPrepaidFragment = new PembelianPlnPrepaidFragment();
    private PembelianTopUpVoucherFragment pembelianTopUpVoucherFragment = new PembelianTopUpVoucherFragment();



    public PembelianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pembelian");
        return inflater.inflate(R.layout.fragment_pembelian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView mnPembelianPlnPrepaid = (CardView) view.findViewById(R.id.mn_pembelian_pln_prepaid);
        CardView mnPembelianTopUpVoucher = (CardView) view.findViewById(R.id.mn_pembelian_top_up_voucher);

        mnPembelianPlnPrepaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembelianPlnPrepaidFragment);
            }
        });

        mnPembelianTopUpVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembelianTopUpVoucherFragment);
            }
        });
    }

    private void changeFragment(Fragment fragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_home, fragment)
                .addToBackStack(null)
                .commit();
    }
}
