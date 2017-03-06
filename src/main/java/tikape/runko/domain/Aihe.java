package tikape.runko.domain;

public class Aihe {

    private Integer id;
    private Integer viestiMaara;
    private String otsikko;
    private String paivays;

    public Aihe(Integer id, Integer viestiMaara, String otsikko, String paivays) {
        this.id = id;
        this.viestiMaara = viestiMaara;
        this.otsikko = otsikko;
        this.paivays = paivays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getViestiMaara() {
        return viestiMaara;
    }

    public void setViestiMaara(Integer viestiMaara) {
        this.viestiMaara = viestiMaara;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }
    
    public String getPaivays(){
        return paivays;
    }
    
    public void setPaivays(String paivays){
        this.paivays = paivays;
    }

}
