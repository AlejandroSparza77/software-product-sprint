package com;

/** An item on a todo list. */
public final class Task {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final boolean myTalent;
    private final boolean myCuriosity;
    private final String specialization;
    private final String joke;
    private final long timestamp;

    public Task(long id, String firstName, String lastName, boolean myTalent,
    boolean myCuriosity, String specialization, String joke, long timestamp) {
    
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.myTalent = myTalent;
        this.myCuriosity = myCuriosity;
        this.specialization = specialization;
        this.joke = joke;
        this.timestamp = timestamp;

    }
    
}