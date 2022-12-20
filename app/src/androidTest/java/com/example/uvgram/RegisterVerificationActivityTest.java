package com.example.uvgram;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.uvgram.Activities.RegisterVerificationActivity;
import com.example.uvgram.Models.User;

import org.junit.Rule;
import org.junit.Test;

public class RegisterVerificationActivityTest {

    public User createSetupUser() {
        User user = new User();
        user.setName("Joaquin Correa");
        user.setPresentation("Hola.");
        user.setUsername("joaqcorrea");
        user.setPassword("123456");
        user.setPhoneNumber("1234567890");
        user.setEmail("joaqcorrea@gmail.com");
        user.setBirthday("1997-07-05");
        return user;
    }

    @Rule
    public ActivityScenarioRule<RegisterVerificationActivity> activityScenarioRule =
            new ActivityScenarioRule<>(new Intent(ApplicationProvider.getApplicationContext(), RegisterVerificationActivity.class)
                    .putExtra("USER", createSetupUser()));

    @Test
    public void emptyInputTest() {
        onView(withId(R.id.sendVerificationButton))
                .perform(click());
        onView(withText(R.string.emptyInputs))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void invalidCodeFormatTest() {
        onView(withId(R.id.verificationInput))
                .perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.sendVerificationButton))
                .perform(click());
        onView(withText(R.string.invalidCodeFormat))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

}
