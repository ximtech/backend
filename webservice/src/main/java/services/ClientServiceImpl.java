package services;

import java.util.List;

import dto.CurrencyResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientDAO;
import repository.entities.Log;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger LOGGER = Logger.getLogger(ClientServiceImpl.class);

    private ClientDAO clientDAO;

    @Override
    public void performCleanUp() {
        LOGGER.info("Performing Log Table cleanup");
        clientDAO.clear();
    }

    @Override
    public void persistClientData(CurrencyResponse response) {
        Log log = new Log();
        log.setCurrencyCode(response.getCurrencyCode());
        log.setRequestDateTime(response.getRequestDateAndTime());
        log.setClientIP(response.getClientIP());
        log.setErrorrDescription(response.getErrorMessage());

        try {
            clientDAO.save(log);
        } catch (Exception e) {
            LOGGER.info("Exception trying to save client log to DB " + e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
    }

    @Override
    public List<List<String>> getAllClientDataFromDB() {
        return null;    //TODO implement batch get
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
