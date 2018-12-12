package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.Kecamatan;

public class ListKecamatanResponse extends BaseResponse {

    List<Kecamatan> data = new ArrayList<>();

    public List<Kecamatan> getData() {
        return data;
    }

    public void setData(List<Kecamatan> data) {
        this.data = data;
    }
}
