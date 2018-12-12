package id.co.koperasisyariah212.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.MutasiTabungan;
import id.co.koperasisyariah212.ui.adapters.ListMutasiTabunganAdapter;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MutasiTabunganFragment extends FragmentHelper {
    private RecyclerView rvMutasiTabungan;
    private ListMutasiTabunganAdapter listMutasiTabunganAdapter;
    private List<MutasiTabungan> mutasiTabungans = new ArrayList<>();
    private DetailMutasiTabunganFragment detailMutasiTabunganFragment = new DetailMutasiTabunganFragment();

    public MutasiTabunganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setTitle("Mutasi Tabungan");
        listMutasiTabunganAdapter = new ListMutasiTabunganAdapter(mutasiTabungans, new ListMutasiTabunganAdapter.OnItemClickListener() {
            @Override
            public void onAdapterItemClick(int position) {
                changeFragment(detailMutasiTabunganFragment);
            }
        });
        return inflater.inflate(R.layout.fragment_mutasi_tabungan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMutasiTabungan = (RecyclerView) view.findViewById(R.id.rv_mutasi_tabungan);
        rvMutasiTabungan.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvMutasiTabungan.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvMutasiTabungan.addItemDecoration(dividerDecoration);
        rvMutasiTabungan.setItemAnimator(new DefaultItemAnimator());
        rvMutasiTabungan.setAdapter(listMutasiTabunganAdapter);
        prepareData();
    }

    private void prepareData(){
        MutasiTabungan mutasiTabungan1 = new MutasiTabungan("00107102862", "Simpanan Multiguna");
        mutasiTabungans.add(mutasiTabungan1);
        MutasiTabungan mutasiTabungan2 = new MutasiTabungan("00107104552", "Wakaf Uang");
        mutasiTabungans.add(mutasiTabungan2);
        MutasiTabungan mutasiTabungan3 = new MutasiTabungan("00452218745", "Simpanan LKS");
        mutasiTabungans.add(mutasiTabungan3);
        MutasiTabungan mutasiTabungan4 = new MutasiTabungan("00106588542", "Simpanan Properti");
        mutasiTabungans.add(mutasiTabungan4);
        MutasiTabungan mutasiTabungan5 = new MutasiTabungan("00107625568", "Simpanan Mini Market");
        mutasiTabungans.add(mutasiTabungan5);
        MutasiTabungan mutasiTabungan6 = new MutasiTabungan("00121335648", "Simpanan Wajib");
        mutasiTabungans.add(mutasiTabungan6);
        MutasiTabungan mutasiTabungan7 = new MutasiTabungan("00107103365", "Simpanan Pokok");
        mutasiTabungans.add(mutasiTabungan7);

        listMutasiTabunganAdapter.notifyDataSetChanged();
    }
}
