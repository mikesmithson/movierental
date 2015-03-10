package com.ford.movie.rental;

import java.util.ArrayList;
import java.util.List;

public class Customer 
{
	static final String NEW_LINE = "\n";
	static final String TAB = "\t";
	private StatementBuilder statementBuilder = new StatementBuilder();

	public Customer (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
	
	String createBody() {
		StringBuilder lineItems = new StringBuilder();
		for (Rental rental:rentals) {
			lineItems.append(statementBuilder.createLineItem(this, rental));
		}
		return lineItems.toString();
	}

	void calculateFrequentRenterPoints(Rental rental) {
		frequentRenterPoints++;
		
		if (rental.getMovie ().getPriceCode () == Movie.NEW_RELEASE 
				&& rental.getDaysRented () > 1)
			frequentRenterPoints++;
	}

	double determineAmountForRental(Rental rental) {
		double 		thisAmount = 0;
		
		// determines the amount for each line
		switch (rental.getMovie ().getPriceCode ()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (rental.getDaysRented () > 2)
					thisAmount += (rental.getDaysRented () - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += rental.getDaysRented () * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (rental.getDaysRented () > 3)
					thisAmount += (rental.getDaysRented () - 3) * 1.5;
				break;
		}
		return thisAmount;
	}

	String createFooter() {
		StringBuilder footer = new StringBuilder();
		footer.append("You owed ").append(totalAmount).append(NEW_LINE);
		footer.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");
		return footer.toString();
	}

	String createHeader() {
		return "Rental Record for "+getName () +NEW_LINE;
	}
	

	private String name;
	List <Rental>rentals = new ArrayList<Rental> ();
	double totalAmount = 0;
	private int frequentRenterPoints = 0;
	
	public Double getOwed() {
		return totalAmount;
	}

	public int getPointsEarned() {
		
		return frequentRenterPoints;
	}
}