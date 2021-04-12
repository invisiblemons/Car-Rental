/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhdc.tblUser;

import java.io.Serializable;

/**
 *
 * @author MONS
 */
public class TblSignupInsertError implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String phoneLengthError;
    private String fullnameLengthError;
    private String usernameIsExist;

    public TblSignupInsertError() {
    }

    
    public TblSignupInsertError(String usernameLengthError, String passwordLengthError, String phoneLengthError, String fullnameLengthError, String usernameIsExist) {
        this.usernameLengthError = usernameLengthError;
        this.passwordLengthError = passwordLengthError;
        this.phoneLengthError = phoneLengthError;
        this.fullnameLengthError = fullnameLengthError;
        this.usernameIsExist = usernameIsExist;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getUsernameIsExist() {
        return usernameIsExist;
    }

    public void setUsernameIsExist(String usernameIsExist) {
        this.usernameIsExist = usernameIsExist;
    }
    
}
