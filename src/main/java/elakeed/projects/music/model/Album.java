package elakeed.projects.music.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    private String albumName;

    private String releasedDate;


    @ManyToOne
    @JoinColumn(name = "artistId")
    @NotNull
    private Artist artist;


    @ManyToOne
    @JoinColumn(name = "genreId")
    @NotNull
    private Genre genre;

    public Album() {
    }

    public Album(String albumName, String releasedDate, Artist artist, Genre genre) {
        this.albumName = albumName;
        this.releasedDate = releasedDate;
        this.artist = artist;
        this.genre = genre;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", releasedDate=" + releasedDate +
                ", artist=" + artist +
                ", genre=" + genre +
                '}';
    }
}
