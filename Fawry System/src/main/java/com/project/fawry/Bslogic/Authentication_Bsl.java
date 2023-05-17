package com.project.fawry.Bslogic;


import com.project.fawry.Models.BaseUser;
import com.project.fawry.Repos.Authentication_Repo;
import org.springframework.stereotype.Service;

@Service
public class Authentication_Bsl {
    private final Authentication_Repo authenticationRepo;

    public Authentication_Bsl(Authentication_Repo authenticationRepo) {
        this.authenticationRepo = authenticationRepo;
    }

    public Authentication_Repo getAuthenticationRepo() {
        return authenticationRepo;
    }

    public String add(BaseUser baseUser) {
        if (authenticationRepo.isExistSignUp(baseUser)) {
            return "This UserName is already Exist.";
        }
        return "Successfully Sign Up";
    }
    public String log(String username,String password) {
        BaseUser baseUser = authenticationRepo.searchForSignUp(username,password);
        int flag = authenticationRepo.isExistLogin(baseUser);
        if (flag == 0) {
            return "This Account is already logged in.";
        } else if (flag == 1) {
            return "Successfully Log in";
        }
        else
            return "Please Sign up first";
    }


}
