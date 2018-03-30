package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.CurrencyResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.interfaces.ClientDAO;
import repository.entities.Log;
import services.interfaces.ClientService;

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
        log.setRequestDateTime(new Date());
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
        List<Log> listOfLogs = clientDAO.getAllLogEntries();
        return convertLogList(listOfLogs);
    }

    private List<List<String>> convertLogList(List<Log> logList) {
        List<List<String>> result = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        for (Log log : logList) {
            List<String> list = new ArrayList<>();
            list.add(log.getCurrencyCode());
            list.add(log.getClientIP());
            list.add(log.getErrorrDescription());
            list.add(dateFormat.format(log.getRequestDateTime()));
            result.add(list);
        }
        return result;
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
