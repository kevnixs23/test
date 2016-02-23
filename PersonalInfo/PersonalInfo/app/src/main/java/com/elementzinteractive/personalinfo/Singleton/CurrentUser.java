package com.elementzinteractive.personalinfo.Singleton;

import com.elementzinteractive.personalinfo.model.Person;

/**
 * Created with IntelliJ IDEA.
 * Date: 13/05/13
 * Time: 10:36
 */
public class CurrentUser {

    private static CurrentUser mInstance = null;

    private CurrentUser(){
    }

    public static CurrentUser getInstance(){
        if(mInstance == null)
        {
            mInstance = new CurrentUser();
        }
        return mInstance;
    }

    private Person mPerson;

    // PERSON
    public void setPerson(Person person){
        mPerson  = person;
    }

    public Person getPerson()
    {
        return mPerson;
    }
    //endregion

}