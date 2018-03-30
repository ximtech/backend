package services;

import java.util.List;

import dto.CurrencyResponse;
import org.springframework.stereotype.Component;

@Component
public interface ClientService {

    void performCleanUp();

    void persistClientData(CurrencyResponse response);

    List<List<String>> getAllClientDataFromDB();

}
