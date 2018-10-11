package com.jeepc.binderdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeepc on 2018/10/10.
 */
public class PersonBean implements Parcelable {

    private int age;
    private String name;

    public PersonBean(){}

    protected PersonBean(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public void readFromParcel(Parcel in){
        this.age = in.readInt();
        this.name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonBean> CREATOR = new Creator<PersonBean>() {
        @Override
        public PersonBean createFromParcel(Parcel in) {
            return new PersonBean(in);
        }

        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
