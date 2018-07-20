package org.harvan.example.springboot.webflux.entity;

public class MainEntityBuilder {
    private MainEntity mainEntity;

    public MainEntityBuilder() {
        mainEntity = new MainEntity();
    }

    public MainEntityBuilder withId(Integer id) {
        mainEntity.setId(id);
        return this;
    }

    public MainEntityBuilder withDescription(String description) {
        mainEntity.setDescription(description);
        return this;
    }

    public MainEntityBuilder withName(String name) {
        mainEntity.setName(name);
        return this;
    }

    public MainEntity build() {
        return mainEntity;
    }
}
