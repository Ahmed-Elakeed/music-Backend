package elakeed.projects.music;

import elakeed.projects.music.api.GenreResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MusicApplication implements CommandLineRunner {


    private GenreResource genreResource;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MusicApplication(GenreResource genreResource) {

        this.genreResource = genreResource;
    }


    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.genreResource.getAllGenres();


//        System.out.println("--------Jpa Repository--------");
//        System.out.println(genreRepository.getAllGenres());
//        System.out.println(genreRepository.getGenreById(15L));
//        System.out.println(genreRepository.saveGenre(new Genre("Jpa Save neww")));
//        System.out.println(genreRepository.updateGenre(new Genre("Jpa Save update aasada"),16L));
//        genreRepository.deleteGenreById(18L);
//        System.out.println("--------Entity Manager--------");
//        System.out.println(genreRepository.getAllGenresByEntityManager());
//        System.out.println(genreRepository.getGenreByIdByEntityManager(15L));
//        genreRepository.persistGenreByEntityManager(new Genre("Jpa Sgyy"));
//        System.out.println(genreRepository.saveGenreByEntityManager(new Genre("Jpa Save ucate"),19L));
//        genreRepository.deleteGenreByIdByEntityManager(30L);
//        System.out.println("--------Hibernate--------");
//        System.out.println(genreRepository.getAllByHibernateSession());
//        System.out.println(genreRepository.getGenreByIdByHibernateSession(15L));
//        genreRepository.saveGenreByHibernateSession(new Genre("a Smhgadave"));
//        genreRepository.updateGenreByHibernateSession(new Genre("Jpa Save update"),24L);
//        genreRepository.deleteGenreByIdByHibernateSession(33L);
    }
}
