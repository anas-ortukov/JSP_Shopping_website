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
    private LocalDateTime completedTime;

    public String showDateTime(LocalDateTime time) {
        if (time == null) {
            return "Not Yet";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm d-MMMM yyyy");
        return time.format(formatter);
    }

}
