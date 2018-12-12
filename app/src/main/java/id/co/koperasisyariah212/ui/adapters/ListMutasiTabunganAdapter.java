package id.co.koperasisyariah212.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.MutasiTabungan;

public class ListMutasiTabunganAdapter extends RecyclerView.Adapter<ListMutasiTabunganAdapter.MutasiTabunganHolder> {

    private List<MutasiTabungan> listMutasiTabungan = new ArrayList<>();
    private OnItemClickListener listener;

    public ListMutasiTabunganAdapter(List<MutasiTabungan> listMutasiTabungan, OnItemClickListener listener) {
        this.listMutasiTabungan = listMutasiTabungan;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onAdapterItemClick(int position);
    }

    public class MutasiTabunganHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView lblNorek;
        private TextView lblKet;

        public MutasiTabunganHolder(@NonNull View itemView) {
            super(itemView);

            lblNorek = itemView.findViewById(R.id.lbl_no_rekening);
            lblKet = itemView.findViewById(R.id.lbl_keterangan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onAdapterItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MutasiTabunganHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_mutasi_tabungan, viewGroup, false);
        return new MutasiTabunganHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MutasiTabunganHolder mutasiTabunganHolder, int i) {
        MutasiTabungan mutasiTabungan = listMutasiTabungan.get(i);
        mutasiTabunganHolder.lblNorek.setText(mutasiTabungan.getNorek());
        mutasiTabunganHolder.lblKet.setText(mutasiTabungan.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return listMutasiTabungan.size();
    }
}
