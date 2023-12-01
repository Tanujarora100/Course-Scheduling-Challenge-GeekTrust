package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationDto {
    private final String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;
}
