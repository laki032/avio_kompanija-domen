package util;

import java.io.Serializable;

/**
 *
 * @author Lazar Vujadinovic
 */
public class TransferKlasa implements Serializable {

    private int operacija;
    private Object parametar;
    private Object rezultat;
    private Object izuzetak;

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Object getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Object izuzetak) {
        this.izuzetak = izuzetak;
    }

}
