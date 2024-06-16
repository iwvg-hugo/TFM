package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

class UsuarioControllerTestIT extends BaseTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UsuarioController usuarioController;

    @Test
    void testGetUsuarios() throws Exception {
        List<UsuarioDto> response = new ArrayList<>();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        response = mapper.readValue(ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<UsuarioDto>>() {
                });
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testgetUsuario() throws Exception {
        int usuarioId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/usuarios/{id}", usuarioId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        UsuarioDto response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<UsuarioDto>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Puedes agregar más aserciones para verificar el contenido del objeto
        assertEquals(usuarioId, response.getId());
    }

    @Test
    void testCreateUsuariodelete() throws Exception {
        UsuarioDto usua = new UsuarioDto();
        usua.setName("test");
        usua.setEmail("correo");
        usua.setPassword("pass");

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usua));

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
                .delete("/usuarios/{id}", response)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateUsuario() throws Exception {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(9999);
        usuarioDto.setName("testNuevo");
        usuarioDto.setEmail("correo");
        usuarioDto.setPassword("pass");

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/usuarios/{id}", usuarioDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuarioDto));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/usuarios/{id}", usuarioDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        UsuarioDto response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<UsuarioDto>() {
                });
        assertNotNull(response2);
        assertEquals(usuarioDto.getName(), response2.getName());

        //lo vuelvo a dejar como estaba
        usuarioDto.setName("test");
        requestBuilder = MockMvcRequestBuilders
                .put("/usuarios/{id}", usuarioDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuarioDto));
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }


}
