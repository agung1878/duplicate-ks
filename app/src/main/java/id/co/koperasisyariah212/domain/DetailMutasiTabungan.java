package id.co.koperasisyariah212.domain;

public class DetailMutasiTabungan {

    private String tglMutasi;
    private String nominal;
    private String keterangan;

    public DetailMutasiTabungan(String tglMutasi, String nominal, String keterangan){
        this.tglMutasi = tglMutasi;
        this.nominal = nominal;
        this.keterangan = keterangan;
    }

    public String getTglMutasi() {
        return tglMutasi;
    }

    public void setTglMutasi(String tglMutasi) {
        this.tglMutasi = tglMutasi;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
