package com.example.uvgram;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.uvgram.Activities.LoginActivity;
import com.example.uvgram.Activities.RegistrationActivity;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule public ActivityScenarioRule<LoginActivity> activityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void emptyTextTest() {
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withText(R.string.emptyInputs))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void emptyPasswordFieldTest() {
        onView(withId(R.id.emailInputText))
                .perform(typeText("usuario"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withText(R.string.emptyInputs))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void emptyUsernameFieldTest() {
        onView(withId(R.id.passwordInputText))
                .perform(typeText("contrasena"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withText(R.string.emptyInputs))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void userNotFoundTest() {
        onView(withId(R.id.emailInputText))
                .perform(typeText("usuario"), closeSoftKeyboard());
        onView(withId(R.id.passwordInputText))
                .perform(typeText("contrasena"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withText(R.string.userNotFound))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void wrongPasswordTest() {
        onView(withId(R.id.emailInputText))
                .perform(typeText("gabofl"), closeSoftKeyboard());
        onView(withId(R.id.passwordInputText))
                .perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withText(R.string.incorrectPassword))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void invalidFormatPasswordTest() {
        onView(withId(R.id.emailInputText))
                .perform(typeText("gabofl"), closeSoftKeyboard());
        onView(withId(R.id.passwordInputText))
                .perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.loginButton))
                .perform(click());
        onView(withText(R.string.passwordFormatError))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void registerButtonTest() {
        Intents.init();
        onView(withId(R.id.registrationButton))
                .perform(click());
        intended(hasComponent(RegistrationActivity.class.getName()));
    }

}
