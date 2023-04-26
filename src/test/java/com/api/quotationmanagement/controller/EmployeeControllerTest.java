package com.api.quotationmanagement.controller;

import com.api.quotationmanagement.domain.mapper.EmployeeMapper;
import com.api.quotationmanagement.domain.mother.EmployeeMother;
import com.api.quotationmanagement.domain.mother.ErrorResponseMother;
import com.api.quotationmanagement.domain.request.EmployeeRequest;
import com.api.quotationmanagement.domain.response.EmployeeResponse;
import com.api.quotationmanagement.domain.response.ErrorResponse;
import com.api.quotationmanagement.domain.service.EmployeeService;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeMapper employeeMapper;

    @DisplayName("Controller Test to create Employee")
    @Test
    public void givenEmployeeRequestWhenCreateEmployeeThenReturn200Ok() throws Exception {
        EmployeeRequest employeeRequest = EmployeeMother.getEmployeeRequest();
        EmployeeRequestVo employeeRequestVo = EmployeeMother.getEmployeeRequestVo();
        EmployeeResponseVo employeeResponseVo = EmployeeMother.getEmployeeResponseVo();
        EmployeeResponse employeeResponse = EmployeeMother.getEmployeeResponse();

        given(employeeMapper.requestToVo(employeeRequest)).willReturn(employeeRequestVo);
        given(employeeService.createEmployee(employeeRequestVo)).willReturn(employeeResponseVo);
        given(employeeMapper.responseVoToResponse(employeeResponseVo)).willReturn(employeeResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        EmployeeResponse result = objectMapper.readValue(response.getContentAsString(), EmployeeResponse.class);

        verify(employeeMapper).requestToVo(employeeRequest);
        verify(employeeService).createEmployee(employeeRequestVo);
        verify(employeeMapper).responseVoToResponse(employeeResponseVo);

        then(result).usingRecursiveComparison().isEqualTo(employeeResponse);
    }

    @DisplayName("Controller Test to create Employee with missing name")
    @Test
    public void givenMissingEmployeeNameWhenCreateEmployeeThenReturn400BadRequest() throws Exception {
        EmployeeRequest employeeRequest = EmployeeMother.getEmployeeRequest();
        employeeRequest.setName(null);

        MockHttpServletResponse response = mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ErrorResponse result = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);

        then(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

    @DisplayName("Controller Test to get Employee by ID")
    @Test
    public void givenIdWhenGetEmployeeByIdThenReturn200Ok() throws Exception {
        Long employeeId = 10L;
        EmployeeResponseVo employeeResponseVo = EmployeeMother.getEmployeeResponseVo();
        EmployeeResponse employeeResponse = EmployeeMother.getEmployeeResponse();

        when(employeeService.getByEmployeeId(employeeId)).thenReturn(employeeResponseVo);
        when(employeeMapper.responseVoToResponse(employeeResponseVo)).thenReturn(employeeResponse);

        MockHttpServletResponse response = mockMvc.perform(get("/employee/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        EmployeeResponse result = objectMapper.readValue(response.getContentAsByteArray(), EmployeeResponse.class);

        verify(employeeService).getByEmployeeId(employeeId);
        verify(employeeMapper).responseVoToResponse(employeeResponseVo);

        then(result).usingRecursiveComparison().isEqualTo(employeeResponse);
    }

    @DisplayName("Controller Test to get Employee by ID with no param and return internal server error")
    @Test
    public void givenNoParamWhenGetEmployeeByIdThenReturn500InternalServerError() throws Exception {
        ErrorResponse errorResponse = ErrorResponseMother.getRequestMethodNotSupportedResponse();

        MockHttpServletResponse response = mockMvc.perform(get("/employee/"))
                .andExpect(status().isInternalServerError())
                .andReturn().getResponse();

        ErrorResponse result = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);

        then(result.getStatus()).isEqualTo(errorResponse.getStatus());
        then(result.getMessage()).isEqualTo(errorResponse.getMessage());
    }

    @DisplayName("Controller Test to get all Employees")
    @Test
    public void givenNothingWhenGetAllEmployeesThenReturn200Ok() throws Exception {
        List<EmployeeResponseVo> employeeResponseVos = Collections.singletonList(EmployeeMother.getEmployeeResponseVo());
        List<EmployeeResponse> employeeResponses = Collections.singletonList(EmployeeMother.getEmployeeResponse());

        given(employeeService.getAllEmployees()).willReturn(employeeResponseVos);
        given(employeeMapper.responseListVoToResponseList(employeeResponseVos)).willReturn(employeeResponses);

        MockHttpServletResponse response = mockMvc.perform(get("/allEmployees"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        List<EmployeeResponse> result = objectMapper.readValue(response.getContentAsByteArray(), new TypeReference<>() {
        });

        verify(employeeService).getAllEmployees();
        verify(employeeMapper).responseListVoToResponseList(employeeResponseVos);

        then(result).usingRecursiveComparison().isEqualTo(employeeResponses);
    }

    @DisplayName("Controller Test to update Employee")
    @Test
    public void givenEmployeeInfoWhenUpdateEmployeeThenReturn200Ok() throws Exception {
        Long employeeId = 1L;
        EmployeeRequest employeeRequest = EmployeeMother.getEmployeeRequest();
        EmployeeRequestVo employeeRequestVo = EmployeeMother.getEmployeeRequestVo();
        EmployeeResponseVo employeeResponseVo = EmployeeMother.getEmployeeResponseVo();
        EmployeeResponse employeeResponse = EmployeeMother.getEmployeeResponse();

        given(employeeMapper.requestToVo(employeeRequest)).willReturn(employeeRequestVo);
        willDoNothing().given(employeeService).updateEmployee(employeeId, employeeRequestVo);

        mockMvc.perform(patch("/employee/{employeeId}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isNoContent());

        verify(employeeMapper).requestToVo(employeeRequest);
        verify(employeeService).updateEmployee(employeeId, employeeRequestVo);
    }

    @DisplayName("Controller Test to delete Employee")
    @Test
    public void givenEmployeeIDWhenDeleteEmployeeThenReturn200Ok() throws Exception {
        Long employeeId = 1L;
        willDoNothing().given(employeeService).deleteEmployee(employeeId);

        mockMvc.perform(delete("/employee/{employeeId}", employeeId))
                .andExpect(status().isNoContent());

        verify(employeeService).deleteEmployee(employeeId);
    }
}