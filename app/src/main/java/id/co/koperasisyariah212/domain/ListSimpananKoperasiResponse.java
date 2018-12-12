package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.SimpananKoperasi;

public class ListSimpananKoperasiResponse extends BaseResponse {

    private List<SimpananKoperasi> data = new ArrayList<>();

    public List<SimpananKoperasi> getData() {
        return data;
    }

    public void setData(List<SimpananKoperasi> data) {
        this.data = data;
    }
}
