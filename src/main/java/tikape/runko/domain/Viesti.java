package tikape.runko.domain;

public class Viesti {
    
    private Integer id;
    private String teksti;
    private String nimimerkki;
    private String paivays;
    
    public Viesti(Integer id, String teksti, String nimimerkki, String paivays){
        this.id = id;
        this.teksti = teksti;
        this.nimimerkki = nimimerkki;
        this.paivays = paivays;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getTeksti(){
        return teksti;
    }
    
    public void setString(String teksti){
        this.teksti = teksti;
    }
    
    public String getNimimerkki(){
        return nimimerkki;
    }
    
    public void setNimimerkki(String nimimerkki){
        this.nimimerkki = nimimerkki;
    }
    
    public String getPaivays(){
        return paivays;
    }
    
    public void setPaivays(String paivays){
        this.paivays = paivays;
    }
    
}
