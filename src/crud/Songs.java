package crud;

/**
 *
 * @author Sika Dali
 */
public class Songs {
    private int id;
    private String title;
    private String artist;
    private String genre;
    private int year;

    public Songs(int id, String title, String artist, String genre, int year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
    
    
}
