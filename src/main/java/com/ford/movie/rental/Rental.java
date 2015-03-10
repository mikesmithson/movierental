package com.ford.movie.rental;

public class Rental
{
	public Rental (Movie movie, int daysRented) {
		this.movie 		= movie;
		this.daysRented = daysRented;
	}
	
	public int getDaysRented () {
		return daysRented;
	}
	
	public Movie getMovie () {
		return movie;
	}
	
	public void addRental (Customer customer) {
		customer.rentals.add(this);
	}

	private Movie movie;
	private int daysRented;
}
