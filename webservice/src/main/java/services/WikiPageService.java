package services;

import java.io.IOException;

public interface WikiPageService {

    void parseCurrencyDataFromWikiPage(String url) throws IOException;
}
