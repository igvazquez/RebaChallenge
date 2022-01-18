package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "persons")
@Data
@Builder
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_person_id_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "persons_person_id_seq", name = "persons_person_id_seq")
    @Column(name = "person_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthdate;

    @OneToOne(optional = false, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "parents",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private PersonEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<PersonEntity> children;

    protected PersonEntity() {
        //
    }

    public boolean isParentOf(final PersonEntity p){
        return p.getParent() != null && p.getParent().equals(this);
    }

    public boolean isSiblingOf(final PersonEntity p){
        return p.getParent() != null && p.getParent().equals(parent);
    }

    public void addChild(final PersonEntity c) {
        if (children == null){
            children = List.of(c);
        }else {
            children.add(c);
        }
    }

    public boolean isCousinOf(final PersonEntity p) {
        return parent != null &&
                parent.getParent() != null &&
                p.getParent() != null &&
                p.getParent().getParent() != null
                && p.getParent().getParent().equals(parent.getParent());
    }

    public boolean isUncleOf(final PersonEntity p) {
        return parent != null &&
                p.getParent() != null &&
                p.getParent().getParent() != null &&
                parent.equals(p.getParent().getParent());
    }

    public boolean isNephewOf(final PersonEntity p) {
        return parent != null &&
                parent.getParent() != null &&
                p.getParent() != null &&
                p.getParent().equals(parent.getParent());
    }
}
