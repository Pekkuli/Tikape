/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

/**
 *
 * @author Sami
 */
public class Alue {

    private Integer id;
    private Integer viestiMaara;
    private String otsikko;
    private String paivays;

    public Alue(Integer id, Integer viesteja, String otsikko, String paivays) {
        this.id = id;
        this.viestiMaara = viesteja;
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
