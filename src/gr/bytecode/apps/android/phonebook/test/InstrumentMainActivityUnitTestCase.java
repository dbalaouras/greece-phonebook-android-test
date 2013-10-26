package gr.bytecode.apps.android.phonebook.test;

import gr.bytecode.apps.android.phonebook.ui.activities.EntryListActivity;
import gr.bytecode.apps.android.phonebook.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * @author Dimitris Balaouras
 * @copyright 2013 ByteCode.gr
 * 
 */
public class InstrumentMainActivityUnitTestCase extends
		ActivityInstrumentationTestCase2<MainActivity> {

	/**
	 * The tested Activity
	 */
	private MainActivity activity;

	public InstrumentMainActivityUnitTestCase() {

		super(MainActivity.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.test.ActivityUnitTestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {

		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}

	/**
	 * Test the home screen functionality
	 */
	@SmallTest
	public void testHomeScreen() {

		// load all the home page tile ids
		List<Integer> buttonIds = new ArrayList<Integer>();
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_emergency);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_embassies);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_healthcare);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_helplines);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_info);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_police);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_public_services);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_transportation);
		buttonIds.add(gr.bytecode.apps.android.phonebook.R.id.menu_user);

		// get the list_container item id
		int listItemId = gr.bytecode.apps.android.phonebook.R.id.list_container;

		// add monitor to check for the EntryListActivity activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				EntryListActivity.class.getName(), null, false);

		// a tile
		TextView tile = null;

		// verify all buttons exist on the home screen
		for (Integer buttonId : buttonIds) {

			Object locateItem = activity.findViewById(buttonId);

			// verify the button exists
			assertNotNull(locateItem);

			// Okay, item exists
			tile = (TextView) locateItem;

			// click on the tile
			TouchUtils.clickView(this, tile);

			// Wait 2 seconds for the start of the activity
			EntryListActivity listActivity = (EntryListActivity) monitor
					.waitForActivityWithTimeout(1000);

			// check if the activity started
			assertNotNull(listActivity);

			// locate the list
			Object listItem = listActivity.findViewById(listItemId);

			// verify the list exists
			assertNotNull(listItem);

			// Press back and continue
			this.sendKeys(KeyEvent.KEYCODE_BACK);

		}

	}
}
