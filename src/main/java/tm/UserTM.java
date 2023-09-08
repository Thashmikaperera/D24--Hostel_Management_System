package tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserTM {
    private String userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPassword;
    private String userPasswordHint;
}
