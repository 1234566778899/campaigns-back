package backend.helpinghand.security;

import backend.helpinghand.entities.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}


