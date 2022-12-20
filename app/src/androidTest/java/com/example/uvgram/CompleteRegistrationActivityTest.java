package com.example.uvgram;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.uvgram.Activities.CompleteRegistrationActivity;
import com.example.uvgram.Models.User;

import org.junit.Rule;
import org.junit.Test;

public class CompleteRegistrationActivityTest {

    public User setUpUser() {
        User user = new User();
        user.setName("Usuario");
        user.setPassword("usuario1");
        user.setEmail("usuario@email.com");
        return user;
    }

    @Rule
    public ActivityScenarioRule<CompleteRegistrationActivity> activityScenarioRule =
            new ActivityScenarioRule<>(new Intent(
                    ApplicationProvider.getApplicationContext(), CompleteRegistrationActivity.class)
                    .putExtra("PARTIAL_USER", setUpUser())
            );

    @Test
    public void emptyTextTest() {
        onView(withId(R.id.sendVerificationButton))
                .perform(click());
        onView(withText(R.string.emptyInputs))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
        )));
    }


}
