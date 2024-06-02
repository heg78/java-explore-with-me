package ru.practicum.model.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.CategoryNewDto;
import ru.practicum.model.Category;

@UtilityClass
public class CategoryMapper {

    public Category toCategory(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }

    public Category toNewCategoryDto(CategoryNewDto categoryNewDto) {
        return Category.builder()
                .name(categoryNewDto.getName())
                .build();
    }

    public CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}