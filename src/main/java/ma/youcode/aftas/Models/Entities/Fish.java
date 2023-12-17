package ma.youcode.aftas.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name is mandatory")
    private String name;
    @NotNull
    @Min(value = 1, message = "Average weight must be greater than 0")
    private float averageWeight;

    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hunting> huntings = new ArrayList<Hunting>();

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}