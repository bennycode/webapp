package com.welovecoding.data.base;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable & Comparable> extends AbstractPersistable<PK> implements
        Comparable<BaseEntity<PK>> {

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastModified;

//  @Version
//  private long version;
    protected BaseEntity() {
    }

    protected BaseEntity(PK id, Date created, Date lastModified) {
        this.setId(id);
        this.created = created;
        this.lastModified = lastModified;
    }

    //  public long getVersion() {
//    return version;
//  }
    public Date getCreated() {
        return created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    protected void setCreated(Date created) {
        this.created = created;
    }

    protected void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.created = now;
        this.lastModified = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModified = new Date();
    }

    public abstract Comparable comparableAttribute();

    @Override

    public int compareTo(BaseEntity be) {
        if (be.comparableAttribute() == null && this.comparableAttribute() == null) {
            return 0;
        }
        if (this.comparableAttribute() == null) {
            return 1;
        }
        if (be.comparableAttribute() == null) {
            return -1;
        }
        return this.comparableAttribute().compareTo(be.comparableAttribute());
    }

}
