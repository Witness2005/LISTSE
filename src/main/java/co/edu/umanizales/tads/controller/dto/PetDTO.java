package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class PetDTO {

        @NotBlank(message = "No se puede dejar el espacio de identificacion vacio ")
        private String identification;

        @NotBlank(message = "No se puede dejar el espacio del nombre vacio")
        @Size(min = 3, max = 30, message = "minimo 3 y maximo 30 caracteres debe tener el nombre")
        private String name;
        @Min(value = 1, message = "La edad minima es 1")
        @Max(value = 15, message = "La edad debe ser menor o igual a 15")
        private byte age;



        private Gender gender;
        @Size(min = 3, max = 8, message = "Solo son validos los codigos de 3 a 8 caracteres ")
        private String codeLocation;


        private boolean bañado;
    }


