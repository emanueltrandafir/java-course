package service;

import java.util.List;

import model.Item;
import repository.ItemRepositoryImpl;
import repository.RepositoryInterface;

public class DBStorgeImpl implements ServiceInterface{

	private RepositoryInterface itemRepository = ItemRepositoryImpl.getInstance();
	
	@Override
	public int createItem(Item item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item readItem(int id) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateItem(int id, Item item) throws NullPointerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItem(int id) throws NullPointerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> readAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

}
