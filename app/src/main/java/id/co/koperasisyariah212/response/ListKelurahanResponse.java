package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.Kelurahan;

public class ListKelurahanResponse extends BaseResponse {
    List<Kelurahan> data = new ArrayList<>();

    public List<Kelurahan> getData() {
        return data;
    }

    public void setData(List<Kelurahan> data) {
        this.data = data;
    }
}

