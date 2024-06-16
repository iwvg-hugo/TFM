package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.tripplanner.dtos.PlanDto;
import com.miw.tripplanner.dtos.detalle.PlanDetalleDto;
import com.miw.tripplanner.utils.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlanControllerTestIT extends BaseTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PlanController planController;

    @Test
    void testGetPlanes() throws Exception {
        List<PlanDto> response = new ArrayList<>();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/planes")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        response = mapper.readValue(ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<PlanDto>>() {
                });
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testGetPlan() throws Exception {
        int planId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/planes/{id}", planId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        PlanDetalleDto response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<PlanDetalleDto>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(planId, response.getId());
    }

    @Test
    void testCreatePlanDelete() throws Exception {
        PlanDto planDto = new PlanDto();
        planDto.setId(1);
        planDto.setIdViaje(9999);
        planDto.setNombre("Plan 1");
        planDto.setImportancia(1);
        planDto.setDescripcion("Descripcion del plan 1");
        planDto.setIdPago(9999);
        planDto.setIdUbicacion(9999);
        planDto.setIdHorario(9999);

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/planes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planDto));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un Integer
        Integer response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<Integer>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(1, response);

        // Preparar la solicitud
        requestBuilder = MockMvcRequestBuilders
                .delete("/planes/{id}", response)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdatePlan() throws Exception {
        PlanDto planDto = new PlanDto();
        planDto.setId(9999);
        planDto.setIdViaje(9999);
        planDto.setNombre("Plan 2");
        planDto.setImportancia(1);
        planDto.setDescripcion("Descripcion del plan 1");
        planDto.setIdPago(9999);
        planDto.setIdUbicacion(9999);
        planDto.setIdHorario(9999);

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/planes/{id}", planDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planDto));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/planes/{id}", planDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        PlanDetalleDto response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<PlanDetalleDto>() {
                });
        assertNotNull(response2);
        assertEquals(planDto.getNombre(), response2.getNombre());

        //lo vuelvo a dejar como estaba
        planDto.setNombre("Vuelo");
        requestBuilder = MockMvcRequestBuilders
                .put("/propuestas/{id}", planDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planDto));
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDindPlanesByIdViaje() throws Exception {
        int idViaje = 9999;
        List<PlanDetalleDto> response = new ArrayList<>();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/planes/search?idViaje={idViaje}", idViaje)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        response = mapper.readValue(ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<PlanDetalleDto>>() {
                });
        assertNotNull(response);
        assertEquals(2, response.size());
    }
}