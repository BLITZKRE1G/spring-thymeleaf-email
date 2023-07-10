package personal.project.email.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalRequest implements Request{
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String mailId;
    
    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
