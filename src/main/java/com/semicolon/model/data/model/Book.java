package com.semicolon.model.data.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude  // basically used at the child side to avoid the two models from calling each others toString() methods endlessly.
    @EqualsAndHashCode.Exclude  // the above explanation will suffice for this annotation too.
    @JsonBackReference // used to correct the failed to write HTTP error message (this annotation is used on the child side).
    private Author author;

    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        return id != null && id.equals(((Book) obj) .id);
    }


}
