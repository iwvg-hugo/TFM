package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.tripplanner.dtos.PagoDto;
import com.miw.tripplanner.dtos.detalle.PagoDetalleDto;
import com.miw.tripplanner.dtos.requests.PagoRequest;
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

class PagoControllerTestIT extends BaseTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PagoController planController;

    @Test
    void testGetPagos() throws Exception {
        List<PagoDto> response = new ArrayList<>();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/pagos")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        response = mapper.readValue(ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<PagoDto>>() {
                });
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testGetPago() throws Exception {
        int pagoId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/pagos/{id}", pagoId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        PagoDetalleDto response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<PagoDetalleDto>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(pagoId, response.getId());
    }

    @Test
    void testCreatePagoDelete() throws Exception {

        //creo una ubicacion para el test:
        PagoRequest pagoRequest = new PagoRequest();
        pagoRequest.setIdUsuario(9999);
        PagoDto pagoDto = new PagoDto();
        pagoDto.setTotal(100.0F);

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pagoRequest));

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

        // Preparar la solicitud
        requestBuilder = MockMvcRequestBuilders
                .delete("/pagos/{id}", response)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdatePlan() throws Exception {
        PagoDto pagoDto = new PagoDto();
        pagoDto.setId(9999);
        pagoDto.setTotal(100.0F);

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/pagos/{id}", pagoDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pagoDto));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/pagos/{id}", pagoDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        PagoDetalleDto response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<PagoDetalleDto>() {
                });
        assertNotNull(response2);
        assertEquals(pagoDto.getTotal(), response2.getTotal());

        //lo vuelvo a dejar como estaba
        pagoDto.setTotal(1000F);
        requestBuilder = MockMvcRequestBuilders
                .put("/pagos/{id}", pagoDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pagoDto));
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
