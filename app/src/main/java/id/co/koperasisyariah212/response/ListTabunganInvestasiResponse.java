package id.co.koperasisyariah212.response;

import java.util.ArrayList;
import java.util.List;

import id.co.koperasisyariah212.domain.TabunganInvestasi;

public class ListTabunganInvestasiResponse extends BaseResponse {
    List<TabunganInvestasi> data = new ArrayList<>();

    public List<TabunganInvestasi> getData() {
        return data;
    }

    public void setData(List<TabunganInvestasi> data) {
        this.data = data;
    }
}
