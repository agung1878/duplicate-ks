package id.co.koperasisyariah212.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import id.co.koperasisyariah212.response.ListKecamatanResponse;
import id.co.koperasisyariah212.response.ListKelurahanResponse;
import id.co.koperasisyariah212.response.ListKotaResponse;
import id.co.koperasisyariah212.response.ListProvinsiResponse;
import id.co.koperasisyariah212.response.ListSimpananKoperasiResponse;
import id.co.koperasisyariah212.response.ListTabunganInvestasiResponse;
import id.co.koperasisyariah212.response.ListWakafResponse;

public class HttpService {

    private static final String BASE_URL = "http://192.168.100.23:8080";
    private RestTemplate rest = new RestTemplate();

    public HttpService(){
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public ListProvinsiResponse getAllProvinsi(){
        String url = BASE_URL + "/api/alamat/provinsi";

        ResponseEntity<ListProvinsiResponse> entityResponse =
                rest.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, ListProvinsiResponse.class);

        return entityResponse.getBody();
    }

    public ListKotaResponse getKotaByProvinsi(String id){
        String url = BASE_URL + "/api/alamat/kota?idParent=" + id;

        ResponseEntity<ListKotaResponse> entityResponse =
                rest.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, ListKotaResponse.class);

        return entityResponse.getBody();
    }

    public ListKecamatanResponse getKecamatanByKota(String id){
        String url = BASE_URL + "/api/alamat/kecamatan?idParent=" + id;

        ResponseEntity<ListKecamatanResponse> entityResponse =
                rest.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, ListKecamatanResponse.class);

        return entityResponse.getBody();
    }

    public ListKelurahanResponse getKelurahanByKecamatan (String id){
        String url = BASE_URL + "/api/alamat/kelurahan?idParent=" + id;

        ResponseEntity<ListKelurahanResponse> entityResponse =
                rest.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, ListKelurahanResponse.class);

        return entityResponse.getBody();
    }

    public ListSimpananKoperasiResponse getAllSimpananResponse(){
        String url = BASE_URL + "/api/simpananKoperasi/findAll";

        ResponseEntity<ListSimpananKoperasiResponse> entityResponse =
                rest.exchange(url,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        ListSimpananKoperasiResponse.class);
        return entityResponse.getBody();
    }

    public ListTabunganInvestasiResponse getAllTabunganInvestasi(){
        String url = BASE_URL + "/api/tabunganInvestasi/findAll";

        ResponseEntity<ListTabunganInvestasiResponse> entityResponse =
                rest.exchange(url,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        ListTabunganInvestasiResponse.class);
        return entityResponse.getBody();
    }

    public ListWakafResponse getAllWakaf(){
        String url = BASE_URL + "/api/wakaf/findAll";

        ResponseEntity<ListWakafResponse> entityResponse =
                rest.exchange(url,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        ListWakafResponse.class);
        return entityResponse.getBody();
    }
}
