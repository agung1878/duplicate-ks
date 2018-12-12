package id.co.koperasisyariah212.domain;

public class Pekerjaan {
    private String namaPekerjaan;

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public void setNamaPekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    @Override
    public String toString() {
        return namaPekerjaan;
    }
}
