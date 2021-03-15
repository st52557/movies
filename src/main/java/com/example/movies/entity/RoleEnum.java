package com.example.movies.entity;

public enum RoleEnum {

    DIRECTOR("Režisér"),
    ACTOR("Herec"),
    SCRIPT("Scénář");


    private String name;

    public String getName()
    {
        return this.name;
    }

    private RoleEnum(String name)
    {
        this.name = name;
    }

}
