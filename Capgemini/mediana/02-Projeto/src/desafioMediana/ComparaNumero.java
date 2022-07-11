package desafioMediana;

import java.util.Comparator;

public class ComparaNumero implements Comparator<Mediana> {

    @Override
    public int compare(Mediana m1, Mediana m2) {
        return Integer.compare(m1.getNumero(), m2.getNumero());
    }

	
}