package biblioteka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AutorTest {
	
	Autor a;

	@BeforeEach
	void setUp() throws Exception {
		a = new Autor();
	}

	@AfterEach
	void tearDown() throws Exception {
		a = null;
	}

	@Test
	void testSetImeSveOk() {
		a.setIme("Ana");
		
		assertEquals("Ana", a.getIme());
	}
	
	@Test
	void testSetImeNull() {
		Exception e = assertThrows(NullPointerException.class, () -> a.setIme(null));
		
		assertEquals("Ime ne sme biti null", e.getMessage());
	}
	
	@Test
	void testSetImeEmpty() {
		assertThrows(IllegalArgumentException.class, () -> a.setIme(""));
	}
	
	@Test
	void testSetPrezimeSveOk() {
		a.setPrezime("Vucic");
		
		assertEquals("Vucic", a.getPrezime());
	}
	
	@Test
	void testPrezimeImeNull() {
		Exception e = assertThrows(NullPointerException.class, () -> a.setPrezime(null));
		
		assertEquals("Prezime ne sme biti null", e.getMessage());
	}
	
	@Test
	void testSetPrezimeEmpty() {
		assertThrows(IllegalArgumentException.class, () -> a.setPrezime(""));
	}
	
	@Test
	void testToString() {
		a.setIme("Ana");
		a.setPrezime("Vucic");
		String s = a.toString();
		assertTrue(s.contains(a.getIme()));
		assertTrue(s.contains("Vucic"));
	}
	
	
	@ParameterizedTest
	@CsvSource ( {
		"Laza,Lazic,Laza,Lazic,true",
		"Laza,Lazic,Mika,Lazic,false",
		"Laza,Lazic,Laza,Mikic,false",
		"Laza,Lazic,Mika,Mikic,false"

	})
	void testEquals(String ime1, String prezime1, String ime2, String prezime2, boolean isti) {
		a.setIme(ime1);
		a.setPrezime(prezime1);
		
		Autor b = new Autor();
		b.setIme(ime2);
		b.setPrezime(prezime2);
		
		assertEquals(isti, a.equals(b));
		
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(a.equals(null));
	}
	
	@Test
	void testEqualsSameObject() {
		assertTrue(a.equals(a));
	}
	
	@Test
	void testEqualsWrongClass() {
		assertFalse(a.equals(new Exception()));
	}
	

}
