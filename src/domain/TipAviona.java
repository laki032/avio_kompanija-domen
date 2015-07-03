package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lazar Vujadinovic
 */
public class TipAviona implements Serializable, AbstractDomainObject {

    private int sifraTipa;
    private String nazivTipa;

    public TipAviona() {
    }

    public TipAviona(int sifraTipa, String nazivTipa) {
        this.sifraTipa = sifraTipa;
        this.nazivTipa = nazivTipa;
    }

    public int getSifraTipa() {
        return sifraTipa;
    }

    public void setSifraTipa(int sifraTipa) {
        this.sifraTipa = sifraTipa;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    @Override
    public String toString() {
        return nazivTipa;
    }

    @Override
    public String vratiNazivTabele() {
        return "TipAviona";
    }

    @Override
    public String vratiRedosledZaInsert() {
        return "(tipID, naziv)";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return sifraTipa + ",'" + nazivTipa + "'";
    }

    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> lt = new ArrayList<>();
        while (rs.next()) {
            TipAviona ta = new TipAviona();
            ta.setNazivTipa(rs.getString("naziv"));
            ta.setSifraTipa(rs.getInt("tipID"));
            lt.add(ta);
        }
        rs.close();
        return lt;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv= '" + nazivTipa + "'";
    }

    @Override
    public String vratiNazivPK() {
        return "tipID";
    }

    @Override
    public String vratiVrednostPK() {
        return sifraTipa + "";
    }

    @Override
    public void setPK(int sledeci) {
        setSifraTipa(sledeci);
    }

    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        return "";
    }

}
