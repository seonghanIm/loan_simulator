package com.han.loan_simulator.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.regex.Pattern;

@Entity
@Table(name ="user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-Za-z가-힣0-9]+$");

    public boolean nickNameValidate(){
        if(nickNameLengthValidate() && nickNameStringValidate()) return true;
        return false;
    }

    private boolean nickNameLengthValidate(){
        if(nickName.length() > 20) return false;
        return true;
    }

    private boolean nickNameStringValidate(){
        if(nickName == null || nickName.isEmpty()) return false;
        if(!VALID_PATTERN.matcher(nickName).matches()) return false;
        return true;
    }
}
