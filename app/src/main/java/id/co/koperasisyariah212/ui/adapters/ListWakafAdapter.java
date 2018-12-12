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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.domain.Wakaf;
import id.co.koperasisyariah212.response.ListWakafResponse;
import id.co.koperasisyariah212.service.HttpService;

public class ListWakafAdapter extends RecyclerView.Adapter<ListWakafAdapter.WakafHolder> {

    private List<Wakaf> list = new ArrayList<>();
    private HttpService httpService = new HttpService();
    private TextWatcher tw;

    public ListWakafAdapter() {
        this.list = list;
        new GetWakafExe().execute();

    }


    public class GetWakafExe extends AsyncTask<Void, Void, ListWakafResponse>{

        @Override
        protected ListWakafResponse doInBackground(Void... voids) {
            return httpService.getAllWakaf();
        }

        @Override
        protected void onPostExecute(ListWakafResponse listWakafResponse) {
            super.onPostExecute(listWakafResponse);
            list = listWakafResponse.getData();
            notifyDataSetChanged();
        }
    }


    public static class WakafHolder extends RecyclerView.ViewHolder {

        private TextView tvTitleWakaf;
        private TextView tvDescWakaf;
        private TextView tvNominal;
        private CardView cvWakaf;
        private TextInputEditText txtInputWakaf;
        private TextInputLayout lblWakaf;

        public WakafHolder(@NonNull View itemView) {
            super(itemView);

            tvTitleWakaf = (TextView) itemView.findViewById(R.id.tv_title_wakaf);
            tvDescWakaf = (TextView) itemView.findViewById(R.id.tv_desc_wakaf);
            tvNominal = (TextView) itemView.findViewById(R.id.tv_nominal_wakaf);
            cvWakaf = (CardView) itemView.findViewById(R.id.cv_wakaf);
            txtInputWakaf = (TextInputEditText) itemView.findViewById(R.id.input_txt_wakaf);
            lblWakaf = (TextInputLayout) itemView.findViewById(R.id.input_lbl_wakaf);
        }
    }

    @NonNull
    @Override
    public WakafHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wakaf, viewGroup, false);
        return new WakafHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WakafHolder wakafHolder, int i) {

        Wakaf w = list.get(i);

        String desc = w.getDescription().toString();
        wakafHolder.tvTitleWakaf.setText(w.getTitle().toString());
        wakafHolder.tvDescWakaf.setText(w.getDescription().toString());
        wakafHolder.tvDescWakaf.setText(desc);
        wakafHolder.tvNominal.setText("0");
        tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                wakafHolder.tvNominal.setText(wakafHolder.txtInputWakaf.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        wakafHolder.txtInputWakaf.addTextChangedListener(tw);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
