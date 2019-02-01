
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner lukija = new Scanner(System.in);

    public static void main(String args[]) {

        List<String> sanat;
        List<Character> arvaukset;
        Character kirjain;
        String tilanne, vastaus = "alustus";
        String tiedostonNimi = null, merkkijono = null;
        Sanalista lista = null;
        int arvaustenLkm = 0, sananPituus = 0;

        System.out.println("Tervetuloa Hirsipuu-peliin!");

        while (tiedostonNimi == null || lista == null) {

            try {

                System.out.printf("Syota tiedoston nimi, josta sanat luetaan >");
                tiedostonNimi = lukija.nextLine();
                lista = new Sanalista(tiedostonNimi);

                if (lista.annaSanat() != null); else {
                    tiedostonNimi = null;
                }

            } catch (Exception e) {
                System.out.println("Tiedoston avaamisessa tapahtui virhe. Yrita uudelleen.");
                tiedostonNimi = null;
            }
        }

        while (arvaustenLkm <= 0) { // Arvausten lukumaaran maarittaminen

            try {

                System.out.printf("Kuinka monta arvausyritysta? >");
                arvaustenLkm = lukija.nextInt();
                lukija.nextLine();

                if (arvaustenLkm == 0) {
                    System.out.println("Arvausten lukumaara ei voi olla nolla!");
                }
                if (arvaustenLkm < 0) {
                    System.out.println("Arvausten lukumaara ei voi olla negatiivinen!");
                }

            } catch (Exception e) {
                System.out.println("Virheellinen syote!");
                lukija.nextLine();
                arvaustenLkm = 0;
            }
        }

        while (!vastaus.equals("p") && !vastaus.equals("k") && !vastaus.equals("e")) { //Lisäasetusten maarittaminen

            System.out.println("Haluatko valita sanan pituuden tai kirjainten perusteella?");
            System.out.printf("Pituuden perusteella - p\nKirjainten perusteella - k\nEn halua - e\nVastauksesi >");
            vastaus = lukija.nextLine();
            
            if(!vastaus.equals("p") && !vastaus.equals("k") && !vastaus.equals("e")){
                System.out.println("Virheellinen syote!");
            }
        }

        if (vastaus.equals("p")) { // Sanan määrittäminen sen pituuden perusteella
            while (sananPituus <= 0 || lista.annaSanat().size() <= 0) {
                
                lista = new Sanalista(tiedostonNimi); // Sanojen reset
                
                try {

                    System.out.printf("Syota haluamasi sananpituus >");
                    sananPituus = lukija.nextInt();
                    lukija.nextLine();

                    if (sananPituus == 0) {
                        System.out.println("Sananpituus ei voi olla nolla!");
                    }
                    if (sananPituus < 0) {
                        System.out.println("Sananpituus ei voi olla negatiivinen!");
                    }

                } catch (Exception e) {
                    System.out.println("Virheellinen syote!");
                    lukija.nextLine();
                    sananPituus = 0;
                }
                lista = lista.sanatJoidenPituusOn(sananPituus);
                if(lista.annaSanat().isEmpty() && sananPituus > 0){
                    System.out.println("Ei yhtaan kriteerit tayttavaa sanaa! Yrita uudelleen!");
                }
            }
        }
        
        if (vastaus.equals("k")){ // Sanan määrittäminen sen pituuden ja kirjainten perusteella
            
            while(lista.annaSanat().size() <= 0 || merkkijono == null) {
                
                lista = new Sanalista(tiedostonNimi); // Sanojen reset
                
                try{
                    
                    System.out.println("Voit valita hirsipuun sanan maarittamalla osan kirjaimista.");
                    System.out.println("_ -merkit tarkoittavat mita tahansa kirjainta.");
                    System.out.println("Esimerkiksi syotteella \"_a_e__\" voidaan suodattaa mahdollisiksi sanoiksi sanat kaveri ja kameli.");
                    System.out.printf("\nSyota haluamasi sanan kirjaimet ohjeiden mukaisesti >");
                    merkkijono = lukija.nextLine();
                    
                }catch(Exception e){
                    System.out.println("Virheellinen syote!");
                    merkkijono = null;
                }
                
                lista = lista.sanatJoissaMerkit(merkkijono);
                
                if(lista.annaSanat().isEmpty()){
                    System.out.println("\nEi yhtaan kriteerit tayttavaa sanaa! Yrita uudelleen!");
                }
            }
        }

        sanat = lista.annaSanat(); // Haetaan sanat sanalistalta
        Hirsipuu peli = new Hirsipuu(sanat, arvaustenLkm);
        
        
        while (!peli.onLoppu() && peli.arvauksiaOnJaljella() > 0) { // PELI ALKAA

            tilanne = peli.kerroTilanne();
            tilanne = tilanne.replace("", " ");
            System.out.println("Tilanne: " + tilanne); //TILANTEEN MÄÄRITYS

            System.out.printf("\nArvaa kirjain >");
            vastaus = lukija.nextLine();
            kirjain = vastaus.charAt(0);

            peli.arvaa(kirjain);

            arvaustenLkm = peli.arvauksiaOnJaljella();
            System.out.println("Arvauksia jaljella: " + arvaustenLkm);

            arvaukset = peli.arvaukset();
            System.out.println("Olet arvannut: " + arvaukset);
        }

        System.out.println("\nSana oli \"" + peli.sana() + "\"."); //PELI LOPPUI
        if (peli.onLoppu()) {
            System.out.println("Voitit pelin!");
        } else {
            System.out.println("Havisit pelin!");
        }
    }
}
