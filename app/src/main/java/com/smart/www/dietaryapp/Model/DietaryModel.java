package com.smart.www.dietaryapp.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DietaryModel {
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("UserName")
    @Expose
    private String userName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
