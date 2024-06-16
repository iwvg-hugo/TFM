package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.tripplanner.dtos.UbicacionDto;
import com.miw.tripplanner.dtos.UsuarioDto;
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

class UbicacionControllerTestIT extends BaseTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UbicacionController ubicacionController;

    @Test
    void testGetUbicaciones() throws Exception {
        List<UbicacionDto> response = new ArrayList<>();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/ubicaciones")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        response = mapper.readValue(ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<UbicacionDto>>() {
                });
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testgetUbicacion() throws Exception {
        int ubicacionId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/ubicaciones/{id}", ubicacionId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        UbicacionDto response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<UbicacionDto>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(ubicacionId, response.getId());
    }

    @Test
    void testCreateUbicaciondelete() throws Exception {
        UbicacionDto ubicacionDto = new UbicacionDto();
        ubicacionDto.setId(9999);
        ubicacionDto.setCoordenadas("coordenadas");
        ubicacionDto.setEsExterior(true);
        List<String> requisitos = new ArrayList<>();
        requisitos.add("requisito1");
        ubicacionDto.setRequisitos(requisitos);
        ubicacionDto.setTipoVestimenta("tipoVestimenta");
        ubicacionDto.setDireccion("direccion");

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/ubicaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ubicacionDto));

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
                .delete("/ubicaciones/{id}", response)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateUbicacion() throws Exception {
        UbicacionDto ubicacionDto = new UbicacionDto();
        ubicacionDto.setId(9999);
        ubicacionDto.setCoordenadas("coordenadasNuevas");
        ubicacionDto.setEsExterior(true);
        List<String> requisitos = new ArrayList<>();
        requisitos.add("requisito1");
        ubicacionDto.setRequisitos(requisitos);

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/ubicaciones/{id}", ubicacionDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ubicacionDto));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/ubicaciones/{id}", ubicacionDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        UbicacionDto response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<UbicacionDto>() {
                });
        assertNotNull(response2);
        assertEquals(ubicacionDto.getCoordenadas(), response2.getCoordenadas());

        //lo vuelvo a dejar como estaba
        ubicacionDto.setCoordenadas("coordenadas");
        requestBuilder = MockMvcRequestBuilders
                .put("/ubicaciones/{id}", ubicacionDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ubicacionDto));
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }


}
