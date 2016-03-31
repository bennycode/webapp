package com.welovecoding.data.user.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Password implements Serializable{
    @NotNull
    @Size(min = 60, max = 60)
    @Column(length = 60)
    private final String string;

    public Password(String string) {
        this.string = string;
    }

    public String get() {
        return string;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.string);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Password other = (Password) obj;
        if (!Objects.equals(this.string, other.string)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "****";
    }
    
    
}
