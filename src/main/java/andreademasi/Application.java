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
        listaLibri.forEach(System.out::println);

        Supplier<Riviste> rivisteSupplier = () -> new Riviste(faker.medical().diseaseName(), rndm.nextInt(2000, 2023), rndm.nextInt(10, 300), Periodicita.randomPeriodicita());
        List<Catalogo> listaRiviste = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            listaRiviste.add(rivisteSupplier.get());
        }
        listaRiviste.forEach(System.out::println);

        // listaLibri.add(addBook());
        listaLibri.forEach(System.out::println);
        //listaRiviste.add(addRivista());
        listaRiviste.forEach(System.out::println);

        // removeElementByISBN(listaLibri);
        //searchElementByISBN(listaLibri);
        //searchElementByPublishDate(listaLibri);
        //searchElementByAuthor(listaLibri);


        loop:
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                System.out.println("Cosa vuoi fare? Puoi scegliere tra: ");
                System.out.println("1: AGGIUNGI UN ELEMENTO, 2: RIMUOVI UN ELEMENTO, 3: RICERCA UN ELEMENTO, 4: CONSULTA L'ARCHIVIO, 0: ESCI");
                int choose = Integer.parseInt(input.nextLine());
                loop2:
                switch (choose) {
                    case 0: {
                        break loop;
                    }
                    case 1: {
                        System.out.println("Digita 1 per aggiungere un LIBRO o digita 2 per aggiungere una RIVISTA");
                        int chooseAdd = Integer.parseInt(input.nextLine());
                        first:
                        switch (chooseAdd) {
                            case 1: {
                                listaLibri.add(addBook());
                                break first;
                            }
                            case 2: {
                                listaRiviste.add(addRivista());
                                break first;
                            }
                            default:
                                break first;

                        }
                        break loop2;
                    }
                    case 2: {
                        System.out.println("Digita 1 per rimuovere un LIBRO tramite ISBN o digita 2 per rimuovere una tramite ISBN");
                        int chooseRemove = Integer.parseInt(input.nextLine());
                        second:
                        switch (chooseRemove) {
                            case 1: {
                                removeElementByISBN(listaLibri);
                                break second;
                            }
                            case 2: {
                                removeElementByISBN(listaRiviste);
                                break second;
                            }
                            default:
                                break second;
                        }

                        break loop2;

                    }
                    case 3: {
                        System.out.println("Con quale parametro vuoi fare la ricerca?");
                        System.out.println("1: CODICE ISBN");
                        System.out.println("2: ANNO DI PUBBLICAZIONE");
                        System.out.println("3: AUTORE");
                        int chooseSearch = Integer.parseInt(input.nextLine());
                        third:
                        switch (chooseSearch) {
                            case 1: {
                                System.out.println("Se vuoi cercare un libro digita 1 se vuoi cercare un articolo digita 2");
                                int chooseSearch2 = Integer.parseInt(input.nextLine());
                                switch (chooseSearch2) {
                                    case 1: {
                                        searchElementByISBN(listaLibri);
                                        break third;
                                    }
                                    case 2: {
                                        searchElementByISBN(listaRiviste);
                                        break third;
                                    }
                                    default:
                                        break third;
                                }


                            }
                            case 2: {
                                System.out.println("Se vuoi cercare un libro digita 1 se vuoi cercare un articolo digita 2");
                                int chooseSearch3 = Integer.parseInt(input.nextLine());
                                switch (chooseSearch3) {
                                    case 1: {
                                        searchElementByPublishDate(listaLibri);
                                        break third;
                                    }
                                    case 2: {
                                        searchElementByPublishDate(listaRiviste);
                                        break third;
                                    }
                                    default:
                                        break third;
                                }

                            }
                            case 3: {
                                searchElementByAuthor(listaLibri);
                                break third;
                            }


                        }
                        break loop2;

                    }
                    case 4: {
                        try {
                            saveData(listaLibri, listaRiviste);
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }

                        List<Catalogo> fullList = new ArrayList<>();
                        try {
                            fullList = loadFromDisk();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                        fullList.forEach(System.out::println);
                        break;
                    }
                    default: {
                        System.out.println("Il numero inserito non e' valido");
                        break;
                    }
                }

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

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

    public static List<Catalogo> loadFromDisk() throws IOException {
        File file = new File("src/output.txt");
        List<Libri> listaLibri = new ArrayList<>();
        List<Riviste> listaRiviste = new ArrayList<>();
        String toStringFile = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        List<String> splittedList = Arrays.asList(toStringFile.split("Fine-Lista-Libri"));
        String[] libri = splittedList.get(0).split("#");
        String[] riviste = splittedList.get(1).split("#");

        for (String lib : libri) {
            String[] bookDetails = lib.split("@");
            listaLibri.add(new Libri(bookDetails[0], Integer.parseInt(bookDetails[1]), Integer.parseInt(bookDetails[2]), bookDetails[3], bookDetails[4]));
        }

        for (String riv : riviste) {
            String[] journalDetails = riv.split("@");
            listaRiviste.add(new Riviste(journalDetails[0], Integer.parseInt(journalDetails[1]), Integer.parseInt(journalDetails[2]), Periodicita.valueOf(journalDetails[3])));
        }

        List<Catalogo> fullList = new ArrayList<>();
        fullList.addAll(listaLibri);
        fullList.addAll(listaRiviste);

        return fullList;
    }
}







