package com.team22.backend.Entity;
import lombok.*;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="History") //ชื่อตาราง
public class History {
    @Id
    @SequenceGenerator(name="History_seq",sequenceName="History_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="History_seq")
    @Column(name="HistoryId",unique = true, nullable = false)
    private @NonNull Long HistoryId;

    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restoreId")
    private  Restore restore;

}