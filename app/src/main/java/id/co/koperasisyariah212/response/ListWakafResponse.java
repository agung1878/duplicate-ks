package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.Wakaf;

public class ListWakafResponse extends BaseResponse {

    List<Wakaf> data = new ArrayList<>();

    public List<Wakaf> getData() {
        return data;
    }

    public void setData(List<Wakaf> data) {
        this.data = data;
    }
}
