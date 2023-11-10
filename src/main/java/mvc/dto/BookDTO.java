package mvc.dto;

import jakarta.validation.constraints.*;

public record BookDTO(
        @NotBlank @Size(min = 2, max = 20) String isbn,
        String title,
        double price,
        @NotBlank String author) {
}
