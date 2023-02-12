package com.example.test.enumeration;

public enum RideStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    ACTIVE,
    FINISHED;

    public static RideStatus getEnum(String s){
        if(PENDING.name().equals(s)){
            return PENDING;
        }else if(ACCEPTED.name().equals(s)){
            return ACCEPTED;
        }else if(REJECTED.name().equals(s)){
            return REJECTED;
        }else if (ACTIVE.name().equals(s)){
            return ACTIVE;
        }else if (FINISHED.name().equals(s)){
            return FINISHED;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }
}
