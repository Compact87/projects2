package tech01;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EMProducer {
@Produces
@PersistenceContext(name="tech01")
private EntityManager em;
}
