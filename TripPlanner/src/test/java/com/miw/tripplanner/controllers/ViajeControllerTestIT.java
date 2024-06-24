package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.tripplanner.dtos.ViajeDto;
import com.miw.tripplanner.dtos.detalle.ViajeDetalleDto;
import com.miw.tripplanner.dtos.requests.ViajeRequest;
import com.miw.tripplanner.utils.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ViajeControllerTestIT extends BaseTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ViajeController viajeController;

    @Test
    void testGetViajes() throws Exception {
        List<ViajeDto> response = new ArrayList<>();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/viajes")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        List<ViajeDto> viajes = mapper.readValue(ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<ViajeDto>>() {
                });
        response = viajes;
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testgetViaje() throws Exception {
        int viajeId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/viajes/{id}", viajeId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        ViajeDetalleDto response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<ViajeDetalleDto>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(viajeId, response.getId());
    }

    @Test
    void testfindViajesByUserId() throws Exception {
        int userId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/viajes/usuario/{idUsuario}", userId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como una lista de ViajeDto
        List<ViajeDto> response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<ViajeDto>>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(1, response.size());
        assertEquals(9999, response.get(0).getId());
    }

    @Test
    void testCreateViajedelete() throws Exception {
        ViajeRequest viajeRequest = new ViajeRequest();
        viajeRequest.setTitulo("Viaje de prueba");
        viajeRequest.setFechaInicio(new Timestamp(System.currentTimeMillis()));
        viajeRequest.setFechaFin(new Timestamp(System.currentTimeMillis()));
        viajeRequest.setUserEmail("csdf");
        viajeRequest.setEmailParticipantes(new ArrayList<>());
        viajeRequest.setPropuestas(new ArrayList<>());
        viajeRequest.setImagen("imagen");

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/viajes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(viajeRequest));

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
                .delete("/api/viajes/{id}", response)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateViaje() throws Exception {
        ViajeRequest viajeRequest = new ViajeRequest();
        ViajeDto viajeDto = new ViajeDto();
        viajeDto.setId(9999);
        viajeDto.setIdHorario(9998);
        viajeDto.setTitulo("Viaje de prueba");
        viajeRequest.setUserEmail("correo@mail.com");
        viajeRequest.setEmailParticipantes(new ArrayList<>());
        viajeRequest.setPropuestas(new ArrayList<>());
        viajeRequest.setImagen("imagen");
        viajeRequest.setTitulo("Viaje de prueba");

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/viajes/{id}", viajeDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(viajeRequest));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/api/viajes/{id}", viajeDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        ViajeDetalleDto response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<ViajeDetalleDto>() {
                });
        assertNotNull(response2);
    }


}
