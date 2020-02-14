package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.Item; 

public class MemoryStorageImpl implements ServiceInterface{

	Map<Integer, Item> itemMap  = new HashMap<>();
	int counter = 0;
	
	@Override
	public int createItem(Item item) {
		counter++;
		item.setId(counter);
		itemMap.put(item.getId(), item);
		return counter;
	}

	@Override
	public Item readItem(int id) throws NullPointerException {
		if(!itemMap.containsKey(id)) {
			throw new NullPointerException();
		}
		return itemMap.get(id);
	}

	@Override
	public void updateItem(int id, Item item) throws NullPointerException {
		if(!itemMap.containsKey(id)) {
			throw new NullPointerException();
		}
		item.setId(id);
		itemMap.put(id, item);
		
	}

	@Override
	public void deleteItem(int id) throws NullPointerException {
		if(!itemMap.containsKey(id)) {
			throw new NullPointerException();
		}
		itemMap.remove(id);
	}

	@Override
	public List<Item> readAllItems() {
		return itemMap.values().stream().collect(Collectors.toList());
	}
 
	
}
