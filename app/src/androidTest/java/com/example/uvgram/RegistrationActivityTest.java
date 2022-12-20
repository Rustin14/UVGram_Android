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

import com.example.uvgram.Activities.CompleteRegistrationActivity;
import com.example.uvgram.Activities.LoginActivity;
import com.example.uvgram.Activities.RegistrationActivity;

import org.junit.Rule;
import org.junit.Test;

public class RegistrationActivityTest {

    @Rule
    public ActivityScenarioRule<RegistrationActivity> activityScenarioRule =
            new ActivityScenarioRule<RegistrationActivity>(RegistrationActivity.class);

    @Test
    public void emptyTextTest() {
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withText(R.string.emptyInputs))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void invalidPasswordFormatTest() {
        onView(withId(R.id.nameInput))
                .perform(typeText("usuario"), closeSoftKeyboard());
        onView(withId(R.id.emailInputText))
                .perform(typeText("email@email.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordInputText))
                .perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.confirmedPasswordInput))
                .perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withText(R.string.passwordFormatError))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void passwordsNotMatchingTest() {
        onView(withId(R.id.nameInput))
                .perform(typeText("usuario"), closeSoftKeyboard());
        onView(withId(R.id.emailInputText))
                .perform(typeText("email@email.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordInputText))
                .perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.confirmedPasswordInput))
                .perform(typeText("1234567"), closeSoftKeyboard());
        onView(withId(R.id.signUpButton))
                .perform(click());
        onView(withText(R.string.passwordsNotMatching))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void loginButtonTest() {
        Intents.init();
        onView(withId(R.id.loginButton))
                .perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void registerVerificationTest() {
        Intents.init();
        onView(withId(R.id.nameInput))
                .perform(typeText("usuario"), closeSoftKeyboard());
        onView(withId(R.id.emailInputText))
                .perform(typeText("email@email.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordInputText))
                .perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.confirmedPasswordInput))
                .perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.signUpButton))
                .perform(click());
        intended(hasComponent(CompleteRegistrationActivity.class.getName()));
        Intents.release();
    }



}
