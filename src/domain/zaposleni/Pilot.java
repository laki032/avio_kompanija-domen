package domain.zaposleni;

import domain.AbstractDomainObject;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lazar Vujadinovic
 */
public class Pilot extends Zaposleni implements Serializable {

    private Date datumPregleda;
    private boolean ocenaZdravstvenogStanja;

    public Pilot(Date datumPregleda, boolean ocenaZdravstvenogStanja, String jmbg, String imePrezime, int godinaRodjenja) {
        super(jmbg, imePrezime, godinaRodjenja);
        this.datumPregleda = datumPregleda;
        this.ocenaZdravstvenogStanja = ocenaZdravstvenogStanja;
    }

    public Pilot(String jmbg, String imePrezime, int godinaRodjenja) {
        super(jmbg, imePrezime, godinaRodjenja);
        datumPregleda = null;
        ocenaZdravstvenogStanja = false;
    }

    public Pilot() {
        super();
        datumPregleda = null;
        ocenaZdravstvenogStanja = false;
    }

    public Date getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(Date datumPregleda) {
        this.datumPregleda = datumPregleda;
    }

    public boolean isProsaoTestZdravstvenogStanja() {
        return ocenaZdravstvenogStanja;
    }

    public void setOcenaZdravstvenogStanja(boolean ocenaZdravstvenogStanja) {
        this.ocenaZdravstvenogStanja = ocenaZdravstvenogStanja;
    }

    @Override
    public String vratiNazivTabele() {
        return "Pilot";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + jmbg + "', '" + new java.sql.Date(datumPregleda.getTime()) + "', " + ocenaZdravstvenogStanja;
    }

    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> l = new ArrayList<>();
        while (rs.next()) {
            Pilot p = new Pilot();
            p.setJmbg(rs.getString("JMBG"));
            p.setDatumPregleda(new Date(rs.getDate("datumPregleda").getTime()));
            p.setGodinaRodjenja(rs.getInt("godinaRodjenja"));
            p.setImePrezime(rs.getString("imePrezime"));
            p.setOcenaZdravstvenogStanja(rs.getBoolean("ocenaStanja"));
            l.add(p);
        }
        rs.close();
        return l;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "'" + datumPregleda + "', " + ocenaZdravstvenogStanja;
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
        //nikad se ne koristi
    }

    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        if (odo != null) {
            return "FROM Pilot LEFT JOIN Zaposleni ON Pilot.JMBG = Zaposleni.JMBG WHERE "
                    + odo.vratiNazivTabele() + "." + odo.vratiNazivPK() + "=" + odo.vratiVrednostPK();
        }
        return "LEFT JOIN Zaposleni ON Pilot.JMBG = Zaposleni.JMBG";
    }

    @Override
    public String vratiRedosledZaInsert() {
        return "(JMBG, datumPregleda, ocenaStanja)";
    }

}
