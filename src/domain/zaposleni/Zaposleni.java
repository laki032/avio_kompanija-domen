package domain.zaposleni;

import domain.AbstractDomainObject;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lazar Vujadinovic
 */
public class Zaposleni implements Serializable, AbstractDomainObject {

    String jmbg;
    String imePrezime;
    int godinaRodjenja;

    public Zaposleni() {
        jmbg = "0000000000000";
        imePrezime = "Anonymous";
        godinaRodjenja = -1;
    }

    public Zaposleni(String jmbg, String imePrezime, int godinaRodjenja) {
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
        this.godinaRodjenja = godinaRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public int getGodinaRodjenja() {
        return godinaRodjenja;
    }

    public void setGodinaRodjenja(int godinaRodjenja) {
        this.godinaRodjenja = godinaRodjenja;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "Zaposleni";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + jmbg + "', '" + imePrezime + "', " + godinaRodjenja;
    }

    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> l = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setJmbg(rs.getString("JMBG"));
            z.setGodinaRodjenja(rs.getInt("godinaRodjenja"));
            z.setImePrezime(rs.getString("imePrezime"));
            l.add(z);
        }
        rs.close();
        return l;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "imePrezime='" + imePrezime + "'";
    }

    @Override
    public String vratiNazivPK() {
        return "JMBG";
    }

    @Override
    public String vratiVrednostPK() {
        return "'" + jmbg + "'";
    }

    @Override
    public void setPK(int sledeci) {
        //ne setuje se ovako nego preko setJMBG()
    }

    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        if (odo != null) {
            return " WHERE " + odo.vratiNazivTabele() + "." + odo.vratiNazivPK() + "=" + odo.vratiVrednostPK();
        }
        return "";
    }

    @Override
    public String vratiRedosledZaInsert() {
        return "(JMBG, imePrezime, godinaRodjenja)";
    }

}
