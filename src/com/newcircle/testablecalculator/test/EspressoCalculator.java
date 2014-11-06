package com.newcircle.testablecalculator.test;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.*;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.*;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.*;

import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.newcircle.calculatorlib.Logic.OP;
import com.newcircle.testablecalculator.R;

public class EspressoCalculator extends BaseTest {

	public EspressoCalculator() {
	}

	public void calcByEditText(Double x, Double y, OP op, String expectedResults) {
		onView(withId(R.id.function)).perform(clearText());
		onView(withId(R.id.function)).perform(typeText(String.valueOf(x) + opToString(op) + String.valueOf(y)));
	
		onView(withId(R.id.result)).check(matches(withText(expectedResults)));
	}

	public void calcByClickingDigits(Double x, Double y, OP op, String expectedResults) {
		onView(withId(R.id.function)).perform(clearText());
		Espresso.closeSoftKeyboard();
		
		onView(withId(R.id.firstNumber)).perform(click());
		onView(withText("CLR")).perform(click());
		clickThis(x);

		onView(withId(R.id.secondNumber)).perform(click());
		onView(withText("CLR")).perform(click());
		clickThis(y);

		switch(op) {
		case DIV:
			onView(withId(R.id.div)).perform(click());
			break;
		case MINUS:
			onView(withId(R.id.minus)).perform(click());
			break;
		case MUL:
			onView(withId(R.id.mul)).perform(click());
			break;
		case PLUS:
			onView(withId(R.id.plus)).perform(click());
			break;
		default:
			break;
		}   	

		onView(withId(R.id.result)).check(matches(withText(expectedResults)));
	}	

	private void clickThis(Double num) {
		String numStr = String.valueOf(num);
		for(int i = 0; i < numStr.length(); i++) {
			switch(numStr.charAt(i)) {
			case '.': onView(withId(R.id.dot)).perform(click()); break;
			case '0': onView(withId(R.id.digit0)).perform(click()); break;
			case '1': onView(withId(R.id.digit1)).perform(click()); break;
			case '2': onView(withId(R.id.digit2)).perform(click()); break;
			case '3': onView(withId(R.id.digit3)).perform(click()); break;
			case '4': onView(withId(R.id.digit4)).perform(click()); break;
			case '5': onView(withId(R.id.digit5)).perform(click()); break;
			case '6': onView(withId(R.id.digit6)).perform(click()); break;
			case '7': onView(withId(R.id.digit7)).perform(click()); break;
			case '8': onView(withId(R.id.digit8)).perform(click()); break;
			case '9': onView(withId(R.id.digit9)).perform(click()); break;
			}
		}
	}

}
