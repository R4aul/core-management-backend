package com.business_control_system.web.controller;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.domain.dto.CategoryRequest;
import com.business_control_system.domain.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    public Category category = new Category(1,"name", "desc");

    public CategoryRequest request = new CategoryRequest("name","desc");

    @Test
    void OBTENER_PAGINA_CATEGORIAS() throws Exception {
        Mockito.when(categoryService.getAll(0,9))
                .thenReturn(Page.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page","0")
                .param("elements","9")
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void CREAR_UNA_CATEGORIA() throws Exception {
        Mockito.when(categoryService.create(request))
                .thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name":"Mouse",
                                    "description":"Desc"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void OBTENER_CATEGORIA_POR_IDENTIFICADOR() throws Exception {
        Mockito.when(categoryService.getById(1))
                .thenReturn(category);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id":1,
                                    "name":"name",
                                    "description":"desc"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void ACTUALIZAR_CATEGORIA() throws Exception {
        CategoryRequest categoryRequestUpdate = new CategoryRequest("Update","Desc");
        Category response = new Category(1,"New", "Desc");
        Mockito.when(categoryService.update(1,categoryRequestUpdate))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id":1,
                                    "name":"New",
                                    "description":"Desc"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}