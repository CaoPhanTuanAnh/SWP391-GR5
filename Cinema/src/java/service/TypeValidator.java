/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import java.time.format.DateTimeParseException;
import javax.swing.text.DateFormatter;

/**
 *
 * @author GIGABYTE
 */
public class TypeValidator {

    public static boolean validatePassword(String password) throws Exception {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,16}$";
        if (password.isBlank()) {
            throw new Exception("Password can't be empty!");
        } else if (!password.matches(regex)) {
            throw new Exception("""
                                Password must have 8-16 characters,
                                 1 special character, 1 digit,
                                 1 uppercase and 1 lowercase charater!""");
        } else {
            return true;
        }
    }

    public static boolean validatePhone(String phone) throws Exception {
        phone=phone.trim();
        String regex = "^[0][0-9]{9}$";
        if (phone.isBlank()) {
            throw new Exception("Phone number can't be empty!");
        } else if (!phone.matches(regex)) {
            throw new Exception("Phone number must exactly 10 digits with leading zero!");
        } else {
            return true;
        }
    }

    public static boolean validateEmail(String email) throws Exception {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        if (email.isBlank()) {
            throw new Exception("Email can't be empty!");
        } else if (!email.matches(regex)) {
            throw new Exception("Email format is incorrect!");
        } else {
            return true;
        }
    }

    public static boolean validateFullName(String fullName) throws Exception {
        fullName=fullName.trim();
        String regex = "^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$";
        if (!fullName.matches(regex)||fullName.length()<2||fullName.length()>100) {
            throw new Exception("Name format is incorrect!");
        }
        return true;
    }

    public static boolean validateBirthDate(String birthDate) throws Exception {
        birthDate=birthDate.trim();
        if (birthDate.isBlank()) {
            throw new Exception("Birth Date can't be empty!");
        }else {
            try {
                LocalDate date = LocalDate.parse(birthDate, ISO_LOCAL_DATE);    
                LocalDate back = LocalDate.parse("1900-01-01", ISO_LOCAL_DATE);    
                if(date.isBefore(back)){
                    throw new Exception("Birth Date must be after 1900-01-01!");
                }else if(date.isAfter(LocalDate.now())){
                    throw new Exception("Birth Date can't be in the future!");
                }
            } catch (DateTimeParseException e) {
                throw new Exception("Birth Date must follow yyyy-MM-dd format!");
            }
        }
        return true;
    }

}
