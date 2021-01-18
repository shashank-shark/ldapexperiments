package dapexp.client;

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
public class SearchClient {

    @Autowired
    @Qualifier("ldapTemplate")
    LdapTemplate ldapTemplate;

    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectClass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attributes) throws NamingException {
                        return (String) attributes.get("cn").get();
                    }
                }
        );
    }
}
