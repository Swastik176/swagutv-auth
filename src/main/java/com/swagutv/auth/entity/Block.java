package com.swagutv.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "blocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"blocker_id","blocked_id"}
                )
        })
@Getter
@Setter

public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="blocker_id")
    private User blocker;

    @ManyToOne
    @JoinColumn(name="blocked_id")
    private User blocked;
}

