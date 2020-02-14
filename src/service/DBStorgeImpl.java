package service;

import java.util.List;

import model.Item;
import repository.ItemRepositoryImpl;
import repository.RepositoryInterface;

public class DBStorgeImpl implements ServiceInterface{

	private RepositoryInterface itemRepository = ItemRepositoryImpl.getInstance();
	
	@Override
	public int createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item readItem(int id) throws NullPointerException {
		if(!itemRepository.exists(id)) {
			throw new NullPointerException();
		}
		return itemRepository.getById(id);
	}

	@Override
	public void updateItem(int id, Item item) throws NullPointerException {
		if(!itemRepository.exists(id)) {
			throw new NullPointerException();
		}
		item.setId(id);
		itemRepository.update(item);
	}

	@Override
	public void deleteItem(int id) throws NullPointerException {
		if(!itemRepository.exists(id)) {
			throw new NullPointerException();
		}
		itemRepository.delete(id);
	}

	@Override
	public List<Item> readAllItems() {
		return itemRepository.findAll();
	}

}
