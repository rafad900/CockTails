package com.rememberthekey.cocktail;


import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    // Create the activity
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    // Set up the monitor
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(NameActivity.class.getName(), null, false);

    @org.junit.Before
    public void setUp() {
        // Get the activity that was created
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfSecondActivityOnClick() {
        assertNotNull(mActivity.findViewById(R.id.name_search));

        onView(withId(R.id.name_search)).perform(click());
        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull(secondActivity);
        secondActivity.finish();
    }

    @org.junit.After
    public void tearDown() {
        mActivity = null;
    }
}