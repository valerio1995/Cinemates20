package mvp;
import java.util.Date;

public class Movie {
	String title;
	Date releaseDate;
	String director;
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public String getDirector() {
		return this.director;
	}
}
