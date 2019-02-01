
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hirsipuu {

    private int arvaustenLkm = 0;
    private final String sana;
    private final List<Character> arvaukset;
    private String tilanne;
    private char[] tilanneKirjaimina;

    private final Random rand = new Random();

    public Hirsipuu(List sanalista, int arvaustenLkm) {

        this.arvaustenLkm = arvaustenLkm;
        arvaukset = new ArrayList<>();
        sana = sanalista.get(arpominen(sanalista.size())).toString().toUpperCase();
        tilanne = sana.replaceAll("[A-Z]", "_");

    }

    public boolean arvaa(Character merkki) {

        boolean oikein = true;
        int osumia = 0;

        merkki = Character.toUpperCase(merkki);

        if (!arvaukset.contains(merkki)) {

            for (int i = 0; i < sana.length(); i++) {

                if (merkki == sana.charAt(i)) {
                    osumia++;
                    tilanneKirjaimina = tilanne.toCharArray();
                    tilanneKirjaimina[i] = Character.toUpperCase(sana.charAt(i));
                    tilanne = String.valueOf(tilanneKirjaimina);
                }

            }

            if (osumia == 0) {
                System.out.println("Sanassa ei ole kyseista kirjainta");
                oikein = false;
                arvaustenLkm--;
            } else {
                System.out.println("Sanassa on kyseinen kirjain " + osumia + " kertaa.");
            }

            arvaukset.add(merkki);

        } else {
            System.out.println("Olet jo arvannut kyseisen kirjaimen!");
        }
        return oikein;
    }

    public List<Character> arvaukset() {
        return arvaukset;
    }

    public int arvauksiaOnJaljella() {
        return arvaustenLkm;
    }

    public String sana() {
        return sana;
    }

    public boolean onLoppu() {

        boolean oikein = true;

        for (int i = 0; i < sana.length(); i++) {

            if (arvaukset.contains(Character.toUpperCase(sana.charAt(i)))); else {
                oikein = false;
            }
        }
        return oikein;
    }

    private int arpominen(int koko) {
        int luku = rand.nextInt(koko);
        return luku;
    }

    public String kerroTilanne() {
        return tilanne;
    }
}
