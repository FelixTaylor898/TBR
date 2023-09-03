public class Book {
	String title;
	String author;
	String date;
	double rating;

	public Book(String title, String author, String date, double rating) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		String[] s = author.split(", ");
		return title + " by " + s[1] + " " + s[0] + " (p. " + date + String.format(") (%.2f)", rating);
	}

}
