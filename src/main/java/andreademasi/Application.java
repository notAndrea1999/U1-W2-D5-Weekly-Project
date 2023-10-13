package andreademasi;

import andreademasi.catalogo.Libri;
import andreademasi.catalogo.Periodicita;
import andreademasi.catalogo.Riviste;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {


    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ITALY);
        Random rndm = new Random();


        Supplier<Libri> libriSupplier = () -> new Libri(faker.book().title(), rndm.nextInt(2000, 2023), rndm.nextInt(10, 300), faker.book().author(), faker.book().genre());
        List<Libri> listaLibri = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listaLibri.add(libriSupplier.get());
        }
        listaLibri.forEach(libri -> System.out.println(libri));

        Supplier<Riviste> rivisteSupplier = () -> new Riviste(faker.medical().diseaseName(), rndm.nextInt(2000, 2023), rndm.nextInt(10, 300), Periodicita.randomPeriodicita());
        List<Riviste> listaRiviste = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listaRiviste.add(rivisteSupplier.get());
        }
        listaRiviste.forEach(riviste -> System.out.println(riviste));
    }


}
