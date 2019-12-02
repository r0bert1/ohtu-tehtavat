package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    
    public Nollaa(TextField tulostekentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tulostekentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        sovellus.nollaa();
        syotekentta.setText("");
        tulostekentta.setText("" + sovellus.tulos());
    }
    
    @Override
    public void peru() {}
}