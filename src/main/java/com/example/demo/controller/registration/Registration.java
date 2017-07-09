package com.example.demo.controller.registration;

import com.example.demo.bean.User;
import com.example.demo.bean.User.UserBuilder;
import lombok.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by marta on 08.07.17.
 */
@Getter
@Setter
@Data
@NoArgsConstructor
public class Registration {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String passwordConfirmation;

    private String birthday;

    public User getUser(){
        UserBuilder builder  = User.builder()
                .name(getFirstName()+" "+getLastName())
                .email(getEmail());
        try{
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            builder.birthday(format.parse(getBirthday()));
        }catch (ParseException e){
            builder.birthday(null);

        }

        return builder.build();

    }

}


