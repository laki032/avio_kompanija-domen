package domain;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Lazar Vujadinovic
 */
public interface AbstractDomainObject {

    /**
     *
     * @return ime tabele u bazi koja odgovara objektu
     */
    public String vratiNazivTabele();

    public String vratiVrednostiZaInsert();

    public List<AbstractDomainObject> napuni(ResultSet rs, AbstractDomainObject odo) throws Exception;

    public String vratiVrednostiZaUpdate();

    public String vratiNazivPK();

    public String vratiVrednostPK();

    public void setPK(int sledeci);

    public String dajUslovZaSelect(AbstractDomainObject odo);

    public String vratiRedosledZaInsert();

    
}
