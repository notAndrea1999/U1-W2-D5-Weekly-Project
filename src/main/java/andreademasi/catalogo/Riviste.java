package andreademasi.catalogo;

public class Riviste extends Catalogo {

    private Periodicita periodicita;

    public Riviste(String titolo, int annoDiPubblicazione, int numeroDiPagine, Periodicita periodicita) {
        super(titolo, annoDiPubblicazione, numeroDiPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
