package section2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Item;
import repository.RepositoryInterface;
import section2.repository.MockRepository;
import service.DBStorgeImpl;
import service.ServiceInterface;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 																							 *
 *  	 	     _                     _          _                            				 *
 *     		    | |                   | |        | |                           				 *
 * 	  		  __| | ___    _ __   ___ | |_    ___| |__   __ _ _ __   __ _  ___ 				 *
 *	 		 / _` |/ _ \  | '_ \ / _ \| __|  / __| '_ \ / _` | '_ \ / _` |/ _ \				 *
 * 			| (_| | (_) | | | | | (_) | |_  | (__| | | | (_| | | | | (_| |  __/				 *
 *  		 \__,_|\___/  |_| |_|\___/ \__|  \___|_| |_|\__,_|_| |_|\__, |\___|				 *
 *		                                                             __/ |     				 *
 *		                                                            |___/      				 *
 * 																							 *
 *   																						 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class DBStorageImplTests {

	private static ServiceInterface 	service;
	private static RepositoryInterface  mockedRepo;
	public  static Item 				testItem;
	public  static int 					nonExistingId = 99;
	
	@BeforeClass
	public static void init() { 
		service = new DBStorgeImpl();

		testItem 	= new Item();
		testItem.setId(1);
		testItem.setBarcode("12345678");
		testItem.setName("Brocoli");
		testItem.setCode(172);
		
		try {
			mockedRepo = new MockRepository();
			Field repositoryField = service.getClass().getDeclaredField("itemRepository");
			repositoryField.setAccessible(true);
			repositoryField.set(service, mockedRepo);
			
		} catch (Exception e) {	}
	}
	
	
	@Before
	public void cleanMockRepo() {
		((MockRepository) mockedRepo).refresh();
	}
	
	
	@Test
	public void createItem_RepoSaveMethodCalled() { 
		
		int testItemId = service.createItem(testItem);
		Item passedItem = ((MockRepository) mockedRepo).savedItem;
		
		assertTrue(testItem.getName().equals(passedItem.getName()));
		assertTrue(testItem.getBarcode().equals(passedItem.getBarcode()));
		assertEquals(0, testItemId);
	}

	@Test
	public void readItem_existing_repoMethodsCalledItemReturned() { 
		
		int id = testItem.getId();
		Item returned = service.readItem(id);
		
		int checkedId = ((MockRepository) mockedRepo).idOfCheckedItem;
		assertEquals(id, checkedId);
		
		int readId = ((MockRepository) mockedRepo).idOfReadedItem;
		assertEquals(id, readId);
	}
	

	@Test(expected = NullPointerException.class)
	public void readItem_notExisting_repoMethodsCalledThrowsNullPointer() { 
		
		service.readItem(nonExistingId);
		
		int checkedId = ((MockRepository) mockedRepo).idOfCheckedItem;
		assertEquals(nonExistingId, checkedId);
	}
	
	@Test
	public void updateItem_existing_RepoUpdateMethodCalled() { 
		
		service.updateItem(testItem.getId(), testItem);
		
		int checkedId = ((MockRepository) mockedRepo).idOfCheckedItem;
		assertEquals(testItem.getId(), checkedId);
		
		Item updatedItem = ((MockRepository) mockedRepo).updatedItem;
		assertEquals(testItem, updatedItem);
	}

	@Test(expected = NullPointerException.class)
	public void updateItem_nonExisting_RepoExistingCheckThenThrowsNullPointer() { 
		
		service.updateItem(nonExistingId, testItem);
		
		int checkedId = ((MockRepository) mockedRepo).idOfCheckedItem;
		assertEquals(testItem.getId(), checkedId);
	}
	
	@Test
	public void deleteItem_existing_RepoDeleteMethodCalled() { 
		
		service.deleteItem(testItem.getId());
		
		int checkedId = ((MockRepository) mockedRepo).idOfCheckedItem;
		assertEquals(testItem.getId(), checkedId);
		
		int deletedId = ((MockRepository) mockedRepo).idOfDeletedItem;
		assertEquals(testItem, deletedId);
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteItem_nonExisting_RepoExistingCheckThenThrowsNullPointer() { 
		
		service.deleteItem(nonExistingId);
		
		int checkedId = ((MockRepository) mockedRepo).idOfCheckedItem;
		assertEquals(testItem.getId(), checkedId);
	}
	
	@Test
	public void readAllItems_RepoFindAllMethodCalled() { 
		
		service.readAllItems();
		assertTrue(((MockRepository) mockedRepo).findAllCalled);
	}
}
