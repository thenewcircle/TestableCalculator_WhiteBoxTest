package com.newcircle.testablecalculator.test;

import java.util.Random;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;

import com.newcircle.calculatorlib.Logic.OP;
import com.newcircle.testablecalculator.Calculator;
import com.robotium.solo.Solo;

public class TestCalculator extends ActivityInstrumentationTestCase2<Calculator>{
	private static final Double MIN_RANDOM_D = 0.0;
	private static final Double MAX_RANDOM_D = 100000.0;

	private static final Integer MIN_RANDOM_I = 0;
	private static final Integer MAX_RANDOM_I = 100000;
	
	enum ETestingTool {
		TOOL_ROBOTIUM,
		TOOL_ESPRESSO
	}
	
	private ETestingTool mTestingTool =  ETestingTool.TOOL_ROBOTIUM;
	
	// Robotium
	private Solo mSolo;
	private RobotiumCalculator mRobotiumCalculator;
	
	// Espresso
	private EspressoCalculator mEspressoCalculator;
	
	Random mRandom = new Random();

	public TestCalculator() {
		super(Calculator.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// Robotium
		mSolo = new Solo(getInstrumentation(), getActivity());
		mRobotiumCalculator = new RobotiumCalculator(mSolo);
		
		// Espresso
		mEspressoCalculator = new EspressoCalculator();
	}
	
	public void testBasicDouble() {
		singleOP(getRandomDouble(), getRandomDouble(), OP.DIV);

		singleOP(getRandomDouble(), getRandomDouble(), OP.MUL);

		singleOP(getRandomDouble(), getRandomDouble(), OP.PLUS);

		singleOP(getRandomDouble(), getRandomDouble(), OP.MINUS);
	}

	public void testBasicInteger() {
		singleOP(getRandomInteger(), getRandomInteger(), OP.DIV);

		singleOP(getRandomInteger(), getRandomInteger(), OP.MUL);

		singleOP(getRandomInteger(), getRandomInteger(), OP.PLUS);

		singleOP(getRandomInteger(), getRandomInteger(), OP.MINUS);
	}
	
	private Double getRandomDouble() {
		return MIN_RANDOM_D + (MAX_RANDOM_D - MIN_RANDOM_D)*mRandom.nextDouble();
	}

	private Integer getRandomInteger() {
		return MIN_RANDOM_I +  mRandom.nextInt(MAX_RANDOM_I-MIN_RANDOM_I);
	}
	
	@SuppressLint("DefaultLocale")
	public static String fmt(double d)
	{
		if(d == (long)d)
			return Long.toString((long)d);
		else 
			return String.format("%.4f", d);
	}
	
	private void singleOP(Double x, Double y, OP op) {
		String expResults = fmt(mRobotiumCalculator.calcGoldenModel(x, y, op));

		switch (mTestingTool) {
		
		case TOOL_ROBOTIUM:
			mRobotiumCalculator.calcByEditText(x, y, op);
			assertTrue(mSolo.searchText(expResults));
			mRobotiumCalculator.calcByClickingDigits(x,y,op);
			assertTrue(mSolo.searchText(expResults));
			break;
		
		case TOOL_ESPRESSO:
			mEspressoCalculator.calcByEditText(x, y, op, expResults);
			mEspressoCalculator.calcByClickingDigits(x, y, op, expResults);
			break;
			
		default:
			
			break;
		
		}
	}

	private void singleOP(Integer x, Integer y, OP op) {
		singleOP((double)x, (double)y, op);
	}
	
	@Override
	protected void tearDown() throws Exception{
		mSolo.finishOpenedActivities();
	}
}
