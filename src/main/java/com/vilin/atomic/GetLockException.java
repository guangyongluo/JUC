package com.vilin.atomic;

public class GetLockException extends Exception{

    public GetLockException(){
        super();
    }

    public GetLockException(String message){
        super(message);
    }
}
