package id.co.koperasisyariah212.domain;

public class NominalTopUp {
    private String nominal;

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    @Override
    public String toString() {
        return  nominal;
    }
}
