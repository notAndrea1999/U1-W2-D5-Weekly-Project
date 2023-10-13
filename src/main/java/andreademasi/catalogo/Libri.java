package andreademasi.catalogo;

import java.util.Random;

public class Libri extends Catalogo {
    private String autore;
    private String genere;

    public Libri(String titolo, int annoDiPubblicazione, int numeroDiPagine, String autore, String genere) {
        super(titolo, annoDiPubblicazione, numeroDiPagine);
        Random rndm = new Random();
        this.ISBN = rndm.nextInt(0, 100);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

}
