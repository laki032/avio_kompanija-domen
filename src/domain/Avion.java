package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lazar Vujadinovic
 */
public class Avion implements Serializable, AbstractDomainObject {
    // ako je avionID == -1 znaci da je avion tek kreiran i u DBB mu treba dodeliti ID
    private int avionID;
    private String oznaka;
    private int godinaProizvodnje;
    private int brojPutnika;
    private int nosivost;
    private TipAviona tip;
    
    public Avion(int id, String oznaka, int godinaProizvodnje, int brojPutnika, int nosivost, TipAviona tip) {
        avionID = id;
        this.oznaka = oznaka;
        this.godinaProizvodnje = godinaProizvodnje;
        this.brojPutnika = brojPutnika;
        this.nosivost = nosivost;
        this.tip = tip;
    }
    
    public Avion() {
        avionID = -1;
        oznaka = "nije dodeljena";
        godinaProizvodnje = -1;
        brojPutnika = -1;
        nosivost = -1;
        tip = null;
    }
    
    public String getOznaka() {
        return oznaka;
    }
    
    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }
    
    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }
    
    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }
    
    public int getBrojPutnika() {
        return brojPutnika;
    }
    
    public void setBrojPutnika(int brojPutnika) {
        this.brojPutnika = brojPutnika;
    }
    
    public int getNosivost() {
        return nosivost;
    }
    
    public void setNosivost(int nosivost) {
        this.nosivost = nosivost;
    }
    
    public TipAviona getTip() {
        return tip;
    }
    
    public void setTip(TipAviona tip) {
        this.tip = tip;
    }
    
    public int getAvionID() {
        return avionID;
    }
    
    public void setAvionID(int avionID) {
        this.avionID = avionID;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "Avion";
    }
    
    @Override
    public String vratiRedosledZaInsert() {
        return "(avionID, oznaka, godProizvodnje, brojPutnika, nosivost, tipID)";
    }
    
    @Override
    public String vratiVrednostiZaInsert() {
        return avionID + ", '" + oznaka + "', " + godinaProizvodnje + ", " + brojPutnika + ", " + nosivost + ", " + tip.getSifraTipa();
    }
    
    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> la = new ArrayList<>();
        while (rs.next()) {
            TipAviona ta = new TipAviona();
            ta.setNazivTipa(rs.getString("naziv"));
            ta.setSifraTipa(rs.getInt("tipID"));
            Avion a = new Avion();
            a.setTip(ta);
            a.setAvionID(rs.getInt("avionID"));
            a.setBrojPutnika(rs.getInt("brojPutnika"));
            a.setGodinaProizvodnje(rs.getInt("godProizvodnje"));
            a.setNosivost(rs.getInt("nosivost"));
            a.setOznaka(rs.getString("oznaka"));
            la.add(a);
        }
        rs.close();
        return la;
    }
    
    @Override
    public String vratiVrednostiZaUpdate() {
        return "oznaka= '" + oznaka + "', godProizvodnje= " + godinaProizvodnje + ", brojPutnika= " + brojPutnika + ", nosivost= " + nosivost + ", tipID= " + tip.getSifraTipa();
    }
    
    @Override
    public String vratiNazivPK() {
        return "avionID";
    }
    
    @Override
    public String vratiVrednostPK() {
        return avionID + "";
    }
    
    @Override
    public void setPK(int sledeci) {
        avionID = sledeci;
    }
    
    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        return "INNER JOIN TipAviona ON Avion.tipID = TipAviona.tipID";
    }
    
    @Override
    public String toString() {
        return oznaka;
    }

    
    
}
