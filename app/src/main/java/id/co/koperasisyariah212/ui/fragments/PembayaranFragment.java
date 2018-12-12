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
public class PembayaranFragment extends Fragment {

    private PembayaranPlnPostpaidFragment pembayaranPlnPostpaidFragment = new PembayaranPlnPostpaidFragment();
    private PembayaranPlnNonTaglisFragment pembayaranPlnNonTaglisFragment = new PembayaranPlnNonTaglisFragment();
    private PembayaranTagihanTelkomFragment pembayaranTagihanTelkomFragment = new PembayaranTagihanTelkomFragment();
    private PembayaranTagihanLainFragment pembayaranTagihanLainFragment = new PembayaranTagihanLainFragment();
    private PembayaranBpjsFragment pembayaranBpjsFragment = new PembayaranBpjsFragment();

    public PembayaranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Pembayaran");
        return inflater.inflate(R.layout.fragment_pembayaran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView mnPlnPostpaid = (CardView) view.findViewById(R.id.mn_pln_postpaid);
        CardView mnPlnNonTaglis = (CardView) view.findViewById(R.id.mn_pln_non_taglis);
        CardView mnTagihanTelkom = (CardView) view.findViewById(R.id.mn_tagihan_telkom);
        CardView mnTagihanLain = (CardView) view.findViewById(R.id.mn_tagihan_lain);
        CardView mnBpjs = (CardView) view.findViewById(R.id.mn_bpjs);

        mnPlnPostpaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembayaranPlnPostpaidFragment);
            }
        });

        mnPlnNonTaglis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembayaranPlnNonTaglisFragment);
            }
        });

        mnTagihanTelkom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembayaranTagihanTelkomFragment);
            }
        });

        mnTagihanLain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembayaranTagihanLainFragment);
            }
        });

        mnBpjs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembayaranBpjsFragment);
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
