package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.tripplanner.dtos.UsuarioDto;
import com.miw.tripplanner.dtos.detalle.UsuarioDetalle;
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
                .get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(null));

        ResultActions ra = mockMvc.perform(requestBuilder);

        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar directamente a una lista de ViajeDto
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
                .get("/api/usuarios/{id}", usuarioId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
        usua.setFullName("test");
        usua.setEmail("correo");
        usua.setPassword("pass");

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usua));

        // Ejecutar la solicitud y obtener el resultado
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
                .delete("/api/usuarios/{id}", response)
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
        usuarioDto.setFullName("testNuevo");
        usuarioDto.setEmail("correo");
        usuarioDto.setPassword("pass");

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/usuarios/{id}", usuarioDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuarioDto));

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/api/usuarios/{id}", usuarioDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        UsuarioDto response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<UsuarioDto>() {
                });
        assertNotNull(response2);
        assertEquals(usuarioDto.getFullName(), response2.getFullName());

        //lo vuelvo a dejar como estaba
        usuarioDto.setFullName("test");
        requestBuilder = MockMvcRequestBuilders
                .put("/api/usuarios/{id}", usuarioDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usuarioDto));
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetUsuariosByIdViaje() throws Exception {
        int viajeId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/usuarios/viaje/{id}", viajeId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<UsuarioDto> response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<UsuarioDto>>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        assertEquals(1, response.size());
    }

    @Test
    void testFindUsuarios() throws Exception {
        int viajeId = 9999;

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/usuarios/search?idViaje={idViaje}", viajeId)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un ViajeDto
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<UsuarioDetalle> response = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<UsuarioDetalle>>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        assertEquals(1, response.size());
    }

    @Test
    void testFindUsuarioByEmail() throws Exception {
        String email = "correo";

        // Preparar la solicitud
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/usuarios/findUsuarioByEmail/{email}", email)
                .contentType(MediaType.APPLICATION_JSON);

        // Ejecutar la solicitud y obtener el resultado
        ResultActions ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Obtener el contenido de la respuesta
        String jsonResponse = ra.andReturn().getResponse().getContentAsString();

        // Imprimir la respuesta para depuración
        System.out.println("Response: " + jsonResponse);

        // Deserializar la respuesta como un UsuarioDto
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        UsuarioDto response = mapper.readValue(
                jsonResponse,
                new TypeReference<UsuarioDto>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        // Verificar que el email es correcto
        assertEquals(email, response.getEmail());
    }


}
