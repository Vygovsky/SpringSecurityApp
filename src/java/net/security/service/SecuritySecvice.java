package net.security.service;

public interface SecuritySecvice {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
