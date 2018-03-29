package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import errors.CurrencyNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import repository.entities.Currency;

@Repository
@Transactional
public class CurrencyDAOImpl implements CurrencyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Currency currency) {
        entityManager.persist(currency);
    }

    @Override
    public void update(Currency currency) {
        entityManager.merge(currency);
    }

    @Override
    public Currency findById(Long id) {
        Currency currency;
        try {
            currency = entityManager.find(Currency.class, id);
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Currency data with id: " + id + " is not exists.");
        }
        return currency;
    }

    @Override
    public Currency findByCode(String code) {
        Currency currency;
        try {
            currency = (Currency) entityManager.createQuery("SELECT c FROM Currency c WHERE c.code LIKE ?")
                            .setParameter(1, code.toUpperCase()).getSingleResult();
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Currency data with code: " + code + " wasn't exists.");
        }
        return currency;
    }

    @Override
    public void delete(Currency currency) {
        entityManager.remove(currency);
    }

    @Override
    public void clear() {
        entityManager.createNativeQuery("TRUNCATE TABLE EXTCURRENCY ").executeUpdate();
    }
}
