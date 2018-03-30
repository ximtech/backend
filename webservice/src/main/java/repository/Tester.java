package repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.interfaces.CurrencyDAO;
import springbootweb.rest.RestApplication;

public class Tester {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RestApplication.class);
        CurrencyDAO dao = applicationContext.getBean(CurrencyDAO.class);
      /*  Currency currency = new Currency();
        currency.setCode("USD");
        currency.setNum("111");
        currency.setE("2");
        currency.setCurrency("Test currency");
        dao.save(currency);

        Currency curr = dao.findByCode(currency.getCode());
        System.out.println(curr);

        curr.setCode("HABA");
        dao.update(curr);
        curr = dao.findByCode(curr.getCode());
        System.out.println(curr);*/

       dao.clear();

    }
}
