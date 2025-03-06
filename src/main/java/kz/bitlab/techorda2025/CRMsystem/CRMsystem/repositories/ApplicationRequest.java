package kz.bitlab.techorda2025.CRMsystem.CRMsystem.repositories;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String commentary;
    private String phone;
    private boolean handled; //false — не обработан
    @ManyToOne
    private Courses course;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operators> operators;
}
