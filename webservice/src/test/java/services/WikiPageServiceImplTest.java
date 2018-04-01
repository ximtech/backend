package services;

import org.junit.Test;

public class WikiPageServiceImplTest {

    @Test(expected = RuntimeException.class)
    public void parseCurrencyDataFromWikiPage() throws Exception {
        WikiPageServiceImpl pageService = new WikiPageServiceImpl();
        pageService.parseCurrencyDataFromWikiPage("https://google.com");
    }

}