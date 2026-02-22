package org.itsjinxed.assessment.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    protected LocalDateTime updatedAt;
}
