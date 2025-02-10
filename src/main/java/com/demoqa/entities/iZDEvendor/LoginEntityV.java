package com.demoqa.entities.iZDEvendor;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class LoginEntityV {

    private String emailOrPhone;
    private String password;
}
