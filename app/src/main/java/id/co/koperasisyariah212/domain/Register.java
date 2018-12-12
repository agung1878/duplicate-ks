package id.co.koperasisyariah212.domain;

import java.io.Serializable;

public class Register implements Serializable {

    private String nik;
    private String fullname;
    private String alamatKtp;
    private String alamatKorespondensi;
    private String noTelepon;
    private String email;
    private Occupation occupation;
    private String tempatLahir;
    private String jenisKelamin;
    private Income income;
    private String ibuKandung;
    private Provinsi provinsi;
    private Kota kota;
    private Kecamatan kecamatan;
    private Kelurahan kelurahan;
    private boolean alamatIsSame;
    private String tanggalLahir;
    private Komunitas communityGroup;
    private SimpananKoperasi cooperativeSavingsValues;
    private TabunganInvestasi investmentValue;
    private Wakaf wakafValue;


    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAlamatKtp() {
        return alamatKtp;
    }

    public void setAlamatKtp(String alamatKtp) {
        this.alamatKtp = alamatKtp;
    }

    public String getAlamatKorespondensi() {
        return alamatKorespondensi;
    }

    public void setAlamatKorespondensi(String alamatKorespondensi) {
        this.alamatKorespondensi = alamatKorespondensi;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public String getIbuKandung() {
        return ibuKandung;
    }

    public void setIbuKandung(String ibuKandung) {
        this.ibuKandung = ibuKandung;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }

    public Kelurahan getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(Kelurahan kelurahan) {
        this.kelurahan = kelurahan;
    }

    public boolean isAlamatIsSame() {
        return alamatIsSame;
    }

    public void setAlamatIsSame(boolean alamatIsSame) {
        this.alamatIsSame = alamatIsSame;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Komunitas getCommunityGroup() {
        return communityGroup;
    }

    public void setCommunityGroup(Komunitas communityGroup) {
        this.communityGroup = communityGroup;
    }

    public SimpananKoperasi getCooperativeSavingsValues() {
        return cooperativeSavingsValues;
    }

    public void setCooperativeSavingsValues(SimpananKoperasi cooperativeSavingsValues) {
        this.cooperativeSavingsValues = cooperativeSavingsValues;
    }

    public TabunganInvestasi getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(TabunganInvestasi investmentValue) {
        this.investmentValue = investmentValue;
    }

    public Wakaf getWakafValue() {
        return wakafValue;
    }

    public void setWakafValue(Wakaf wakafValue) {
        this.wakafValue = wakafValue;
    }

    @Override
    public String toString() {
        return nik + fullname + alamatKtp + alamatKorespondensi
                + noTelepon +  occupation + tempatLahir
                + jenisKelamin + income + ibuKandung + provinsi + kota + kecamatan
                + kelurahan + alamatIsSame + tanggalLahir +  communityGroup
                + cooperativeSavingsValues + investmentValue + wakafValue + email;
    }
}
