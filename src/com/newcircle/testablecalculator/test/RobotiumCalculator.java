package com.newcircle.testablecalculator.test;

import android.widget.EditText;
import android.widget.TextView;

import com.newcircle.calculatorlib.Logic.OP;
import com.newcircle.testablecalculator.R;
import com.robotium.solo.Solo;

public class RobotiumCalculator extends BaseTest {
	private Solo mSolo;

	public RobotiumCalculator(Solo solo) {
		mSolo = solo;
	}

	public Double calcByEditText(Double x, Double y, OP op) {
		EditText function = (EditText)mSolo.getView(R.id.function);
		mSolo.clearEditText(function);
		mSolo.enterText(function, String.valueOf(x) + opToString(op) + String.valueOf(y));

		mSolo.sleep(1000);

		TextView result = (TextView)mSolo.getView(R.id.result);

		return Double.valueOf(result.getText().toString());
	}

	public Double calcByClickingDigits(Double x, Double y, OP op) {
		EditText function = (EditText)mSolo.getView(R.id.function);
		mSolo.clearEditText(function);

		mSolo.clickOnView(mSolo.getView(R.id.firstNumber));
		mSolo.clickOnButton("CLR");
		clickThis(x);

		mSolo.clickOnView(mSolo.getView(R.id.secondNumber));
		mSolo.clickOnButton("CLR");
		clickThis(y);

		switch(op) {
		case DIV:
			mSolo.clickOnView(mSolo.getView(R.id.div));
			break;
		case MINUS:
			mSolo.clickOnView(mSolo.getView(R.id.minus));
			break;
		case MUL:
			mSolo.clickOnView(mSolo.getView(R.id.mul));
			break;
		case PLUS:
			mSolo.clickOnView(mSolo.getView(R.id.plus));
			break;
		default:
			break;
		}   	

		mSolo.sleep(100);

		TextView result = (TextView)mSolo.getView(R.id.result);

		return Double.valueOf(result.getText().toString());
	}	

	private void clickThis(Double num) {
		String numStr = String.valueOf(num);
		for(int i = 0; i < numStr.length(); i++) {
			switch(numStr.charAt(i)) {
			case '.': mSolo.clickOnView(mSolo.getView(R.id.dot)); break;
			case '0': mSolo.clickOnView(mSolo.getView(R.id.digit0)); break;
			case '1': mSolo.clickOnView(mSolo.getView(R.id.digit1)); break;
			case '2': mSolo.clickOnView(mSolo.getView(R.id.digit2)); break;
			case '3': mSolo.clickOnView(mSolo.getView(R.id.digit3)); break;
			case '4': mSolo.clickOnView(mSolo.getView(R.id.digit4)); break;
			case '5': mSolo.clickOnView(mSolo.getView(R.id.digit5)); break;
			case '6': mSolo.clickOnView(mSolo.getView(R.id.digit6)); break;
			case '7': mSolo.clickOnView(mSolo.getView(R.id.digit7)); break;
			case '8': mSolo.clickOnView(mSolo.getView(R.id.digit8)); break;
			case '9': mSolo.clickOnView(mSolo.getView(R.id.digit9)); break;
			}
		}
	}

}
