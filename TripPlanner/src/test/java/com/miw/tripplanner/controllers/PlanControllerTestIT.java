package com.miw.tripplanner.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miw.tripplanner.dtos.*;
import com.miw.tripplanner.dtos.detalle.PlanDetalleDto;
import com.miw.tripplanner.dtos.requests.PagoRequest;
import com.miw.tripplanner.utils.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
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
        //Creo un pago:
        PagoRequest pagoRequest = new PagoRequest();
        PagoDto pagoDto = new PagoDto();
        pagoDto.setId(7777);
        pagoDto.setTotal(100.0F);
        HorarioDto horarioDto = new HorarioDto();
        horarioDto.setId(7777);
        horarioDto.setInicio(new Timestamp(System.currentTimeMillis()));
        horarioDto.setFin(new Timestamp(System.currentTimeMillis()));
        pagoDto.setHorario(horarioDto);
        pagoRequest.setPagoDto(pagoDto);
        pagoRequest.setIdUsuario(9999);
        pagoRequest.setPagador(true);
        pagoRequest.setPagoDto(pagoDto);
        List<UsuarioDto> usuariosImplicados = new ArrayList<>();
        pagoRequest.setUsuariosImplicados(usuariosImplicados);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pagoRequest));

        ResultActions ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta

        Integer idPago = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<Integer>() {
                });

        PlanDto planDto = new PlanDto();
        planDto.setId(1);
        planDto.setIdViaje(9999);
        planDto.setNombre("Plan 1");
        planDto.setImportancia(1);
        planDto.setDescripcion("Descripcion del plan 1");
        UbicacionDto ubicacionDto = new UbicacionDto();
        ubicacionDto.setDireccion("Calle Falsa 123");
        ubicacionDto.setTipoVestimenta("Casual");
        ubicacionDto.setCoordenadas("123,123");
        planDto.setUbicacion(ubicacionDto);

        // Preparar la solicitud
        requestBuilder = MockMvcRequestBuilders
                .post("/planes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planDto));

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

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

        HorarioDto horarioDto = new HorarioDto();
        horarioDto.setInicio(new java.sql.Timestamp(System.currentTimeMillis()));
        horarioDto.setFin(new java.sql.Timestamp(System.currentTimeMillis()));

        // Preparar la solicitud
        requestBuilder = MockMvcRequestBuilders
                .post("/horarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(horarioDto));

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        // Deserializar la respuesta como un Integer
        Integer response2 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<Integer>() {
                });

        // Verificar que el objeto deserializado no es nulo
        assertNotNull(response);

        PlanDto planDto = new PlanDto();
        planDto.setId(9999);
        planDto.setIdViaje(9999);
        planDto.setNombre("Plan 2");
        planDto.setImportancia(1);
        planDto.setDescripcion("Descripcion del plan 1");
        planDto.setIdUbicacion(response);
        planDto.setIdHorario(response2);
        planDto.setUbicacion(ubicacionDto);

        // Preparar la solicitud
        requestBuilder = MockMvcRequestBuilders
                .put("/planes/{id}", planDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planDto));

        // Ejecutar la solicitud y obtener el resultado
        ra = mockMvc.perform(requestBuilder);

        // Verificar que el estado de la respuesta es 200 OK
        ra.andExpect(MockMvcResultMatchers.status().isOk());

        //compruebo que se actualizo con un getbyid
        requestBuilder = MockMvcRequestBuilders
                .get("/planes/{id}", planDto.getId())
                .contentType(MediaType.APPLICATION_JSON);
        ra = mockMvc.perform(requestBuilder);
        ra.andExpect(MockMvcResultMatchers.status().isOk());
        PlanDetalleDto response3 = mapper.readValue(
                ra.andReturn().getResponse().getContentAsString(),
                new TypeReference<PlanDetalleDto>() {
                });
        assertNotNull(response2);
        assertEquals(planDto.getNombre(), response3.getNombre());

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
    void testFindPlanesByIdViaje() throws Exception {
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
