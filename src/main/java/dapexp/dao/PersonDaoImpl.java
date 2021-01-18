package dapexp.dao;

import dapexp.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class PersonDaoImpl implements PersonDao {

    @Autowired
    @Qualifier("ldapTemplate")
    private LdapTemplate ldapTemplate;

    private class PersonAttributesMapper implements AttributesMapper<Person> {
        public Person mapFromAttributes(Attributes attributes) throws NamingException {
            Person person = new Person();
            person.setFirstName("Hello World");
            return person;
        }
    }

    public List<Person> getAllPersons() {
        return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
    }
}
