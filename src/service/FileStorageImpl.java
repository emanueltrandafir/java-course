package service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Item;

public class FileStorageImpl implements ServiceInterface{

	private Map items = new HashMap<Integer, Item>();
	private int currentId = 0;
	private String fileName = "./items_file.json";
	
	
	
	@Override
	public int createItem(Item item) {

		readItemsFromFile();
		int id = getNextId();
		item.setId(id);
		items.put(id, item);

		writeItemsTofile();
		return id;
	}

	@Override
	public Item readItem(int id) throws NullPointerException {

		readItemsFromFile();
		if(items.containsKey(id)) {
			return (Item) items.get(id);
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void updateItem(int id, Item item) throws NullPointerException {

		readItemsFromFile();
		if(items.containsKey(id)) {
			item.setId(id);
			items.put(id, item);
			writeItemsTofile();
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteItem(int id) throws NullPointerException {

		readItemsFromFile();
		if(items.containsKey(id)) {
			items.remove(id);
			writeItemsTofile();
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public List<Item> readAllItems() {
		readItemsFromFile();
		return (List<Item>) items.values().stream().collect(Collectors.toList());
	}
	
	public int getNextId(){
		currentId ++;
		return currentId;
	}
	
	private boolean writeItemsTofile() {
		try {
		    Gson gson = new Gson();
		    String jsonContent = gson.toJson(this.items); 
		    BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
		    
		    writer.write(jsonContent); 
		    writer.close();
		} catch(Exception e) {
		    return false;
		}  
		return true;
	}
	
	private boolean readItemsFromFile() {
		try {
			Path path = Paths.get(this.fileName);
			String json = "";

			List<String> contents = Files.readAllLines(path);
			  
		    for(String content:contents){ 
		       json += content;
		    }
		    
		    Type itemMapType = new TypeToken<HashMap<Integer, Item>>(){}.getType();

		    this.items = new Gson().fromJson(json, itemMapType);
		    
		    int maxKey = this.items.keySet()
		    				.stream()
		    				.mapToInt(key -> (Integer)key)
		    				.max()
		    				.getAsInt();
		    
		    this.currentId = maxKey;
		    
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
