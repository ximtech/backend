package services.interfaces;

import java.util.List;

import dto.CurrencyResponse;
import dto.LogDTO;
import org.springframework.stereotype.Component;

@Component
public interface ClientService {

    void performCleanUp();

    void persistClientData(CurrencyResponse response);

    List<LogDTO> getAllClientDataFromDB();

}
