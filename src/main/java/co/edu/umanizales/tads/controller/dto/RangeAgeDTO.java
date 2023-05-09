package co.edu.umanizales.tads.controller.dto;
import co.edu.umanizales.tads.model.Rango;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RangeAgeDTO {
    private Rango rango;
    int quantity;

}
