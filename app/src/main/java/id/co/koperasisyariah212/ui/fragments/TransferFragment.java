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
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferFragment extends FragmentHelper {

    private TransferSesamaAnggotaFragment transferSesamaAnggotaFragment = new TransferSesamaAnggotaFragment();
    private TransferAntarKoperasiFragment transferAntarKoperasiFragment = new TransferAntarKoperasiFragment();

    public TransferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setTitle("Transfer");
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView mnSesamaAnggota = (CardView) view.findViewById(R.id.mn_transfer_sesama_anggota);
        CardView mnAntarKoperasi = (CardView) view.findViewById(R.id.mn_transfer_antar_koperasi);

        mnSesamaAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(transferSesamaAnggotaFragment);
            }
        });

        mnAntarKoperasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(transferAntarKoperasiFragment);
            }
        });

    }

}
