package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidsByGenderDTO {

private Gender gender;

private int quantity;




}
