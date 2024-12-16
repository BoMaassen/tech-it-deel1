package nl.bo.techiteasydeel1.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ci_modules")
public class CIModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Double price;

}
