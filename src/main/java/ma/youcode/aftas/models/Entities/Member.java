package ma.youcode.aftas.models.Entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.youcode.aftas.models.Enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_members")
public class Member extends User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Number is mandatory")
    private Integer num;
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Family name is mandatory")
    private String familyName;
    @NotNull(message = "Accession date is mandatory")
    private Date accessionDate;
    @NotNull(message = "nationality is mandatory")
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @NotNull(message = "Identity number is mandatory")
    private String identityNumber;
    private Boolean isActivated = false;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ranking> rankings = new ArrayList<Ranking>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hunting> huntings = new ArrayList<Hunting>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("MEMBER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}