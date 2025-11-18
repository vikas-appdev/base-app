package com.gradlic.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gradlic.login.domain.RequestContext;
import com.gradlic.login.exception.ApiException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();

    @NotNull
    private Long createdBy;
    @NotNull
    private Long updatedBy;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @CreatedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist(){
         var userId = RequestContext.getUserId();
        //var userId = 1L;
        if(userId == null){
            throw new ApiException("Can not persist entity without user id in RequestContext for this thread");
        }

        setCreatedAt(now());
        setCreatedBy(userId);
        setUpdatedAt(now());
        setUpdatedBy(userId);

    }

    @PreUpdate
    public void beforeUpdate(){
         var userId = RequestContext.getUserId();
        //var userId = 1L;
        if (userId == null){
            throw new ApiException("Can not update entity without user id in RequestContext for this thread");
        }

        setUpdatedBy(userId);
        setUpdatedAt(now());
    }

}
