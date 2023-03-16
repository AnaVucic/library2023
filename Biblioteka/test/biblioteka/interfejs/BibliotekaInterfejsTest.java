package biblioteka.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import biblioteka.Knjiga;

public abstract class BibliotekaInterfejsTest {

	protected BibliotekaInterfejs biblioteka;

	@Test
	void testDodajKnjigu() {
		Knjiga k = new Knjiga();
		k.setISBN(123);
		biblioteka.dodajKnjigu(k);
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k));
		
	}
	
	@Test
	
	@DisplayName("Provera da li je dozvoljeno unosenje duple knjige")
	void testDodajKnjiguDuplicate() {
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(123);
		
		biblioteka.dodajKnjigu(k1);
		assertThrows(IllegalArgumentException.class, () -> biblioteka.dodajKnjigu(k2));
		
	}
	
	@Test
	void testDodajKnjiguNull() {
		assertThrows(NullPointerException.class, () -> biblioteka.dodajKnjigu(null));
		
	}

	@Test
	void testObrisiKnjigu() {
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		biblioteka.dodajKnjigu(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(234);
		biblioteka.dodajKnjigu(k2);
		
		Knjiga k3 = new Knjiga();
		k3.setISBN(123);
		
		biblioteka.obrisiKnjigu(k3);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k2));
		
	}
	
	@Test
	@DisplayName("Deleting non existent book")
	void testObrisiKnjiguNonExistent() {
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		biblioteka.dodajKnjigu(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(234);
		biblioteka.dodajKnjigu(k2);
		
		Knjiga k3 = new Knjiga();
		k3.setISBN(999);
		
		biblioteka.obrisiKnjigu(k3);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k1));
		assertTrue(knjige.contains(k2));
	}
	
	@Test
	@DisplayName("Deleting null book")
	void testObrisiKnjiguNull() {
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		biblioteka.dodajKnjigu(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(234);
		biblioteka.dodajKnjigu(k2);
		
		biblioteka.obrisiKnjigu(null);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k1));
		assertTrue(knjige.contains(k2));
	}
	
	@Test
	@DisplayName("Search, all arguments are null")
	void testPronadjiKnjiguAllNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> biblioteka.pronadjiKnjigu(null, -1, null, null));
	}
	
	@Test
	@DisplayName("Search, by title, returns one record")
	void testPronadjiKnjiguOneRecord() {
		
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		k1.setNaslov("LOTR");
		biblioteka.dodajKnjigu(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(234);
		k2.setNaslov("Prohujalo sa vihorom");
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> knjige = biblioteka.pronadjiKnjigu(null, 0, "ViH", null);
		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k2));
	}
	
	
	@Test
	@DisplayName("Search, by title, returns many records")
	void testPronadjiKnjiguManyRecords() {
		
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		k1.setNaslov("Gospodar prstenova");
		biblioteka.dodajKnjigu(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(234);
		k2.setNaslov("Prohujalo sa vihorom");
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> knjige = biblioteka.pronadjiKnjigu(null, 0, "PR", null);
		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k1));
		assertTrue(knjige.contains(k2));
	}
	
	@Test
	@DisplayName("Search, by title, returns no records")
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testPronadjiKnjiguNoRecords() {
		
		Knjiga k1 = new Knjiga();
		k1.setISBN(123);
		k1.setNaslov("Gospodar prstenova");
		biblioteka.dodajKnjigu(k1);
		
		Knjiga k2 = new Knjiga();
		k2.setISBN(234);
		k2.setNaslov("Prohujalo sa vihorom");
		biblioteka.dodajKnjigu(k2);
		
		List<Knjiga> knjige = biblioteka.pronadjiKnjigu(null, 0, "abc", null);
		assertEquals(0, knjige.size());
	}
	
	
	
	

}
