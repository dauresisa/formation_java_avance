package fr.dawan.java_avance.trainingtest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class PenguinServiceTest {
	Penguin kowalsky = new Penguin("kowalsky");
	Penguin ricoh = new Penguin("ricoh");
	Penguin soldat = new Penguin("soldat");
	Penguin commandant = new Penguin("commandant");
	Penguin tux = new Penguin("tux");
	
	List<Penguin> smallList = Arrays.asList(kowalsky, commandant);
	List<Penguin> bigList = Arrays.asList(kowalsky,ricoh,soldat, commandant,tux);

	
	PenguinService penguinService;


	@BeforeAll
	void setUp() throws Exception {
		kowalsky.setFriend(commandant);
		soldat.setFriend(ricoh);
		ricoh.setFriend(tux);
		
		tux.setSpeed(50);
		kowalsky.setSpeed(88);
		commandant.setSpeed(5);
		penguinService=(PenguinService) FactoryGeneric.createInstance(PenguiServiceImpl.class);
	}

	
	@Test
	void testCreatSimplePinguin() {
		Penguin p =penguinService.creatSimplePinguin(); 
		assertTrue(p.getName()!=null);
	}

	@Test
	void testGetPinguinsListInputNull() {
		//
		List<Penguin> actual =penguinService.getPinguins() ;
		assertFalse(actual.equals(bigList));
	}
	
	@Test
	void testGetPinguins() {
		//
		List<Penguin> actual =penguinService.getPinguins(kowalsky,ricoh,soldat, commandant,tux) ;
		assertTrue(actual.equals(bigList));
	}

	@Test
	void testGetPenguinByNameListInputNull() {
		Throwable throwable = assertThrows(Exception.class,() -> penguinService.getPenguinByName(null, "soldat"));
		assertEquals("La liste en entrée est nulle, vous devez la remplir.", throwable.getMessage());
	}
	
	@Test
	void testGetPenguinByNameRenvoiPenguinNull() throws Exception {
		Throwable throwable = assertThrows(Exception.class,() -> penguinService.getPenguinByName(bigList, "toto"));
		assertEquals("Le pinguin toto n'existe pas", throwable.getMessage());
	}
	
	@Test
	void testGetPenguinByName() throws Exception {
		Penguin penguin = penguinService.getPenguinByName(bigList, "soldat");
		assertTrue(penguin.getName().equals("soldat"));
	}
	
	@Test
	void testGetFriendInputNull() {
		assertThrows(NullPointerException.class,() ->penguinService.getFriend(null) );
	}

	@Test
	void testGetFriend() throws Exception {
		Optional<Penguin> penguin = penguinService.getFriend(kowalsky);
		assertTrue(penguin.get().equals(commandant));
	}

	@Test
	void testGetPenguinWithLetter() {
		List<Penguin> actual =penguinService.getPenguinWithLetter(bigList,"a") ;
		assertAll("actual",
				()->assertTrue(actual.contains(kowalsky)),
				()->assertTrue(actual.contains(soldat) ),
				()->assertTrue(actual.contains(commandant) )
				);
	}

	@Test
	void testGetFriendsListInputNUll() {
		assertThrows(NullPointerException.class,() ->penguinService.getFriends(null) );
	}
	
	@Test
	void testGetFriends() {
		List<Penguin> actual =penguinService.getFriends(smallList) ;
		assertTrue(actual.contains(commandant));
	}

	@Test
	void testGetSmallestSizeList1Null() {
		assertThrows(NullPointerException.class,() ->penguinService.getSmallestSize(null, smallList) );
	}
	@Test
	void testGetSmallestSizeList2Null() {
		assertThrows(NullPointerException.class,() ->penguinService.getSmallestSize(bigList, null) );
	}
	@Test
	void testGetSmallestSize2ListNull() {
		assertThrows(NullPointerException.class,() ->penguinService.getSmallestSize(null, null) );
	}
	@Test
	void testGetSmallestSize() {
		List<Penguin> actual =penguinService.getSmallestSize(bigList, smallList) ;
		assertTrue(actual.equals(smallList));
	}

}
