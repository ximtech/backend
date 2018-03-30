package repository;

import java.util.List;

import org.springframework.stereotype.Component;
import repository.entities.Log;

@Component
public interface ClientDAO {

    void save(Log log);

    void update(Log log);

    Log findById(Long id);

    Log findByCode(String code);

    List<Log> getAllLogEntries();

    void delete(Log log);

    void clear();
}
