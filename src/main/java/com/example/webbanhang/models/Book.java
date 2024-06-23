package com.example.webbanhang.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @NotNull(message = "ID không được để trống")
    @Min(value = 1, message = "ID phải là số nguyên dương lớn hơn 0")
    private int id;

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 50, message = "Tên không được vượt quá 50 ký tự")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá phải không nhỏ hơn 1")
    @Max(value = 999999999, message = "Giá không được lớn hơn 99999999")
    private Double price;

    @Length(min = 0, max = 50, message = "tên hình ảnh không quá 50 kí tự")
    private String image; // Thêm trường hình ảnh
}
