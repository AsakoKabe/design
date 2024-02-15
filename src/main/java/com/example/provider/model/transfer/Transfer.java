package com.example.provider.model.transfer;

import com.example.provider.model.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
//import lombok.*;

import java.util.Date;
import java.util.UUID;

//@Getter
@Entity
@Table(name = "transfers")
@Getter
@Setter
@ToString
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transfer_id")
    private UUID id;

    @Column(name="_from")
    @Enumerated(EnumType.STRING)
    private Currency from;

    @Column(name="_to")
    @Enumerated(EnumType.STRING)
    private Currency to;

    @Column(name="value")
    private float value;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

}

