package com.business_control_system.web.controller;

import com.business_control_system.domain.dto.CreateProductRequest;
import com.business_control_system.domain.dto.Product;
import com.business_control_system.domain.dto.UpdateProductRequest;
import com.business_control_system.domain.service.ProductService;
import com.business_control_system.web.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    public Product PRODUCT_PREPARED = new Product(1,"Mouse", "Desc",12.22);
    public CreateProductRequest PRODUCT_REQUEST_CREATE_PREPARED = new CreateProductRequest(
            "Mouse",
            "Desc",
            12.22,
            33,
            1
    );

    public UpdateProductRequest UPDATE_PRODUCT_REQUEST = new UpdateProductRequest(
            "Teclado",
            "Desc",
            12.22,
            33,
            1
    );

    @Test
    void TODOS_LOS_PRODUCTOS_PAGINADOS() throws Exception {
        Mockito.when(productService.getAll(0,9))
                .thenReturn(Page.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/products/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("elements","9")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void CREAR_PRODUCTO() throws Exception {
        Mockito.when(productService.create(PRODUCT_REQUEST_CREATE_PREPARED))
                .thenReturn(PRODUCT_PREPARED);
        mockMvc.perform(MockMvcRequestBuilders.post("/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name":"Mouse",
                                    "description":"Desc",
                                    "price":12.22,
                                    "stock":33,
                                    "categoryId":1
                                }
                                """)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void OBTENER_PRODUCTO_POR_IDENTIFICADOR() throws Exception {
        Mockito.when(productService.getById(1))
                .thenReturn(PRODUCT_PREPARED);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/get/"+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id":1,
                                    "name":"Mouse",
                                    "description":"Desc",
                                    "price":12.22
                                }
                                """)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void ACTUALIZAR_PRODUCTO() throws Exception {
        Mockito.when(productService.update(1, UPDATE_PRODUCT_REQUEST)).thenReturn(PRODUCT_PREPARED);
        mockMvc.perform(MockMvcRequestBuilders.put("/products/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name":"Teclado",
                                    "description":"Desc",
                                    "price":12.22,
                                    "stock":33,
                                    "categoryId":1
                                }
                                """)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void ELEIMIMAR_UN_PRODUCTO() throws Exception {
        Mockito.when(productService.delete(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}