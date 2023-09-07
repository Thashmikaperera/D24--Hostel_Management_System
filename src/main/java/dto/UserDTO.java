package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class UserDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPasswordHint;
}