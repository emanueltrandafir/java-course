package service;

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

  
public interface ServiceInterface {
	
	
	int createItem(Item item);  									// => will save the item and return the associated id

	Item readItem(int id) throws NullPointerException;			    // => will return the item with the given id or throw and error (if not found)
	
	void updateItem(int id, Item item) throws NullPointerException; // => will override the item with the given id or throw and error (if not found)
	
	void deleteItem(int id) throws NullPointerException;		 	// => will delete the item with the given id or throw and error (if not found)
	
	List<Item> readAllItems();									 	// => will return a list of all the registered items
	
	
	
}
