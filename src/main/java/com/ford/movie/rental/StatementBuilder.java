package com.ford.movie.rental;

public class StatementBuilder {

	public String createStatement (Customer customer) {
		StringBuilder statement = new StringBuilder();
		statement.append(customer.createHeader());
		statement.append(customer.createBody());
		statement.append(customer.createFooter());
		return statement.toString();
	}

	String createLineItem(Customer customer, Rental rental) {
		StringBuilder lineItem = new StringBuilder();
		double thisAmount = customer.determineAmountForRental(rental);
	
		customer.calculateFrequentRenterPoints(rental);
			
		customer.totalAmount += thisAmount;
		lineItem.append(Customer.TAB).append(rental.getMovie ().getTitle ()).append(Customer.TAB).append(thisAmount).append(Customer.NEW_LINE);
	
		return lineItem.toString();
		}

}
