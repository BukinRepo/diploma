package api.bodys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserBody {
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
}
