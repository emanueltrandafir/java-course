package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Item; 

public class MemoryStorageImpl implements ServiceInterface{

	private Map items = new HashMap<Integer, Item>();
	private int currentId = 0;
	
	
	@Override
	public int createItem(Item item) {
		int id = getNextId();
		item.setId(id);
		items.put(id, item);
		return id;
	}

	@Override
	public Item readItem(int id) throws NullPointerException {
		if(items.containsKey(id)) {
			return (Item) items.get(id);
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void updateItem(int id, Item item)  throws NullPointerException {
		if(items.containsKey(id)) {
			item.setId(id);
			items.put(id, item);
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteItem(int id)  throws NullPointerException {
		if(items.containsKey(id)) {
			items.remove(id);
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public List<Item> readAllItems() {
		return (List<Item>) items.values().stream().collect(Collectors.toList());
	}

	public int getNextId(){
		currentId ++;
		return currentId;
	}
	
}
