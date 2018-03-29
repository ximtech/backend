package services;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import constants.Constants;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.CurrencyDAO;
import repository.entities.Currency;

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

    private void removeAllInvalidChars(List<String> smallList) {
        for (int i = 0; i < smallList.size(); i++) {
            if (smallList.get(i).matches("\\w+\\*?\\[+\\d+\\]+")) {
                smallList.set(i, smallList.get(i).replaceAll("\\*?\\[+\\d+\\]+", ""));
            }
        }
        Collections.replaceAll(smallList, ".", "");
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
