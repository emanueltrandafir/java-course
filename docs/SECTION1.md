# SECTION 1

## 1. Check the structure of the project.

The classes with the "Do not change" message should be left untouched, the other ones can and should be updated.

## 2. Edit the Item class and add the folowing attributes:

- (String) name
- (String) description
- (String) barcode
- (int) price
- (int) department
- (int) code

    *-> hint: create a separate package 'model' for this class*

## 3. Implement the ServiceInterface class

The first methods that we need to support are:

- create item
- read item
- update item
- delete item
- read all items

Because these methods are important of for our application and they can be achieved in multiple and different ways, we will be have an interface with the them. You may find this interface in the 'service' package.

The first class who will implement this interface is the MemoryStorageImpl class and it will do this by storing the 'Item' Java objects in the memory. It must contain the implementation of the 5 mentioned methods as well as:

- a collection object (it can be a map or a list) where it will store the items
- a way to generate and verify a unique id. This ID will be the key value (if you will be using a map) and it can also be added to the Item model.

## 4. Run the unit tests

*The tests will be added to the project shortly*

## 5. Homework 1

Create another implementation of the service (ServiceImplFileStorage) that will store the items map or list as a json string into a file instead of keeping them in memory.

Run the tests again to check your solution.

*Hint 1 ( for writing the Item collection into a file, as json ): to create the json string from your Java object, you can use the Google's gson library.*

To import Gson to your eclipse project, follow the steps:
- Download the jar from [here](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.6).
- In Eclipse, right-clink on the project, go to **Build Path > Add External Archives** and select the downloaded jar.
- You can now **import com.google.gson.Gson;** and use it in your project;

*Hint 2 (when reading the items from file): After reading the content of the file, parsing the json string to your Map of items may get tricky.

For this, you can also use the gson libary as in the example below:

```java
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
```

```java
    Type itemMapType = new TypeToken<HashMap<Item>>(){}.getType();

    HashMap<Item> itemsMap = g.fromJson(json, itemMapType);
```
