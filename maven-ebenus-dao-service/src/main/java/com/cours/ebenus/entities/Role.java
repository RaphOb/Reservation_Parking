package com.cours.ebenus.entities;
import java.util.Objects;

public class Role {
    private Integer idRole;
    private String identifiant;
    private String description;

    public Role() {
    }

    public Role(Integer idRole, String identifiant, String description) {
        this.idRole = idRole;
        this.identifiant = identifiant;
        this.description = description;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return Objects.equals(idRole, role.idRole);
    }

    @Override
    public int hashCode() {
        return idRole != null ? idRole.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("[idRole=%s, identifiant=%s, description=%s]", idRole, identifiant, description);
    }
}
