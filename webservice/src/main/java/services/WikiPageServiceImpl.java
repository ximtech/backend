package services;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.interfaces.CurrencyDAO;
import repository.entities.Currency;
import services.interfaces.WikiPageService;

@Component
public class WikiPageServiceImpl implements WikiPageService {
    private static final Logger LOGGER = Logger.getLogger(WikiPageServiceImpl.class);

    private CurrencyDAO currencyDAO;

    @Override
    public void parseCurrencyDataFromWikiPage(String url) throws IOException {
        LOGGER.info("Trying to get html page from: " + url);
        Document doc = Jsoup.connect(url).timeout(30000).get();

        Element body = doc.body().getElementsByClass("wikitable sortable").first();

        for (Element element : body.getElementsByTag("tr")) {
            List<String> list = element.getElementsByTag("td").eachText();

            if (list.size() > 0) {
                List<String> resultList = list.subList(0, 4);
                removeAllInvalidChars(resultList);
                Currency currency = fillCurrencyFromResultList(resultList);
                saveCurrencyToDB(currency);
            }
        }
    }

    private void removeAllInvalidChars(List<String> resultList) {
        for (int i = 0; i < resultList.size(); i++) {
            String currencyField = resultList.get(i);
            if (currencyField.matches("\\w+\\*?\\[+\\d+\\]+")) {
                resultList.set(i, currencyField.replaceAll("\\*?\\[+\\d+\\]+", ""));
            }
            if (currencyField.matches("^.+\\[\\d\\]$")) {
                resultList.set(i, currencyField.replaceAll("\\[\\d{1,2}\\]", ""));
            }
        }
        Collections.replaceAll(resultList, ".", "");
    }

    private Currency fillCurrencyFromResultList(List<String> smallList) {
        Currency currency = new Currency();
        currency.setCode(smallList.get(0));
        currency.setNum(smallList.get(1));
        currency.setE(smallList.get(2));
        currency.setCurrency(smallList.get(3));
        return currency;
    }

    private void saveCurrencyToDB(Currency currency) {
        currencyDAO.save(currency);
    }

    @Autowired
    public void setCurrencyDAO(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }
}
