package domain.association;

import domain.AbstractDomainObject;
import domain.Avion;
import domain.zaposleni.Pilot;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lazar Vujadinovic
 */
public class Uloga implements Serializable, AbstractDomainObject {

    private Pilot pilot;
    private Avion avion;
    private Date datumLeta;
    private String nazivUloge;

    public Uloga() {
    }

    public Uloga(Pilot pilot, Avion avion, Date datumLeta, String nazivUloge) {
        this.pilot = pilot;
        this.avion = avion;
        this.datumLeta = datumLeta;
        this.nazivUloge = nazivUloge;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Date getDatumLeta() {
        return datumLeta;
    }

    public void setDatumLeta(Date datumLeta) {
        this.datumLeta = datumLeta;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

    public void setNazivUloge(String nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    @Override
    public boolean equals(Object obj) {
        Uloga u = (Uloga) obj;
        return u.getAvion().equals(avion) && u.getPilot().equals(pilot) && u.getDatumLeta().equals(datumLeta);
    }

    @Override
    public String vratiNazivTabele() {
        return "Uloga";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + nazivUloge + "', '" + pilot.getJmbg() + "', " + avion.getAvionID() + ", '" + new java.sql.Date(datumLeta.getTime()) + "'";
    }

    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> lu = new ArrayList<>();
        while (rs.next()) {
//            TipAviona ta = new TipAviona();
//            ta.setNazivTipa(rs.getString("naziv"));
//            ta.setSifraTipa(rs.getInt("tipID"));
            Avion a = new Avion();
//            a.setTip(ta);
            a.setAvionID(rs.getInt("avionID"));
            a.setBrojPutnika(rs.getInt("brojPutnika"));
            a.setGodinaProizvodnje(rs.getInt("godProizvodnje"));
            a.setNosivost(rs.getInt("nosivost"));
            a.setOznaka(rs.getString("oznaka"));
            Pilot p = new Pilot();
            p.setJmbg(rs.getString("JMBG"));
            p.setDatumPregleda(new Date(rs.getDate("datumPregleda").getTime()));
            p.setGodinaRodjenja(rs.getInt("godinaRodjenja"));
            p.setImePrezime(rs.getString("ImePrezime"));
            p.setOcenaZdravstvenogStanja(rs.getBoolean("ocenaStanja"));
            Date d = new Date(rs.getDate("datum").getTime());
            String nu = rs.getString("nazivUloge");
            Uloga u = new Uloga(p, a, d, nu);
            lu.add(u);
        }
        rs.close();
        return lu;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "nazivUloge= '" + nazivUloge + "'";
    }

    @Override
    public String vratiNazivPK() {
        return "JMBG";
    }

    @Override
    public String vratiVrednostPK() {
        return "'" + pilot.getJmbg() + "'";
    }

    @Override
    public void setPK(int sledeci) {
        // ne setuje se ovako jer je slozen PK i jer su delovi razliciti tipovi podatka
    }

    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        String s = "FROM (Uloga LEFT JOIN (Pilot LEFT JOIN Zaposleni ON Pilot.JMBG = Zaposleni.JMBG) "
                + "ON Uloga.JMBG = Pilot.JMBG) LEFT JOIN Avion ON Uloga.avionID = Avion.avionID";
        if (odo != null) {
            s += " WHERE " + odo.vratiNazivTabele() + "." + odo.vratiNazivPK() + "=" + odo.vratiVrednostPK();
        }
        return s;
    }

    @Override
    public String vratiRedosledZaInsert() {
        return "(nazivUloge, JMBG, avionID, datum)";
    }

}
