package com.hibernate;

import jakarta.persistence.Embeddable;

@Embeddable
public class Marks {
	private double engMarks;
	private double mathMarks;
	private double sciMarks;

	public double getEngMarks() {
		return engMarks;
	}

	public void setEngMarks(double engMarks) {
		this.engMarks = engMarks;
	}

	public double getMathMarks() {
		return mathMarks;
	}

	public void setMathMarks(double mathMarks) {
		this.mathMarks = mathMarks;
	}

	public double getSciMarks() {
		return sciMarks;
	}

	public void setSciMarks(double sciMarks) {
		this.sciMarks = sciMarks;
	}

	@Override
	public String toString() {
		return "\nMarks [\nengMarks=" + engMarks + ", \nmathMarks=" + mathMarks + ", \nsciMarks=" + sciMarks + "\n]";
	}

}
