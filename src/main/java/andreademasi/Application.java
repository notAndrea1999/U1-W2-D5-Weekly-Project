package andreademasi;

import andreademasi.catalogo.Catalogo;
import andreademasi.catalogo.Libri;
import andreademasi.catalogo.Periodicita;
import andreademasi.catalogo.Riviste;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;

public class Application {


    public static void main(String[] args) {
        Faker faker = new Faker(Locale.ITALY);
        Random rndm = new Random();


        Supplier<Libri> libriSupplier = () -> new Libri(faker.book().title(), rndm.nextInt(2000, 2023), rndm.nextInt(10, 300), faker.book().author(), faker.book().genre());
        List<Catalogo> listaLibri = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            listaLibri.add(libriSupplier.get());

        }
//        listaLibri.forEach(System.out::println);

        Supplier<Riviste> rivisteSupplier = () -> new Riviste(faker.medical().diseaseName(), rndm.nextInt(2000, 2023), rndm.nextInt(10, 300), Periodicita.randomPeriodicita());
        List<Catalogo> listaRiviste = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listaRiviste.add(rivisteSupplier.get());
        }
        // listaRiviste.forEach(System.out::println);

        // listaLibri.add(addBook());
        listaLibri.forEach(System.out::println);
        //listaRiviste.add(addRivista());
        listaRiviste.forEach(System.out::println);

        // removeElementByISBN(listaLibri);
        //searchElementByISBN(listaLibri);
        //searchElementByPublishDate(listaLibri);
        //searchElementByAuthor(listaLibri);

        try {
            saveData(listaLibri, listaRiviste);
        } catch (IOException ex) {
            System.out.println(ex);
        }

       
    }

    public static Libri addBook() {
        Random rndm = new Random();
        String titolo;
        int annoDiPubblicazione;
        String autore;
        String genere;
        loop:
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Inserisci il titolo del libro");
                titolo = input.nextLine();
                System.out.println("Inserisci l'anno di pubblicazione");
                annoDiPubblicazione = Integer.parseInt(input.nextLine());
                System.out.println("Inserisci l'autore");
                autore = input.nextLine();
                System.out.println("Inserisci il genere");
                genere = input.nextLine();
                input.close();
                break;


            } catch (Exception ex) {
                System.err.println(ex);

            }
        }
        return new Libri(titolo, annoDiPubblicazione, rndm.nextInt(10, 300), autore, genere);
    }

    public static Riviste addRivista() {
        Random rndm = new Random();
        String titolo;
        int annoDiPubblicazione;
        String autore;
        String genere;
        loop:
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Inserisci il titolo della rivista");
                titolo = input.nextLine();
                System.out.println("Inserisci l'anno di pubblicazione");
                annoDiPubblicazione = Integer.parseInt(input.nextLine());
                input.close();
                break;


            } catch (Exception ex) {
                System.err.println(ex);

            }
        }
        return new Riviste(titolo, annoDiPubblicazione, rndm.nextInt(10, 300), Periodicita.randomPeriodicita());
    }

    public static void removeElementByISBN(List<Catalogo> catalogoList) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Inserisci il codice ISBN dell'elemento che vuoi eliminare");
            int ISBN = Integer.parseInt(input.nextLine());
            List<Catalogo> elementoCatalogo = catalogoList.stream().filter(elem -> elem.getISBN() == ISBN).toList();
            input.close();


            catalogoList.remove(elementoCatalogo.get(0));
            catalogoList.forEach(System.out::println);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void searchElementByISBN(List<Catalogo> catalogoList) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Inserisci il codice ISBN dell'elemento che vuoi cercare");
            int ISBN = Integer.parseInt(input.nextLine());
            List<Catalogo> elementoCatalogo = catalogoList.stream().filter(elem -> elem.getISBN() == ISBN).toList();
            input.close();
            elementoCatalogo.forEach(System.out::println);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void searchElementByPublishDate(List<Catalogo> catalogoList) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Inserisci l'anno di pubblicazione dell'elemento che vuoi visualizzare");
            int annoDiPubblicazione = Integer.parseInt(input.nextLine());
            List<Catalogo> elementoCatalogo = catalogoList.stream().filter(elem -> elem.getAnnoDiPubblicazione() == annoDiPubblicazione).toList();
            input.close();
            elementoCatalogo.forEach(System.out::println);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void searchElementByAuthor(List<Catalogo> catalogoList) {

        try {
            List<Libri> listaLibri = new ArrayList<>();
            catalogoList.forEach(ele -> listaLibri.add((Libri) ele));
            Scanner input = new Scanner(System.in);
            System.out.println("Inserisci l'autore dell'elemento che vuoi visualizzare");
            String autore = input.nextLine();
            List<Libri> list = listaLibri.stream().filter(elem -> elem.getAutore().equalsIgnoreCase(autore)).toList();
            input.close();
            list.forEach(System.out::println);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static boolean saveData(List<Catalogo> libri, List<Catalogo> riviste) throws IOException {

        List<Libri> listaLibri = new ArrayList<>();
        libri.forEach(elem -> listaLibri.add((Libri) elem));
        List<Riviste> listaRiviste = new ArrayList<>();
        riviste.forEach(elem -> listaRiviste.add((Riviste) elem));
        File file = new File("src/output.txt");
        FileUtils.writeStringToFile(file, "", StandardCharsets.UTF_8, false);
        for (int i = 0; i < listaLibri.size(); i++) {
            FileUtils.writeStringToFile(file, listaLibri.get(i).getTitolo() + "@" + listaLibri.get(i).getAnnoDiPubblicazione() + "@" + listaLibri.get(i).getNumeroDiPagine() + "@" + listaLibri.get(i).getAutore() + "@" + listaLibri.get(i).getGenere() + "#", StandardCharsets.UTF_8, true);
        }
        FileUtils.writeStringToFile(file, "Fine-Lista-Libri", StandardCharsets.UTF_8, true);
        for (int i = 0; i < listaRiviste.size(); i++) {
            FileUtils.writeStringToFile(file, listaRiviste.get(i).getTitolo() + "@" + listaRiviste.get(i).getAnnoDiPubblicazione() + "@" + listaRiviste.get(i).getNumeroDiPagine() + "@" + listaRiviste.get(i).getPeriodicita() + "#", StandardCharsets.UTF_8, true);
        }

        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        System.out.println("Nel file e' presente: " + content);
        return false;
    }


}







