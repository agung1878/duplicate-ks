package id.co.koperasisyariah212.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.SimpananKoperasi;
import id.co.koperasisyariah212.response.ListSimpananKoperasiResponse;
import id.co.koperasisyariah212.service.HttpService;

public class ListSimpananKoperasiAdapter extends RecyclerView.Adapter<ListSimpananKoperasiAdapter.SimpananKoperasiHolder> {

    private List<SimpananKoperasi> list = new ArrayList<>();
    private HttpService httpService = new HttpService();
    private TextWatcher tw;

    public ListSimpananKoperasiAdapter() {
        this.list = list;
        new GetSimpananKoperasiExe().execute();
    }


    public class GetSimpananKoperasiExe extends AsyncTask<Void, Void, ListSimpananKoperasiResponse>{

        @Override
        protected ListSimpananKoperasiResponse doInBackground(Void... voids) {
            return httpService.getAllSimpananResponse();
        }

        @Override
        protected void onPostExecute(ListSimpananKoperasiResponse listSimpananKoperasiResponse) {
            super.onPostExecute(listSimpananKoperasiResponse);
            list = listSimpananKoperasiResponse.getData();
            notifyDataSetChanged();
        }
    }

    public static class SimpananKoperasiHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvNominal;
        private CardView cvSimpananKoperasi;
        private TextInputEditText txtInputSimpanan;
        private TextInputLayout lblSimpanan;

        public SimpananKoperasiHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvNominal = itemView.findViewById(R.id.tv_nominal);
            cvSimpananKoperasi = itemView.findViewById(R.id.cv_simpan_koperasi);
            txtInputSimpanan = itemView.findViewById(R.id.input_txt_simpanan);
            lblSimpanan = itemView.findViewById(R.id.input_lbl_simpanan);

        }
    }

    @NonNull
    @Override
    public ListSimpananKoperasiAdapter.SimpananKoperasiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_simpanan_koperasi, viewGroup, false);
        return new SimpananKoperasiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SimpananKoperasiHolder simpananKoperasiHolder, int i) {

        SimpananKoperasi simpananKoperasi = list.get(i);

        String desc = simpananKoperasi.getDescription().toString() + " Minimum (Rp. " + simpananKoperasi.getNominal().toString() + ")";

        simpananKoperasiHolder.tvTitle.setText(simpananKoperasi.getTitle().toString());
        simpananKoperasiHolder.tvDesc.setText(simpananKoperasi.getDescription().toString());
        simpananKoperasiHolder.txtInputSimpanan.setText(simpananKoperasi.getOperator());
        simpananKoperasiHolder.tvNominal.setText(simpananKoperasi.getNominal().toString());
        if(simpananKoperasi.getOperator().equals("SAMA_DENGAN")) {
            disableEditText(simpananKoperasiHolder.txtInputSimpanan);
            simpananKoperasiHolder.txtInputSimpanan.setText(simpananKoperasi.getNominal().toString());
        }else {
            simpananKoperasiHolder.tvDesc.setText(desc);
            simpananKoperasiHolder.txtInputSimpanan.setText("0");
            tw = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    simpananKoperasiHolder.tvNominal.setText(simpananKoperasiHolder.txtInputSimpanan.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
            simpananKoperasiHolder.txtInputSimpanan.addTextChangedListener(tw);
        }



    }

    private void disableEditText(TextInputEditText editText){
        editText.setEnabled(false);
        editText.setFocusable(false);
        editText.setKeyListener(null);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
