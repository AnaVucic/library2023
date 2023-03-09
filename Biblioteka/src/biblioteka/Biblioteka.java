package biblioteka;

import java.util.ArrayList;
import java.util.List;

import biblioteka.interfejs.BibliotekaInterfejs;

public class Biblioteka implements BibliotekaInterfejs {

	private List<Knjiga> knjige = new ArrayList<Knjiga>();

	@Override
	public void dodajKnjigu(Knjiga knjiga) {
		if (knjiga == null)
			throw new NullPointerException("Knjiga koja se unosi ne sme biti null");
		if (knjige.contains(knjiga))
			throw new IllegalArgumentException("Duplikat knjiga ne moze biti ubacena");
		knjige.add(knjiga);
	}

	@Override
	public void obrisiKnjigu(Knjiga knjiga) {
		knjige.remove(knjiga);

	}

	@Override
	public List<Knjiga> vratiSveKnjige() {
		return knjige;
	}

	@Override
	public List<Knjiga> pronadjiKnjigu(Autor autor, long isbn, String naslov, String izdavac) {
		if(autor == null && isbn <= 0 && naslov == null && izdavac == null)
			throw new IllegalArgumentException("Morate uneti barem jedan kriterijum za pretragu");
		List<Knjiga> listaRezultata = new ArrayList<Knjiga>();
		if (naslov != null)
			for (Knjiga k : knjige) {
				if (k.getNaslov().toLowerCase().contains(naslov.toLowerCase().trim())) {
					listaRezultata.add(k);
				}
			}
		return listaRezultata;
	}

}
