package domain.association;

import domain.AbstractDomainObject;
import domain.Avion;
import domain.TipAviona;
import domain.zaposleni.AvioMehanicar;
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
public class Licenca implements Serializable, AbstractDomainObject {

    private AvioMehanicar mehanicar;
    private TipAviona tipAviona;
    private Date datumDobijanjaLicence;

    public Licenca() {
    }

    public Licenca(AvioMehanicar mehanicar, TipAviona tipAviona, Date datumDobijanjaLicence) {
        this.mehanicar = mehanicar;
        this.tipAviona = tipAviona;
        this.datumDobijanjaLicence = datumDobijanjaLicence;
    }

    public AvioMehanicar getMehanicar() {
        return mehanicar;
    }

    public void setMehanicar(AvioMehanicar mehanicar) {
        this.mehanicar = mehanicar;
    }

    public TipAviona getTipAviona() {
        return tipAviona;
    }

    public void setTipAviona(TipAviona tipAviona) {
        this.tipAviona = tipAviona;
    }

    public Date getDatumDobijanjaLicence() {
        return datumDobijanjaLicence;
    }

    public void setDatumDobijanjaLicence(Date datumDobijanjaLicence) {
        this.datumDobijanjaLicence = datumDobijanjaLicence;
    }

    @Override
    public boolean equals(Object obj) {
        Licenca l = (Licenca) obj;
        return l.getTipAviona().equals(tipAviona) && l.getMehanicar().equals(mehanicar);
    }

    @Override
    public String vratiNazivTabele() {
        return "Licenca";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + mehanicar.getJmbg() + "', '" + new java.sql.Date(datumDobijanjaLicence.getTime()) + "', " + tipAviona.getSifraTipa();
    }

    @Override
    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception {
        List<AbstractDomainObject> lu = new ArrayList<>();
        while (rs.next()) {
            TipAviona ta = new TipAviona();
            ta.setNazivTipa(rs.getString("naziv"));
            ta.setSifraTipa(rs.getInt("tipID"));
            AvioMehanicar am = new AvioMehanicar();
            am.setJmbg(rs.getString("JMBG"));
            am.setGodinaRodjenja(rs.getInt("godinaRodjenja"));
            am.setImePrezime(rs.getString("ImePrezime"));
            Date d = new Date(rs.getDate("datumDobijanja").getTime());

            Licenca l = new Licenca(am, ta, d);
            lu.add(l);
        }
        rs.close();
        return lu;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "datumDobijanja= '" + datumDobijanjaLicence + "'";
    }

    @Override
    public String vratiNazivPK() {
        return "JMBG";//slozen primarni kljuc
    }

    @Override
    public String vratiVrednostPK() {
        return "'" + mehanicar.getJmbg() + "'";
    }

    @Override
    public void setPK(int sledeci) {
        // ne setuje se ovako jer je slozen PK i jer su delovi razliciti tipovi podatka
    }

    @Override
    public String dajUslovZaSelect(AbstractDomainObject odo) {
        String s = "FROM (Licenca LEFT JOIN TipAviona ON Licenca.tipID = TipAviona.tipID)"
                + " LEFT JOIN AvioMehanicar ON Licenca.JMBG = AvioMehanicar.JMBG";
        if (odo != null) {
            s += " WHERE " + odo.vratiNazivTabele() + "." + odo.vratiNazivPK() + "=" + odo.vratiVrednostPK();
        }
        return s;
    }

    @Override
    public String vratiRedosledZaInsert() {
        return "(JMBG, datumDobijanja, tipID)";
    }

}
