package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import errors.LogEntryNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.entities.Log;

@Repository
@Transactional
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Log log) {
        entityManager.persist(log);
    }

    @Override
    public void update(Log log) {
        entityManager.merge(log);
    }

    @Override
    public Log findById(Long id) {
        Log log;
        try {
            log = entityManager.find(Log.class, id);
        } catch (Exception e) {
            throw new LogEntryNotFoundException("Client information with id: " + id + " wasn't exist");
        }
        return log;
    }

    @Override
    public Log findByCode(String code) {
        Log log;
        try {
            log = (Log) entityManager.createQuery("SELECT l FROM Log l WHERE l.code LIKE ?")
                            .setParameter(1, code).getSingleResult();
        } catch (Exception e) {
            throw new LogEntryNotFoundException("Client information with code: " + code + " wasn't exist");
        }
        return log;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Log> getAllLogEntries() {
        return entityManager.createQuery("SELECT l FROM Log l").getResultList();
    }

    @Override
    public void delete(Log log) {
        entityManager.remove(log);
    }

    @Override
    public void clear() {
        entityManager.createNativeQuery("TRUNCATE TABLE EXTLOG").executeUpdate();
    }
}
