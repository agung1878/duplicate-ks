package id.co.koperasisyariah212.domain;

public class Komunitas {
    private String namaKomunitas;

    public String getNamaKomunitas() {
        return namaKomunitas;
    }

    public void setNamaKomunitas(String namaKomunitas) {
        this.namaKomunitas = namaKomunitas;
    }

    @Override
    public String toString() {
        return namaKomunitas;
    }
}
