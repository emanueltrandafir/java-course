package section2.repository;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import repository.RepositoryInterface;
import section2.DBStorageImplTests;
import service.DBStorgeImpl;

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

public class MockRepository implements RepositoryInterface{

	public Item savedItem;
	public Item updatedItem;
	public boolean findAllCalled;
	public int idOfCheckedItem;
	public int idOfDeletedItem;
	public int idOfReadedItem;
	
	
	public MockRepository() {
		refresh();
	}
	
	public void refresh() {
		savedItem = null;
		updatedItem = null;
		findAllCalled = false;
		idOfCheckedItem = -1;
		idOfDeletedItem = -1;
		idOfReadedItem = -1;
	}
	
	@Override
	public int save(Item item) {
		this.savedItem = item;
		if(item == DBStorageImplTests.testItem) {
			return item.getId();
		}
		return -1;
	}

	@Override
	public List<Item> findAll() {
		this.findAllCalled = true;
		return new ArrayList<Item>();
	}

	@Override
	public boolean exists(int id) {
		this.idOfCheckedItem = id;
		if(id == DBStorageImplTests.testItem.getId()){
			return true;
		}
		return false;
	}

	@Override
	public void delete(int id) {
		this.idOfDeletedItem = id;		
	}

	@Override
	public Item getById(int id) {
		this.idOfReadedItem = id;
		if(id == DBStorageImplTests.testItem.getId()){
			return DBStorageImplTests.testItem;
		}
		return null;
	}

	@Override
	public void update(Item item) {
		this.updatedItem = item;
		
	}

}
