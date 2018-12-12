package id.co.koperasisyariah212.ui.adapters;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.TabunganInvestasi;
import id.co.koperasisyariah212.response.ListTabunganInvestasiResponse;
import id.co.koperasisyariah212.service.HttpService;

public class ListTabunganInvestasiAdapter extends RecyclerView.Adapter<ListTabunganInvestasiAdapter.TabunganInvestasiHolder> {

    private List<TabunganInvestasi> list = new ArrayList<>();
    private HttpService httpService = new HttpService();
    private TextWatcher tw;

    public ListTabunganInvestasiAdapter() {
        this.list = list;
        new GetTabunganInvestasiExe().execute();
    }


    public class GetTabunganInvestasiExe extends AsyncTask<Void, Void, ListTabunganInvestasiResponse> {


        @Override
        protected ListTabunganInvestasiResponse doInBackground(Void... voids) {
            return httpService.getAllTabunganInvestasi();
        }

        @Override
        protected void onPostExecute(ListTabunganInvestasiResponse listTabunganInvestasiResponse) {
            super.onPostExecute(listTabunganInvestasiResponse);
            list = listTabunganInvestasiResponse.getData();
            notifyDataSetChanged();
        }
    }

    public static class TabunganInvestasiHolder extends RecyclerView.ViewHolder {

        private TextView tvTitleInvestasi;
        private TextView tvDescInvestasi;
        private TextView tvNominal;
        private CardView cvTabunganInvestasi;
        private TextInputEditText txtInputTabungan;
        private TextInputLayout lblTabungan;


        public TabunganInvestasiHolder(@NonNull View itemView) {
            super(itemView);

            tvTitleInvestasi = (TextView) itemView.findViewById(R.id.tv_title_investasi);
            tvDescInvestasi = (TextView) itemView.findViewById(R.id.tv_desc_investasi);
            tvNominal = (TextView) itemView.findViewById(R.id.tv_nominal_investasi);
            cvTabunganInvestasi = (CardView) itemView.findViewById(R.id.cv_tabungan_investasi);
            txtInputTabungan = (TextInputEditText) itemView.findViewById(R.id.input_txt_investasi);
            lblTabungan = (TextInputLayout) itemView.findViewById(R.id.input_lbl_investasi);
        }
    }



    @NonNull
    @Override
    public TabunganInvestasiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tabungan_investasi, viewGroup, false);
        return new TabunganInvestasiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TabunganInvestasiHolder tabunganInvestasiHolder, int i) {
        TabunganInvestasi ti = list.get(i);

        String desc = ti.getDescription().toString();
        tabunganInvestasiHolder.tvTitleInvestasi.setText(ti.getTitle().toString());
        tabunganInvestasiHolder.tvDescInvestasi.setText(ti.getDescription().toString());

        tabunganInvestasiHolder.tvDescInvestasi.setText(desc);
        tabunganInvestasiHolder.txtInputTabungan.setText("0");
        tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tabunganInvestasiHolder.tvNominal.setText(tabunganInvestasiHolder.txtInputTabungan.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        tabunganInvestasiHolder.txtInputTabungan.addTextChangedListener(tw);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
