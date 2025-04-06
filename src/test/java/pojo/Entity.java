package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Pojo класс данных пользователя
 */
@Getter
@Setter
@Builder
public class Entity {
    private Addition addition;
    private Integer id;
    private List<Integer> important_numbers;
    private String title;
    private boolean verified;
}
