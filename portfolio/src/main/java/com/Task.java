package com;

/** An item on a todo list. */
public final class Task {

    private final long id;
    //private final String firstName;
    //private final String lastName;
    private final boolean yourTalent;
    private final boolean yourCuriosity;
    private final String specialization;
    private final String joke;
    private final long timestamp;

    public Task(long id, boolean yourTalent,
    boolean yourCuriosity, String specialization, String joke, long timestamp) {
    
        this.id = id;
        //this.firstName = firstName;
        //this.lastName = lastName;
        this.yourTalent = yourTalent;
        this.yourCuriosity = yourCuriosity;
        this.specialization = specialization;
        this.joke = joke;
        this.timestamp = timestamp;

    }
    
}