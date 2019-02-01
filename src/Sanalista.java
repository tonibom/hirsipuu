
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Sanalista {
    
    private List<String> sanat;
    private String apu;
    
    public Sanalista(String tiedostonNimi){
        
        sanat = new ArrayList<>();
        
        if(lueTiedostosta(tiedostonNimi));
        else{
            sanat = null;
        }
    }
    
    public Sanalista(List<String> sanat){
        this.sanat = sanat;
    }
    
    public List<String> annaSanat(){
        return sanat;
    }
    
    public Sanalista sanatJoidenPituusOn(int pituus){ // Sanojen määrittäminen pituuden perusteella
        
        List<String> uudetSanat = new ArrayList<>();
        
        for(int i = 0; i < sanat.size(); i++){
            
            apu = sanat.get(i);
            if(apu.length() == pituus){
                uudetSanat.add(apu);
            }
        }
        
        Sanalista halututSanat = new Sanalista(uudetSanat);
        return halututSanat;
    }
    
    public Sanalista sanatJoissaMerkit(String mjono){ // Sanojen määrittäminen pituuden ja kirjainten perusteella
        
        List<String> uudetSanat = new ArrayList<>();
        int pituus = mjono.length();
        boolean samanlainen;
        String apu2;
        
        mjono = mjono.toUpperCase();
        
        for(int i = 0; i < sanat.size()-1; i++){
            
            apu = sanat.get(i);
            apu2 = apu.toUpperCase();
            samanlainen = true;
            
            if(apu2.length() == pituus){ // Yhtä pitkät sanat
                
                for(int j = 0; j < pituus; j++){ // Kirjain kirjaimelta eteneminen
                    
                    if(mjono.charAt(j) == '_');// Merkki saa olla mikä vain
                    
                    else{ // Merkin täytyy olla jotakin tiettyä
                        
                        if(mjono.charAt(j) == apu2.charAt(j)); //Ei tehdä mitään, jos ovat samat
                        
                        else{
                            samanlainen = false;
                        }
                    }
                }
                if(samanlainen){ // Jos samanlainen, lisätään sanalistaan
                    uudetSanat.add(apu);
                }
            }
        }
        
        Sanalista halututSanat = new Sanalista(uudetSanat);
        return halututSanat;
    }
    
    private boolean lueTiedostosta( String tiedostonNimi ){ // Lukee sanat tiedostosta
        
        boolean onnistui = true;
        BufferedReader br;
	FileReader fr;
        String rivi;
        
        try {
            
            fr = new FileReader(tiedostonNimi);
            br = new BufferedReader(new FileReader(tiedostonNimi));

            while ((rivi = br.readLine()) != null) {
		sanat.add(rivi);
            }
        }
        
        catch( EOFException eofe){
            System.out.println("Sanat luettu!");
        }
        catch( IOException ioe){
            System.out.println("Tiedostoa ei ole olemassa!");
            onnistui = false;
        }
        return onnistui;
    }
}
