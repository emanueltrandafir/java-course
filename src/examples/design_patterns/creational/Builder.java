package examples.design_patterns.creational;

import com.google.gson.Gson;

import examples.design_patterns.creational.Person.PersonBuilder;

public class Builder {
	
	public static void main(String[] agrs) {
		new Example().runExample();
	}
	
}



class Example {
	
	public void runExample() {
		
		Person person1 = new PersonBuilder()
				.withFirstName("Anna")
				.withLastName("Popescu")
				.withPhone("0770123123")
				.withContry("RO")
				.build();
		
		Person person2 = new PersonBuilder()
				.withFirstName("Alexandru")
				.withLastName("Tanase")
				.withEmail("alexT@yahoo.com")
				.withAddress("str Lunca bradului nr 5")
				.build();
		
		Gson gson = new Gson();
		System.out.println( gson.toJson(person1) );
		System.out.println( gson.toJson(person2) );
	}
}


// the concrete class that needs to be instantiated
class Person {
	
	private String lastName;
	private String firstName;
	private String address;
	private String phone;
	private String email;
	private String country;
	
	private Person( PersonBuilder builder ) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address = builder.address;
		this.phone = builder.phone;
		this.email = builder.email;
		this.country = builder.country;
	}
	
	// the builder inner class
	public static class PersonBuilder {
		private String lastName;
		private String firstName;
		private String address;
		private String phone;
		private String email;
		private String country;
		
		public PersonBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public PersonBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		public PersonBuilder withEmail(String email) {
			this.email = email;
			return this;
		}
		public PersonBuilder withPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public PersonBuilder withAddress(String address) {
			this.address = address;
			return this;
		}
		public PersonBuilder withContry(String country) {
			this.country = country;
			return this;
		}
		public Person build() {
			return new Person(this);
		}
	}
	
}