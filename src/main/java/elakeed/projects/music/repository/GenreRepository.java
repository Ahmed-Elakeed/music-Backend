package elakeed.projects.music.repository;

import elakeed.projects.music.model.Genre;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class GenreRepository {


    private JpaRepository<Genre, Long> repository;

    private EntityManager entityManager;


    @Autowired
    public GenreRepository(JpaRepository<Genre, Long> repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    /////Jpa Repository/////
    public List<Genre> getAllGenres() {
        return this.repository.findAll();
    }

    public Genre getGenreById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Genre saveGenre(Genre genre) {
        return this.repository.save(genre);
    }

    public void deleteGenreById(Long id) {
        this.repository.deleteById(id);
    }

    public Genre updateGenre(Genre genre, Long id) {
        genre.setGenreId(id);
        return this.repository.save(genre);
    }
    ////////////////////////////////////


    /////Entity Manager/////
    public List<Genre> getAllGenresByEntityManager() {
        return this.entityManager.createQuery("select g from Genre g").getResultList();
    }

    public Genre getGenreByIdByEntityManager(Long id) {
        return this.entityManager.find(Genre.class, id);
    }

    @Transactional
    public void persistGenreByEntityManager(Genre genre) {
        this.entityManager.persist(genre);
    }

    @Transactional
    public Genre saveGenreByEntityManager(Genre genre,Long id) {
        genre.setGenreId(id);
        return this.entityManager.merge(genre);
    }

    @Transactional
    public void deleteGenreByIdByEntityManager(Long id) {
        this.entityManager.remove(this.getGenreByIdByEntityManager(id));
    }
    ////////////////////////////////////


    /////Hibernate Session/////
    public List<Genre> getAllByHibernateSession() {
        return this.entityManager.unwrap(Session.class).createQuery("select g from Genre g").getResultList();
    }

    public Genre getGenreByIdByHibernateSession(Long id) {
        return this.entityManager.unwrap(Session.class).find(Genre.class, id);
    }

    public void saveGenreByHibernateSession(Genre genre) {
        this.entityManager.unwrap(Session.class).save(genre);
    }

    @Transactional
    public void deleteGenreByIdByHibernateSession(Long id) {
        this.entityManager.unwrap(Session.class).delete(this.getGenreByIdByHibernateSession(id));
    }

    public void updateGenreByHibernateSession(Genre genre, Long id) {
        genre.setGenreId(id);
        this.entityManager.unwrap(Session.class).saveOrUpdate(genre);
    }
    ////////////////////////////////////
}
