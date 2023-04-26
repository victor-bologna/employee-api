package com.api.quotationmanagement.controller;

import com.api.quotationmanagement.domain.mapper.EmployeeMapper;
import com.api.quotationmanagement.domain.request.EmployeeRequest;
import com.api.quotationmanagement.domain.response.EmployeeResponse;
import com.api.quotationmanagement.domain.response.ErrorResponse;
import com.api.quotationmanagement.domain.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Tag(name = "Employee", description = "APIs for creating and reading employees.")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping(path = "/employee",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  "application/json;**charset=UTF-8**")
    @Operation(summary = "Create Employee into the database, if there's missing information in the request, " +
            "then a Employee Not Found Exception is thrown.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully.", content = {
                    @Content(schema = @Schema(implementation = EmployeeResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Employee data missing.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Stock ID not found.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeMapper.responseVoToResponse(employeeService.createEmployee(
                employeeMapper.requestToVo(employeeRequest))), HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve a specific Employee from the database. If none exists, then a Employee Not Found " +
            "Exception is thrown.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee created successfully.", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeResponse.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Stock ID not found.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping(value = "/employee/{employeeId}",
            produces =  "application/json;**charset=UTF-8**")
    public ResponseEntity<EmployeeResponse> getByStockId(@Parameter(description = "Employee Id", example = "10")
                                                          @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeMapper.responseVoToResponse(employeeService.getByEmployeeId(employeeId)));
    }

    @Operation(summary = "Read all Employees from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Employees retrieved successfully.", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeResponse.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping(value = "/allEmployees",
            produces =  "application/json;**charset=UTF-8**")
    public ResponseEntity<List<EmployeeResponse>> getAllStocks() {
        return ResponseEntity.ok(employeeMapper.responseListVoToResponseList(employeeService.getAllEmployees()));
    }

    @Operation(summary = "Update Employee from database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully.", content = {
                    @Content(schema = @Schema(implementation = EmployeeResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PatchMapping(value = "/employee/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@Parameter(description = "Employee Id", example = "10")
                                                               @PathVariable Long employeeId,
                                                           @RequestBody EmployeeRequest employeeRequest) {
        employeeService.updateEmployee(employeeId, employeeMapper.requestToVo(employeeRequest));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove Employee from database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cache cleared successfully.", content = {
                    @Content(schema = @Schema(implementation = EmployeeResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @DeleteMapping(value = "/employee/{employeeId}")
    public ResponseEntity<EmployeeResponse> clearStockCache(@Parameter(description = "Employee Id", example = "10")
                                                                @PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
