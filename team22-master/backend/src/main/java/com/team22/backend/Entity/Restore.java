package com.team22.backend.Entity;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.time.LocalDate;


@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Restore") //ชื่อตาราง
public class Restore {
    @Id
    @SequenceGenerator(name="Restore_seq",sequenceName="Restore_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Restore_seq")
    @Column(name="Restore_ID",unique = true, nullable = false)
    private @NonNull Long restoreId;
    private Date dateRestore;
    private @NonNull String statusRestore;
    private @NonNull String commentRestore;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leaseId")
    private Lease lease;


}