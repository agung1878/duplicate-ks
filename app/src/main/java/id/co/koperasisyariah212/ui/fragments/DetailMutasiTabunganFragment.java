package id.co.koperasisyariah212.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import id.co.koperasisyariah212.domain.DetailMutasiTabungan;
import id.co.koperasisyariah212.ui.adapters.ListDetailMutasiTabunganAdapter;
import id.co.koperasisyariah212.ui.helper.FragmentHelper;

public class DetailMutasiTabunganFragment extends FragmentHelper {

    private RecyclerView rvRecycleViewDetail;
    private ListDetailMutasiTabunganAdapter listDetailMutasiTabunganAdapter;
    private List<DetailMutasiTabungan> mutasiTabungan = new ArrayList<>();

    public DetailMutasiTabunganFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setTitle("Detail Mutasi Tabungan");
        listDetailMutasiTabunganAdapter = new ListDetailMutasiTabunganAdapter(mutasiTabungan);
        return inflater.inflate(R.layout.fragment_detail_mutasi_tabungan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRecycleViewDetail = (RecyclerView) view.findViewById(R.id.rv_detail_mutasi_tabungan);
        rvRecycleViewDetail.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvRecycleViewDetail.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvRecycleViewDetail.addItemDecoration(dividerDecoration);
        rvRecycleViewDetail.setItemAnimator(new DefaultItemAnimator());
        rvRecycleViewDetail.setAdapter(listDetailMutasiTabunganAdapter);
        prepareData();
    }

    private void prepareData(){
        mutasiTabungan = new ArrayList<>();
        DetailMutasiTabungan detailMutasiTabungan1 = new DetailMutasiTabungan("D 12/10/2018", "Rp 200.000", "Distribusi saldo awal registrasi :");
        mutasiTabungan.add(detailMutasiTabungan1);
        DetailMutasiTabungan detailMutasiTabungan2 = new DetailMutasiTabungan("D 12/10/2018", "Rp 212.000", "Distribusi saldo awal registrasi :");
        mutasiTabungan.add(detailMutasiTabungan2);
        DetailMutasiTabungan detailMutasiTabungan3 = new DetailMutasiTabungan("D 12/10/2018", "Rp 16.000", "Adm registrasi awal");
        mutasiTabungan.add(detailMutasiTabungan3);
        DetailMutasiTabungan detailMutasiTabungan4 = new DetailMutasiTabungan("D 12/10/2018", "Rp 2.000", "Biaya adm transfer dari bank umum/lainnya");
        mutasiTabungan.add(detailMutasiTabungan4);
        DetailMutasiTabungan detailMutasiTabungan5 = new DetailMutasiTabungan("K 12/10/2018", "Rp 400.000", "Transfer dari bank umum/lainnya");
        mutasiTabungan.add(detailMutasiTabungan5);
        listDetailMutasiTabunganAdapter.notifyDataSetChanged();
    }
}
