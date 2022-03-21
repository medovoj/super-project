package app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Book {

    public Integer id;
    public String name;
    public Author author;
    public Integer price;
    public Category category;
}
