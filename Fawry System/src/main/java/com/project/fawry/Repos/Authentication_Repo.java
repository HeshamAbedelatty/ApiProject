package com.project.fawry.Repos;

import com.project.fawry.Models.BaseUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Authentication_Repo {
    private final List<BaseUser> signUpTaple = new ArrayList<>();
    private final List<BaseUser> loginUser= new ArrayList<>();
    public boolean isExistSignUp(BaseUser baseUser)
    {
        for (BaseUser user : signUpTaple) {
            if (user.getUsername().equals(baseUser.getUsername()))
            {
                return true;
            }
        }
        signUpTaple.add(baseUser);
        return false;
    }
    public int isExistLogin (BaseUser baseUser)
    {
        boolean flag = false;
        for (BaseUser user : signUpTaple) {
            if (user.getUsername().equals(baseUser.getUsername())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (BaseUser user : loginUser) {
                if (user.getUsername().equals(baseUser.getUsername())) {
                    return 0;
                }
            }
            loginUser.add(baseUser);
            return 1;
        }
        return 2;
    }
    public BaseUser searchForSignUp(String username, String password)
    {
        for (BaseUser user : signUpTaple) {
            if (user.getUsername().equals(username)
              &&user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }
    public BaseUser search(String username)
    {
        for (BaseUser user : loginUser) {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }
    public BaseUser searchForacceptOrReject(String username)
    {
        for (BaseUser user : signUpTaple) {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        return null;
    }

    public boolean isAdmin (String username)
    {
        for (BaseUser user : signUpTaple) {
            if (user.getUsername().equals(username))
            {
                if (!user.getIsAmin())
                    return true;
            }
        }
        return false;
    }

}
