package elakeed.projects.music;

import elakeed.projects.music.api.ArtistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MusicApplication implements CommandLineRunner {

    private ArtistResource artistResource;

    @Autowired
    public MusicApplication(ArtistResource artistResource) {
        this.artistResource = artistResource;
    }


    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(artistResource.getAlbumsForArtistById(1L).getBody());
    }
}
