package com.api.quotationmanagement.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Employee Request")
public class EmployeeRequest {

    @JsonProperty("nome")
    @NotEmpty(message = "Employee name must not be empty.")
    @Schema(description = "Employee name", example = "Joao Ferreira da Silva")
    private String name;


    @JsonProperty("funcao")
    @NotEmpty(message = "Employee role must not be empty.")
    @Schema(description = "Employee role", example = "Gerente")
    private String role;

    @JsonProperty("salario")
    @Schema(description = "Employee salary", example = "1200.00")
    private Double salary;

    @JsonProperty("telefone")
    @Schema(description = "Employee telephone", example = "+55 (11) 9 5555-4020")
    private String telephone;

    @JsonProperty("endereco")
    @NotEmpty(message = "Employee address must not be empty.")
    @Schema(description = "Employee address", example = "Rua Costa e Silva, 555 - Jardim Triangulo - Poa - SP")
    private String address;
}
