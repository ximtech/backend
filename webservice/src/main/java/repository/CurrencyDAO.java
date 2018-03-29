package repository;

import org.springframework.stereotype.Component;
import repository.entities.Currency;

@Component
public interface CurrencyDAO {

    void save(Currency currency);

    void update(Currency currency);

    Currency findById(Long id);

    Currency findByCode(String code);

    void delete(Currency currency);

    void clear();
}
