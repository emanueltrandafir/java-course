package repository;

import java.util.List;

import model.Item;



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


public interface RepositoryInterface {
	

	Item getById(int id);		// will return the item with the given id

	List<Item> findAll();		// will return a list of all the items from the db

	int save(Item item);		// will persist the item into the db
	
	boolean exists(int id);		// will check if the item exists in the db
	
	void delete(int id);		// will delete an item from the db
	
	void update(Item item);		// will update the item 
	
}
