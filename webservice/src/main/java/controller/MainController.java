package controller;

import java.util.List;

import dto.CurrencyRequest;
import dto.CurrencyResponse;
import dto.LogDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.interfaces.ClientService;
import services.interfaces.CurrencyService;

@Component
@RestController(value = "number")
@RequestMapping("/number")
public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    private CurrencyService currencyService;

    @Autowired
     private ClientService clientService;

    private CurrencyRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public CurrencyResponse getPhoneNumberMetadata() {
        LOGGER.info("Sending response.");
        CurrencyResponse response = currencyService.getCurrencyDataByCode(request);
        clientService.persistClientData(response);
        List<LogDTO> logList = clientService.getAllClientDataFromDB();
        response.setLogList(logList);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void setPhoneNumberMetadata(@RequestBody CurrencyRequest request) {
        LOGGER.info("Request for currency info: " + request.getCurrencyCode() + " and IP: " + request.getClientIP());
        this.request = request;
    }

}
