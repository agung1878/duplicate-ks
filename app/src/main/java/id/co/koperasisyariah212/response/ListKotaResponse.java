package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.Kota;

public class ListKotaResponse extends BaseResponse {

    List<Kota> data = new ArrayList<>();

    public List<Kota> getData() {
        return data;
    }

    public void setData(List<Kota> data) {
        this.data = data;
    }
}
