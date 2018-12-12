package id.co.koperasisyariah212.domain;

public class MutasiTabungan {
    private String norek;
    private String keterangan;

    public MutasiTabungan(String norek, String keterangan) {
        this.norek = norek;
        this.keterangan = keterangan;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
