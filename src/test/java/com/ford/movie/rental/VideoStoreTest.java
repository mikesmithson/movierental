package com.ford.movie.rental;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest
{
	
	private static final String REGULAR_MOVIE3 = "Eraserhead";
	private static final String REGULAR_MOVIE2 = "8 1/2";
	private static final String REGULAR_MOVIE1 = "Plan 9 from Outer Space";
	private static final String MOVIE_TITLE2 = "The Tigger Movie";
	private static final String MOVIE_TITLE1 = "The Cell";
	private static final String CUSTOMER_NAME = "Fred";
	private static final String NEW_LINE = "\n";
	private static final String TAB = "\t";
	private Customer customer;
	private StatementBuilder statementBuilder;
	
	@Before
	public void setUp ()  {
		customer = new Customer (CUSTOMER_NAME);
		statementBuilder = new StatementBuilder();
	}
	
	@Test
	public void calculatesAccountForSingleMovie () {
		addMovieRentalToCustomer( Movie.NEW_RELEASE, MOVIE_TITLE1, 3);
		
		statementBuilder.createStatement(customer);
		
		assertEquals((Double) 9.0, customer.getOwed());
		assertEquals(2, customer.getPointsEarned());
	}


	@Test
	public void calculatesAccountForMultipleNewRelease () {
		addMovieRentalToCustomer(Movie.NEW_RELEASE, MOVIE_TITLE1, 3);
		addMovieRentalToCustomer(Movie.NEW_RELEASE, MOVIE_TITLE2, 3);

		statementBuilder.createStatement (customer);
		
		assertEquals((Double) 18.0, customer.getOwed());
		assertEquals(4, customer.getPointsEarned());

	}

	@Test
	public void calculatesAccountForChildrensMovie () {
		addMovieRentalToCustomer(Movie.CHILDRENS, MOVIE_TITLE2, 3);
		
		statementBuilder.createStatement(customer);
		
		assertEquals((Double) 1.5, customer.getOwed());
		assertEquals(1, customer.getPointsEarned());
	}
	
	@Test
	public void calculatesAccountForMultipleRegularMovies () {
		addMovieRentalToCustomer(Movie.REGULAR,	REGULAR_MOVIE1, 1);
		addMovieRentalToCustomer(Movie.REGULAR,	REGULAR_MOVIE2, 2);
		addMovieRentalToCustomer(Movie.REGULAR,	REGULAR_MOVIE3, 3);

		statementBuilder.createStatement(customer);
		
		assertEquals((Double) 7.5, customer.getOwed());
		assertEquals(3, customer.getPointsEarned());
	}
	
	@Test
	public void formatsStatement() {
		addMovieRentalToCustomer(Movie.REGULAR,	REGULAR_MOVIE1, 1);
		addMovieRentalToCustomer(Movie.REGULAR,	REGULAR_MOVIE2, 2);
		addMovieRentalToCustomer(Movie.REGULAR,	REGULAR_MOVIE3, 3);

		String statement = statementBuilder.createStatement(customer);

		assertEquals ("Rental Record for Fred" +
				NEW_LINE +
				TAB +
				REGULAR_MOVIE1 +
				TAB +
				"2.0" +
				NEW_LINE +
				TAB +
				REGULAR_MOVIE2 +
				TAB +
				"2.0" +
				NEW_LINE +
				TAB +
				REGULAR_MOVIE3 +
				TAB +
				"3.5" +
				NEW_LINE +
				"You owed 7.5" +
				NEW_LINE +
				"You earned 3 frequent renter points" +
				NEW_LINE, 
				statement);
	}

	private void addMovieRentalToCustomer(int movieType, String movieTitle, int daysRented) {
		new Rental (new Movie (movieTitle, movieType), daysRented).addRental (customer);
	}
	

}