package com.business_control_system.domain.service;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.domain.dto.CategoryRequest;
import com.business_control_system.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Servicio de logica de negocio para la gestion de categorias.
 * <p>
 *     Esta clase actuca como clase intermedia enter los controladores de la API
 *     y la percistencia de datos. Cordina las operaciones CRUD que se utilizan en los sitios
 *     web del ecosistema
 * </p>
 * @author Raul Damian Rafael
 * @version 1.0
 * */
@Service
public class CategoryService {

    private final CategoryRepository repository;

    /**
     * Constructor para la inyeccion de dependencias
     * @param repository El repositoria de categorias definido en la capa de dominio.
     * */
    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Recupera una pagina de categorias
     * <p>
     *     Utilizado para mostrar catalogos o listados en el panel administrativo
     * </p>
     * @param page numero de pagina solicitada
     * @param elements cantidad de registros por pagina.
     * @return {@link Page} con objetos {@link Category}.
     * */
    public Page<Category> getAll(int page, int elements){
        return this.repository.getAll(page, elements);
    }

    /**
     * Busca una categoria especifica por su identificador unico
     * @param id Identificador de la categoria.
     * @return la {@link Category} encontrada
     * */
    public Category getById(int id){
        return this.repository.getById(id);
    }

    /**
     * Registra una nueva categoria en el sistema
     * @param request datos de la nueva categoria (name y description).
     * @return El objeto {@link Category} creado con su ID asignado.
     * */
    public Category create(CategoryRequest request){
       return this.repository.create(request);
    }

    /**
     * Actualiza los datos de una categoria existente.
     * @param id Identificador de la categoria a modificar.
     * @param request Objeto {@link CategoryRequest} con nuevos datos para la cateogira.
     * @return La {@link Category} actualizada.
     * */
    public Category update(int id, CategoryRequest request){
        return this.repository.update(id, request);
    }
}
