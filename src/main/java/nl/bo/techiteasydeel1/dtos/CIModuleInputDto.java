package nl.bo.techiteasydeel1.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CIModuleInputDto {
    @Size(max = 20, message = "Name must be between 0-20 characters")
    private String name;
    @NotNull(message = "Type is required")
    private String type;
    @Positive(message = "Price must be higher than zero")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
