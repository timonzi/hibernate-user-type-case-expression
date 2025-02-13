package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Expression;
import jakarta.transaction.Transactional;
import org.acme.dtos.TestDto;
import org.acme.entities.TestEntity;
import org.acme.types.StringWrapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApplicationScoped
@Transactional
public class UserTypeTestBean {

    public static final String ID_001 = "ID_001";
    public static final String STRING_FIELD_VALUE = "stringFieldValue";
    @Inject
    EntityManager em;


    void createEntity() {
        final StringWrapper id = new StringWrapper(ID_001);
        final StringWrapper stringWrapperField = new StringWrapper(STRING_FIELD_VALUE);
        final var entityA = new TestEntity(id, stringWrapperField);
        em.persist(entityA);
    }


    void findEntities() {
        final var entityA = em.find(TestEntity.class, new StringWrapper(ID_001));
        assertEquals(ID_001, entityA.getId().getField());
        assertEquals(STRING_FIELD_VALUE, entityA.getStringField().getField());
    }


    void queryEntities() {
        final var queryA = em.getCriteriaBuilder().createQuery(TestEntity.class);
        queryA.from(TestEntity.class);
        final var resultList = em.createQuery(queryA).getResultList();
        assertEquals(1, resultList.size());
    }


    List<TestDto> queryWithCaseExpression() {
        final var cb = em.getCriteriaBuilder();
        final var query = cb.createQuery(TestDto.class);
        final var root = query.from(TestEntity.class);
        final Expression<Object> caseExpression = cb.selectCase()
                .when(cb.isNull(root.get("id")), new StringWrapper("value1"))
                .otherwise(new StringWrapper("value2"));

        query.select(cb.construct(
                TestDto.class,
                root.get("id"),
                caseExpression
        ));
        final var typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

}
