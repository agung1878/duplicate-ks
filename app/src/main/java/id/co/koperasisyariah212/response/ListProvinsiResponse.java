package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.Provinsi;

public class ListProvinsiResponse extends BaseResponse {

    List<Provinsi> data = new ArrayList<>();

    public List<Provinsi> getData() {
        return data;
    }

    public void setData(List<Provinsi> data) {
        this.data = data;
    }
}
