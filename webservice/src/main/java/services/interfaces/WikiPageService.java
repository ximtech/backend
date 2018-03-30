package services.interfaces;

import java.io.IOException;

public interface WikiPageService {

    void parseCurrencyDataFromWikiPage(String url) throws IOException;
}
