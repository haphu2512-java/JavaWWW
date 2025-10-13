package fit.se.haxuanphu_mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    private String id;

    private String name;

    private String location;

    private String description;
}