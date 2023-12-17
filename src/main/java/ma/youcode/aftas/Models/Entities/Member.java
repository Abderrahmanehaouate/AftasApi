package ma.youcode.aftas.Models.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.youcode.aftas.Models.Enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_members")
public class Member {
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
    @NotNull(message = "")
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @NotNull(message = "Identity number is mandatory")
    private String identityNumber;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ranking> rankings = new ArrayList<Ranking>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hunting> huntings = new ArrayList<Hunting>();
}