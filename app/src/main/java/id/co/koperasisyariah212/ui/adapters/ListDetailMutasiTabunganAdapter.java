package id.co.koperasisyariah212.ui.adapters;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.DetailMutasiTabungan;

public class ListDetailMutasiTabunganAdapter extends RecyclerView.Adapter<ListDetailMutasiTabunganAdapter.DetailMutasiTabunganHolder>{

    private List<DetailMutasiTabungan> detailMutasiTabungans = new ArrayList<>();

    public ListDetailMutasiTabunganAdapter(List<DetailMutasiTabungan> listMutasiTabungan){
        this.detailMutasiTabungans = listMutasiTabungan;
    }

    public static class DetailMutasiTabunganHolder extends RecyclerView.ViewHolder{
        private TextView lblTglMutasi;
        private TextView nominal;
        private TextView keterangan;

        public DetailMutasiTabunganHolder(@NonNull View itView){
            super(itView);
            lblTglMutasi = itView.findViewById(R.id.txt_tanggal_mutasi);
            nominal = itView.findViewById(R.id.txt_distribusi_saldo_awal1);
            keterangan = itView.findViewById(R.id.lbl_keterangan_mutasi);
        }
    }

    @Override
    @NonNull
    public DetailMutasiTabunganHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_detail_mutasi_tabungan, viewGroup, false);
        return new DetailMutasiTabunganHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMutasiTabunganHolder detailMutasiTabunganHolder, int i) {
        DetailMutasiTabungan detailMutasiTabungan = detailMutasiTabungans.get(i);
        detailMutasiTabunganHolder.lblTglMutasi.setText(detailMutasiTabungan.getTglMutasi());
        detailMutasiTabunganHolder.nominal.setText(detailMutasiTabungan.getNominal());
        detailMutasiTabunganHolder.keterangan.setText(detailMutasiTabungan.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return detailMutasiTabungans.size();
    }


}
