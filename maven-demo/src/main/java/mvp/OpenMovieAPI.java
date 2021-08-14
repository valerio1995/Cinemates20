package mvp;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;


public class OpenMovieAPI implements ExternalMovieDAO{
	
	public String searchDirector(String idFilm) {
		String urlDirector = "https://api.themoviedb.org/3/movie/"+idFilm+"/credits?api_key=b115c914f390bfeb9f5349f4d6be4f07";
		
        try{
            URL obj = new URL(urlDirector);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to  URL: " + urlDirector);
            System.out.println("\nResponse code: "+responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            in.close();

            JSONObject myResponse = new JSONObject(response.toString());
            JSONArray mineArrayResults = myResponse.getJSONArray("crew");
            
            for(int index=0; index<mineArrayResults.length(); index++) {
            	JSONObject objectResults = mineArrayResults.getJSONObject(index);
            	if(objectResults.getString("job").equals("Director")) {
            		System.out.println("TORNO con regista " + objectResults.getString("name"));
            		return objectResults.getString("name");
            	}
            }


        }catch (Exception e){
            System.out.println(e);
        }
        
		return "Sconosciuto";
	} //end searchDirecctor
	
	public List<Movie> searchMovie(String title, String movieDirector, String actor, String genre, int year) {
		
		 List<Movie> listMovie = new ArrayList<Movie>();

	     TmdbApi tmdbApi = new TmdbApi("b115c914f390bfeb9f5349f4d6be4f07");
	     
	        if(!title.equals("null")){ //search by title
	        	System.out.println("1");
	            MovieResultsPage movieResultsPage = tmdbApi.getSearch().searchMovie(title,null,"en",false,1);
	            List<MovieDb> listMovies = movieResultsPage.getResults();
	            for(MovieDb movieDb : listMovies) {
	       		 	Movie movie = new Movie();
	            	movie.setTitle(movieDb.getOriginalTitle());
	            	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
	            	try {
						movie.setReleaseDate(dateFormat.parse(movieDb.getReleaseDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	movie.setDirector(searchDirector(Integer.toString(movieDb.getId())));
	            	
	            	listMovie.add(movie);
	            }
	            
	        }else if(!movieDirector.equals("null")){ //search by director
	        	System.out.println("2");
	            MovieResultsPage movieResultsPage = tmdbApi.getSearch().searchMovie(movieDirector,null,"en",false,1);
	            List<MovieDb> listMovies = movieResultsPage.getResults();
	            int i = 0;
	            for(MovieDb movieDb : listMovies) {
	       		 	Movie movie = new Movie();
	            	movie.setTitle(movieDb.getOriginalTitle());
	            	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
	            	try {
						movie.setReleaseDate(dateFormat.parse(movieDb.getReleaseDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	movie.setDirector(searchDirector(Integer.toString(movieDb.getId())));
	            	
	            	listMovie.add(i,movie);
	            	i++;
	            }
	        }else if(!actor.equals("null")){ //search by actor
	        	System.out.println("3");
	        	MovieResultsPage movieResultsPage = tmdbApi.getSearch().searchMovie(actor,null,"en",false,1);
	            List<MovieDb> listMovies = movieResultsPage.getResults();
	            int i = 0;
	            for(MovieDb movieDb : listMovies) {
	       		 	Movie movie = new Movie();
	            	movie.setTitle(movieDb.getOriginalTitle());
	            	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
	            	try {
						movie.setReleaseDate(dateFormat.parse(movieDb.getReleaseDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	            	movie.setDirector(searchDirector(Integer.toString(movieDb.getId())));
	            	
	            	listMovie.add(i,movie);
	            	i++;
	            }
	        }else if(!genre.equals("null")){
	        	System.out.println("4");
	            String url_genre= "https://api.themoviedb.org/3/discover/movie?api_key=b115c914f390bfeb9f5349f4d6be4f07&with_genres=";
	            
	            String id = "0";
	           if(genre.equals("Action")){
	                id = "28";
	           }else if(genre.equals("Adventure")){
	                id = "12";
	           }else if(genre.equals("Animation")){
	                id = "16";
	           }else if(genre.equals("Comedy")){
	                id = "35";
	           }else if(genre.equals("Crime")){
	                id = "80";
	           }else if(genre.equals("Documentary")){
	                id = "99";
	           }else if(genre.equals("Drama")){
	                id  = "18";
	           }else if(genre.equals("Family")){
	                id = "10751";
	           }else if(genre.equals("Fantasy")){
	                id = "14";
	           }else if(genre.equals("History")){
	                id = "36";
	           }else if(genre.equals("Horror")){
	                id = "27";
	           }else if(genre.equals("Music")){
	                id = "10402";
	           }else if(genre.equals("Mystery")){
	                id = "9648";
	           }else if(genre.equals("Romance")){
	                id = "10749";
	           }else if(genre.equals("Science Fiction")){
	                id = "878";
	           }else if(genre.equals("TV Movie")){
	                id = "10770";
	           }else if(genre.equals("Thriller")){
	                id = "53";
	           }else if(genre.equals("War")){
	                id = "10752";
	           }else if(genre.equals("Western")){
	                id = "37";
	           }
	           url_genre = url_genre+id;

	            try{
	                URL obj = new URL(url_genre);
	                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	                int responseCode = con.getResponseCode();
	                System.out.println("\nSending 'GET' request to  URL: " + url_genre);
	                System.out.println("\nResponse code: "+responseCode);

	                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	                String inputLine;
	                StringBuffer response = new StringBuffer();

	                while((inputLine = in.readLine()) != null){
	                    response.append(inputLine);
	                }

	                in.close();

	                JSONObject myResponse = new JSONObject(response.toString()); 
	                JSONArray mineArrayResults = myResponse.getJSONArray("results");
	                int i = 0;
	                for(int index = 0; index< mineArrayResults.length(); index++) { //Iterate the results array
	           		 	Movie movie = new Movie();
	                	JSONObject objectResults = mineArrayResults.getJSONObject(index);
	                	movie.setTitle(objectResults.getString("orginal_title"));
	                	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
	                	movie.setReleaseDate(dateFormat.parse(objectResults.getString("release_date")));
	                	movie.setDirector(searchDirector(objectResults.getString("id")));
	                        	
	                	listMovie.add(i,movie);
	                	i++;
	                }
	                        

	            }catch (Exception e){
	                System.out.println(e);
	            }

	        }else if(year != 0000){
	        	System.out.println("5");
	            MovieResultsPage movieResultsPage = tmdbApi.getSearch().searchMovie("a",year,"en",false,1);
	        	List<MovieDb> listMovies = movieResultsPage.getResults();
	            for(MovieDb movieDb : listMovies) {
	       		 	Movie movie = new Movie();
	            	movie.setTitle(movieDb.getTitle());
	            	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	            	try {
						movie.setReleaseDate(dateFormat.parse(movieDb.getReleaseDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	movie.setDirector(searchDirector(Integer.toString(movieDb.getId())));
	            	System.out.println("TITOLO: "+movie.getTitle());
	            	System.out.println("DATA: "+movie.getReleaseDate());
	            	System.out.println("REGISTA: "+movie.getDirector());
	            	listMovie.add(movie);
	            }
	        }else{
	            System.out.println("Error during the search");
	        }


	        return listMovie;
	    }
	}
