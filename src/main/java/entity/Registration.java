package entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Registration {


    private String regID;
    private final String emailAddress;
    private final String courseID;
    private final boolean isAccepted;
}
