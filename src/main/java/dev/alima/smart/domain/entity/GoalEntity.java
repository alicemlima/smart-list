package dev.alima.smart.domain.entity;

import dev.alima.smart.domain.values.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goal", schema = "goal")
@Entity
@DynamicUpdate
@SuperBuilder
public class GoalEntity extends GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 14)
    private String title;
    @Column(nullable = false, length = 60)
    private String subtitle;
    @Column(nullable = false)
    private Status status;
    @Column
    private LocalDate endDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @OrderBy("endDate")
    private Set<TaskEntity> tasks;
}
