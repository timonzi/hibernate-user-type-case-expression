package org.acme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.acme.types.StringWrapper;
import org.acme.usertypes.StringWrapperUserType;
import org.hibernate.annotations.Type;

@Entity
public class TestEntity {

    @Id
    @Type(StringWrapperUserType.class)
    private StringWrapper id;

    @Type(StringWrapperUserType.class)
    private StringWrapper stringField;


    public TestEntity() {
    }


    public TestEntity(
            final StringWrapper id,
            final StringWrapper stringField
    ) {
        this.id = id;
        this.stringField = stringField;
    }


    public StringWrapper getId() {
        return id;
    }


    public StringWrapper getStringField() {
        return stringField;
    }

}
