package junghun.api.Entity;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dust {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;


    private int pm10Value;
    private int pm25Value;
    private int pm10Grade;
    private int pm25Grade;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

}
