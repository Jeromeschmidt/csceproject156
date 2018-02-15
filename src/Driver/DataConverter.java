package Driver;

import java.util.List;

import com.fileWriter.JsonWriter;
import com.fileWriter.XMLWriter;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import csce156project.Membership;
import csce156project.Persons;
import csce156project.Service;
import fileReader.flatFileReader;

public class DataConverter {

	@SuppressWarnings("unused")
	public static void main(String args[]) {

		XStream xstream = new XStream();

		String xmlString = xstream.toXML(Persons.class);

		Gson gson = new Gson();

		//String jsonString = gson.toJson(Persons.class);
		//flatFileReader fr = new flatFileReader();

		/*
		 * fr Reads data from the flat file; Creates Person objects; and Stores Person
		 * objects in a Person ArrayList
		 */
		List<Persons> personList = flatFileReader.readPersons();
		List<Membership> memberList = flatFileReader.readMembers();
		List<Service> serviceList = flatFileReader.readService();
		// Write Person ArrayList into a Json file
		JsonWriter jWriter = new JsonWriter();
		jWriter.jsonConverter(personList);
		jWriter.jsonConverter2(memberList);
		jWriter.jsonConverter3(serviceList);

		// Write Person ArrayList into an XML file
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.xmlConverter(personList);
		xmlWriter.xmlConverter2(memberList);
		xmlWriter.xmlConverter3(serviceList);

	}

}
