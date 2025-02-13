package org.acme.dtos;

import org.acme.types.StringWrapper;

public class TestDto {

    private StringWrapper id;
    private StringWrapper stringField;


    public TestDto(
            final StringWrapper id,
            final StringWrapper stringField
    ) {
        this.id = id;
        this.stringField = stringField;
    }


    public StringWrapper getId() {
        return id;
    }


    public void setId(final StringWrapper id) {
        this.id = id;
    }


    public StringWrapper getStringField() {
        return stringField;
    }


    public void setStringField(final StringWrapper stringField) {
        this.stringField = stringField;
    }
}
