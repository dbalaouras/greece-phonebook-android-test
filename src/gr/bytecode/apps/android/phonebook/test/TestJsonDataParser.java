package gr.bytecode.apps.android.phonebook.test;

import gr.bytecode.apps.android.phonebook.entities.Entry;
import gr.bytecode.apps.android.phonebook.entities.EntryCategory;
import gr.bytecode.apps.android.phonebook.services.JsonDataParser;

import java.io.InputStream;
import java.util.Iterator;

import junit.framework.TestCase;

/**
 * Test case for the @JsonDataParser
 * 
 * @author Dimitris Balaouras
 * @copyright 2013 ByteCode.gr
 * 
 */
public class TestJsonDataParser extends TestCase {

	/**
	 * The object under test
	 */
	private JsonDataParser dataParser;

	/**
	 * @param name
	 */
	public TestJsonDataParser(String name) {

		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {

		super.setUp();

		// instantiate the parser
		dataParser = new JsonDataParser();

		// set the data
		String data = loadData();

		// verify we have valid data
		assertNotNull(data);

		dataParser.setData(data);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {

		super.tearDown();
	}

	/**
	 * Test Categories iterator
	 */
	public final void testGetEntryCategoryIterator() {

		Iterator<EntryCategory> categoriesIterator = dataParser.getEntryCategoryIterator();

		EntryCategory entryCategory = null;
		int entriesFound = 0;
		while (categoriesIterator.hasNext()) {
			// load the entry
			entryCategory = categoriesIterator.next();

			// make sure we got a name
			assertNotNull(entryCategory.getName());
			assertFalse("".equals(entryCategory.getName()));

			entriesFound++;

		}

		// check if we got all 8 categories
		assertTrue(entriesFound == 8);
	}

	/**
	 * Test Entries iterator
	 */
	public final void testGetEntriesIterator() {

		Iterator<Entry> entriesIterator = dataParser.getEntriesIterator();

		Entry entry = null;
		int entriesFound = 0;
		while (entriesIterator.hasNext()) {
			// load the entry
			entry = entriesIterator.next();

			System.out.println(entry.getExternalCategoryId());

			assertTrue(entry.getExternalCategoryId() > 0);

			entriesFound++;

		}

		System.out.println(entriesFound);

		// check if we got enough entries
		assertTrue(entriesFound > 0);
	}

	/**
	 * @return
	 */
	protected String loadData() {

		String file = "assets/phonebook.json";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);

		// check if there is a problem in loading the file
		assertNotNull(is);

		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";

	}
}
