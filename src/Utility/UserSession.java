package Utility;

import ModelDto.UserDto;

public class UserSession {
    private static UserSession instance;

    private UserDto currentUser;

    private UserSession(){
        System.out.println("Singleton instance Created");
    }

    public static UserSession getInstance() {
        if (instance == null){
            instance = new UserSession();
        }
        return instance;
    }

    public UserDto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDto currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isLoggedIn(){
        return currentUser != null;
    }

    public void clear(){
        currentUser = null;
    }
}
