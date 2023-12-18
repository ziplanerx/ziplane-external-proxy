package com.ziplane.external.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="nhs_audit_detail")
@Data
public class AuditDetails {
    @Id
    @Column(name="audit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auditId;
    @Column(name="action_taken_by_nm")
    private String actionTakenByName;

    @Column(name="processId")
    private String processId;

    @Column(name="case_number")
    private String caseNumber;
    @Column(name="action_taken")
    private String actionTaken;

    @Column(name="origin")
    private String origin;
    @Column(name="destination")
    private String destination;

    @Column(name="create_by_ts", updatable = false, nullable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Date createByDate;

    @Column(name="update_by_ts")
    @UpdateTimestamp(source = SourceType.DB)
    private Date lastUpdatedByDate;
}
