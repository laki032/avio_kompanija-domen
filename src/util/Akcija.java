package util;

/**
 *
 * @author Lazar Vujadinovic
 */
public interface Akcija {

    //0-9 POMOCNE
    public static final int KRAJ = 0;
    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;
    
    //10+ PREGLED SVIH
    public static final int VRATI_SVE_AVIONE = 10;
    public static final int VRATI_SVE_TIPOVE = 11;
    public static final int VRATI_SVE_ZAPOSLENE = 12;
    public static final int VRATI_SVE_PILOTE = 13;

    //20+ PRETRAGA PO KRITERIJUMU
    public static final int PRETRAZI_PILOTE_PO_KRITERIJUMU = 20;
    public static final int PRETRAZI_ZAPOSLENE_PO_KRITERIJUMU = 21;
    public static final int PRETRAZI_MEHANICARE_PO_KRITERIJUMU = 22;
    public static final int PRETRAZI_ULOGE_PO_KRITERIJUMU = 23;
    public static final int PRETRAZI_LICENCE_PO_KRITERIJUMU = 24;

    //30+ INSERT
    public static final int NOVI_AVION = 30;
    public static final int NOVI_ZAPOSLENI = 31;
    public static final int NOVA_ULOGA = 32;
    public static final int NOVA_LICENCA = 33;

    //40+ UPDATE
    public static final int SACUVAJ_IZMENU_AVIONA = 40;
    public static final int SACUVAJ_IZMENU_PILOTA = 41;
    public static final int SACUVAJ_IZMENU_ZAPOSLENOG = 42;
    public static final int SACUVAJ_IZMENU_MEHANICARA = 43;
    public static final int SACUVAJ_IZMENU_ULOGE = 44;
    public static final int SACUVAJ_IZMENU_LICENCE = 45;

    //50+ DELETE
    public static final int BRISI_AVION = 50;
    public static final int BRISI_ZAPOSLENOG = 51;
    public static final int BRISI_MEHANICARA = 52;
    public static final int BRISI_PILOTA = 53;
    public static final int BRISI_ULOGU = 54;
    public static final int BRISI_LICENCU = 55;
    
    
}