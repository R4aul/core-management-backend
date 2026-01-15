package com.business_control_system.perisistence;

import com.business_control_system.domain.dto.Category;
import com.business_control_system.domain.dto.CategoryRequest;
import com.business_control_system.domain.exception.NotFoundException;
import com.business_control_system.domain.repository.CategoryRepository;
import com.business_control_system.perisistence.entity.CategoryEntity;
import com.business_control_system.perisistence.mapper.CategoryMapper;
import com.business_control_system.perisistence.repository.CategoryListCrudRepository;
import com.business_control_system.perisistence.repository.CategoryPagSortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositorio encargado del procesamiento de presisitencia a la base de datos
 * de la tabla de categories
 * @author Raul Damian Rafael
 * @version 1.0
 * */
@Repository
public class CategoryEntityRepository implements CategoryRepository {

    private final CategoryPagSortRepository pagSortRepository;
    private final CategoryListCrudRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryEntityRepository(
            CategoryPagSortRepository pagSortRepository,
            CategoryListCrudRepository repository,
            CategoryMapper mapper
    ) {
        this.pagSortRepository = pagSortRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Recupera un alista paginada de todas las categorias reistradas.
     * @param page el numero de pagina a recuperar. ya que evita la sobrecarga
     * @param elements cantidad de registros por pagina
     * @return Un objeto {@link Page} que contiene los DTOs de {@link Category}
     * @see Pageable
     * @see CategoryMapper
     * */
    @Override
    public Page<Category> getAll(int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);
        Page<CategoryEntity> entities = this.pagSortRepository.findAll(pageable);
        return entities.map(this.mapper::toDTO);
    }

    /**
     * Procesa la obtencion de una categoria individual por el id
     * @param id El identificador unico de la categoria.
     * @return Un objeto {Category} de la categoria encontrada.
     * @throws NotFoundException Si la caateagoria no fue encontrada lanza una execion.
     * */
    @Override
    public Category getById(int id) {
       CategoryEntity entity = this.repository.findById(id)
               .orElseThrow(()-> new NotFoundException("category not found"));
       return this.mapper.toDTO(entity);
    }

    /**
     * Procesa la persistencia de la creacion de una nueva categoria apartir de una solicitud.
     * <p>
     * El proceso sigue estos pasos:
     * 1. Transforma el {@link CategoryRequest} en una {@link CategoryEntity} usando el patron builder
     * 2. Perisiste la entidad en la base de datos atrabes de {@link #repository}.
     * 3. Convierte la entidad persistida (que ya incluye su ID generado) apartir de un DTO de salida
     * </p>
     * @param request Objeto o DTO que contiene el nombre y la descripcion de la categoria
     * @see CategoryEntity
     * @see CategoryMapper
     * */
    @Override
    public Category create(CategoryRequest request) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(request.name())
                .description(request.description())
                .build();
        return this.mapper.toDTO(this.repository.save(categoryEntity));
    }

    /**
     * Actualiza los datos de una caategoria existente en la base de datos
     * <p>
     * El metodo esta marcado como {@code @Transactional} para asegurar la que la recuperacion y la actualizacion
     * se realizen de forma atomica.
     * </p>
     * @param id El identificador de la categoria a modificar
     * @param  request DTO con los nuevos valores para 'name' y 'description'.
     * @return El DTO {@link Category} con los datos actualizados tras la persistencia.
     * @throws NotFoundException Si no existe ninguna categoria con el ID proporcionado
     * @see Transactional
     * @see CategoryRepository#update(int, CategoryRequest)
     * */
    @Override
    @Transactional
    public Category update(int id, CategoryRequest request) {
        CategoryEntity category = this.repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category not found"));

        category.setName(request.name());
        category.setDescription(request.description());

        return this.mapper.toDTO(this.repository.save(category));
    }
}