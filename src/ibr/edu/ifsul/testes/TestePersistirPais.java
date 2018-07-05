
package ibr.edu.ifsul.testes;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Haylton
 */
public class TestePersistirPais {
    public static void main(String[] args) {
        EntityManager em = EntityManagerUtil.getEntityManager(); 
        Pais p = new Pais(); 
        p.setNome("Chile");
        p.setIso("CHI");
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        
        
    }
}
