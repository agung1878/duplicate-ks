package id.co.koperasisyariah212.domain;

public class Pendapatan {
    private String namaPendapatan;

    public String getNamaPendapatan() {
        return namaPendapatan;
    }

    public void setNamaPendapatan(String namaPendapatan) {
        this.namaPendapatan = namaPendapatan;
    }

    @Override
    public String toString() {
        return namaPendapatan;
    }
}
