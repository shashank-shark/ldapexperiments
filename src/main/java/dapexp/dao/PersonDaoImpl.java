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

            person.setFullName((String) attributes.get("cn").get());
            person.setTelephoneNumber((String) attributes.get("telephonenumber").get());
            person.setLocality((String) attributes.get("l").get());
            person.setFirstName((String) attributes.get("givenname").get());
            person.setEmployeeNumber((String) attributes.get("employeenumber").get());
            person.setMail((String) attributes.get("mail").get());
            person.setMobile((String) attributes.get("mobile").get());
            person.setPostalAddress((String) attributes.get("postaladdress").get());
            person.setPostalCode((String) attributes.get("postalcode").get());
            person.setSirName((String) attributes.get("sn").get());
            person.setUid((String) attributes.get("uid").get());

            return person;
        }
    }

    public List<Person> getAllPersons() {
        return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
    }
}
