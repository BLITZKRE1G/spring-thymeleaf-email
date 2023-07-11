package personal.project.email.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequest implements Request{
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private boolean activeRequest;
    private String mailId;
    String token;

    @Override
    public boolean isActive() {
        return activeRequest;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public String getFullName() {
        return firstName + lastName;
    }
}
