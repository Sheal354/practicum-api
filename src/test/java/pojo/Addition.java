package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Pojo класс дополнительных данных о пользователе
 */
@Getter
@Setter
@Builder
public class Addition {
    private String additional_info;
    private Integer additional_number;
    private Integer id;
}
