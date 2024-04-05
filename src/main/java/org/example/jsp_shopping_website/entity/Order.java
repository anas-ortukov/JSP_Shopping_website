package org.example.jsp_shopping_website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsp_shopping_website.entity.enums.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private LocalDateTime dateTime;
    private Integer userId;
    private Status status;

    public String showDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm d-MMMM yyyy");
        return dateTime.format(formatter);
    }

}
