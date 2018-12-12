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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.InformasiTabungan;
import id.co.koperasisyariah212.ui.adapters.ListInformasiTabunganAdapter;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformasiTabunganFragment extends FragmentHelper {

    private RecyclerView rvInformasiTabungan;
    private ListInformasiTabunganAdapter listInformasiTabunganAdapter;
    private List<InformasiTabungan> informasiTabungans = new ArrayList<>();

    public InformasiTabunganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setTitle("Informasi Tabungan");
        listInformasiTabunganAdapter = new ListInformasiTabunganAdapter(informasiTabungans, new ListInformasiTabunganAdapter.OnItemClickListener() {
            @Override
            public void onListInformasiTabunganItemClicked(InformasiTabungan informasiTabungan) {
                Toast.makeText(getActivity(), informasiTabungan.getKeterangan(), Toast.LENGTH_SHORT).show();
            }
        });
        return inflater.inflate(R.layout.fragment_informasi_tabungan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvInformasiTabungan = (RecyclerView) view.findViewById(R.id.rv_informasi_tabungan);
        rvInformasiTabungan.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvInformasiTabungan.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvInformasiTabungan.addItemDecoration(dividerDecoration);
        rvInformasiTabungan.setItemAnimator(new DefaultItemAnimator());
        rvInformasiTabungan.setAdapter(listInformasiTabunganAdapter);
        prepareData();
    }

    private void prepareData(){
        InformasiTabungan informasiTabungan1 = new InformasiTabungan("00107102862", "Simpanan Multiguna");
        informasiTabungans.add(informasiTabungan1);
        InformasiTabungan informasiTabungan2 = new InformasiTabungan("00106102862", "Wakaf Uang");
        informasiTabungans.add(informasiTabungan2);
        InformasiTabungan informasiTabungan3 = new InformasiTabungan("00105102862", "Simpanan LKS");
        informasiTabungans.add(informasiTabungan3);
        InformasiTabungan informasiTabungan4 = new InformasiTabungan("00104102862", "Simpanan Properti");
        informasiTabungans.add(informasiTabungan4);
        InformasiTabungan informasiTabungan5 = new InformasiTabungan("00103102862", "Simpanan Mini Market");
        informasiTabungans.add(informasiTabungan5);
        InformasiTabungan informasiTabungan6 = new InformasiTabungan("00102102862", "Simpanan Wajib");
        informasiTabungans.add(informasiTabungan6);
        InformasiTabungan informasiTabungan7 = new InformasiTabungan("00101102862", "Simpanan Pokok");
        informasiTabungans.add(informasiTabungan7);
        listInformasiTabunganAdapter.notifyDataSetChanged();
    }
}
