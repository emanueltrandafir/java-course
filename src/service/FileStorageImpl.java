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
