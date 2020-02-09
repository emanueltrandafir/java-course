package section1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Item;
import service.MemoryStorageImpl;
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

public class MemoryImplTests {

	private static ServiceInterface 	service;
	private static Item 				testItem;
	private static Item 				otherTestItem;
	 
	@BeforeClass
	public static void init() { 
		testItem 	= new Item();
		testItem.setBarcode("12345678");
		testItem.setName("Brocoli");
		testItem.setCode(172);
		
		otherTestItem 	= new Item();
		otherTestItem.setBarcode("87654321");
		otherTestItem.setName("Paprika");
		otherTestItem.setCode(125);
	} 
	
	@Before
	public void resetService() {
		service = new MemoryStorageImpl();
	}
	
	
	@Test
	public void createItem_readItem_same() { 
		
		int testItemId = service.createItem(testItem);
		Item itemReturned = service.readItem(testItemId);

		assertTrue(testItem.getBarcode().equals(itemReturned.getBarcode()));
		assertTrue(testItem.getName().equals(itemReturned.getName()));
		assertTrue(testItem.getBarcode().equals(itemReturned.getBarcode()));
		
	}
	
	@Test(expected = NullPointerException.class)
	public void readItem_notExisting_throwsNullPointer() {  
		
		service.createItem(testItem); 
		service.readItem(100);
	}
	
	@Test
	public void createItem_updateItem_readItem() {

		int testItemId = service.createItem(testItem);
		service.updateItem(testItemId, otherTestItem);
		
		Item retunedItem = service.readItem(testItemId);

		assertTrue(otherTestItem.getBarcode().equals(retunedItem.getBarcode()));
		assertTrue(otherTestItem.getName().equals(retunedItem.getName()));
		assertTrue(otherTestItem.getBarcode().equals(retunedItem.getBarcode()));
	}
	
	@Test
	public void createItem_repeat100times_allItemsAdded() {
		for(int i = 0; i<100; i++) {
			service.createItem(testItem);
		}
		List<Item> returnedItems = service.readAllItems();
		assertEquals(100, returnedItems.size());
	}

	@Test(expected = NullPointerException.class)
	public void updateItem_notExisting_throwsNullPointer() {

		service.createItem(testItem);
		service.updateItem(100, otherTestItem); 
	}

	@Test(expected = NullPointerException.class)
	public void deleteItem_readItem_throwsNullPointer() {

	    int id = service.createItem(testItem);
	    Item addedItem = service.readItem(id);
	    assertEquals(testItem.getCode(), addedItem.getCode());
	    
	    service.deleteItem(id);
	    service.readItem(id);
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteItem_notExistingItem_throwsNullPointer() {
		
		service.createItem(testItem);
		service.deleteItem(100);
	} 
	
	@Test
	public void readAllItems_noItemsAdded_emptyArray() {

	    List<Item> returnedItems = service.readAllItems();
	    assertEquals(0, returnedItems.size());
	} 
	
	@Test
	public void deleteItem_deleteAllThenRead_returnEmptyArray() {

	    List<Item> returnedItems = service.readAllItems();
	    
	    for(Item i : returnedItems) {
	    	service.deleteItem(i.getId());
	    }
	    
	    returnedItems = service.readAllItems();
	    assertEquals(0, returnedItems.size());
	}
}
