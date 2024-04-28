package org.example.jsp_shopping_website.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jsp_shopping_website.entity.enums.Status;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CurrentTimestamp
    private LocalDateTime dateTime;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Timestamp
    private LocalDateTime completedTime;
    @ManyToMany
    private List<OrderProduct> orderProducts;

    public String showDateTime(LocalDateTime time) {
        if (time == null) {
            return "Not Yet";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm d-MMMM yyyy");
        return time.format(formatter);
    }

}
