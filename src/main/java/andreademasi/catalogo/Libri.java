package andreademasi.catalogo;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class Libri extends Catalogo {
    static Faker faker = new Faker(Locale.ITALY);
    static Random rndm = new Random();
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

    @Override
    public String toString() {
        return "Libri{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", ISBN=" + ISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroDiPagine=" + numeroDiPagine +
                '}';
    }
}
