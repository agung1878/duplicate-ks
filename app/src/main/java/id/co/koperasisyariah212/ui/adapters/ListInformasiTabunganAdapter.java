package id.co.koperasisyariah212.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.InformasiTabungan;

public class ListInformasiTabunganAdapter extends RecyclerView.Adapter<ListInformasiTabunganAdapter.InformasiTabunganHolder> {

    private List<InformasiTabungan> listInformasiTabungan = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onListInformasiTabunganItemClicked(InformasiTabungan informasiTabungan);
    }

    public ListInformasiTabunganAdapter(List<InformasiTabungan> listInformasiTabungan, OnItemClickListener listener) {
        this.listInformasiTabungan = listInformasiTabungan;
        this.listener = listener;
    }

    public class InformasiTabunganHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView lblNorek;
        private TextView lblKeterangan;

        public InformasiTabunganHolder(@NonNull View itemView) {
            super(itemView);
            lblNorek = itemView.findViewById(R.id.lbl_no_rekening);
            lblKeterangan = itemView.findViewById(R.id.lbl_keterangan);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            InformasiTabungan informasiTabungan = listInformasiTabungan.get(getAdapterPosition());
            listener.onListInformasiTabunganItemClicked(informasiTabungan);
        }
    }

    @NonNull
    @Override
    public InformasiTabunganHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_informasi_tabungan, viewGroup, false);
        return new InformasiTabunganHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InformasiTabunganHolder informasiTabunganHolder, int i) {
        InformasiTabungan informasiTabungan = listInformasiTabungan.get(i);
        informasiTabunganHolder.lblNorek.setText(informasiTabungan.getNorek());
        informasiTabunganHolder.lblKeterangan.setText(informasiTabungan.getKeterangan());
    }



    @Override
    public int getItemCount() {
        return listInformasiTabungan.size();
    }


}
