package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Gender;
import lombok.Data;

@Data
public class KidDTO {
    private String identification;
    private String name;
    private byte age;
    private Gender gender;
    private String codeLocation;
}
