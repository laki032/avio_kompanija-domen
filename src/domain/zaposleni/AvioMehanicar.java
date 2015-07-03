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
public class AvioMehanicar extends Zaposleni implements Serializable{

    private String tipMehanicara;

    public AvioMehanicar(String tipMehanicara, String jmbg, String imePrezime, int godinaRodjenja) {
        super(jmbg, imePrezime, godinaRodjenja);
        this.tipMehanicara = tipMehanicara;
    }

    public AvioMehanicar(String jmbg, String imePrezime, int godinaRodjenja) {
        super(jmbg, imePrezime, godinaRodjenja);
        tipMehanicara = "nije dodeljen";
    }

    public AvioMehanicar() {
        super();
        tipMehanicara = "nije dodeljen";
    }

    public String getTipMehanicara() {
        return tipMehanicara;
    }

    public void setTipMehanicara(String tipMehanicara) {
        this.tipMehanicara = tipMehanicara;
    }

    @Override
    public String vratiNazivTabele() {
        return "AvioMehanicar";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + jmbg + "', '" + tipMehanicara + "'";
    }

    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> l = new ArrayList<>();
        while (rs.next()) {
            AvioMehanicar a = new AvioMehanicar();
            a.setJmbg(rs.getString("JMBG"));
            a.setGodinaRodjenja(rs.getInt("godinaRodjenja"));
            a.setImePrezime(rs.getString("imePrezime"));
            a.setTipMehanicara(rs.getString("tipMehanicara"));
            l.add(a);
        }
        rs.close();
        return l;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "'" + tipMehanicara + "'";
    }

    @Override
    public String vratiNazivPK() {
        return "AvioMehanicar.JMBG";
    }

    @Override
    public String vratiVrednostPK() {
        return "'" + jmbg + "'";
    }

    @Override
    public void setPK(int sledeci) {
        //nikad se ne koristi
    }

    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        String s = "LEFT JOIN Zaposleni ON AvioMehanicar.JMBG = Zaposleni.JMBG";
        if (odo != null) {
            s += " WHERE " + odo.vratiNazivTabele() + "." + odo.vratiNazivPK() + "=" + odo.vratiVrednostPK();
        }
        return s;
    }

    @Override
    public String vratiRedosledZaInsert() {
        return "(JMBG, tipMehanicara)";
    }

}
