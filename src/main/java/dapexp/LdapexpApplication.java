package dapexp;

import dapexp.client.SearchClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class LdapexpApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ldapTemplateBean.xml");
		SearchClient searchClient = applicationContext.getBean(SearchClient.class);
		List<String> listOfNames = searchClient.getAllPersonNames();
		listOfNames.forEach(System.out::println);

		SpringApplication.run(LdapexpApplication.class, args);
	}

}
